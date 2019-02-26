package by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception;

public class DBPropertiesFileError extends Exception {
    private static final long serialVersionUID = -6546987889052729546L;

    public DBPropertiesFileError() {
    }

    public DBPropertiesFileError(String message) {
        super(message);
    }

    public DBPropertiesFileError(String message, Throwable cause) {
        super(message, cause);
    }

    public DBPropertiesFileError(Throwable cause) {
        super(cause);
    }
}
