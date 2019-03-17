package by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.AbstractBlackListDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.database.connection.ProxyConnection;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.BlackListRecord;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.SqlConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlBlackListDao extends AbstractBlackListDao {

    private static final class SingletonHolder {
        private static final MySqlBlackListDao INSTANCE = new MySqlBlackListDao();
    }

    private MySqlBlackListDao() {}

    public static MySqlBlackListDao getInstance() {
        return MySqlBlackListDao.SingletonHolder.INSTANCE;
    }

    @Override
    public String getSelectQuery() {
        return SqlConfig.SELECT_FROM_BLACK_LIST;
    }

    @Override
    public String getSelectQueryById() {
        return SqlConfig.SELECT_FROM_BLACK_LIST_BY_ID;
    }

    @Override
    public String getInsertQuery() {
        return SqlConfig.INSERT_USER_TO_BLACK_LIST;
    }

    @Override
    public String getUpdateQuery() {
        return SqlConfig.UPDATE_REASON_BLACK_LIST;
    }

    @Override
    public String getDeleteQuery() {
        return SqlConfig.DELETE_USER_FROM_BLACK_LIST;
    }

    @Override
    protected List<BlackListRecord> parseResultSet(ResultSet resultSet) throws PersistException {
        List<BlackListRecord> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                BlackListRecord record = new BlackListRecord();
                record.setUserId(resultSet.getLong("user_id"));
                record.setReason(resultSet.getString("reason"));
                result.add(record);
            }
        } catch (SQLException e) {
            throw new PersistException("Result set problems.", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, BlackListRecord object)
            throws PersistException {
        try {
            statement.setObject(1, object.getUserId());
            statement.setObject(2, object.getReason());
        }  catch (SQLException e) {
            throw new PersistException("Prepared state forming problem.", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, BlackListRecord object)
            throws PersistException {
        try {
            statement.setObject(1, object.getReason());
            statement.setObject(2, object.getUserId());
        }  catch (SQLException e) {
            throw new PersistException("Prepared state forming problem.", e);
        }
    }
}
