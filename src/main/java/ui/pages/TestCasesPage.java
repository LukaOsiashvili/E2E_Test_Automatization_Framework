package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends BasePage{

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    private final By testCasesSection = By.id("form");

    public boolean isTestCasesPageLoaded(){
        return isVisible(testCasesSection);
    }
}
