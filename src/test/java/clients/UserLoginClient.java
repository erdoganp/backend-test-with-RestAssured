package clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class UserLoginClient {

    public Response login(String token){

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/user/me")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
    }

}
