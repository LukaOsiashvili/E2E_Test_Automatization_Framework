package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage{
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    private final By productName = By.xpath("//div[@class='product-information']/h2");
    private final By productCategory = By.xpath("//div[@class='product-information']/p[contains(., 'Category:')]");
    private final By productPrice = By.xpath("//div[@class='product-information']/span/span");
    private final By productAvailability = By.xpath("//div[@class='product-information']/p[contains(., 'Availability:')]");
    private final By productCondition = By.xpath("//div[@class='product-information']/p[contains(., 'Condition:')]");
    private final By productBrand = By.xpath("//div[@class='product-information']/p[contains(., 'Brand:')]");
    private final By productImage = By.cssSelector(".view-product img");
    private final By quantityInput = By.id("quantity");
    private final By addToCartButton = By.cssSelector("button.cart");

    public boolean isProductDetailsPageLoaded(){
        return isVisible(productName) && isVisible(productPrice);
    }

    @Step("Get product name")
    public String getProductName() {
        return getText(productName);
    }

    @Step("Verify product name is visible")
    public boolean isProductNameVisible() {
        return isVisible(productName);
    }

    @Step("Get product category")
    public String getProductCategory() {
        return getTextAfter(productCategory, ":");
    }

    @Step("Verify product category is visible")
    public boolean isProductCategoryVisible() {
        return isVisible(productCategory);
    }

    @Step("Get product price")
    public String getProductPrice() {
        return getTextAfter(productPrice, ":");
    }

    @Step("Verify product price is visible")
    public boolean isProductPriceVisible() {
        return isVisible(productPrice);
    }

    @Step("Get product availability")
    public String getProductAvailability() {
        return getTextAfter(productAvailability, ":");
    }

    @Step("Verify product availability is visible")
    public boolean isProductAvailabilityVisible() {
        return isVisible(productAvailability);
    }

    @Step("Get product condition")
    public String getProductCondition() {
        return getTextAfter(productCondition, ":");
    }

    @Step("Verify product condition is visible")
    public boolean isProductConditionVisible() {
        return isVisible(productCondition);
    }

    @Step("Get product brand")
    public String getProductBrand() {
        return getTextAfter(productBrand, ":");
    }

    @Step("Verify product brand is visible")
    public boolean isProductBrandVisible() {
        return isVisible(productBrand);
    }

    @Step("Verify all product details are visible")
    public boolean areAllDetailsVisible() {
        return isProductNameVisible() &&
                isProductCategoryVisible() &&
                isProductPriceVisible() &&
                isProductAvailabilityVisible() &&
                isProductConditionVisible() &&
                isProductBrandVisible();
    }

    @Step("Get all product details as formatted string")
    public String getAllProductDetails() {
        return String.format(
                "Product Name: %s\nCategory: %s\nPrice: %s\nAvailability: %s\nCondition: %s\nBrand: %s",
                getProductName(),
                getProductCategory(),
                getProductPrice(),
                getProductAvailability(),
                getProductCondition(),
                getProductBrand()
        );
    }

    @Step("Set product quantity to {quantity}")
    public void setQuantity(String quantity) {
        driver.findElement(quantityInput).clear();
        typeIn(quantityInput, quantity);
    }

    @Step("Click 'Add to Cart' button")
    public void addToCart() {
        click(addToCartButton);
    }
}
