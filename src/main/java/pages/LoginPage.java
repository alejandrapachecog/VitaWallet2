package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class LoginPage extends BasePage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public LoginPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ⬅️ Email
    private By inputEmail = By.xpath("//label[contains(text(),'Correo electrónico')]/following::input[1]");

    // ⬅️ Password
    private By inputPassword = By.xpath("//label[contains(text(),'Contraseña')]/following::input[1]");

    // ⬅️ Botón ingresar
    private By btnIngresar = By.xpath("//button[contains(text(),'Ingresar')]");

    // ----------------------------------------------------

    public void escribirEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmail));
        emailField.click();
        emailField.sendKeys(email);
    }

    public void escribirPassword(String pass) {
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(inputPassword));
        passField.click();
        passField.sendKeys(pass);
    }

    // 🔥 CAPTCHA TEST MODE (solo stage.vitawallet.io)
    public void resolverCaptcha() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("document.getElementById('g-recaptcha-response').value='test';");
        js.executeScript("var btn=document.querySelector(\"button[type='submit']\"); if(btn){btn.disabled=false;}");
    }

    public void clickIngresar() {
        WebElement boton = wait.until(ExpectedConditions.elementToBeClickable(btnIngresar));
        boton.click();
    }
}
