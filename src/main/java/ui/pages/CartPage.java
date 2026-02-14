package ui.pages;

import io.qameta.allure.Step;
import ui.components.Header;
import ui.components.Footer;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{
    protected Header header;
    protected Footer footer;

    public CartPage(WebDriver driver) {

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
}
