package by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.DBConnectionPool;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBDriverClassNotFoundException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBPropertiesFileError;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.IncorrectDBPropertiesException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Application;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.ApplicationState;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class MySqlApplicationDaoTest {
    private DBConnectionPool pool;
    private MySqlApplicationDao dao;


    @BeforeClass
    public void openPool() throws DBDriverClassNotFoundException, DBPropertiesFileError, IncorrectDBPropertiesException {
        pool = DBConnectionPool.getInstance();
        pool.init(5);
        dao = new MySqlApplicationDao();
    }

    @AfterClass
    public void closePool() {
        pool.destroy();
    }

    @Test
    public void testGetAll() throws PersistException {
        List<Application> applications = dao.getAll();
        System.out.println(applications);
    }

    @Test
    public void testDelete() throws PersistException {
        Application application = dao.getById(1);
        dao.delete(application);
    }

    @Test
    public void testInsert() throws PersistException {
        Application application = dao.getById(1);
        application.setId(500);
        application.setState(ApplicationState.ACCEPTED);
        dao.insert(application);
    }

    @Test
    public void testUpdate() throws PersistException {
        Application application = dao.getById(1);
        application.setState(ApplicationState.WAITING);
        dao.update(application);
    }

}