package adapters;

import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static utils.StringConstant.*;

public class BaseAdapter {
    private static final String TOKEN_VALUE = "22e3f70db3fc40f85d1a7a8766e11ac85eb1fa1661f7fab552f7148c4c828c17";
    private static final String BASE_URL = "https://api.qase.io/v1";
    protected Gson converter = new Gson();

    public Response get(String url) {
        return
                given()
                        .log().all()
                        .header(TOKEN_NAME, TOKEN_VALUE)
                        .when()
                        .get(BASE_URL + url)
                        .then()
                        .log().all()
                        .extract()
                        .response();
    }

    public Response post(String url, String body) {
        return
                given()
                        .log().all()
                        .header(TOKEN_NAME, TOKEN_VALUE)
                        .header(CONTENT_TYPE, JSON)
                        .body(body)
                        .when()
                        .post(BASE_URL + url)
                        .then()
                        .log().all()
                        .extract()
                        .response();
    }

    public Response delete(String url) {
        return
                given()
                        .log().all()
                        .header(TOKEN_NAME, TOKEN_VALUE)
                        .when()
                        .delete(BASE_URL + url)
                        .then()
                        .log().all()
                        .extract()
                        .response();

    }
}
