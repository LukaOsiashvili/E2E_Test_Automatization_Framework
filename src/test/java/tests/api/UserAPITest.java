package tests.api;

import api.client.UserClient;
import api.models.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestDataFactory;

import static org.hamcrest.Matchers.equalTo;

@Epic("API Testing")
@Feature("User Account API")
public class UserAPITest {

    private final UserClient userClient = new UserClient();

//    @Test(priority = 0, description = "DEBUG - See raw response")
//    public void debugCreateUser() {
//        User user = TestDataFactory.createDefaultUser();
//
//        Response response = userClient.createUser(user);
//
//        System.out.println("Status Code: " + response.getStatusCode());
//        System.out.println("Content-Type: " + response.getContentType());
//        System.out.println("Raw Body: [" + response.getBody().asString() + "]");
//
//        userClient.deleteUser(user.getEmail(), user.getPassword());
//    }

    @Test(priority = 1, description = "API Test Case 1: POST to Create/Register User Account")
    @Description("POST to /createAccount with all fields. It should return 201. Cleanup in the end")
    public void testCreateUser(){
        User user = TestDataFactory.createDefaultUser();

        Response response = userClient.createUser(user);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 201);
        Assert.assertEquals(response.jsonPath().getString("message"), "User created!");

        Response DELETE_Response = userClient.deleteUser(user.getEmail(), user.getPassword());

