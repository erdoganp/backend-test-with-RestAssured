package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Execution(ExecutionMode.CONCURRENT)
public class ProductTestParallel extends BaseTest {

    @Test
    public void TestParallel1(){
        given()
                .spec(getRequestSpec())
                .when()
                .get("/products/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("id",equalTo(1));

    }

    @Test
    public void TestParallel2(){
        given()
                .spec(getRequestSpec())
                .when()
                .get("/products/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("id",equalTo(2));

    }
}
