package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage{

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    private final By signupFormBanner = By.xpath("//*[@id=\"form\"]/div/div/div/div/h2/b");

    private final By titleMrRadio = By.id("id_gender1");
    private final By titleMrsRadio = By.id("id_gender2");

    private final By nameInput = By.id("name");
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By dayDropdown = By.id("days");
    private final By monthDropdown = By.id("months");
    private final By yearDropdown = By.id("years");
    private final By newsletterCheckbox = By.id("newsletter");
    private final By offersCheckbox = By.id("optin");
    private final By addressInfoBanner = By.xpath("//*[@id=\"form\"]/div/div/div/div/form/h2/b");
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By companyNameInput = By.id("company");
    private final By addressPrimaryInput = By.id("address1");
    private final By addressSecondaryInput = By.id("address2");
    private final By countryDropdown = By.id("country");
    private final By stateInput = By.id("state");
    private final By cityInput = By.id("city");
    private final By zipcodeInput = By.id("zipcode");
    private final By mobileNumberInput = By.id("mobile_number");
    private final By createAccountButton = By.xpath("//button[contains(text(), 'Create Account')]");


    public void selectTitle(String title){
        if(title.equals("Mr")){
            click(titleMrRadio);
        } else {
            click(titleMrsRadio);
        }
    }

    public void enterName(String name){
        typeIn(nameInput, name);
    }

    public void enterPassword(String password){
        typeIn(passwordInput, password);
    }

    public void enterDOB(String day, String month, String year){
        selectByValue(dayDropdown, day);
        selectByValue(monthDropdown, month);
        selectByValue(yearDropdown, year);
    }

    public void subscribeAdditionalInfo(boolean newsletter, boolean offers){
        if(newsletter){
            click(newsletterCheckbox);
        }

        if(offers){
            click(offersCheckbox);
        }
    }

    public void enterFullName(String firstName, String lastName){
        typeIn(firstNameInput, firstName);
        typeIn(lastNameInput, lastName);
    }

    public void enterCompanyName(String companyName){
        typeIn(companyNameInput, companyName);
    }

    public void enterAddressInfo(String addressPrimary, String addressSecondary, String country, String state, String city, String zipcode){
        typeIn(addressPrimaryInput, addressPrimary);
        if(addressSecondary != null) typeIn(addressSecondaryInput, addressSecondary);
        selectByValue(countryDropdown, country);
        typeIn(stateInput, state);
        typeIn(cityInput, city);
        typeIn(zipcodeInput, zipcode);
    }

    public void enterMobileNumber(String mobileNumber){
        typeIn(mobileNumberInput, mobileNumber);
    }

    public void createAccount(){
        click(createAccountButton);
    }

    public boolean isSignupFormVisible(){
        return isVisible(signupFormBanner);
    }

    public void scrollToAddressInfo(){
        action.scrollToElement(driver.findElement(addressInfoBanner)).perform();
    }
}
