package by.epam.javaweb.evgeniyyaskevich.finalproject.util;

public class SqlConfig {
    public static final String SELECT_USER_QUERY =
            "SELECT user_name, password, user_id, bonus, level_access FROM User";
    public static final String SELECT_USER_BY_ID =
            "SELECT user_name, password, user_id, bonus, level_access FROM User WHERE user_id = ?";
    public static final String INSERT_USER_QUERY =
            "INSERT INTO User (user_name, password, level_access) VALUES (?, ?, ?);";
    public static final String DELETE_USER_QUERY = "DELETE FROM User WHERE user_id = ?";
    public static final String UPDATE_USER_QUERY =
            "UPDATE User SET user_name = ?, password = ?, bonus = ?, level_access = ? WHERE user_id = ?;";


    public static final String SELECT_CAR_QUERY =
            "SELECT car_id, driver_id, car_brand, car_type, child_seat FROM Car";
    public static final String SELECT_CAR_BY_ID =
            "SELECT car_id, driver_id, car_brand, car_type, child_seat FROM Car WHERE car_id = ?";
    public static final String INSERT_CAR_QUERY =
            "INSERT INTO Car (car_id, driver_id, car_brand, car_type, child_seat) VALUES (?, ?, ?, ?, ?);";
    public static final String DELETE_CAR_QUERY = "DELETE FROM Car WHERE car_id = ?";
    public static final String UPDATE_CAR_QUERY =
            "UPDATE Car SET driver_id = ?, car_brand = ?, car_type = ?, child_seat = ? WHERE car_id = ?;";


    public static final String SELECT_FROM_BLACK_LIST = "SELECT user_id, reason FROM Black_list";
    public static final String SELECT_FROM_BLACK_LIST_BY_ID = "SELECT user_id, reason FROM Black_list WHERE user_id = ?;";
    public static final String INSERT_USER_TO_BLACK_LIST = "INSERT INTO Black_list (user_id, reason) VALUES (?, ?);";
    public static final String DELETE_USER_FROM_BLACK_LIST = "DELETE FROM Black_list WHERE user_id = ?";
    public static final String UPDATE_REASON_BLACK_LIST = "UPDATE Black_list SET reason = ? WHERE user_id = ?;";


    public static final String SELECT_APPLICATION_QUERY =
            "SELECT application_id, client_id, application_time, state FROM Application";
    public static final String SELECT_APPLICATION_BY_ID =
            "SELECT application_id, client_id, application_time, state FROM Application WHERE application_id = ?";

    public static final String INSERT_APPLICATION_QUERY =
            "INSERT Application (client_id, application_time, state) VALUES (?, ?, ?);";
    public static final String DELETE_APPLICATION_QUERY =
            "DELETE FROM Application WHERE application_id = ?;";
    public static final String UPDATE_APPLICATION_QUERY =
            "UPDATE Application SET client_id = ?, application_time = ?, state = ? WHERE application_id = ?;";

}
