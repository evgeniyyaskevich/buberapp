package by.epam.javaweb.evgeniyyaskevich.finalproject.service;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlUserDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.PasswordManager;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private MySqlUserDao userDao = new MySqlUserDao();
    private ResourceManager messageManager = new ResourceManager("messages");
    private ResourceManager configManager = new ResourceManager("config");

    public boolean checkPassword(String login, char[] password) {
        boolean result;
        try {
            PasswordManager passwordManager = PasswordManager.getInstance();
            User user = userDao.getByLogin(login);
            result = passwordManager.validatePassword(password, user.getPassword());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.error("Password hashing problems.");
            e.printStackTrace();
            result = false;
        } catch (PersistException e) {
            LOGGER.error("UserDao error while getting user by login.");
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean isUserExist(String login) {
        try {
            User user = userDao.getByLogin(login);
            return user != null;
        } catch (PersistException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    public boolean register(String login, char[] password) {
        //TODO: builder
        try {
            PasswordManager passwordManager = PasswordManager.getInstance();
            User user = new User();
            user.setName(login);
            user.setPassword(passwordManager.generatePasswordHash(password));
            userDao.insert(user);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | PersistException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    public User getUser(String login) {
        User user = null;
        try {
            user = userDao.getByLogin(login);
        } catch (PersistException e) {
            LOGGER.error(e);
        }
        return user;
    }
}