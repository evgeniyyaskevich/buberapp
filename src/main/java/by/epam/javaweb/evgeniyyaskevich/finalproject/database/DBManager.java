package by.epam.javaweb.evgeniyyaskevich.finalproject.database;

import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBDriverClassNotFoundException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBPropertiesFileError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBManager {
    private static final Logger LOGGER = LogManager.getLogger(DBManager.class);
    private String url;
    private String login;
    private String password;
    private String driver;

    DBManager() {}

    void openAccess(String DBPropertiesName) throws DBPropertiesFileError, DBDriverClassNotFoundException {
        InputStream is;
        Properties properties = new Properties();
        try {
            is = getClass().getResourceAsStream("/" + DBPropertiesName);
            properties.load(is);
            init(properties);
            Class.forName(driver);
            LOGGER.trace("Database is ready for using.");
        } catch (IOException e) {
            LOGGER.error("Properties wasn`t opened.");
            throw new DBPropertiesFileError(e);
        } catch (ClassNotFoundException e) {
            LOGGER.fatal("Can`t find driver class file.");
            throw new DBDriverClassNotFoundException(e);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    private void init(Properties properties) {
        url = properties.getProperty("database.url");
        login = properties.getProperty("database.login");
        password = properties.getProperty("database.password");
        driver = properties.getProperty("database.driver");
    }
}
