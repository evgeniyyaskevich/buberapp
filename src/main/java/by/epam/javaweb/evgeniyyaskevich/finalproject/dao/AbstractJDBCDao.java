package by.epam.javaweb.evgeniyyaskevich.finalproject.dao;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.DBConnectionPool;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.connection.ProxyConnection;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.BaseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractJDBCDao<T extends BaseEntity> implements GenericDao<T> {
    private DBConnectionPool connectionPool = DBConnectionPool.getInstance();

    AbstractJDBCDao() { }

    public abstract String getSelectQuery();
    public abstract String getSelectQueryById();
    public abstract String getInsertQuery();
    public abstract String getUpdateQuery();
    public abstract String getDeleteQuery();
    protected abstract List<T> parseResultSet(ResultSet resultSet) throws PersistException;
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    @Override
    public List<T> getAll() throws PersistException {
        try (ProxyConnection connection = connectionPool.getConnection()) {
            String sql = getSelectQuery();
            List<T> list;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet result = statement.executeQuery();
                list = parseResultSet(result);
            } catch (SQLException e) {
                throw new PersistException(e);
            }
            return list;
        }
    }

    @Override
    public T getById(long id) throws PersistException {
        try (ProxyConnection connection = connectionPool.getConnection()) {
            String sql = getSelectQueryById();
            List<T> list;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                ResultSet result = statement.executeQuery();
                list = parseResultSet(result);
            } catch (SQLException e) {
                throw new PersistException(e);
            }

            if (list != null && list.size() != 0) {
                return list.iterator().next();
            } else {
                return null;
            }
        }
    }

    @Override
    public void insert(T object) throws PersistException {
        try (ProxyConnection connection = connectionPool.getConnection()) {
            String sql = getInsertQuery();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                prepareStatementForInsert(statement, object);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new PersistException(e);
            }
        }
    }

    @Override
    public void update(T object) throws PersistException {
        try (ProxyConnection connection = connectionPool.getConnection()) {
            String sql = getUpdateQuery();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                prepareStatementForUpdate(statement, object);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new PersistException(e);
            }
        }
    }

    @Override
    public void delete(T object) throws PersistException {
        try (ProxyConnection connection = connectionPool.getConnection()) {
            String sql = getDeleteQuery();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setObject(1, object.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new PersistException(e);
            }
        }
    }
}
