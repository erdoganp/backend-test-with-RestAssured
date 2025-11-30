package base;

import io.restassured.RestAssured;
import io.restassured.config.FailureConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;

public class BaseTest {

    @BeforeAll
    static void setup(){
        RestAssured.baseURI = "https://dummyjson.com";




    }
}
