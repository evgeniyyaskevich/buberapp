package by.epam.javaweb.evgeniyyaskevich.finalproject.validation;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginValidatorTest {
    private LoginValidator loginValidator = LoginValidator.getInstance();

    @Test(dataProvider = "loginValidatorTest", dataProviderClass = LoginValidatorTestData.class)
    public void validate(String username, boolean expected) {
        boolean result = loginValidator.validate(username);
        Assert.assertEquals(result, expected);
    }
}