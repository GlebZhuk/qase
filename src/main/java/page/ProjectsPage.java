package page;

import io.qameta.allure.Step;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import utils.AllureUtils;
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
    List<WebElement> buttonsRemoveOrSettingsProjects =
            driver.findElements(By.xpath("//td[8]/div/button"));
    @FindBy(xpath = "//button[@tabindex='0']")
    private WebElement buttonRemoveProject;
    @FindBy(xpath = "//a[contains(text(),'Settings')]")
    private WebElement buttonSettingsProject;
    @FindBy(xpath = "//button/span[contains(text(),'Delete')]")
    private WebElement buttonDeleteProject;
    @FindBy(xpath = "//div[contains(text(), 'have any projects' )]")
    private WebElement messageAboutYouDontHaveProjects;
    @FindBy(xpath = "//input[@type='text']")
    private WebElement searchField;
    // @FindBy(xpath = "//a[@class='MfvNFg']")
    @FindBy(xpath = "//tbody//a[@class]")
    private WebElement searchResult;
    @FindBy(xpath = "//a[contains(text(), 'Projects')]")
    private WebElement buttonMenuProjects;
    @FindBy(xpath = "//button/i")
    private WebElement cleanSearchButton;
    @FindBy(xpath = "//tr[1]//a[contains(text(), 'QA automation')]")
    private WebElement verifySearchResult;
    @Getter
    @Setter
    private int countProjects =buttonsRemoveOrSettingsProjects.size()-1;

    private boolean cycleIndicator = true;


    @Step("Find text on the page after registration")
    public String findTextProjectsOnPage() {
        return Waiter.waitElementToBeVisible(textProjectsOnPage).getText();
    }

    @Step("Click 'Create new project' button")
    public ProjectsPage clickCreateNewProjectButton() {
        log.info("Click 'Create new project button'");
        createNewProjectButton.click();
        return this;
    }

    @Step("Click 'Create project' button")
    public ProjectNamePage clickCreateProjectButton() {
        log.info("Click 'Create project' button");
        createProjectButton.click();
        return new ProjectNamePage();
    }

    @Step("Input project name")
    public ProjectsPage fillInProjectName(String name) {
        log.info("Input project name");
        Waiter.waitElementToBeVisible(projectNameField).sendKeys(name);
        return this;
    }

    @Step("Get text project code")
    public String getProjectCode() {
        log.info("Get text project code");
        return Waiter.waitElementToBeVisible(projectCodeField).getAttribute("value");
    }

    @Step("Fill in project description")
    public ProjectsPage fillInProjectDescription(String description) {
        log.info("Fil in project description");
        projectDescriptionField.sendKeys(description);
        return this;
    }

    @Step("Set project access type")
    public ProjectsPage setProjectAccessType() {
        log.info("Set project access type");
        projectAccessTypePublic.click();
        return this;
    }


    @Step("Click buttons which have buttons remove and settings")
    public ProjectsPage clickButtonsRemoveOrSettingProject(int i) {
boolean y=true;
        log.info("Click buttons which have buttons remove and settings");
        driver.navigate().refresh();

       // countProjects = buttonsRemoveOrSettingsProjects.size() - 1;
        do {
            driver.navigate().refresh();
            buttonsRemoveOrSettingsProjects = driver.findElements(By.xpath("//td[8]/div/button"));
            if (!buttonsRemoveOrSettingsProjects.isEmpty()) {
                Waiter.waitElementToBeVisible(buttonsRemoveOrSettingsProjects.get(i)).click();
                y = false;
            }
        } while (y);

        return this;
    }


    @Step("Click remove project button")
    public ProjectsPage clickButtonRemoveProject() {
        log.info("Click remove project button");
        Waiter.waitElementToBeClickable(buttonRemoveProject).click();
        return this;
    }

    @Step("Click delete project button")
    public ProjectsPage clickButtonDeleteProject() {
        log.info("Click delete project button");
        Waiter.waitElementToBeClickable(buttonDeleteProject).click();
        return this;
    }

    @Step("Get message about you don't have any projects")
    public String getMeassageDontHaveProjects() {
        log.info("Get message about you don't have any projects");
        AllureUtils.takeScreenshot(driver);
        return Waiter.waitElementToBeVisible(messageAboutYouDontHaveProjects).getText();
    }

    @Step("Input in search field")
    public ProjectsPage inputSearchField(String value) {
        log.info("Input in search field");
        Waiter.waitElementToBeVisible(searchField).sendKeys(value);
        return this;
    }

    @Step("Get search projects resalt")
    public String getSearchResult() throws InterruptedException {
        log.info("Get Search projects result");
        //Waiter.waitElementToBeVisible(verifySearchResult);
        driver.navigate().refresh();
        return Waiter.waitElementToBeVisible(searchResult).getText();
    }

    @Step("Click button 'Projects' in menu")
    public ProjectsPage clickButtonMenuProjects() {
        log.info("Click button 'Projects' in menu");
        Waiter.waitElementToBeClickable(buttonMenuProjects).click();
        return this;
    }

    @Step("Click clean search button")
    public ProjectsPage clickCleanSearchButton() {
        log.info("Click clear search button");
        Waiter.waitElementToBeVisible(cleanSearchButton).click();
        return this;
    }

    @Step("Click settings project button")
    public ProjectsPage clickButtonSettingsProject() {
        log.info("Click settings project button");
        Waiter.waitElementToBeClickable(buttonSettingsProject).click();
        return this;
    }

}
