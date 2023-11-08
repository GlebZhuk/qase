package page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.AllureUtils;
import utils.Waiter;

@Log4j2
public class ProjectNamePage extends Page{
    @FindBy(xpath="//h1[@class='fGDnu0']")
    private WebElement nameOfCreatedProject;
    @FindBy(xpath = "//div[contains(text(), 'already in use')]")
    private WebElement messageAboutUsedCode;
    @FindBy(xpath = "//a[contains(text(), 'Projects')]")
    private WebElement buttonMenuProjects;
    @FindBy(xpath = "//span[contains(text(), 'new suite')]")
    private WebElement buttonCreateNewSuite;
    @FindBy(xpath = "//input[@id='title']")
    private WebElement suiteNameField;
    @FindBy(xpath = "//span[text()='Create']")
    private WebElement buttonCreateSuite;
    @FindBy(xpath = "//span[contains(text(), 'Suite was successfully created')]")
    private WebElement messageSuiteCreated;
    @FindBy(xpath = "//a[@id='create-case-button']")
    private WebElement buttonCreateNewCase;
    @FindBy(xpath = "//span[contains(text(),'case was created')]")
    private WebElement messageCaseCreated;

    @Step("Get text from name of new project")
    public String getNameOfNEwProject() {
        log.info("Get text from name of new project");
        return Waiter.waitElementToBeVisible(nameOfCreatedProject).getText();
    }

    @Step("Get text about that project code is already in used")
    public String getTextCodeIsUsed() {
        log.info("Get text about that project code is already in used");
        AllureUtils.takeScreenshot(driver);
        return Waiter.waitElementToBeVisible(messageAboutUsedCode).getText();
    }

    @Step("Click button 'Projects' in menu")
    public ProjectsPage clickButtonMenuProjects() {
        log.info("Click button 'Projects' in menu");
        Waiter.waitElementToBeClickable(buttonMenuProjects).click();
        return new ProjectsPage();
    }

    @Step("Click new suite button")
    public ProjectNamePage clickButtonNewSuite() {
        log.info("Click new suite button");
        buttonCreateNewSuite.click();
        return this;
    }

    @Step("Fill in suite name field")
    public ProjectNamePage inputSuiteName(String suiteName) {
        log.info("Fill in suite name field");
        suiteNameField.sendKeys(suiteName);
        return this;
    }

    @Step("Click create suite button")
    public ProjectNamePage clickButtonCreateSuite() {
        log.info("Click create suite button");
      Waiter.waitElementToBeClickable(buttonCreateSuite).click();
        return this;
    }

    @Step("Get message about suite successfully created")
    public String getMessageOfCreatedSuite() {
        log.info("Get message about suite successfully created");
        return Waiter.waitElementToBeVisible(messageSuiteCreated).getText();
    }

    @Step("Click new case button")
    public ProjectNamePage clickButtonNewCase() {
        log.info("Click new case button");
        buttonCreateNewCase.click();
        return this;
    }

    @Step("Get message about case created")
    public String getMessageOfCreatedCase() {
        log.info("Get message about case created");
        return Waiter.waitElementToBeVisible(messageCaseCreated).getText();
    }
}
