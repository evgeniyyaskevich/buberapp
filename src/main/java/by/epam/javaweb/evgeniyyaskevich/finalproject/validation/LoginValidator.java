package by.epam.javaweb.evgeniyyaskevich.finalproject.validation;

public class LoginValidator {
    private static final String LOGIN_REGEX = "^[A-Za-z0-9_-]{3,18}$";

    private static final class SingletonHolder {
        private static final LoginValidator INSTANCE = new LoginValidator();
    }

    private LoginValidator() {}

    public static LoginValidator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean validate(String login) {
        return login.matches(LOGIN_REGEX);
    }
}