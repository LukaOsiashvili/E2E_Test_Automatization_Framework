package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.pages.*;
import utils.DriverFactory;
import utils.FileUtils;


@Epic("Basic Features Functionality")
@Feature("Verify Contact Us, Product Search, etc. Works as Intended")
public class BasicFeaturesFunctionalityTests extends BaseTest {

    @Test(description = "Test Case 6: Contact Us Form")
    @Description("Verify that user can send contact us form successfully with correct details")
    public void fillInContactUsForm(){

        //LOCAL VARS
        String name = "testName";
        String email = "doNotDeleteEmail@example.com";
        String subject = "test contact us subject line";
        String message = "this is the test message for the message textarea input";
        String filePath = FileUtils.getTestFilePath("test_file.txt");

        DriverFactory.getDriver().get("https://automationexercise.com");

        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        ContactUsPage contactUsPage = new ContactUsPage(DriverFactory.getDriver());

        Assert.assertTrue(mainPage.isHomePageLoaded(), "Homepage should be loaded");

        mainPage.getHeader().clickContactUs();

        Assert.assertTrue(contactUsPage.isContactUsFormVisible(), "GET IN TOUCH has to be visible");

        contactUsPage.enterFormInformationAndSubmit(name, email, subject, message, filePath);

        Assert.assertTrue(contactUsPage.isSuccessful(), "Success Alert Block has to be visible");

        contactUsPage.navigateToHome();
    }

    @Test(description = "Test Case 7: Verify Test Cases Page")
    @Description("Navigate to the tst cases page and verify that it is loaded")
    public void verifyTestPage(){
        DriverFactory.getDriver().get("https://automationexercise.com");

        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        TestCasesPage testCasesPage = new TestCasesPage(DriverFactory.getDriver());

        mainPage.getHeader().clickTestCases();

        Assert.assertTrue(testCasesPage.isTestCasesPageLoaded(), "Test Cases Page has to be loaded");
    }

    @Test(description = "Test Case 8: Verify All Products and product detail page")
    @Description("Navigate to Products page and verify it is visible, then click 'View Product' button and navigate to product details page")
    public void verifyProductsPage(){
        DriverFactory.getDriver().get("https://automationexercise.com");

        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(DriverFactory.getDriver());

        mainPage.getHeader().clickProducts();

        Assert.assertTrue(productsPage.isProductsPageLoaded(), "Products Page should be loaded");
        Assert.assertTrue(productsPage.isProductListVisible(), "Product List has to be visible");

        productsPage.clickFirstProductViewButton();

        Assert.assertTrue(productDetailsPage.isProductDetailsPageLoaded(), "Product Details page should be loaded");
        Assert.assertTrue(productDetailsPage.areAllDetailsVisible(), "Product Details have to be visible");
    }

    @Test(description = "Test Case 9: Search Product")
    @Description("Verify that user can search for products and see relevant results")
    public void testSearchProduct(){
        String searchTerm = "top";

        DriverFactory.getDriver().get("https://automationexercise.com");

        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());

        Assert.assertTrue(mainPage.isHomePageLoaded(), "Home Page should be loaded");

        mainPage.getHeader().clickProducts();

        Assert.assertTrue(productsPage.isProductsPageLoaded(), "All Products page should be loaded");

        productsPage.searchProduct(searchTerm);

        Assert.assertTrue(productsPage.isSearchedProductsBannerVisible());
        Assert.assertTrue(productsPage.isProductListVisible(), "Search result should display products and at least one product should be found");
        Assert.assertTrue(productsPage.doAllProductsContainSearchTerm(searchTerm), "All products should contain the search term: '" + searchTerm + "'");
    }

    @Test(description = "Test Case 10: Verify Subscription on Home Page")
    @Description("Scroll down to footer and subscribe newsletter")
    public void verifySubscriptionOnHomePage(){
        String email = "doNotDeleteEmail@example.com";

        DriverFactory.getDriver().get("https://automationexercise.com");

        MainPage mainPage = new MainPage(DriverFactory.getDriver());

        Assert.assertTrue(mainPage.isHomePageLoaded());
        Assert.assertTrue(mainPage.getFooter().isSubscribeHeaderVisible(), "The SUBSCRIBE header has to be visible");
        mainPage.getFooter().subscribeToNewsletter(email);

        Assert.assertTrue(mainPage.getFooter().isSubscriptionSuccessMessageVisible(), "Success Message should be visible");
        Assert.assertEquals(mainPage.getFooter().getSubscriptionSuccessMessage(), "You have been successfully subscribed!", "The success message should equal the expected value: 'You have been successfully subscribed!'");
    }

    @Test(description = "Test Case 11: Verify Subscription on Cart Page")
    @Description("Scroll down to footer and subscribe newsletter")
    public void verifySubscriptionOnCartPage(){
        String email = "doNotDeleteEmail@example.com";

        DriverFactory.getDriver().get("https://automationexercise.com");

        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        CartPage cartPage = new CartPage(DriverFactory.getDriver());

        Assert.assertTrue(mainPage.isHomePageLoaded());
        mainPage.getHeader().clickCart();

        Assert.assertTrue(cartPage.getFooter().isSubscribeHeaderVisible(), "The SUBSCRIBE header has to be visible");
        cartPage.getFooter().subscribeToNewsletter(email);

        Assert.assertTrue(cartPage.getFooter().isSubscriptionSuccessMessageVisible(), "Success Message should be visible");
        Assert.assertEquals(cartPage.getFooter().getSubscriptionSuccessMessage(), "You have been successfully subscribed!", "The success message should equal the expected value: 'You have been successfully subscribed!'");
    }
}
