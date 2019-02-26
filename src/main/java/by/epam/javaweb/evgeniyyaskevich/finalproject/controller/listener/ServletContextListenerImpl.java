package by.epam.javaweb.evgeniyyaskevich.finalproject.controller.listener;

import by.epam.javaweb.evgeniyyaskevich.finalproject.database.DBConnectionPool;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBConnectionException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBDriverClassNotFoundException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBPropertiesFileError;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.IncorrectDBPropertiesException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(ServletContextListenerImpl.class);
    private static final Integer CONNECTIONS_NUMBER = 10;
    private final DBConnectionPool connectionPool = DBConnectionPool.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            connectionPool.init(CONNECTIONS_NUMBER);
        } catch (DBPropertiesFileError | DBDriverClassNotFoundException
                | IncorrectDBPropertiesException exception) {
            LOGGER.fatal("DBInitializing error with message: " + exception.getMessage());
            throw new DBConnectionException(exception);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        connectionPool.destroy();
    }
}
