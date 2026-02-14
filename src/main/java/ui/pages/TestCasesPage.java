package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends BasePage{
    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    private final By testCasesSection = By.id("form");

    @Step("Verify test cases page is loaded")
    public boolean isTestCasesPageLoaded(){
        return isVisible(testCasesSection);
    }
}
