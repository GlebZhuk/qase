package ui.page;

import lombok.extern.log4j.Log4j2;
import model.Suite;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

@Log4j2
public class ProjectNamePage extends Page {
    @FindBy(xpath = "//h1[@class='fGDnu0']")
    private WebElement nameOfCreatedProject;
    @FindBy(xpath = "//div[contains(text(), 'already in use')]")
    private WebElement messageAboutUsedCode;
    @FindBy(xpath = "//a[contains(text(), 'Projects')]")
    private WebElement buttonMenuProjects;
    @FindBy(xpath = "//a[@id='create-suite-button']")
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

    public String getNameOfNEwProject() {
        log.info("Get text from name of new project");
        return Waiter.waitElementToBeVisible(nameOfCreatedProject).getText();
    }

    public String getTextCodeIsUsed() {
        log.info("Get text about that project code is already in used");
        return Waiter.waitElementToBeVisible(messageAboutUsedCode).getText();
    }

    public void clickButtonMenuProjects() {
        log.info("Click button 'Projects' in menu");
        Waiter.waitElementToBeClickable(buttonMenuProjects).click();
    }

    public ProjectNamePage clickButtonNewSuite() {
        log.info("Click new suite button");
        buttonCreateNewSuite.click();
        return this;
    }

    public ProjectNamePage inputSuiteName(Suite suite) {
        log.info("Fill in suite name field");
        suiteNameField.sendKeys(suite.getSuiteName());
        return this;
    }

    public ProjectNamePage clickButtonCreateSuite() {
        log.info("Click create suite button");
        Waiter.waitElementToBeClickable(buttonCreateSuite).click();
        return this;
    }

    public String getMessageOfCreatedSuite() {
        log.info("Get message about suite successfully created");
        return Waiter.waitElementToBeVisible(messageSuiteCreated).getText();
    }

    public void clickButtonNewCase() {
        log.info("Click new case button");
        buttonCreateNewCase.click();
    }

    public String getMessageOfCreatedCase() {
        log.info("Get message about case created");
        return Waiter.waitElementToBeVisible(messageCaseCreated).getText();
    }
}
