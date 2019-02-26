package by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception;

public class DBDriverClassNotFoundException extends Exception {
    private static final long serialVersionUID = -8019099483503120553L;

    public DBDriverClassNotFoundException() {
    }

    public DBDriverClassNotFoundException(String message) {
        super(message);
    }

    public DBDriverClassNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBDriverClassNotFoundException(Throwable cause) {
        super(cause);
    }
}
