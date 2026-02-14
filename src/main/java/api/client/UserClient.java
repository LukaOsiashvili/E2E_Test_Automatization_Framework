package api.client;

import api.models.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.RequestSpecFactory;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("API: Create user account with email: {user.email}")

    public Response createUser(User user){
        attachRequestDetails("POST", "/createAccount", userToParamsMap(user));

        Response response = given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("name", user.getName())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .formParam("title", user.getTitle())
                .formParam("birth_date", user.getBirth_date())
                .formParam("birth_month", user.getBirth_month())
                .formParam("birth_year", user.getBirth_year())
                .formParam("firstname", user.getFirstname())
                .formParam("lastname", user.getLastname())
                .formParam("company", user.getCompany())
                .formParam("address1", user.getAddress1())
                .formParam("address2", user.getAddress2())
                .formParam("country", user.getCountry())
                .formParam("zipcode", user.getZipcode())
                .formParam("state", user.getState())
                .formParam("city", user.getCity())
                .formParam("mobile_number", user.getMobile_number())
                .when()
                .post("/createAccount");

        attachResponseDetails(response);
        return response;
    }

    @Step("API: Get user details by email: {email}")
    public Response getUserDetailByEmail(String email){
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        attachRequestDetails("GET", "/getUserDetailByEmail", params);

        Response response = given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("email", email)
                .when()
                .get("/getUserDetailByEmail");

        attachResponseDetails(response);
        return response;
    }

    @Step("API: Update user account with email: {user.email}")
    public Response updateUser(User user){
        attachRequestDetails("PUT", "/updateAccount", userToParamsMap(user));

        Response response = given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("name", user.getName())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .formParam("title", user.getTitle())
                .formParam("birth_date", user.getBirth_date())
                .formParam("birth_month", user.getBirth_month())
                .formParam("birth_year", user.getBirth_year())
                .formParam("firstname", user.getFirstname())
                .formParam("lastname", user.getLastname())
                .formParam("company", user.getCompany())
                .formParam("address1", user.getAddress1())
                .formParam("address2", user.getAddress2())
                .formParam("country", user.getCountry())
                .formParam("zipcode", user.getZipcode())
                .formParam("state", user.getState())
                .formParam("city", user.getCity())
                .formParam("mobile_number", user.getMobile_number())
                .when()
                .put("/updateAccount");

        attachResponseDetails(response);
        return response;
    }

    @Step("API: Delete user account with email: {email}")
    public Response deleteUser(String email, String password){
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        attachRequestDetails("DELETE", "/deleteAccount", params);

        Response response = given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .delete("/deleteAccount");

        attachResponseDetails(response);
        return response;
    }

    @Step("API: Verify login with email: {email}")
    public Response verifyLogin(String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        attachRequestDetails("POST", "/verifyLogin", params);

        Response response = given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post("/verifyLogin");

        attachResponseDetails(response);
        return response;
    }

    @Step("API: Verify login without email parameter")
    public Response verifyLoginWithoutEmail(String password) {
        Map<String, String> params = new HashMap<>();
        params.put("password", password);
        attachRequestDetails("POST", "/verifyLogin", params);

        Response response = given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("password", password)
                .when()
                .post("/verifyLogin");

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

    private Map<String, String> userToParamsMap(User user) {
        Map<String, String> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("email", user.getEmail());
        params.put("password", user.getPassword()); // Don't log passwords
        params.put("title", user.getTitle());
        params.put("birth_date", user.getBirth_date());
        params.put("birth_month", user.getBirth_month());
        params.put("birth_year", user.getBirth_year());
        params.put("firstname", user.getFirstname());
        params.put("lastname", user.getLastname());
        params.put("company", user.getCompany());
        params.put("address1", user.getAddress1());
        params.put("address2", user.getAddress2());
        params.put("country", user.getCountry());
        params.put("zipcode", user.getZipcode());
        params.put("state", user.getState());
        params.put("city", user.getCity());
        params.put("mobile_number", user.getMobile_number());
        return params;
    }
}
