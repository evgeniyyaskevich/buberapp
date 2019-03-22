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
            "UPDATE User SET user_name = ?, bonus = ?, level_access = ? WHERE user_id = ?;";


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
            "SELECT application_id, client_id, application_time, state, destination, price, child_seat, car_type" +
                    " FROM Application";
    public static final String SELECT_APPLICATION_BY_ID =
            "SELECT application_id, client_id, child_seat, car_type, application_time, state," +
                    "destination, price FROM Application WHERE application_id = ?";
    public static final String INSERT_APPLICATION_QUERY =
            "INSERT Application (client_id, state, destination, price, child_seat, car_type) VALUES (?, ?, ?, ?, ?, ?);";
    public static final String DELETE_APPLICATION_QUERY =
            "DELETE FROM Application WHERE application_id = ?;";
    public static final String UPDATE_APPLICATION_QUERY =
            "UPDATE Application SET client_id = ?, application_time = ?, state = ? WHERE application_id = ?;";


    public static final String SELECT_DESTINATION_QUERY =
            "SELECT destination_id, destination_name, south_coord, north_coord FROM Destination";
    public static final String SELECT_DESTINATION_QUERY_BY_ID =
            "SELECT destination_id, destination_name, south_coord, north_coord FROM Destination" +
                    " WHERE destination_id = ?";
    public static final String INSERT_DESTINATION_QUERY =
            "INSERT Destination (destination_name, south_coord, north_coord) VALUE (?, ?, ?);";
    public static final String DELETE_DESTINATION_QUERY =
            "DELETE FROM Destination WHERE destination_id = ?;";
    public static final String UPDATE_DESTINATION_QUERY =
            "UPDATE Destination SET destination_name = ?, north_coord = ?, south_coord = ? WHERE destination_id = ?;";

}
