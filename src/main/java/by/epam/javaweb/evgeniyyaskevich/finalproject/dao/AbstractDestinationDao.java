package by.epam.javaweb.evgeniyyaskevich.finalproject.dao;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Destination;

public abstract class AbstractDestinationDao extends AbstractJDBCDao<Destination> {
   protected AbstractDestinationDao() {}

   public abstract Destination getByName(String destination) throws PersistException;
}
