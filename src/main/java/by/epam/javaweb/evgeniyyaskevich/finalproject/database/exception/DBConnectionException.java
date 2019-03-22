package by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception;

public class DBConnectionException extends RuntimeException {
    private static final long serialVersionUID = -6898965331368095419L;

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
