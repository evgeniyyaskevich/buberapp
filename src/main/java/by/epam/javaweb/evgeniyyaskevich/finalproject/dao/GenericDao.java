package by.epam.javaweb.evgeniyyaskevich.finalproject.dao;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;

import java.util.List;

public interface GenericDao<T> {
    void insert(T object) throws PersistException;
    void update(T object) throws PersistException;
    void delete(T object) throws PersistException;
    T getById(Long id) throws PersistException;
    List<T> getAll() throws PersistException;
}
