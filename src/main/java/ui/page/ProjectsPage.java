package ui.page;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import model.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

import java.util.List;

@Log4j2
public class ProjectsPage extends Page {

    @FindBy(xpath = "//h1[contains(text(), 'Projects')]")
    private WebElement textProjectsOnPage;
    @FindBy(xpath = "//span[contains(text(),'Create')]")
    private WebElement createNewProjectButton;
    @FindBy(xpath = "//span[text()='Create project']")
    private WebElement createProjectButton;
    @FindBy(xpath = "//input[@id='project-name']")
    private WebElement projectNameField;
    @FindBy(xpath = "//input[@id='project-code']")
    private WebElement projectCodeField;
    @FindBy(xpath = "//textarea")
    private WebElement projectDescriptionField;
    @FindBy(xpath = "//input[@value='public']")
    private WebElement projectAccessTypePublic;
    private List<WebElement> buttonsRemoveOrSettingsProjects =
            driver.findElements(By.xpath("//td[8]/div/button"));
    @FindBy(xpath = "//button[@tabindex='0']")
    private WebElement buttonRemoveProject;
    @FindBy(xpath = "//a[contains(text(),'Settings')]")
    private WebElement buttonSettingsProject;
    @FindBy(xpath = "//button/span[contains(text(),'Delete')]")
    private WebElement buttonDeleteProject;
    @FindBy(xpath = "//div[contains(text(), 'have any projects' )]")
    private WebElement messageAboutYouDontHaveProjects;
    @FindBy(xpath = "//input[@placeholder]")
    private WebElement searchField;
    @FindBy(xpath = "//tbody//a[@class]")
    private WebElement searchResult;
    @FindBy(xpath = "//button[@aria-label]")
    private WebElement cleanSearchButton;
    @FindBy(xpath = "//button//span[contains(text(), 'Status')]")
    private WebElement buttonStatusProjects;
    @FindBy(xpath = "//td[3]/div/div/a")
    private WebElement projectName;
    @Getter
    private int countProjects = buttonsRemoveOrSettingsProjects.size() - 1;

    public String findTextProjectsOnPage() {
        return Waiter.waitElementToBeVisible(textProjectsOnPage).getText();
    }

    public ProjectsPage clickCreateNewProjectButton() {
        log.info("Click 'Create new project button'");
        createNewProjectButton.click();
        return this;
    }

    public void clickCreateProjectButton() {
        log.info("Click 'Create project' button");
        createProjectButton.click();
    }

    public ProjectsPage fillInProjectName(Project project) {
        log.info("Input project name");
        Waiter.waitElementToBeVisible(projectNameField).sendKeys(project.getProjectName());
        return this;
    }

    public String getProjectCode() {
        log.info("Get text project code");
        return Waiter.waitElementToBeVisible(projectCodeField).getAttribute("value");
    }

    public ProjectsPage fillInProjectDescription(Project project) {
        log.info("Fil in project description");
        projectDescriptionField.sendKeys(project.getDescription());
        return this;
    }

    public void setProjectAccessType() {
        log.info("Set project access type");
        projectAccessTypePublic.click();
    }

    public ProjectsPage clickButtonsRemoveOrSettingProject(int i) {
        log.info("Click buttons which have buttons remove and settings");
        boolean cycleIndicator = true;
        do {
            driver.navigate().refresh();
            buttonsRemoveOrSettingsProjects = driver.findElements(By.xpath("//td[8]/div/button"));
            if (!buttonsRemoveOrSettingsProjects.isEmpty()) {
                Waiter.waitElementToBeClickable(buttonsRemoveOrSettingsProjects.get(i)).click();
                cycleIndicator = false;
            }
        } while (cycleIndicator);
        return this;
    }

    public ProjectsPage clickButtonRemoveProject() {
        log.info("Click remove project button");
        Waiter.waitElementToBeClickable(buttonRemoveProject).click();
        return this;
    }

    public void clickButtonDeleteProject() {
        log.info("Click delete project button");
        Waiter.waitElementToBeClickable(buttonDeleteProject).click();
    }

    public String getMessageDontHaveProjects() {
        log.info("Get message about you don't have any projects");
        return Waiter.waitElementToBeVisible(messageAboutYouDontHaveProjects).getText();
    }

    public void inputSearchField(String value) {
        log.info("Input in search field");
        driver.navigate().refresh();
        Waiter.waitElementToBeVisible(searchField).sendKeys(value);
    }

    public String getSearchResult() {
        log.info("Get Search projects result");
        driver.navigate().refresh();
        return Waiter.waitElementToBeVisible(searchResult).getText();
    }

    public void clickCleanSearchButton() {
        log.info("Click clear search button");
        Waiter.waitElementToBeVisible(cleanSearchButton).click();
    }

    public void clickButtonSettingsProject() {
        log.info("Click settings project button");
        Waiter.waitElementToBeClickable(buttonSettingsProject).click();
    }

    public ProjectsPage clickButtonStatusProjects() {
        log.info("Click button 'Status'");
        buttonStatusProjects.click();
        return this;
    }

    public ProjectsPage setVisibilityStatusProjects() {
        log.info("Set visibility status projects");
        List<WebElement> visibilityProjectsStatus =
                driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement projectStatus : visibilityProjectsStatus) {
            if (!projectStatus.isSelected()) {
                projectStatus.click();
            }
        }
        return this;
    }

    public void clickProjectName() {
        log.info("Click project name");
        projectName.click();
    }
}
