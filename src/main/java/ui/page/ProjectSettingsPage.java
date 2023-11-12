package ui.page;

import lombok.extern.log4j.Log4j2;
import model.Project;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

@Log4j2
public class ProjectSettingsPage extends Page {
    @FindBy(xpath = "//input[@id='project-name']")
    private WebElement nameOfProjectField;
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

    public ProjectSettingsPage clearProjectNameField() {
        log.info("Clear project name field");
        nameOfProjectField.clear();
        return this;
    }

    public ProjectSettingsPage inputNewNameOfProject(Project project) {
        log.info("Input new project's name");
        nameOfProjectField.sendKeys(project.getProjectRename());
        return this;
    }

    public void clickButtonUpdateSettings() {
        log.info("Confirm new settings");
        buttonUpdateSettings.click();
    }

    public String getMessageSuccessfulUpdate() {
        log.info("Get message about successful update settings project");
        return Waiter.waitElementToBeVisible(messageAboutSuccessfulUpdate).getText();
    }

    public void clickButtonMenuProjects() {
        log.info("Click button 'Projects' in menu");
        Waiter.waitElementToBeClickable(buttonMenuProjects).click();
    }

    public void clickButtonArchiveProject() {
        log.info("Click button archive project");
        driver.navigate().refresh();
        Waiter.waitElementToBeClickable(buttonArchiveProject).click();
    }

    public String getMessageSuccessfulArchived() {
        log.info("Get message about successful archive project");
        return Waiter.waitElementToBeVisible(messageAboutSuccessfulArchiveProject).getText();
    }
}
