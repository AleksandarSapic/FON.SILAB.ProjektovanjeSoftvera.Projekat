package main.logic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.broker.ConnectionPool;
import main.broker.IDatabaseBroker;
import main.broker.SQLBroker;

public abstract class BusinessLogic {

    protected IDatabaseBroker databaseBroker;
    private Connection connection;

    public BusinessLogic() throws SQLException {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            databaseBroker = new SQLBroker(connection);
        } catch (IOException ex) {
            System.out.println("IOExcpetion u konstruktoru BusinessLogic: " + ex.getMessage());
        }
    }

    public void execute(Object object) throws Exception {
        try {
            validate(object);
            executeOperation(object);
            commit();
        } catch (Exception ex) {
            rollback();
            throw ex;
        }
    }

    private void commit() throws SQLException {
        try {
            databaseBroker.commit();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (IOException ex) {
            Logger.getLogger(BusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void rollback() throws SQLException {
        try {
            databaseBroker.rollback();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (IOException ex) {
            Logger.getLogger(BusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected abstract void executeOperation(Object object) throws Exception;

    protected abstract void validate(Object object) throws Exception;
}
