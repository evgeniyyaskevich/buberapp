package by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.AbstractApplicationDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.connection.ProxyConnection;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Application;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.ApplicationState;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Car;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.SqlConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MySqlApplicationDao extends AbstractApplicationDao {
    public MySqlApplicationDao() {}

    @Override
    public String getSelectQuery() {
        return SqlConfig.SELECT_APPLICATION_QUERY;
    }

    @Override
    public String getSelectQueryById() {
        return SqlConfig.SELECT_APPLICATION_BY_ID;
    }

    @Override
    public String getInsertQuery() {
        return SqlConfig.INSERT_APPLICATION_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConfig.UPDATE_APPLICATION_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return SqlConfig.DELETE_APPLICATION_QUERY;
    }

    @Override
    public List<Application> getByClientId(long id) throws PersistException {
        try (ProxyConnection connection = connectionPool.getConnection()) {
            String sql = getSelectQuery() + " WHERE client_id = " + id + " ORDER BY application_time DESC;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                return parseResultSet(resultSet);
            } catch (SQLException e) {
                throw new PersistException(e);
            }
        }
    }

    @Override
    public List<Application> getByCar(Car car) throws PersistException {
        try (ProxyConnection connection = connectionPool.getConnection()) {
            String sql = getSelectQuery() + " WHERE state = \"WAITING\" AND car_type = \"" + car.getType().toString() +
                    "\" AND child_seat = \"" + car.getChildSeat() + "\";";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                return parseResultSet(resultSet);
            } catch (SQLException e) {
                throw new PersistException(e);
            }
        }
    }

    @Override
    protected List<Application> parseResultSet(ResultSet resultSet) throws PersistException {
        List<Application> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Application application = new Application();
                application.setId(resultSet.getLong("application_id"));
                application.setClientId(resultSet.getLong("client_id"));
                application.setDestination(resultSet.getString("destination"));
                application.setPrice(resultSet.getInt("price"));
                application.setChildSeat(resultSet.getBoolean("child_seat"));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime =
                        LocalDateTime.from(formatter.parse(resultSet.getString("application_time")));
                application.setDateTime(LocalDateTime.from(dateTime));

                application.setState(ApplicationState.valueOf(resultSet.getString("state")));
                result.add(application);
            }
        } catch (SQLException e) {
            throw new PersistException("Result set problems.", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Application object) throws PersistException {
        try {
            statement.setObject(1, object.getClientId());
            statement.setObject(2, object.getState().toString());
            statement.setObject(3, object.getDestination());
            statement.setObject(4, object.getPrice());
            statement.setObject(5, object.getChildSeat());
            statement.setObject(6, object.getCarType().toString());
        } catch (SQLException e) {
            throw new PersistException("Prepared state forming problem.", e);
        }
    }


    //TODO: check this preparing
    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Application object) throws PersistException {
        try {
            statement.setObject(1, object.getClientId());
            statement.setObject(2, object.getDateTime().toString());
            statement.setObject(3, object.getState().toString());
            statement.setObject(4, object.getId());
        } catch (SQLException e) {
            throw new PersistException("Prepared state forming problem.", e);
        }
    }
}
