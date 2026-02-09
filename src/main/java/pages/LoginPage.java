package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    //LOGIN FORM SIDE

    private By loginFormBanner = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/h2");
    private By emailInput = By.name("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]");
    private By passwordInput = By.name("password");
    private By loginButton = By.xpath("//button[contains(text(), 'Login')]");

    //REGISTER FORM SIDE
    private By registerFormBanner = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/h2");
    private By registerNameInput = By.name("name");
    private By registerEmailInput = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]");
    private By registerButton = By.xpath("//button[contains(text(), 'Signup')]");

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

    public boolean isSignupFormVisible() {
        return isVisible(registerFormBanner);
    }

    public String getLoginFormBanner() {
        return getText(loginFormBanner);
    }

    public String getRegisterFormBanner(){
        return getText(registerFormBanner);
    }
}
