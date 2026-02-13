package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage{

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    private final By titleBanner = By.xpath("//*[@id=\"form\"]/div/div/div/h2/b");

    private final By congratulationsParagraph = By.xpath("//*[@id=\"form\"]/div/div/div/p[1]");
    private final By infoParagraph = By.xpath("//*[@id=\"form\"]/div/div/div/p[2]");
    private final By continueButton = By.xpath("//a[contains(text(), 'Continue')]");

    public void clickContinue(){
        click(continueButton);
    }

    public boolean isTitleBannerVisible(){
        return isVisible(titleBanner);
    }
}
