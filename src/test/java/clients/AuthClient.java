package clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthClient {

    public Response getAuthLogin(Map<String,String> map){
        return given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("/auth/login")
                .then()
                .log().all()
                .extract()
                .response();

    }
}
