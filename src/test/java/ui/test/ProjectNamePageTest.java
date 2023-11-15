package ui.test;

import jdk.jfr.Description;
import model.CaseModel;
import model.Project;
import model.Suite;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.service.LoginPageService;
import ui.service.ProjectNamePageService;
import ui.service.ProjectSettingsPageService;

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
                .projectName("Mobile develop")
                .description("Project for ProjectNamePageTest")
                .build();
    }

    @Description("Verify create new suite")
    @Test(testName = "Create new suite", priority = 5)
    public void verifyCreateSuiteTest() {
        Suite suite = Suite.builder()
                .suiteName("Test suite")
                .build();
        ProjectNamePageService projectNamePageService =
                loginPageService.login(user)
                        .inputProjectsData(project)
                        .createNewProject();
        String actualMessageAboutCreatedSuite = projectNamePageService.createNewSuite(suite);
        projectNamePageService.clickButtonProjects()
                .removeProject();
        Assert.assertEquals(actualMessageAboutCreatedSuite, SUITE_CREATED,
                "Error create suite");
    }

    @Description("Verify create new case")
    @Test(testName = "Create new case", priority = 6)
    public void verifyCreateCaseTest() {
        CaseModel caseModel = CaseModel.builder()
                .title("Web application case")
                .build();
        ProjectNamePageService projectNamePageService =
                loginPageService.login(user)
                        .inputProjectsData(project)
                        .createNewProject()
                        .clickCreateNewCase()
                        .createNewCase(caseModel);
        String actualMessageAboutCreatedCase = projectNamePageService.getMessageCaseCreated();
        projectNamePageService.clickButtonProjects()
                .removeProject();
        Assert.assertEquals(actualMessageAboutCreatedCase, TEST_CREATED,
                "Error create case");
    }

    @Description("Verify update project")
    @Test(testName = "Update project", priority = 7)
    public void verifyUpdateProjectTest() {
        Project projectRename = Project.builder()
                .projectRename("Web Development")
                .build();
        ProjectSettingsPageService projectSettingsPageService =
                loginPageService.login(user)
                        .inputProjectsData(project)
                        .createNewProject()
                        .clickButtonProjects()
                        .clickSettingProjectButton()
                        .renameProject(projectRename);
        String actualMessageAboutUpdateProject = projectSettingsPageService.getMessageProjectUpdate();
        projectSettingsPageService.clickButtonMenuProjects()
                .removeProject();
        Assert.assertEquals(actualMessageAboutUpdateProject, TEST_UPDATED,
                "Error update project");
    }

    @Description("Verify archive project")
    @Test(testName = "archive project", priority = 8)
    public void verifyArchiveProjectTest() {
        ProjectSettingsPageService projectSettingsPageService =
                loginPageService.login(user)
                        .inputProjectsData(project)
                        .createNewProject()
                        .clickButtonProjects()
                        .clickSettingProjectButton()
                        .clickButtonArchiveProject();
        String actualMessageAboutArchiveProject = projectSettingsPageService.getMessageProjectArchived();
        projectSettingsPageService
                .clickButtonMenuProjects()
                .setVisibilityProjects()
                .removeProject();
        Assert.assertEquals(actualMessageAboutArchiveProject, PROJECT_ARCHIVED,
                "Error archive project");
    }
}
