package test;

import jdk.jfr.Description;
import model.CaseModel;
import model.Project;
import model.Suite;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.LoginPageService;
import service.ProjectNamePageService;
import service.ProjectSettingsPageService;
import service.ProjectsPageService;

public class ProjectNamePageTest extends BaseTest {
    private LoginPageService loginPageService;

    private User user;
    private Project project;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        user = new User();
        project = new Project();

    }

    @Description("Verify create new suite")
    @Test(description = "Create new suite", priority = 4)
    public void verifyCreateSuiteTest() {
        Suite suite = new Suite();
        ProjectNamePageService projectNamePageService = loginPageService.login(user)
                .inputProjectsData(project)
                .createNewProject();
        String actualMessageAboutCreatedSuite = projectNamePageService.createNewSuite(suite.getSuiteName());
        String expectedMessageAboutCreatedSuite = "Suite was successfully created.";
        projectNamePageService.clickButtonProjects()
                .removeProject();
        Assert.assertEquals(actualMessageAboutCreatedSuite, expectedMessageAboutCreatedSuite,
                "Error create suite");
    }

    @Description("Verify create new case")
    @Test(description = "Create new case", priority = 4)
    public void verifyCreateCaseTest() {
        CaseModel caseModel = new CaseModel();

        ProjectNamePageService projectNamePageService =
                loginPageService.login(user)
                        .inputProjectsData(project)
                        .createNewProject()
                        .clickCreateNewCase()
                        .createNewCase(caseModel);
        String actualMessageAboutCreatedCase = projectNamePageService.getMessageCaseCreated();
        String expectedMessageAboutCreatedCase = "Test case was created successfully!";
        projectNamePageService.clickButtonProjects()
                .removeProject();
        Assert.assertEquals(actualMessageAboutCreatedCase, expectedMessageAboutCreatedCase,
                "Error create case");
    }

    @Description("Verify update project")
    @Test(description = "Update project", priority = 4)
    public void verifyUpdateProjectTest() {

        ProjectSettingsPageService projectSettingsPageService =
                loginPageService.login(user)
                        .inputProjectsData(project)
                        .createNewProject()
                        .clickButtonProjects()
                        .clickSettingProjectButton()
                        .renameProject(project);
        String actualMessageAboutUpdateProject = projectSettingsPageService.getMessageProjectUpdate();
        String expectedMessageAboutUpdateProject = "Project settings were successfully updated!";
        projectSettingsPageService
                .clickButtonMenuProjects()
                .removeProject();
         Assert.assertEquals(actualMessageAboutUpdateProject, expectedMessageAboutUpdateProject,
              "Error update project");
    }

    @Description("Verify archive project")
    @Test(description = "archive project", priority = 4)
    public void verifyArchiveProjectTest() {

        ProjectSettingsPageService projectSettingsPageService =
                loginPageService.login(user)
                        .inputProjectsData(project)
                        .createNewProject()
                        .clickButtonProjects()
                        .clickSettingProjectButton()
                        .clickButtonArchiveProject();
        String actualMessageAboutArchiveProject = projectSettingsPageService.getMessageProjectArchived();
        String expectedMessageAboutArchiveProject = "Project was successfully archived!";
        projectSettingsPageService
                .clickButtonMenuProjects()
                .removeProject();
        Assert.assertEquals(actualMessageAboutArchiveProject, expectedMessageAboutArchiveProject,
                "Error update project");
    }
}
