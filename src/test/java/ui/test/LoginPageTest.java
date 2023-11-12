package ui.test;

import jdk.jfr.Description;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ui.service.LoginPageService;
import ui.service.ProjectsPageService;

import static utils.StringConstant.*;

public class LoginPageTest extends BaseTest {
    private LoginPageService loginPageService;
    private User user;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        user = new User();
    }

    @Description("Verify login on our site")
    @Test(testName = "Test login user", priority = 3)
    public void checkSuccessfulLoginTest() {
        ProjectsPageService projectsPageService = loginPageService.login(user);
        String actualTextOnProjectPage = projectsPageService.getTextProjectsOnPage();
        Assert.assertEquals(actualTextOnProjectPage, PROJECTS,
                "The actual text on the page doesn't match with expected. Error of registration");
    }

    @Description("Verify empty fields Email and Password")
    @Test(testName = "Test empty fields", priority = 1)
    public void checkErrorMessageIfEmptyFieldsTest() {
        String actualTextOfEmptyEmail = loginPageService.clickButtonWithEmptyEmail();
        String actualTextOfEmptyPassword = loginPageService.clickButtonWithEmptyPassword();
        String expectedTextOfEmptyFields = FIELD_IS_REQUIRED;
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTextOfEmptyEmail, expectedTextOfEmptyFields,
                ERROR_OF_EMPTY_FIELD);
        softAssert.assertEquals(actualTextOfEmptyPassword, expectedTextOfEmptyFields,
                ERROR_OF_EMPTY_FIELD);
        softAssert.assertAll();
    }

    @DataProvider(name = "get incorrect values for test")
    public Object[][] getIncorrectValues() {
        return new Object[][]{
                {"@@@", "_!#("},
                {"111111", " "},
                {"HHHHHHHHH", "FGFFFFF"}
        };
    }

    @Description("Verify incorrect values in registration fields")
    @Test(testName = "Test incorrect values", dataProvider = "get incorrect values for test", threadPoolSize = 3, priority = 2)
    public void checkIncorrectValuesInFieldsTest(String userName, String userPassword) {
        String actualMessageAboutIncorrectValue = loginPageService.inputIncorrectValues(userName, userPassword);
        String expectedMessageAboutIncorrectValue = "Value '" + userName + "' " + EMAIL_FORMAT;
        Assert.assertEquals(actualMessageAboutIncorrectValue, expectedMessageAboutIncorrectValue,
                "The actual text on the page doesn't match with expected. Error protected of registration");
    }
}
