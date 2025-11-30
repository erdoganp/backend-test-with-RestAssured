package tests;

import base.BaseTest;
import clients.AuthClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthLoginTest extends BaseTest {
    private final AuthClient authClient= new AuthClient();

    @Test
    public void loginTest(){

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "emilys");
        credentials.put("password", "emilyspass");

       Response response= authClient.getAuthLogin(credentials);

        JsonPath jsonPath = response.jsonPath();

        String username = jsonPath.get("username");
        String token = jsonPath.getString("accessToken");

        assertNotNull(jsonPath, "Response null olamaz");
        assertEquals(credentials.get("username"), username, "usernameler eşit olmalı");
        assertNotNull(token, "Token null olamaz");



    }
    @Test
    void loginNegativeTest(){
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "emisdfsdflys");
        credentials.put("password", "emisfdflyspass");


        Response response= authClient.getAuthLogin(credentials);

        assertEquals(response.statusCode(),400, "Invalid credentials");
    }
}
