package by.epam.javaweb.evgeniyyaskevich.finalproject.database.connection;


import by.epam.javaweb.evgeniyyaskevich.finalproject.database.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;


public class ProxyConnection implements AutoCloseable {
    private Connection connection;

    public ProxyConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    @Override
    public void close() {
        DBConnectionPool.getInstance().returnConnection(this);
    }

    public void destroyConnection() throws SQLException {
        connection.close();
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }
}
