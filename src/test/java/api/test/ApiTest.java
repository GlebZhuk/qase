package api.test;

import api.adapters.CaseAdapter;
import api.adapters.ProjectAdapter;
import api.adapters.SuiteAdapter;
import model.CaseModel;
import model.Project;
import model.Suite;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_OK;

public class ApiTest {
    @Test(testName = "Verify create new project", priority = 1)
    public void checkCreationNewProjectResultCodeTest() {
        Project project = Project.builder()
                .title("Web Application")
                .code("WA")
                .description("Test project")
                .build();
        String resultCode = new ProjectAdapter().createNewProject(project).body().path("result.code");
        Assert.assertEquals(resultCode, project.getCode(), "Error of get information about projects");
    }

    @Test(testName = "Verify create new suite", priority = 2)
    public void checkCreateSuiteIdTest() {
        Suite suite = Suite.builder()
                .title("SmokeTest")
                .description("Test")
                .preconditions("some preconditions")
                .build();
        int resultId = new SuiteAdapter().createNewTestSuite("WA", suite).body().path("result.id");
        Assert.assertTrue(resultId > 0, "Error of created suite");
    }

    @Test(testName = "Get all projects", priority = 3)
    public void checkGetAllProjectsStatusCode() {
        int statusCode = new ProjectAdapter().getAllProjects().statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Error of list of all projects");
    }

    @Test(testName = "Verify create new case", priority = 4)
    public void checkCreateCaseIdTest() {
        CaseModel caseModel = CaseModel.builder()
                .title("My test case")
                .description("Check created test case")
                .build();
        int resultId = new CaseAdapter().createNewTestCase("WA", caseModel).body().path("result.id");
        Assert.assertTrue(resultId > 0, "Error of create case");
    }

    @Test(testName = "Verify delete project", priority = 5)
    public void checkDeleteProjectTest() {
        int statusCode = new ProjectAdapter().deleteProject("WA").statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Error of delete project");
    }
}
