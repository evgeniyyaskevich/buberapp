package by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception;

public class DBConnectionException extends RuntimeException {
    private static final long serialVersionUID = -2936043580201672121L;

    public DBConnectionException() {
        super();
    }

    public DBConnectionException(String message) {
        super(message);
    }

    public DBConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBConnectionException(Throwable cause) {
        super(cause);
    }
}
