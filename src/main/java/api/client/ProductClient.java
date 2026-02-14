package api.client;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.RequestSpecFactory;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProductClient {

    @Step("API: Get all products list")
    public Response getAllProducts(){
        attachRequestDetails("GET", "/productsList", null);

        Response response = given()
                .spec(RequestSpecFactory.formSpec())
                .when()
                .get("/productsList");

        attachResponseDetails(response);

        return response;
    }

    @Step("API: POST to products list (expect 405 error)")
    public Response postToProductsList(){
        attachRequestDetails("POST", "/productsList", null);

        Response response = given()
                .spec(RequestSpecFactory.formSpec())
                .when()
                .post("/productsList");

        attachResponseDetails(response);
        return response;
    }

    @Step("API: Search for product with term: {searchTerm}")
    public Response searchProduct(String searchTerm){
        Map<String, String> params = new HashMap<>();
        params.put("search_product", searchTerm);

        attachRequestDetails("POST", "/searchProduct", params);

        Response response = given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("search_product", searchTerm)
                .when()
                .post("/searchProduct");

        attachResponseDetails(response);
        return response;
    }

    @Step("API: Search product without search parameter (expect 400 error)")
    public Response searchProductWithoutParam(){
        attachRequestDetails("POST", "/searchProduct", null);

        Response response = given()
                .spec(RequestSpecFactory.formSpec())
                .when()
                .post("/searchProduct");

        attachResponseDetails(response);
        return response;
    }

    private void attachRequestDetails(String method, String endpoint, Map<String, String> formParams) {
        StringBuilder requestInfo = new StringBuilder();
        requestInfo.append("Method: ").append(method).append("\n");
        requestInfo.append("Endpoint: ").append(endpoint).append("\n");
        requestInfo.append("Base URL: https://automationexercise.com/api\n");
        requestInfo.append("Content-Type: application/x-www-form-urlencoded; charset=UTF-8\n");

        if (formParams != null && !formParams.isEmpty()) {
            requestInfo.append("\n=== Form Parameters ===\n");
            formParams.forEach((key, value) ->
                    requestInfo.append(key).append(" = ").append(value).append("\n")
            );
        } else {
            requestInfo.append("\nNo parameters\n");
        }

        Allure.addAttachment("API Request", "text/plain", requestInfo.toString());
    }
    private void attachResponseDetails(Response response) {
        StringBuilder responseInfo = new StringBuilder();
        responseInfo.append("Status Code: ").append(response.getStatusCode()).append("\n");
        responseInfo.append("Response Time: ").append(response.getTime()).append(" ms\n");
        responseInfo.append("Content-Type: ").append(response.getContentType()).append("\n");
        responseInfo.append("\n=== Response Headers ===\n");
        response.getHeaders().forEach(header ->
                responseInfo.append(header.getName()).append(": ").append(header.getValue()).append("\n")
        );

        responseInfo.append("\n=== Response Body ===\n");

        try {
            String body = response.getBody().asPrettyString();
            responseInfo.append(body);
            Allure.addAttachment("API Response", "application/json", body);
        } catch (Exception e) {
            String body = response.getBody().asString();
            responseInfo.append(body);
            Allure.addAttachment("API Response", "text/plain", responseInfo.toString());
        }
    }
}
