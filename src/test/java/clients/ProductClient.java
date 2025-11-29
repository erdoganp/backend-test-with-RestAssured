package clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.ProductRequest;
import models.ProductResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ProductClient {

    public Response getProductById(int id) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response getProductForSchemaValidation(int id) {

        return
                given()
                        .pathParam("id", id)
                        .when()
                        .get("/products/{id}")
                        .then()
                        .body(matchesJsonSchemaInClasspath("schemas/getProductSchema.json"))
                        .extract()
                        .response();


    }

    public Response createProduct(ProductRequest request) {
        return given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/products/add")
                .then()
                .statusCode(201)
                .extract()
                .response();

    }
}


