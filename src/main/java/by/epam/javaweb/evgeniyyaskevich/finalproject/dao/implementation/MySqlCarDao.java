package by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.AbstractCarDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Car;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.CarType;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.SqlConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCarDao extends AbstractCarDao {
    public MySqlCarDao() {}

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
    protected List<Car> parseResultSet(ResultSet resultSet) throws PersistException {
        List<Car> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getLong("car_id"));
                car.setType(CarType.valueOf(resultSet.getString("car_type")));
                car.setBrand(resultSet.getString("car_brand"));
                car.setChildSeat(resultSet.getBoolean("child_seat"));
                car.setDriverId(resultSet.getLong("driver_id"));
                result.add(car);
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
