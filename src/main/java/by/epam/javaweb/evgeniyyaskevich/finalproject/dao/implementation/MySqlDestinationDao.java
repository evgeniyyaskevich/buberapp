package by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.AbstractDestinationDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Destination;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.SqlConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlDestinationDao extends AbstractDestinationDao {

    public MySqlDestinationDao() {}

    @Override
    public String getSelectQuery() {
        return SqlConfig.SELECT_DESTINATION_QUERY;
    }

    @Override
    public String getSelectQueryById() {
        return null;
    }

    @Override
    public String getInsertQuery() {
        return SqlConfig.INSERT_DESTINATION_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConfig.UPDATE_DESTINATION_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return SqlConfig.DELETE_DESTINATION_QUERY;
    }

    @Override
    protected List<Destination> parseResultSet(ResultSet resultSet) throws PersistException {
        List<Destination> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Destination destination = new Destination();
                destination.setId(resultSet.getLong("destination_id"));
                destination.setName(resultSet.getString("destination_name"));
                destination.setNorthCoord(resultSet.getInt("north_coord"));
                destination.setSouthCoord(resultSet.getInt("south_coord"));
                result.add(destination);
            }
        } catch (SQLException e) {
            throw new PersistException("Result set problems.", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Destination object)
            throws PersistException {
        try {
            statement.setObject(1, object.getName());
            statement.setObject(2, object.getSouthCoord());
            statement.setObject(3, object.getNorthCoord());
        } catch (SQLException e) {
            throw new PersistException("Prepared Statement isn`t right.", e);
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Destination object)
            throws PersistException {
        try {
            statement.setObject(1, object.getNorthCoord());
            statement.setObject(2, object.getSouthCoord());
            statement.setObject(3, object.getName());
        } catch (SQLException e) {
            throw new PersistException("Prepared Statement isn`t right.", e);
        }
    }
}
