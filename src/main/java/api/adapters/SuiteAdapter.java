package api.adapters;

import io.restassured.response.Response;
import model.Suite;

public class SuiteAdapter extends BaseAdapter {
    public static final String SUITE_API_ENDPOINT = "/suite/%s";

    public Response createNewTestSuite(String projectCode, Suite suite) {
        return post(String.format(SUITE_API_ENDPOINT, projectCode), converter.toJson(suite));
    }
}
