package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class Footer extends BasePage {

    private final By subscriptionHeading = By.xpath("//h2[text()='Subscription']");
    private final By subscriptionEmailField = By.id("susbscribe_email");
    private final By subscribeButton = By.id("subscribe");
    private final By successMessage = By.cssSelector(".alert-success");

    public Footer(WebDriver driver) {
        super(driver);
    }

    public void scrollToFooter() {
        action.scrollToElement(driver.findElement(subscriptionHeading)).perform();
    }

    public void subscribeToNewsletter(String email) {
        typeIn(subscriptionEmailField, email);
        click(subscribeButton);
    }

    public String getSubscriptionSuccessMessage() {
        return getText(successMessage);
    }
}