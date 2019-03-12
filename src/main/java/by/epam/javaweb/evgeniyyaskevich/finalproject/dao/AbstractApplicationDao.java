package by.epam.javaweb.evgeniyyaskevich.finalproject.dao;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Application;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Car;

import java.util.List;

public abstract class AbstractApplicationDao extends AbstractJDBCDao<Application> {
    protected AbstractApplicationDao() {}

    public abstract List<Application> getByClientId(long id) throws PersistException;
    public abstract List<Application> getByCar(Car car) throws PersistException;
}
