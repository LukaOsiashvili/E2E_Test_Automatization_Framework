package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.RequestSpecFactory;

import static io.restassured.RestAssured.given;

public class ProductClient {

    @Step("API: Get all products list")
    public Response getAllProducts(){
        return given()
                .spec(RequestSpecFactory.formSpec())

                .when()
                .get("/productsList");
    }

    @Step("API: POST to products list (expect 405 error)")
    public Response postToProductsList(){
        return given()
                .spec(RequestSpecFactory.formSpec())
                .when()
                .post("/productsList");
    }

    @Step("API: Search for product with term: {searchTerm}")
    public Response searchProduct(String searchTerm){
        return given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("search_product", searchTerm)
                .when()
                .post("/searchProduct");
    }

    @Step("API: Search product without search parameter (expect 400 error)")
    public Response searchProductWithoutParam(){
        return given()
                .spec(RequestSpecFactory.formSpec())
                .when()
                .post("/searchProduct");
    }
}
