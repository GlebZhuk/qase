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

import static utils.StringConstant.*;

public class ProjectNamePageTest extends BaseTest {
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

    @Description("Verify create new suite")
    @Test(testName = "Create new suite", priority = 4)
    public void verifyCreateSuiteTest() {
        Suite suite = Suite.builder()
                .suiteName("Test suite")
                .build();
        ProjectNamePageService projectNamePageService =
                loginPageService.login(user)
                        .clickProjectName();
        String actualMessageAboutCreatedSuite = projectNamePageService.createNewSuite(suite);
        String expectedMessageAboutCreatedSuite = SUITE_CREATED;
        Assert.assertEquals(actualMessageAboutCreatedSuite, expectedMessageAboutCreatedSuite,
                "Error create suite");
    }

    @Description("Verify create new case")
    @Test(testName = "Create new case", priority = 3)
    public void verifyCreateCaseTest() {
        CaseModel caseModel = CaseModel.builder()
                .title("Web application case")
                .build();
        ProjectNamePageService projectNamePageService =
                loginPageService.login(user)
                     //   .inputProjectsData(project)
                       // .createNewProject()
                        .clickProjectName()
                        .clickCreateNewCase()
                        .createNewCase(caseModel);
        String actualMessageAboutCreatedCase = projectNamePageService.getMessageCaseCreated();
        String expectedMessageAboutCreatedCase = TEST_CREATED;
        Assert.assertEquals(actualMessageAboutCreatedCase, expectedMessageAboutCreatedCase,
                "Error create case");
    }

    @Description("Verify update project")
    @Test(testName = "Update project", priority = 5)
    public void verifyUpdateProjectTest() {
        Project projectRename = Project.builder()
                .projectRename("Web Development")
                .build();
        ProjectSettingsPageService projectSettingsPageService =
                loginPageService.login(user)
                        .clickSettingProjectButton()
                        .renameProject(projectRename);
        String actualMessageAboutUpdateProject = projectSettingsPageService.getMessageProjectUpdate();
        String expectedMessageAboutUpdateProject = TEST_UPDATED;
        Assert.assertEquals(actualMessageAboutUpdateProject, expectedMessageAboutUpdateProject,
                "Error update project");
    }

    @Description("Verify archive project")
    @Test(testName = "archive project", priority = 6)
    public void verifyArchiveProjectTest() {
        ProjectSettingsPageService projectSettingsPageService =
                loginPageService.login(user)
                        .clickSettingProjectButton()
                        .clickButtonArchiveProject();
        String actualMessageAboutArchiveProject = projectSettingsPageService.getMessageProjectArchived();
        String expectedMessageAboutArchiveProject = PROJECT_ARCHIVED;
        projectSettingsPageService
                .clickButtonMenuProjects()
                .setVisibilityProjects()
                .removeProject();
        Assert.assertEquals(actualMessageAboutArchiveProject, expectedMessageAboutArchiveProject,
                "Error update project");
    }
}
