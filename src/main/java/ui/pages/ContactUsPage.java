package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class ContactUsPage extends BasePage{

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    private final By contactUsPageBanner = By.xpath("//*[@id=\"contact-page\"]/div[1]/div/div/h2");

    private final By contactUsFormBanner = By.xpath("//h2[contains(text(), 'Get In Touch')]");
    private final By nameInput = By.name("name");
    private final By emailInput = By.name("email");
    private final By subjectInput = By.name("subject");
    private final By messageInput = By.id("message");
    private final By fileUploadInput = By.name("upload_file");
    private final By submitButton = By.name("submit");
    private final By successAlertStatus = By.xpath("//div[contains(text(), 'Success! Your details have been submitted successfully.')]");
    private final By backToHomeButton = By.xpath("//a[contains(text(), ' Home')]");

    @Step("Fill and submit contact us form: name={name}, email={email}, subject={subject}")
    public void enterFormInformationAndSubmit(String name, String email, String subject, String message, String filePath){
        typeIn(nameInput, name);
        typeIn(emailInput, email);
        typeIn(subjectInput, subject);
        typeIn(messageInput, message);
        uploadFile(filePath);

        click(submitButton);

        handleAlert();
    }

    @Step("Accept alert after form submission")
    private void handleAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            // Alert might not appear in some cases
            System.out.println("No alert present or already handled");
        }
    }

    @Step("Upload file: {filePath}")
    public void uploadFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filePath);
        }
        driver.findElement(fileUploadInput).sendKeys(file.getAbsolutePath());
    }

    @Step("Navigate to home page from contact us page")
    public void navigateToHome(){
        click(backToHomeButton);
    }

    @Step("Verify form submission was successful")
    public boolean isSuccessful(){
        return isVisible(successAlertStatus);
    }

    @Step("Verify 'Get In Touch' form is visible")
    public boolean isContactUsFormVisible(){
        return isVisible(contactUsFormBanner);
    }
}
