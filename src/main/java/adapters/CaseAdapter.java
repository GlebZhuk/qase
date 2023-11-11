package adapters;

import io.restassured.response.Response;
import model.CaseModel;

public class CaseAdapter extends BaseAdapter {
    public static final String CASE_API_ENDPOINT = "/case/%s";

    public Response createNewTestCase(String projectCode, CaseModel caseModel) {
        return post(String.format(CASE_API_ENDPOINT, projectCode), converter.toJson(caseModel));
    }
}
