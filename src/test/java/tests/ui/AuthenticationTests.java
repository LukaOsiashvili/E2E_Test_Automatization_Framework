package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.pages.*;
import utils.DriverFactory;

@Epic("Authentication")

@Feature("Test Login, Register, and associated methods")
public class AuthenticationTests extends BaseTest {

    @Test(description = "Test Case 1: Full Register and Delete Account Pipeline")
    @Description("Verify that user can register successfully with correct details, will be logged in after registration, and can delete account")
    public void registerUser(){
        DriverFactory.getDriver().get("https://automationexercise.com");

        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        SignupPage signupPage = new SignupPage(DriverFactory.getDriver());
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(DriverFactory.getDriver());
        AccountDeletePage accountDeletePage = new AccountDeletePage(DriverFactory.getDriver());

        Assert.assertTrue(mainPage.isHomePageLoaded(), "Homepage has to be loaded");

        mainPage.getHeader().clickSignupLogin();

        Assert.assertTrue(loginPage.isSignupFormVisible(), "Signup/Login Page has to be loaded and form has to be visible");

        loginPage.registerUser("testName", "testEmailEmail@example.com");

        Assert.assertTrue(signupPage.isSignupFormVisible(), "Signup form has to be visible");

        signupPage.selectTitle("Mr");
        signupPage.enterName("testName");
        signupPage.enterPassword("123456798");
        signupPage.enterDOB("1", "1", "2000");
        signupPage.subscribeAdditionalInfo(true, true);

        signupPage.scrollToAddressInfo();

        signupPage.enterFullName("John", "Doe");
        signupPage.enterCompanyName("Example_Company");
        signupPage.enterAddressInfo("Test St. 1", "Test St. 1", "United States", "Florida", "Miami", "10000");
        signupPage.enterMobileNumber("123456789");

        signupPage.createAccount();

        Assert.assertTrue(accountCreatedPage.isTitleBannerVisible(), "Account created confirmation has to be visible");

        accountCreatedPage.clickContinue();

        Assert.assertTrue(mainPage.getHeader().isLoggedIn(), "User should be logged in after registration and indication has to be visible");

        mainPage.getHeader().clickDeleteAccount();

        Assert.assertTrue(accountDeletePage.isTitleBannerVisible(), "Account delete confirmation has to be visible");

        accountDeletePage.clickContinue();
    }

    @Test(description = "Test Case 2: Login User with Correct Email and Password")
    public void loginUserWithCorrectCredentials(){
        DriverFactory.getDriver().get("https://automationexercise.com");

        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        AccountDeletePage accountDeletePage = new AccountDeletePage(DriverFactory.getDriver());

        Assert.assertTrue(mainPage.isHomePageLoaded(), "Homepage has to be loaded");

        mainPage.getHeader().clickSignupLogin();

        Assert.assertTrue(loginPage.isLoginFormVisible(), "Signup/Login Page has to be loaded and form has to be visible");

        loginPage.enterLoginInfoAndSubmit("correctEmail@example.com", "123456789");

        Assert.assertTrue(mainPage.getHeader().isLoggedIn(), "User should be logged in after registration and indication has to be visible");

        mainPage.getHeader().clickDeleteAccount();

        Assert.assertTrue(accountDeletePage.isTitleBannerVisible(), "Account delete confirmation has to be visible");

        accountDeletePage.clickContinue();
    }

    @Test(description = "Test Case 3: Login User with Incorrect Email and Password")
    public void loginUserWithIncorrectCredentials(){
        DriverFactory.getDriver().get("https://automationexercise.com");

        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        Assert.assertTrue(mainPage.isHomePageLoaded(), "Homepage has to be loaded");

        mainPage.getHeader().clickSignupLogin();

        Assert.assertTrue(loginPage.isLoginFormVisible(), "Signup/Login Page has to be loaded and form has to be visible");

        loginPage.enterLoginInfoAndSubmit("incorrectEmail@example.com", "123456789");

        Assert.assertTrue(loginPage.isIncorrectCredentialsErrorVisible(), "Error regarding using incorrect credentials should be visible");
    }

    @Test(description = "Test Case 4: Login and Logout User")
    public void loginAndLogoutUser(){
        DriverFactory.getDriver().get("https://automationexercise.com");

        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        Assert.assertTrue(mainPage.isHomePageLoaded(), "Homepage has to be loaded");

        mainPage.getHeader().clickSignupLogin();

        Assert.assertTrue(loginPage.isLoginFormVisible(), "Signup/Login Page has to be loaded and form has to be visible");

        loginPage.enterLoginInfoAndSubmit("doNotDeleteEmail@example.com", "123456789");

        Assert.assertTrue(mainPage.getHeader().isLoggedIn(), "User should be logged in after registration and indication has to be visible");

        mainPage.getHeader().clickLogout();

        Assert.assertTrue(loginPage.isLoginFormVisible(), "user has to be navigated to login page");

    }

    @Test(description = "Test Case 5: Try Registering User with Already Used Email")
    public void registerUserWithUsedEmail(){
        DriverFactory.getDriver().get("https://automationexercise.com");

        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        Assert.assertTrue(mainPage.isHomePageLoaded(), "Homepage has to be loaded");

        mainPage.getHeader().clickSignupLogin();

        Assert.assertTrue(loginPage.isSignupFormVisible(), "Signup/Login Page has to be loaded and form has to be visible");

        loginPage.registerUser("name", "doNotDeleteEmail@example.com");

        Assert.assertTrue(loginPage.isAlreadyUsedEmailErrorVisible(), "Error regarding registering using already existing email should be visible");
    }
}
