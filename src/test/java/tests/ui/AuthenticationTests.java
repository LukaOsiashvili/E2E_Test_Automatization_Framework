package tests.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.Main;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import tests.BaseTest;
import utils.DriverFactory;

@Epic("Authentication")
@Feature("Test Login, Register, and associated methods")
public class AuthenticationTests extends BaseTest {

    @Test
    public void registerUser(){
        DriverFactory.getDriver().get("https://automationexercise.com");

        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        SignupPage signupPage = new SignupPage(DriverFactory.getDriver());
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(DriverFactory.getDriver());
        AccountDeletePage accountDeletePage = new AccountDeletePage(DriverFactory.getDriver());

        Assert.assertTrue(mainPage.isHomePageLoaded());

        mainPage.getHeader().clickSignupLogin();

        Assert.assertTrue(loginPage.isSignupFormVisible());

        loginPage.registerUser("testName", "testEmailEmail@example.com");

        Assert.assertTrue(signupPage.isSignupFormVisible());

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

        Assert.assertTrue(accountCreatedPage.isTitleBannerVisible());

        accountCreatedPage.clickContinue();

        Assert.assertTrue(mainPage.getHeader().isLoggedIn());

        mainPage.getHeader().clickDeleteAccount();

        Assert.assertTrue(accountDeletePage.isTitleBannerVisible());

        accountDeletePage.clickContinue();
    }
}
