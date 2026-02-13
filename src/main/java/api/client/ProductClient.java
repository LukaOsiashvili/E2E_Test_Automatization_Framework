package api.client;

import io.restassured.response.Response;
import utils.RequestSpecFactory;

import static io.restassured.RestAssured.given;

public class ProductClient {

    public Response getAllProducts(){
        return given()
                .spec(RequestSpecFactory.formSpec())

                .when()
                .get("/productsList");
    }

    public Response postToProductsList(){
        return given()
                .spec(RequestSpecFactory.formSpec())
                .when()
                .post("/productsList");
    }

    public Response searchProduct(String searchTerm){
        return given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("search_product", searchTerm)
                .when()
                .post("/searchProduct");
    }

    public Response searchProductWithoutParam(){
        return given()
                .spec(RequestSpecFactory.formSpec())
                .when()
                .post("/searchProduct");
    }
}
