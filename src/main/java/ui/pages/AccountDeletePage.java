package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletePage extends BasePage{
    public AccountDeletePage(WebDriver driver) {
        super(driver);
    }

    private final By titleBanner = By.xpath("//*[@id=\"form\"]/div/div/div/h2/b");
    private final By accountDeletedParagraph = By.xpath("//*[@id=\"form\"]/div/div/div/p[1]");

    private final By infoParagraph = By.xpath("//*[@id=\"form\"]/div/div/div/p[2]");
    private final By continueButton = By.xpath("//a[contains(text(), 'Continue')]");

    @Step("Click 'Continue' button on account deleted page")
    public void clickContinue(){
        click(continueButton);
    }

    @Step("Verify account deleted title banner is visible")
    public boolean isTitleBannerVisible(){
        return isVisible(titleBanner);
    }
}
