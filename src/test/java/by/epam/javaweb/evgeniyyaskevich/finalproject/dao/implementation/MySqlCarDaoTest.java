package by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.DBConnectionPool;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBDriverClassNotFoundException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.DBPropertiesFileError;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception.IncorrectDBPropertiesException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Car;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class MySqlCarDaoTest {

    private DBConnectionPool pool;
    private MySqlCarDao dao;


    @BeforeClass
    public void openPool() throws DBDriverClassNotFoundException, DBPropertiesFileError, IncorrectDBPropertiesException {
        pool = DBConnectionPool.getInstance();
        pool.init(5);
        dao = new MySqlCarDao();
    }

    @AfterClass
    public void closePool() {
        pool.destroy();
    }

    @Test
    public void testGetAll() throws PersistException {
        List<Car> cars = dao.getAll();
        System.out.println(cars);
    }

    @Test
    public void testDelete() throws PersistException {
        Car car = dao.getById(1);
        dao.delete(car);
    }

    @Test
    public void testInsert() throws PersistException {
        Car car = dao.getById(2);
        car.setId(500);
        car.setDriverId(1);
        dao.insert(car);
    }

    @Test
    public void testUpdate() throws PersistException {
        Car car = dao.getById(12);
        car.setBrand("MEGAAA");
        car.setChildSeat(true);
        car.setDriverId(3);
        dao.update(car);
    }
}