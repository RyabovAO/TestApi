package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Requests {

    private final static String ALL = "all";
    private final static String CONTENT_TYPE = "Content-Type";
    private final static String JSON = "application/json";

    public Response getRequest(String Uri, String value, String resource) {
        if (value.equalsIgnoreCase(ALL)) {
            return given()
                    .baseUri(Uri)
                    .contentType(ContentType.JSON)
                    .when()
                    .get(resource)
                    .then().log().all()
                    .extract().response();
        } else {
            return given()
                    .baseUri(Uri)
                    .contentType(ContentType.JSON)
                    .when()
                    .get(resource + value)
                    .then().log().all()
                    .extract().response();
        }
    }

    public Response postRequest(String Uri, String resource, Object register) {
        return given()
                .baseUri(Uri)
                .header(CONTENT_TYPE, JSON)
                .body(register)
                .when()
                .post(resource)
                .then().log().all()
                .extract().response();
    }
}
