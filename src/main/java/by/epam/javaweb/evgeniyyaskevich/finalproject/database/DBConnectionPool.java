package by.epam.javaweb.evgeniyyaskevich.finalproject.database;

import by.epam.javaweb.evgeniyyaskevich.finalproject.database.connection.ProxyConnection;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DBConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String DB_PROPERTIES_FILE_NAME = "database.properties";
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> takenConnections;

    private static final class SingletonHolder {
        private static final DBConnectionPool INSTANCE = new DBConnectionPool();
    }

    private DBConnectionPool() {}

    public static DBConnectionPool getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(int poolSize) throws DBPropertiesFileError, DBDriverClassNotFoundException,
            IncorrectDBPropertiesException {
        freeConnections = new ArrayBlockingQueue<>(poolSize);
        takenConnections = new ArrayBlockingQueue<>(poolSize);
        DBManager dbManager = new DBManager();
        dbManager.openAccess(DB_PROPERTIES_FILE_NAME);
        for (int i = 0; i < poolSize; ++i) {
            freeConnections.add(openConnection(dbManager));
        }
        LOGGER.trace("Connection pool was initialized successfully.");
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection;
        try {
            connection = freeConnections.take();
        } catch (InterruptedException e) {
            LOGGER.fatal("Connection can`t be taken.");
            throw new DBConnectionException(e);
        }
        takenConnections.add(connection);
        LOGGER.trace("Connection was taken from pool.");
        return connection;
    }

    public void returnConnection(ProxyConnection connection) {
        if (connection != null) {
            takenConnections.remove(connection);
            freeConnections.offer(connection);
            LOGGER.trace("Connection returned to pool.");
        }
    }

    public void destroy() {
        try {
            for (ProxyConnection connection : takenConnections) {
                connection.destroyConnection();
            }
            for (ProxyConnection connection : freeConnections) {
                connection.destroyConnection();
            }
        } catch (SQLException e) {
            LOGGER.fatal("Close connection error occurs.");
            throw new DBConnectionException("Database access error while closing connection", e);
        }
        LOGGER.trace("Connection pool was destroyed successfully.");
    }

    private ProxyConnection openConnection(DBManager dbManager) throws IncorrectDBPropertiesException {
        String url = dbManager.getUrl();
        String login = dbManager.getLogin();
        String password = dbManager.getPassword();
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            LOGGER.fatal("Can`t get connection. Incorrect database properties.");
            throw new IncorrectDBPropertiesException(e);
        }
        return new ProxyConnection(connection);
    }
}