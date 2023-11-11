package page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.Project;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

@Log4j2
public class ProjectSettingsPage extends Page {
    @FindBy(xpath = "//input[@id='project-name']")
    private WebElement nameOfProjectField;
    @FindBy(xpath = "//input[@value='private']")
    private WebElement buttonPrivateProject;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonUpdateSettings;
    @FindBy(xpath = "//span[contains(text(), 'settings were successfully updated')]")
    private WebElement messageAboutSuccessfulUpdate;
    @FindBy(xpath = "//a[contains(text(), 'Projects')]")
    private WebElement buttonMenuProjects;
    @FindBy(xpath = "//div/button[1]/span")
    private WebElement buttonArchiveProject;
    @FindBy(xpath = "//span[contains(text(), 'was successfully archived')]")
    private WebElement messageAboutSuccessfulArchiveProject;

    @Step("Clear project name field")
    public ProjectSettingsPage clearProjectNameField() {
        log.info("Clear project name field");
        nameOfProjectField.clear();
        return this;
    }

    @Step("Input new project's name")
    public ProjectSettingsPage inputNewNameOfProject(Project project) {
        log.info("Input new project's name");
        nameOfProjectField.sendKeys(project.getProjectRename());
        return this;
    }

    @Step("Make project private")
    public ProjectSettingsPage clickPrivateProjectButton() {
        log.info("Make project private");
        buttonPrivateProject.click();
        return this;
    }

    @Step("Confirm new settings")
    public ProjectSettingsPage clickButtonUpdateSettings() {
        log.info("Confirm new settings");
        buttonUpdateSettings.click();
        return this;
    }

    @Step("Get message about successful update settings project")
    public String getMessageSuccessfulUpdate() {
        log.info("Get message about successful update settings project");
        return Waiter.waitElementToBeVisible(messageAboutSuccessfulUpdate).getText();
    }

    @Step("Click button 'Projects' in menu")
    public ProjectsPage clickButtonMenuProjects() {
        log.info("Click button 'Projects' in menu");
        Waiter.waitElementToBeClickable(buttonMenuProjects).click();
        return new ProjectsPage();
    }

    @Step("Click button archive project")
    public ProjectSettingsPage clickButtonArchiveProject() {
        log.info("Click button archive project");
        driver.navigate().refresh();
        Waiter.waitElementToBeClickable(buttonArchiveProject).click();
        return this;
    }

    @Step("Get message about successful archive project")
    public String getMessageSuccessfulArchived() {
        log.info("Get message about successful archive project");
        return Waiter.waitElementToBeVisible(messageAboutSuccessfulArchiveProject).getText();
    }
}
