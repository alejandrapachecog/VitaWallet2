import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.Test;

import java.net.URL;

public class ChromeTest {

    @Test
    public void testChromeAndroid() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Android Emulator");
        options.setAutomationName("UiAutomator2");

        // MUY IMPORTANTE
        options.setCapability("browserName", "Chrome");

        // Opcional si usas chromedriver específico
        options.setCapability("chromedriverExecutable", "/Users/alejandrapacheco/Drivers/chromedriver142/chromedriver");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);


        driver.get("https://stage.vitawallet.io/menu");
        System.out.println("Título: " + driver.getTitle());

        driver.quit();
    }
}
