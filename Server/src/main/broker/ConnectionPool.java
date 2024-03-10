package main.broker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool implements IConnectionPool {

    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE;
    private static ConnectionPool instance;

    private ConnectionPool() throws SQLException, FileNotFoundException, IOException {
        try {
            FileReader reader = new FileReader("src/config/app.config");
            Properties p = new Properties();
            p.load(reader);

            url = p.getProperty("url");
            user = p.getProperty("user");
            password = p.getProperty("password");
            INITIAL_POOL_SIZE = Integer.parseInt(p.getProperty("INITIAL_POOL_SIZE"));
            connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);

            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    System.out.println("Konekcija sa bazom podataka uspesno uspostavljena!");

                    connection.setAutoCommit(false);
                    connectionPool.add(connection);
                } catch (SQLException ex) {
                    System.out.println("Greska! Konekcija sa bazom nije uspesno uspostavljena!\n" + ex.getMessage());
                    throw ex;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno uspostavljena!\n" + ex.getMessage());
            throw ex;
        } catch (IOException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno uspostavljena!\n" + ex.getMessage());
            throw ex;
        }
    }

    public static ConnectionPool getInstance() throws SQLException, FileNotFoundException, IOException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        if (connectionPool.isEmpty()) {
            try {
                Connection newConnection = DriverManager.getConnection(url, user, password);
                System.out.println("New connection created!");
                newConnection.setAutoCommit(false);
                return newConnection;
            } catch (SQLException ex) {
                System.out.println("Error! Failed to create new connection!\n" + ex.getMessage());
                return null;
            }
        } else {
            Connection connection = connectionPool.remove(connectionPool.size() - 1);
            usedConnections.add(connection);
            return connection;
        }
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        if (usedConnections.remove(connection)) {
            connectionPool.add(connection);
            return true;
        } else {
            return false;
        }
    }
}
