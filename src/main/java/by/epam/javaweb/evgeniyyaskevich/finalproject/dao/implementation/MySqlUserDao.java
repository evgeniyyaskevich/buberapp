package by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation;

import by.epam.javaweb.evgeniyyaskevich.finalproject.builder.UserBuilder;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.AbstractUserDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.connection.ProxyConnection;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.AccessLevel;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.SqlConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao extends AbstractUserDao {

    private static final class SingletonHolder {
        private static final MySqlUserDao INSTANCE = new MySqlUserDao();
    }

    private MySqlUserDao() {}

    public static MySqlUserDao getInstance() {
        return MySqlUserDao.SingletonHolder.INSTANCE;
    }

    @Override
    public String getSelectQueryById() {
        return SqlConfig.SELECT_USER_BY_ID;
    }

    @Override
    public String getSelectQuery() {
        return SqlConfig.SELECT_USER_QUERY;
    }

    @Override
    public String getInsertQuery() {
        return SqlConfig.INSERT_USER_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConfig.UPDATE_USER_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return SqlConfig.DELETE_USER_QUERY;
    }

    @Override
    public User getByLogin(String login) throws PersistException {
        try (ProxyConnection connection = connectionPool.getConnection()) {
            User user;
            String sql = getSelectQuery() + " WHERE user_name = \"" + login + "\";";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                List<User> userList = parseResultSet(resultSet);
                user = (userList.size() != 0) ? userList.get(0) : null;
            } catch (SQLException e) {
                throw new PersistException("Prepare Statement problems.", e);
            }
            return user;
        }
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws PersistException {
        List<User> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setId(resultSet.getLong("user_id"))
                        .setName(resultSet.getString("user_name"))
                        .setBonus(resultSet.getInt("bonus"))
                        .setPassword(resultSet.getString("password"))
                        .setLevel(AccessLevel.valueOf(resultSet.getString("level_access")));
                result.add(userBuilder.build());
            }
        } catch (SQLException e) {
            throw new PersistException("ResultSet problems.", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setObject(1, object.getName());
            statement.setObject(2, object.getPassword());
            statement.setObject(3, object.getLevel().toString());
        } catch (SQLException e) {
            throw new PersistException("Prepared Statement isn`t right.", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setObject(1, object.getName());
            statement.setObject(2, object.getBonus());
            statement.setObject(3, object.getLevel().toString());
            statement.setObject(4, object.getId());
        } catch (SQLException e) {
            throw new PersistException("Prepared Statement isn`t right.", e);
        }
    }
}
