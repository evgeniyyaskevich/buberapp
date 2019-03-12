package by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.DBConnectionPool;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBDriverClassNotFoundException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBPropertiesFileError;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.IncorrectDBPropertiesException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.AccessLevel;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class MySqlUserDaoTest {

    private DBConnectionPool pool;
    private MySqlUserDao dao;


    @BeforeClass
    public void openPool() throws DBDriverClassNotFoundException, DBPropertiesFileError, IncorrectDBPropertiesException {
        pool = DBConnectionPool.getInstance();
        pool.init(5);
        dao = new MySqlUserDao();
    }

    @AfterClass
    public void closePool() {
        pool.destroy();
    }

    @org.testng.annotations.Test
    public void testGetByName() throws PersistException {
        User user = dao.getByLogin("Evgeniy");
        System.out.println(user);
    }

    @Test
    public void testGetAll() throws PersistException {
        List<User> users = dao.getAll();
        System.out.println(users);
    }

    @Test
    public void testDelete() throws PersistException {
        User user = dao.getByLogin("Igor");
        dao.delete(user);
    }

    @Test
    public void testInsert() throws PersistException {
        User user = dao.getByLogin("Elizaveta");
        user.setName("Ilya");
        dao.insert(user);
    }

    @Test
    public void testUpdate() throws PersistException {
        User user = dao.getByLogin("Ilya");
        user.setBonus(500);
        user.setLevel(AccessLevel.ADMIN);
        dao.update(user);
    }
}