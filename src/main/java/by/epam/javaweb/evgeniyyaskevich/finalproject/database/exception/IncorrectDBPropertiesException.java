package by.epam.javaweb.evgeniyyaskevich.finalproject.database.exception;

public class IncorrectDBPropertiesException extends Exception {
    private static final long serialVersionUID = -3353821108188310618L;

    public IncorrectDBPropertiesException() {
    }

    public IncorrectDBPropertiesException(String message) {
        super(message);
    }

    public IncorrectDBPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDBPropertiesException(Throwable cause) {
        super(cause);
    }
}
