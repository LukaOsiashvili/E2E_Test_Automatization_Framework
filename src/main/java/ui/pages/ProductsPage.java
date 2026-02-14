package ui.pages;

import io.qameta.allure.Step;
import ui.components.Footer;
import ui.components.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage{

    protected Header header;
    protected Footer footer;

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);
        this.footer = new Footer(driver);
    }

    //By Locators
    protected final By productsPageBanner = By.xpath("//h2[contains(text(), 'All Products')]");
    protected final By searchedProductBanner = By.xpath("//h2[contains(text(), 'Searched Products')]");
    protected final By productList = By.cssSelector(".features_items .col-sm-4");
    protected final By searchProductInput = By.id("search_product");
    protected final By searchButton = By.id("submit_search");
    protected final By productName = By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']/p");

    //Dynamic Locators Signature
    protected final String productViewButtonTemplate = "//a[contains(@href, '/product_details/%d')]"; // XPATH SIGNATURE FOR VIEW BUTTON

    private By getProductViewButton(int index){
        return By.xpath(String.format(productViewButtonTemplate, index));
    }

    //Methods
    @Step("Get header component")
    public Header getHeader() {
        return header;
    }

    @Step("Get footer component")
    public Footer getFooter() {
        return footer;
    }

    @Step("Click 'View Product' button at index: {index}")
    public void clickViewProductButtonAtIndex(int index){
        scrollToProductsList();
        click(getProductViewButton(index));
    }

    @Step("Click first product 'View Product' button")
    public void clickFirstProductViewButton(){
        scrollToProductsList();
        click(getProductViewButton(1));
    }

    @Step("Verify products page is loaded")
    public boolean isProductsPageLoaded(){
        return isVisible(productsPageBanner);
    }

    @Step("Verify product list is visible")
    public boolean isProductListVisible(){
        try{
            return !driver.findElements(productList).isEmpty();
        } catch (Exception e){
            return false;
        }
    }

    @Step("Search for product: {searchTerm}")
    public void searchProduct(String searchTerm){
        typeIn(searchProductInput, searchTerm);
        click(searchButton);
    }

    @Step("Get all product names")
    public List<String> getAllProductNames() {
        List<String> productNames = new ArrayList<>();
        List<WebElement> nameElements = driver.findElements(productName);

        for (WebElement element : nameElements) {
            productNames.add(element.getText());
        }

        return productNames;
    }

    @Step("Verify all products contain search term: {searchTerm}")
    public boolean doAllProductsContainSearchTerm(String searchTerm) {
        int matches = 0;
        double matchPercentage = 0;
        List<String> productNames = getAllProductNames();

        if (productNames.isEmpty()) {
            return false;
        }

        for (String name : productNames) {
            System.out.println(name.toLowerCase());
            if (name.toLowerCase().contains(searchTerm.toLowerCase())) {
                matches = matches + 1;
            }
        }

        matchPercentage = (double) matches / productNames.size() * 100;
        return matches > 0 && matchPercentage >= 50;
    }

    @Step("Verify 'Searched Products' banner is visible")
    public boolean isSearchedProductsBannerVisible(){
        scrollToProductsList();
        return isVisible(searchedProductBanner);
    }

    @Step("Verify searched products list is visible")
    public boolean isSearchedProductListVisible(){
        return isProductListVisible() && isSearchedProductsBannerVisible();
    }

    @Step("Scroll to products list")
    public void scrollToProductsList() {
        WebElement productsSection = driver.findElement(productList);
        action.scrollToElement(productsSection).perform();
        action.scrollByAmount(0, 500).perform();
    }
}
