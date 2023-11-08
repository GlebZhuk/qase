package page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.AllureUtils;
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

    @Step("Open starting page")
    public LoginPage openPage(String url) {
        log.info("Open login page");
        driver.get(url);
        return this;
    }

    @Step("Input user name field")
    public LoginPage inputUserNameField(String userName) {
        log.info("Input user name field");
        Waiter.waitElementToBeVisible(userNameField).sendKeys(userName);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Input user password field")
    public LoginPage inputUserPasswordField(String userPassword) {
        log.info("Input user password field");
        Waiter.waitElementToBeVisible(userPasswordField).sendKeys(userPassword);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Click button 'Sign in'")
    public LoginPage clickButtonSignIn() {
        log.info("Click button 'Sign in'");
        buttonSignIn.click();
        return this;
    }

    @Step("Get text  about Email field is empty")
    public String findTextErrorWhenEmptyEmail() {
        log.info("Get text  about Email field is empty");
        AllureUtils.takeScreenshot(driver);
        return errorMessageEmptyEmailField.getText();
    }

    @Step("Get text  about Password field is empty")
    public String findTextErrorWhenEmptyPassword() {
        log.info("Get text  about Password field is empty");
        AllureUtils.takeScreenshot(driver);
        return errorMessageEmptyPasswordField.getText();
    }

    @Step("Get text  about incorrect format in fields registration")
    public String getTextAboutIncorrectFormatInField() {
        log.info("Get text  about incorrect format in fields registration");
        String messageAboutIncorrectType = Waiter.waitElementToBeVisible(errorMessageIncorrectFormat).getText();
        AllureUtils.takeScreenshot(driver);
        return messageAboutIncorrectType;
    }
}
