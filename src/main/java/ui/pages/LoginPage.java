package ui.pages;

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

    public void enterLoginInfoAndSubmit(String email, String password){
        typeIn(emailInput, email);
        typeIn(passwordInput, password);

        click(loginButton);
    }

    public void registerUser(String name, String email){
        typeIn(registerNameInput, name);
        typeIn(registerEmailInput, email);

        click(registerButton);
    }

    public boolean isLoginFormVisible() {
        return isVisible(loginFormBanner);
    }

    public boolean isIncorrectCredentialsErrorVisible(){
        return isVisible(incorrectCredentialsError);
    }

    public boolean isSignupFormVisible() {
        return isVisible(registerFormBanner);
    }
    public boolean isAlreadyUsedEmailErrorVisible(){
        return isVisible(alreadyUsedEmailError);
    }

    public String getLoginFormBanner() {
        return getText(loginFormBanner);
    }

    public String getRegisterFormBanner(){
        return getText(registerFormBanner);
    }
}
