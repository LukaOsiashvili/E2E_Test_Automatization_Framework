package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    //LOGIN FORM SIDE

    private final By loginFormBanner = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/h2");
    private final By emailInput = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.xpath("//button[contains(text(), 'Login')]");
    private final By incorrectCredentialsError = By.xpath("//p[contains(text(), 'Your email or password is incorrect!')]");

    //REGISTER FORM SIDE
    private final By registerFormBanner = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/h2");
    private final By registerNameInput = By.name("name");
    private final By registerEmailInput = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]");
    private final By registerButton = By.xpath("//button[contains(text(), 'Signup')]");
    private final By alreadyUsedEmailError = By.xpath("//p[contains(text(), 'Email Address already exist!')]");

    @Step("Enter login credentials and submit: email={email}")
    public void enterLoginInfoAndSubmit(String email, String password){
        typeIn(emailInput, email);
        typeIn(passwordInput, password);

        click(loginButton);
    }

    @Step("Register new user: name={name}, email={email}")
    public void registerUser(String name, String email){
        typeIn(registerNameInput, name);
        typeIn(registerEmailInput, email);

        click(registerButton);
    }

    @Step("Verify login form is visible")
    public boolean isLoginFormVisible() {
        return isVisible(loginFormBanner);
    }

    @Step("Verify incorrect credentials error is visible")
    public boolean isIncorrectCredentialsErrorVisible(){
        return isVisible(incorrectCredentialsError);
    }

    @Step("Verify signup form is visible")
    public boolean isSignupFormVisible() {
        return isVisible(registerFormBanner);
    }

    @Step("Verify 'email already exists' error is visible")
    public boolean isAlreadyUsedEmailErrorVisible(){
        return isVisible(alreadyUsedEmailError);
    }

}
