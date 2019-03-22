package by.epam.javaweb.evgeniyyaskevich.finalproject.validation;

import org.testng.annotations.DataProvider;

public class LoginValidatorTestData {

    @DataProvider(name="loginValidatorTest")
    public static Object[][] loginValidateData() {
        return new Object[][]{
                {"1", false},
                {"a2", false},
                {"na", false},
                {"123", true},
                {"<script>", false},
                {"asd_sad", true},
                {"ab1", true},
                {"_______", true},
        };
    }
}
