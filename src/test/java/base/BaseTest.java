package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.FailureConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;

public class BaseTest {

    protected static ThreadLocal<RequestSpecification> requestSpec = new ThreadLocal<>();


    @BeforeAll
    static void setup(){
        RestAssured.baseURI = "https://dummyjson.com";

        RequestSpecification spec = new RequestSpecBuilder()
                .setContentType("application/json")
                .setAccept("application/json")
                .build();

        requestSpec.set(spec);

    }

    protected RequestSpecification getRequestSpec() {
        return requestSpec.get();
    }
}
