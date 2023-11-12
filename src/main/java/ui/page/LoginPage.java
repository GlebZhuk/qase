package ui.page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

@Log4j2
public class LoginPage extends Page {
    @FindBy(xpath = "//input[@type='text']")
    private WebElement userNameField;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement userPasswordField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSignIn;
    @FindBy(xpath = "//input[@type='password']/ancestor::div[@class='tdishH']/small")
    private WebElement errorMessageEmptyPasswordField;
    @FindBy(xpath = "//input[@type='text']/ancestor::div[@class='tdishH']/small")
    private WebElement errorMessageEmptyEmailField;
    @FindBy(xpath = "//span[contains(text(), 'does not match format')]")
    private WebElement errorMessageIncorrectFormat;

    public LoginPage openPage(String url) {
        log.info("Open login page");
        driver.get(url);
        return this;
    }

    public LoginPage inputUserNameField(String userName) {
        log.info("Input user name field");
        Waiter.waitElementToBeVisible(userNameField).sendKeys(userName);
        return this;
    }

    public LoginPage inputUserPasswordField(String userPassword) {
        log.info("Input user password field");
        Waiter.waitElementToBeVisible(userPasswordField).sendKeys(userPassword);
        return this;
    }

    public LoginPage clickButtonSignIn() {
        log.info("Click button 'Sign in'");
        buttonSignIn.click();
        return this;
    }

    public String findTextErrorWhenEmptyEmail() {
        log.info("Get text  about Email field is empty");
        return errorMessageEmptyEmailField.getText();
    }

    public String findTextErrorWhenEmptyPassword() {
        log.info("Get text  about Password field is empty");
        return errorMessageEmptyPasswordField.getText();
    }

    public String getTextAboutIncorrectFormatInField() {
        log.info("Get text  about incorrect format in fields registration");
        return Waiter.waitElementToBeVisible(errorMessageIncorrectFormat).getText();
    }
}
