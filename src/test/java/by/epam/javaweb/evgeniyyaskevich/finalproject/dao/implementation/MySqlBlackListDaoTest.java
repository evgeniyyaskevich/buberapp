package by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.DBConnectionPool;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBDriverClassNotFoundException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBPropertiesFileError;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.IncorrectDBPropertiesException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.BlackListRecord;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class MySqlBlackListDaoTest {

    private DBConnectionPool pool;
    private MySqlBlackListDao dao;


    @BeforeClass
    public void openPool() throws DBDriverClassNotFoundException, DBPropertiesFileError, IncorrectDBPropertiesException {
        pool = DBConnectionPool.getInstance();
        pool.init(5);
        dao = MySqlBlackListDao.getInstance();
    }

    @AfterClass
    public void closePool() {
        pool.destroy();
    }

    @Test
    public void testGetAll() throws PersistException {
        List<BlackListRecord> cars = dao.getAll();
        System.out.println(cars);
    }

    @Test
    public void testDelete() throws PersistException {
        BlackListRecord record = dao.getById(1L);
        record.setUserId(1);
        dao.delete(record);
    }

    @Test
    public void testInsert() throws PersistException {
        BlackListRecord record = new BlackListRecord();
        record.setUserId(1);
        record.setReason("He is dreamer.");
        dao.insert(record);
    }

    @Test
    public void testUpdate() throws PersistException {
       BlackListRecord record = dao.getById(1L);
       record.setReason("DREAMER X2");
       dao.update(record);
    }
}