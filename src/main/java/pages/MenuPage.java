package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage {

    private WebDriver driver;

    // Locator del título
    public By titulo = By.xpath("//h2[contains(text(),'Vita Wallet')]");

    public MenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean menuCargado() {
        try {
            return driver.findElement(titulo).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
