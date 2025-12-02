package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void loginExitoso() {
        LoginPage login = new LoginPage(driver);

        login.escribirEmail("alejandra@gmail.com");
        login.escribirPassword("Rafaella21#");

        login.resolverCaptcha();

        login.clickIngresar();
    }




}
