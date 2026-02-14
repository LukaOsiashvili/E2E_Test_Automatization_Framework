package ui.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.pages.BasePage;

public class Footer extends BasePage {

    private final By subscriptionHeading = By.xpath("//h2[text()='Subscription']");
    private final By subscriptionEmailField = By.id("susbscribe_email");
    private final By subscribeButton = By.id("subscribe");
    private final By successMessage = By.cssSelector(".alert-success");

    private final By copyrightMessage = By.xpath("//p[contains(text(), 'Copyright © 2021 All rights reserved')]");

    public Footer(WebDriver driver) {
        super(driver);
    }

    @Step("Scroll to footer")
    public void scrollToFooter() {
        action.scrollToElement(driver.findElement(copyrightMessage)).perform();
    }

    @Step("Subscribe to newsletter with email: {email}")
    public void subscribeToNewsletter(String email) {
        scrollToFooter();
        typeIn(subscriptionEmailField, email);
        click(subscribeButton);
    }

    @Step("Get subscription success message")
    public String getSubscriptionSuccessMessage() {
        return getText(successMessage);
    }

    @Step("Verify subscription success message is visible")
    public boolean isSubscriptionSuccessMessageVisible(){
        return isVisible(successMessage);
    }

    @Step("Verify subscription header is visible")
    public boolean isSubscribeHeaderVisible(){
        return isVisible(subscriptionHeading);
    }
}