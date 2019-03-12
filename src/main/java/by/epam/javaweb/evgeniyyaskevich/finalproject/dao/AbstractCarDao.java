package by.epam.javaweb.evgeniyyaskevich.finalproject.dao;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Car;

import java.util.List;

public abstract class AbstractCarDao extends AbstractJDBCDao<Car> {
    protected AbstractCarDao() {}

    public abstract List<Car> getByDriverId(long id) throws PersistException;
}
