package ui.test;

import jdk.jfr.Description;
import model.Project;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.service.LoginPageService;
import ui.service.ProjectNamePageService;
import ui.service.ProjectsPageService;

import static utils.StringConstant.CODE_IS_USED;
import static utils.StringConstant.DONT_HAVE_PROJECTS;

public class ProjectPageTest extends BaseTest {
    private LoginPageService loginPageService;
    private User user;
    private Project project;


    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        user = new User();
        project = Project.builder()
                .projectName("Web Application")
                .description("This is my test project")
                .build();
    }

    @Description("Verify successful create project")
    @Test(testName = "Create Project", priority = 1)
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
    @Test(testName = "Creat existing project", priority = 2)
    public void verifyMessageIfProjectCodeIsUsedTest() {
        String actualMessageAboutProjectCodeIsUsed = loginPageService.login(user)
                .inputProjectsData(project)
                .createNewProject()
                .getMassageIfProjectCodeUsed();
        Assert.assertEquals(actualMessageAboutProjectCodeIsUsed, CODE_IS_USED,
                "Error creating project recode message");
    }

    @Description("Verify delete project")
    @Test(testName = "Delete project", priority = 3)
    public void verifyDeleteProjectTest() {
        String actualMessageAboutYouDontHaveAnyProjects = loginPageService.login(user)
                .removeProject()
                .getMessageYouDontHaveProjects();
        Assert.assertEquals(actualMessageAboutYouDontHaveAnyProjects, DONT_HAVE_PROJECTS,
                "Error remove project");
    }

    @Description("Verify search projects")
    @Test(testName = "Search project", priority = 4)
    public void verifySearchProjectTest() {
        Project projectWebDevelopment;
        Project projectMobileResearch;
        Project projectQAAutomation;
        projectMobileResearch = Project.builder()
                .projectName("Mobile research")
                .description("This is my test project")
                .build();
        projectQAAutomation = Project.builder()
                .projectName("QA automation")
                .description("This is my test project")
                .build();
        projectWebDevelopment = Project.builder()
                .projectName("Web development")
                .description("This is my test project")
                .build();
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
