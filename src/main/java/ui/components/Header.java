package ui.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.pages.BasePage;

public class Header extends BasePage {

    // Header locators
    private final By logo = By.cssSelector(".logo");
    private final By homeLink = By.xpath("//a[text()=' Home']");
    private final By productsLink = By.xpath("//a[text()=' Products']");
    private final By cartLink = By.xpath("//a[text()=' Cart']");
    private final By signupLoginLink = By.xpath("//a[text()=' Signup / Login']");
    private final By testCasesLink = By.xpath("//a[text()=' Test Cases']");
    private final By contactUsLink = By.xpath("//a[text()=' Contact us']");
    private final By logoutLink = By.xpath("//a[text()=' Logout']");
    private final By deleteAccountLink = By.xpath("//a[text()=' Delete Account']");
    private final By loggedInAsText = By.xpath("//a[contains(text(), 'Logged in as')]");

    public Header(WebDriver driver) {
        super(driver);
    }

    @Step("Click 'Home' link in header")
    public void clickHome() {
        click(homeLink);
    }

    @Step("Click 'Products' link in header")
    public void clickProducts() {
        click(productsLink);
    }

    @Step("Click 'Cart' link in header")
    public void clickCart() {
        click(cartLink);
    }

    @Step("Click 'Test Cases' link in header")
    public void clickTestCases(){
        click(testCasesLink);
    }

    @Step("Click 'Signup / Login' link in header")
    public void clickSignupLogin() {
        click(signupLoginLink);
    }

    @Step("Click 'Contact us' link in header")
    public void clickContactUs(){
        click(contactUsLink);
    }

    @Step("Click 'Logout' link in header")
    public void clickLogout() {
        click(logoutLink);
    }

    @Step("Click 'Delete Account' link in header")
    public void clickDeleteAccount() {
        click(deleteAccountLink);
    }

    @Step("Get logged in username")
    public String getLoggedInUsername() {
        return getText(loggedInAsText);
    }

    @Step("Verify user is logged in")
    public boolean isLoggedIn() {
        try {
            return getText(loggedInAsText).contains("Logged in as");
        } catch (Exception e) {
            return false;
        }
    }
}