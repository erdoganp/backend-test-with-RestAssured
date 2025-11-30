package tests;

import base.BaseTest;
import clients.AuthClient;
import clients.UserLoginClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserLoginTest extends BaseTest {

    private final AuthClient  authClient = new AuthClient();

    private final UserLoginClient userLoginClient = new UserLoginClient();

    @Test
    public void userLoginTest(){
        String token;

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username","emilys");
        credentials.put("password","emilyspass");

       Response responseAuth= authClient.getAuthLogin(credentials);
       JsonPath jsonPath= responseAuth.jsonPath();

       token =jsonPath.getString("accessToken");

       Response responseUser=userLoginClient.login(token);

       JsonPath jsonPathUser=responseUser.jsonPath();


       assertEquals(jsonPathUser.getString("username"), "emilys");
       assertNotNull(jsonPathUser.getString("email"));
       assertEquals(responseUser.getStatusCode(),200,"Status code");
       assertTrue(jsonPathUser.getInt("id")> 0);




    }
}
