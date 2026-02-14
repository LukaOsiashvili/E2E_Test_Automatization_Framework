package ui.pages;

import io.qameta.allure.Step;
import ui.components.Header;
import ui.components.Footer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{

    protected Header header;
    protected Footer footer;

    private final By carouselSlider = By.id("slider-carousel");

    private final By featuredItemsHeading = By.xpath("//h2[text()='Features Items']");

    public MainPage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);
        this.footer = new Footer(driver);
    }

    @Step("Get header component")
    public Header getHeader(){
        return header;
    }

    @Step("Get footer component")
    public Footer getFooter(){
        return footer;
    }

    @Step("Verify home page is loaded")
    public boolean isHomePageLoaded(){
        return isVisible(carouselSlider) && isVisible(featuredItemsHeading);
    }

}
