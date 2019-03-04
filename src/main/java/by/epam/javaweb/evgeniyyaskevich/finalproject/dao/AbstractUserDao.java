package by.epam.javaweb.evgeniyyaskevich.finalproject.dao;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;

public abstract class AbstractUserDao extends AbstractJDBCDao<User> {

    protected AbstractUserDao() {}

    public abstract User getByLogin(String name) throws PersistException;
}
