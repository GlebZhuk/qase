package test;

import jdk.jfr.Description;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import service.LoginPageService;
import service.ProjectsPageService;

public class LoginPageTest extends BaseTest {
    private LoginPageService loginPageService;

    private User user;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        user = new User();
    }

    @Description("Verify login on our site")
    @Test(description = "Test login user", priority = 3)
    public void checkSuccessfulLoginTest() {
        ProjectsPageService projectsPageService = loginPageService.login(user);
        String actualTextOnProjectPage = projectsPageService.getTextProjectsOnPage();
        String expectedTextOnProjectPage = "Projects";
        Assert.assertEquals(actualTextOnProjectPage, expectedTextOnProjectPage,
                "The actual text on the page doesn't match with expected. Error of registration");
    }

    @Description("Verify empty fields Email and Password")
    @Test(description = "Test empty fields", priority = 1)
    public void checkErrorMessageIfEmptyFieldsTest() {
        String actualTextOfEmptyEmail = loginPageService.clickButtonWithEmptyEmail();
        String actualTextOfEmptyPassword = loginPageService.clickButtonWithEmptyPassword();
        String expectedTextOfEmptyFields = "This field is required";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTextOfEmptyEmail, expectedTextOfEmptyFields, "Error of test empty fields");
        softAssert.assertEquals(actualTextOfEmptyPassword, expectedTextOfEmptyFields, "Error of test empty fields");
        softAssert.assertAll();
    }

    @DataProvider(name = "values")
    public Object[][] values() {
        return new Object[][]{
                {"@@@", "_!#("},
                {"111111", " "},
                {"HHHHHHHHH", "FGFFFFF"}
        };
    }

    @Description("Verify incorrect values in registration fields")
    @Test(description = "Test incorrect values", dataProvider = "values", threadPoolSize = 3, priority = 2)
    public void checkIncorrectValuesInFieldsTest(String userName, String userPassword) {
        String actualMeassageAboutIncorectValue = loginPageService.inputIncorrectValues(userName, userPassword);
        String expectedMeassageAboutIncorectValue = "Value '" + userName + "' does not match format email of type string";
        Assert.assertEquals(actualMeassageAboutIncorectValue, expectedMeassageAboutIncorectValue,
                "The actual text on the page doesn't match with expected. Error protected of registration");
    }

}
