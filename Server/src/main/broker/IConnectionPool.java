package main.broker;

import java.sql.*;

public interface IConnectionPool {

    Connection getConnection();

    boolean releaseConnection(Connection connection);

}
