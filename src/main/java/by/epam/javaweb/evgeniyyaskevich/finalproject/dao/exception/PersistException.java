package by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception;

public class PersistException extends Exception {
    private static final long serialVersionUID = 2504994489718319288L;

    public PersistException() {
    }

    public PersistException(String message) {
        super(message);
    }

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistException(Throwable cause) {
        super(cause);
    }
}
