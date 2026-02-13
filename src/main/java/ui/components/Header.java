package ui.components;

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

    public void clickHome() {
        click(homeLink);
    }

    public void clickProducts() {
        click(productsLink);
    }

    public void clickCart() {
        click(cartLink);
    }

    public void clickTestCases(){
        click(testCasesLink);
    }

    public void clickSignupLogin() {
        click(signupLoginLink);
    }

    public void clickContactUs(){
        click(contactUsLink);
    }
    public void clickLogout() {
        click(logoutLink);
    }

    public void clickDeleteAccount() {
        click(deleteAccountLink);
    }

    public String getLoggedInUsername() {
        return getText(loggedInAsText);
    }

    public boolean isLoggedIn() {
        try {
            return getText(loggedInAsText).contains("Logged in as");
        } catch (Exception e) {
            return false;
        }
    }
}