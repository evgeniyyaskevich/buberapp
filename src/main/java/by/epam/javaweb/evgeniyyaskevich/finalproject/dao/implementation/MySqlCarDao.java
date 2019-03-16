package by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation;

import by.epam.javaweb.evgeniyyaskevich.finalproject.builder.CarBuilder;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.AbstractCarDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.connection.ProxyConnection;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Car;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.CarType;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.SqlConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCarDao extends AbstractCarDao {

    private static final class SingletonHolder {
        private static final MySqlCarDao INSTANCE = new MySqlCarDao();
    }

    private MySqlCarDao() {}

    public static MySqlCarDao getInstance() {
        return MySqlCarDao.SingletonHolder.INSTANCE;
    }

    @Override
    public String getSelectQueryById() {
        return SqlConfig.SELECT_CAR_BY_ID;
    }

    @Override
    public String getSelectQuery() {
        return SqlConfig.SELECT_CAR_QUERY;
    }

    @Override
    public String getInsertQuery() {
        return SqlConfig.INSERT_CAR_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConfig.UPDATE_CAR_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return SqlConfig.DELETE_CAR_QUERY;
    }

    @Override
    public List<Car> getByDriverId(Long id) throws PersistException {
        try (ProxyConnection connection = connectionPool.getConnection()) {
            String sql = getSelectQuery() + " WHERE driver_id = " + id;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                return parseResultSet(resultSet);
            } catch (SQLException e) {
                throw new PersistException(e);
            }
        }
    }

    @Override
    protected List<Car> parseResultSet(ResultSet resultSet) throws PersistException {
        List<Car> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                CarBuilder carBuilder = new CarBuilder();
                carBuilder.setId(resultSet.getLong("car_id"))
                        .setType(CarType.valueOf(resultSet.getString("car_type")))
                        .setBrand(resultSet.getString("car_brand"))
                        .setChildSeat(resultSet.getBoolean("child_seat"))
                        .setDriverId(resultSet.getLong("driver_id"));
                result.add(carBuilder.build());
            }
        } catch (SQLException e) {
            throw new PersistException("Result set problems.", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Car object) throws PersistException {
        try {
            statement.setObject(1, object.getId());
            statement.setObject(2, object.getDriverId());
            statement.setObject(3, object.getBrand());
            statement.setObject(4, object.getType().toString());
            statement.setObject(5, object.getChildSeat());
        } catch (SQLException e) {
            throw new PersistException("Prepared state forming problem.", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Car object) throws PersistException {
        try {
            statement.setObject(1, object.getDriverId());
            statement.setObject(2, object.getBrand());
            statement.setObject(3, object.getType().toString());
            statement.setObject(4, object.getChildSeat());
            statement.setObject(5, object.getId());
        } catch (SQLException e) {
            throw new PersistException("Prepared state forming problem.", e);
        }
    }
}
