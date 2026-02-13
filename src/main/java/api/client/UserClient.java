package api.client;

import api.models.User;
import io.restassured.response.Response;
import utils.RequestSpecFactory;

import static io.restassured.RestAssured.given;

public class UserClient {
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

    public Response getUserDetailByEmail(String email){
        return given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("email", email)
                .when()
                .get("/getUserDetailByEmail");
    }

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

    public Response deleteUser(String email, String password){
        return given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .delete("/deleteAccount");
    }

    public Response verifyLogin(String email, String password) {
        return given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post("/verifyLogin");
    }

    public Response verifyLoginWithoutEmail(String password) {
        return given()
                .spec(RequestSpecFactory.formSpec())
                .formParam("password", password)
                .when()
                .post("/verifyLogin");
    }
}