        Assert.assertEquals(DELETE_Response.getStatusCode(), 200);
        Assert.assertEquals(DELETE_Response.jsonPath().getInt("responseCode"), 200);
        Assert.assertEquals(DELETE_Response.jsonPath().getString("message"), "Account deleted!");
    }

    @Test(priority = 2, description = "API Test Case 2: GET User Account Details by Email")
    @Description("Create User, GET details by email, verify the recived data, Cleanup in the End")
    public void testGetUserDetailsByEmail(){
        User user = TestDataFactory.createDefaultUser();
        Response POST_Response = userClient.createUser(user);

        Assert.assertEquals(POST_Response.jsonPath().getInt("responseCode"), 201);

        Response GET_Response = userClient.getUserDetailByEmail(user.getEmail());

        Assert.assertEquals(GET_Response.getStatusCode(), 200);
        Assert.assertEquals(GET_Response.jsonPath().getInt("responseCode"), 200);
        Assert.assertEquals(GET_Response.jsonPath().getString("user.name"), user.getName());
        Assert.assertEquals(GET_Response.jsonPath().getString("user.email"), user.getEmail());
        Assert.assertEquals(GET_Response.jsonPath().getString("user.first_name"), user.getFirstname());
        Assert.assertEquals(GET_Response.jsonPath().getString("user.last_name"), user.getLastname());
        Assert.assertEquals(GET_Response.jsonPath().getString("user.company"), user.getCompany());
        Assert.assertEquals(GET_Response.jsonPath().getString("user.address1"), user.getAddress1());
        Assert.assertEquals(GET_Response.jsonPath().getString("user.address2"), user.getAddress2());
        Assert.assertEquals(GET_Response.jsonPath().getString("user.country"), user.getCountry());
        Assert.assertEquals(GET_Response.jsonPath().getString("user.state"), user.getState());
        Assert.assertEquals(GET_Response.jsonPath().getString("user.city"), user.getCity());
        Assert.assertEquals(GET_Response.jsonPath().getString("user.zipcode"), user.getZipcode());

        Response DELETE_Response = userClient.deleteUser(user.getEmail(), user.getPassword());

        Assert.assertEquals(DELETE_Response.getStatusCode(), 200);
        Assert.assertEquals(DELETE_Response.jsonPath().getInt("responseCode"), 200);
        Assert.assertEquals(DELETE_Response.jsonPath().getString("message"), "Account deleted!");
    }

    @Test(priority = 3, description = "API Test Case 3: PUT to Update User Account")
    @Description("Create user, update using PUT, verify changes, cleanup")
    public void testUpdateUser(){
        User user = TestDataFactory.createDefaultUser();
        User updatedUser = TestDataFactory.createUpdatedUser(user);

        Response POST_Response = userClient.createUser(user);

        Assert.assertEquals(POST_Response.jsonPath().getInt("responseCode"), 201);

        Response PUT_Response = userClient.updateUser(updatedUser);

        Assert.assertEquals(PUT_Response.getStatusCode(), 200);
        Assert.assertEquals(PUT_Response.jsonPath().getInt("responseCode"), 200);
        Assert.assertEquals(PUT_Response.jsonPath().getString("message"), "User updated!");

        Response GET_Response = userClient.getUserDetailByEmail(user.getEmail());

        Assert.assertEquals(GET_Response.jsonPath().getInt("responseCode"), 200);
        Assert.assertEquals(GET_Response.jsonPath().getString("user.last_name"), "Smith");
        Assert.assertEquals(GET_Response.jsonPath().getString("user.company"), "UpdatedCorp LLC");
        Assert.assertEquals(GET_Response.jsonPath().getString("user.country"), "Canada");
        Assert.assertEquals(GET_Response.jsonPath().getString("user.city"), "Toronto");

        Response DELETE_Response = userClient.deleteUser(user.getEmail(), user.getPassword());

        Assert.assertEquals(DELETE_Response.getStatusCode(), 200);
        Assert.assertEquals(DELETE_Response.jsonPath().getInt("responseCode"), 200);
        Assert.assertEquals(DELETE_Response.jsonPath().getString("message"), "Account deleted!");
    }

    @Test(priority = 4, description = "API Test Case 4: DELETE To Delete User Account")
    @Description("Create user, then DELETE the account, expect 200")
    public void testDeleteUser(){
        User user = TestDataFactory.createDefaultUser();

        Response POST_Response = userClient.createUser(user);

        Assert.assertEquals(POST_Response.jsonPath().getInt("responseCode"), 201);

        Response DELETE_Response = userClient.deleteUser(user.getEmail(), user.getPassword());

        Assert.assertEquals(DELETE_Response.getStatusCode(), 200);
        Assert.assertEquals(DELETE_Response.jsonPath().getInt("responseCode"), 200);
        Assert.assertEquals(DELETE_Response.jsonPath().getString("message"), "Account deleted!");
    }

    @Test(priority = 5, description = "API Test Case 5: POST To Verify Login with valid details")
    @Description("Create user, verify login with valid credentials, cleanup")
    public void testVerifyLoginWithValidCredentials(){
        User user = TestDataFactory.createDefaultUser();

        Response POST_Response = userClient.createUser(user);

        Assert.assertEquals(POST_Response.jsonPath().getInt("responseCode"), 201);

        Response VerifyPOST_Response = userClient.verifyLogin(user.getEmail(), user.getPassword());

        Assert.assertEquals(VerifyPOST_Response.getStatusCode(), 200);
        Assert.assertEquals(VerifyPOST_Response.jsonPath().getInt("responseCode"), 200);
        Assert.assertEquals(VerifyPOST_Response.jsonPath().getString("message"), "User exists!");

        Response DELETE_Response = userClient.deleteUser(user.getEmail(), user.getPassword());

        Assert.assertEquals(DELETE_Response.getStatusCode(), 200);
        Assert.assertEquals(DELETE_Response.jsonPath().getInt("responseCode"), 200);
        Assert.assertEquals(DELETE_Response.jsonPath().getString("message"), "Account deleted!");

    }

    @Test(priority = 6, description = "API Test Case 6: POST To Verify Login without email parameter")
    @Description("POST to verifyLogin with only password, expect 400")
    public void testVerifyLoginWithoutEmail() {
        Response POST_Response = userClient.verifyLoginWithoutEmail("somePassword123");

        Assert.assertEquals(POST_Response.getStatusCode(), 200);
        Assert.assertEquals(POST_Response.jsonPath().getInt("responseCode"), 400);
        Assert.assertEquals(POST_Response.jsonPath().getString("message"),
                "Bad request, email or password parameter is missing in POST request.");
    }
}
