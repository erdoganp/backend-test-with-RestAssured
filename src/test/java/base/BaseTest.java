package base;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void setup(){
        RestAssured.baseURI = "https://dummyjson.com";

        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout", 5000)
                );



    }
}
