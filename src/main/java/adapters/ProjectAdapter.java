package adapters;

import io.restassured.response.Response;
import model.Project;

public class ProjectAdapter extends BaseAdapter {
    private static final String PROJECT_API_ENDPOINT = "/project/";

    public Response getAllProjects() {
        return get(PROJECT_API_ENDPOINT);
    }

    public Response createNewProject(Project project) {
        return post(PROJECT_API_ENDPOINT, converter.toJson(project));
    }

    public Response deleteProject(String projectCode) {
        return delete(String.format(PROJECT_API_ENDPOINT + projectCode));
    }
}
