package tests;

import base.BaseTest;
import clients.ProductClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.ProductRequest;
import models.ProductResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.CONCURRENT)
public class ProductTests extends BaseTest {

    private final ProductClient client = new ProductClient();


    // Pozitif
    @Test
    void getProductByIdPositiveTest() {
        int productId = 1;

        Response response = client.getProductById(productId);
        JsonPath jsonPath = response.jsonPath();

        assertNotNull(jsonPath, "Response null olamaz");
        assertEquals(productId,jsonPath.getInt("id"),"ID eşleşmeli");
        assertNotNull(jsonPath.getString("title"), "Title boş olamaz");
        assertTrue(jsonPath.getFloat("price") > 0, "Price 0’dan büyük olmalı");
        assertNotNull(jsonPath.getString("description"), "Description boş olamaz");
    }

    // Negatif
   //@Test
    void getProductByIdNegativeTest() {
        int productId = -11;
        Response response = client.getProductById(productId);
        assertEquals(response.getStatusCode(),404,"Status code");

    }


    // Pozitif POST Testi (Yeni Product oluştur)

    @Test
    void createProductPositiveTest() {
        ProductRequest request = new ProductRequest();


        request.setTitle("Yeni Ürün");
        request.setDescription("Test için oluşturuldu");
        request.setPrice(1500);
        request.setCategory("electronics");

        Response response = client.createProduct(request);
        JsonPath jsonPath= response.jsonPath();

        assertNotNull(jsonPath, "Response null olamaz");
        assertNotEquals(0,jsonPath.getInt("id"), "ID oluşturulmuş olmalı");
        assertEquals(request.getTitle(),jsonPath.getString("title"), "Title eşleşmeli");
        assertEquals(request.getPrice(), jsonPath.getFloat("price"), "Price eşleşmeli");
        assertEquals(request.getCategory(), jsonPath.getString("category"), "Category eşleşmeli");
    }

    @Test
    void createProductNegativeTest() {
        given()
                .when()
                .post("/products/a")
                .then()
                .log().all()
                .statusCode(415)
                .body("message",equalTo("unsupported charset \"ISO-8859-1\""));

    }

    @Test
    void getSchemeValidationTest(){
        int productId = 1;

        Response response = client.getProductForSchemaValidation(productId);
        JsonPath jsonPath= response.jsonPath();

        assertEquals(productId, jsonPath.getInt("id"), "ID eşleşmeli");
        assertNotNull(jsonPath.getString("title"), "Title boş olamaz");
        assertTrue(jsonPath.getFloat("price")> 0, "Price 0’dan büyük olmalı");
        assertNotNull(jsonPath.getString("description"), "Description boş olamaz");
        assertFalse(jsonPath.getString("category").isEmpty(), "Category boş olamaz");



    }

    @Test
    void updateProductPositiveTest() {
        int productId = 1;
        ProductRequest request = new ProductRequest();


        request.setTitle("Yeni Ürün2");
        request.setDescription("Test için oluşturuldu2");
        request.setPrice(3000);
        request.setCategory("mekanik");

        Response response = client.updateProduct(productId,request);
        JsonPath jsonPath= response.jsonPath();

        assertNotNull(jsonPath, "Response null olamaz");
        assertNotNull(jsonPath.getString("title"), "Title boş olamaz");
        assertTrue(jsonPath.getFloat("price")> 0, "Price 0’dan büyük olmalı");
        assertNotNull(jsonPath.getString("description"), "Description boş olamaz");
        assertFalse(jsonPath.getString("category").isEmpty(), "Category boş olamaz");
    }
    @Test
    void  deleteProductPositiveTest() {
        int productId = 1;
        Response response = client.deleteProduct(productId);

        JsonPath jsonPath= response.jsonPath();
        assertNotNull(jsonPath, "Response null olamaz");
        assertEquals(response.getStatusCode(),200,"Status code");
        assertNotNull(jsonPath.getString("title"), "Title boş olamaz");
        assertTrue(jsonPath.getFloat("price")> 0, "Price 0’dan büyük olmalı");
        assertNotNull(jsonPath.getString("description"), "Description boş olamaz");
        assertFalse(jsonPath.getString("category").isEmpty(), "Category boş olamaz");
        assertEquals(jsonPath.getString("isDeleted"),"true");
    

   }

    }

