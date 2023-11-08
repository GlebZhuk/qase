package test;

import jdk.jfr.Description;
import model.Project;
import model.Suite;
import model.User;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.*;
import page.ProjectsPage;
import service.LoginPageService;
import service.ProjectNamePageService;
import service.ProjectsPageService;
import utils.Waiter;

public class ProjectPageTest extends BaseTest {
    private LoginPageService loginPageService;

    private User user;
    private Project project;
    private Project projectWebDevelopment;
    private Project projectMobileResearch;
    private Project projectQAAutomation;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        user = new User();
        project = new Project();

    }

    @Description("Verify successful create project")
    @Test(description = "Create Project", priority = 1)
    public void verifySuccessfulCreateProjectTest() {
        ProjectsPageService projectsPageService = loginPageService.login(user)
                .inputProjectsData(project);
        String expectedTextOnProjectPage = projectsPageService.getProjectCode() + " repository";
        ProjectNamePageService projectNamePageService = projectsPageService.createNewProject();
        String actualTextOnProjectNamePage = projectNamePageService.getNameOfNEwProject();
        Assert.assertEquals(actualTextOnProjectNamePage, expectedTextOnProjectPage,
                "The actual text on the page doesn't match with expected. Error of creating project");
    }

    @Description("Verify message if you try use existing project code")
    @Test(description = "Creat existing project", priority = 2)
    public void verifyMessageIfProjectCodeIsUsedTest() {
        String actualMessageAboutProjectCodeIsUsed = loginPageService.login(user)
                .inputProjectsData(project)
                .createNewProject()
                .getMassageIfProjectCodeUsed();
        String expectedMessageAboutProjectCodeIsUsed = "The selected project code is already in use.";
        Assert.assertEquals(actualMessageAboutProjectCodeIsUsed, expectedMessageAboutProjectCodeIsUsed,
                "Error creating project recode message");
    }

    @Description("Verify delete project")
    @Test(description = "Delete project", priority = 3)
    public void verifyDeleteProjectTest() {
        String actualMessageAboutYouDontHaveAnyProjects = loginPageService.login(user)
                .inputProjectsData(project)
                .createNewProject()
                .clickButtonProjects()
                .removeProject()
                .getMessageYouDontHaveProjects();
        String expectedMessageAboutYouDontHaveAnyProjects = "Looks like you don’t have any projects yet.";
        Assert.assertEquals(actualMessageAboutYouDontHaveAnyProjects, expectedMessageAboutYouDontHaveAnyProjects,
                "Error remove project");
    }


    @Description("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Verify search projects")
    @Test(description = "Search project", priority = 4)
    public void verifySearchProjectTest() throws InterruptedException {
        projectMobileResearch = new Project("Mobile research", "This is my test project");
        projectQAAutomation = new Project("QA automation", "This is my test project");
        projectWebDevelopment = new Project("Web development", "This is my test project");
        ProjectsPageService projectsPageService =
                loginPageService.login(user)
                        .inputProjectsData(projectMobileResearch)
                        .createNewProject()
                        .clickButtonProjects()
                        .inputProjectsData(projectQAAutomation)
                        .createNewProject()
                        .clickButtonProjects()
                        .inputProjectsData(projectWebDevelopment)
                        .createNewProject()
                        .clickButtonProjects();
        String expectedSearchResult = projectQAAutomation.getProjectName();
        System.out.println(expectedSearchResult);
        String actualSearchResult = projectsPageService
                .searchProjectFromValue(projectQAAutomation.getProjectName())
                .getSearchResult();
        System.out.println(actualSearchResult);
        projectsPageService
                .clickCleanSearchButton()
                .removeProject();
        Assert.assertEquals(actualSearchResult, expectedSearchResult, "Error search!");
    }


}







