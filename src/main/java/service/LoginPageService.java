package service;

import io.qameta.allure.Step;
import model.User;
import page.LoginPage;

public class LoginPageService {
    private static final String LOGIN_PAGE_URL="https://app.qase.io/login";
    private LoginPage loginPage = new LoginPage();
    @Step("Login user")
    public ProjectsPageService login (User user) {
        loginPage.openPage(LOGIN_PAGE_URL)
                .inputUserNameField(user.getUserName())
                .inputUserPasswordField(user.getUserPassword())
                .clickButtonSignIn();
        return new ProjectsPageService();
    }
    @Step("Click 'Sign in' when Email is empty")
    public String clickButtonWithEmptyEmail () {
       return loginPage.openPage(LOGIN_PAGE_URL)
                .clickButtonSignIn().findTextErrorWhenEmptyEmail();
    }
    @Step("Click 'Sign in' when Password is empty")
    public String clickButtonWithEmptyPassword () {
        return loginPage.openPage(LOGIN_PAGE_URL)
                .clickButtonSignIn().findTextErrorWhenEmptyPassword();
    }
    @Step("Input incorrect values in Password and Email fields")
    public String inputIncorrectValues (String userName, String userPassword) {
         return loginPage.openPage(LOGIN_PAGE_URL)
                .inputUserNameField(userName)
                .inputUserPasswordField(userPassword)
                .clickButtonSignIn()
                .getTextAboutIncorrectFormatInField();
    }
}
