package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    protected void click(WebElement element) {
        element.click();
    }

    protected void write(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return element.getText();
    }
}
