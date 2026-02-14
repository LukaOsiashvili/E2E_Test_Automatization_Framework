package api.client;

import api.models.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.RequestSpecFactory;

import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("API: Create user account with email: {user.email}")

    public Response createUser(User user){
        return given()
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
    }

    @Step("API: Get user details by email: {email}")
    public Response getUserDetailByEmail(String email){
        return given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("email", email)
                .when()
                .get("/getUserDetailByEmail");
    }

    @Step("API: Update user account with email: {user.email}")
    public Response updateUser(User user){
        return given()
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
    }

    @Step("API: Delete user account with email: {email}")
    public Response deleteUser(String email, String password){
        return given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .delete("/deleteAccount");
    }

    @Step("API: Verify login with email: {email}")
    public Response verifyLogin(String email, String password) {
        return given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post("/verifyLogin");
    }

    @Step("API: Verify login without email parameter")
    public Response verifyLoginWithoutEmail(String password) {
        return given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("password", password)
                .when()
                .post("/verifyLogin");
    }
}
