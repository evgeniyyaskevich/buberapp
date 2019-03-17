package by.epam.javaweb.evgeniyyaskevich.finalproject.service;

import by.epam.javaweb.evgeniyyaskevich.finalproject.builder.UserBuilder;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlBlackListDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlUserDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.BlackListRecord;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.PasswordManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private MySqlUserDao userDao = MySqlUserDao.getInstance();
    private MySqlBlackListDao blackListDao = MySqlBlackListDao.getInstance();

    private static final class SingletonHolder {
        private static final UserService INSTANCE = new UserService();
    }

    private UserService() {}

    public static UserService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean checkPassword(String login, char[] password) {
        boolean result;
        try {
            User user = userDao.getByLogin(login);
            PasswordManager passwordManager = PasswordManager.getInstance();
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
            LOGGER.error("UserService problem: ", e);
            return false;
        }
    }

    public boolean isBanned(String login) {
        try {
            User user = userDao.getByLogin(login);
            BlackListRecord record = blackListDao.getById(user.getId());
            return record != null;
        } catch (PersistException e) {
            LOGGER.error("UserService problem: ", e);
        }
        return false;
    }

    public boolean register(String login, char[] password) {
        try {
            PasswordManager passwordManager = PasswordManager.getInstance();
            UserBuilder userBuilder = new UserBuilder();
            userBuilder.setName(login)
                    .setPassword(passwordManager.generatePasswordHash(password));
            userDao.insert(userBuilder.build());
            return true;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | PersistException e) {
            LOGGER.error("UserService problem: ", e);
            return false;
        }
    }

    public User getUser(String login) {
        User user = null;
        try {
            user = userDao.getByLogin(login);
        } catch (PersistException e) {
            LOGGER.error("UserService problem: ", e);
        }
        return user;
    }

    public void fillSession(HttpSession session, String login) {
        User user = getUser(login);
        user.setPassword("");
        session.setAttribute("isAuthorized", true);
        session.setAttribute("user", user);
    }
}
