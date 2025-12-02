package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;
import java.util.List;
import java.util.Set;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeClass
    public void setUp() throws Exception {

        // Opciones de Chrome
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-geolocation");
        chromeOptions.addArguments("--no-first-run");
        chromeOptions.addArguments("--disable-default-apps");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-features=NotificationTriggers");
        chromeOptions.addArguments("--disable-permissions-api");

        // Opciones de Appium
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("emulator-5554");
        options.setCapability("udid", "emulator-5554");
        options.setCapability("appium:noReset", true);
        options.withBrowserName("Chrome");
        options.setChromedriverExecutable("/Users/alejandrapacheco/Drivers/chromedriver142/chromedriver");
        options.setCapability("adbExecTimeout", 60000);


        // Añadir ChromeOptions
        options.setCapability("goog:chromeOptions", chromeOptions);

        // Crear driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        // Cerrar popup nativos de Chrome si aparecen
        cerrarPopupChrome();

        // Cambiar automáticamente a CHROMIUM (modo web)
        cambiarAWebview();

        // Ir a tu URL de login
        driver.get("https://stage.vitawallet.io/login");
    }


    // ------------------------------------------------------------
    // 🔥 Cerrar popups nativos de Chrome automáticamente
    // ------------------------------------------------------------
    public void cerrarPopupChrome() {
        try {
            System.out.println("Intentando cerrar popup de Chrome...");

            // Ir a contexto nativo
            for (String ctx : driver.getContextHandles()) {
                if (ctx.contains("NATIVE")) {
                    driver.context(ctx);
                }
            }

            Thread.sleep(800);

            String[] posiblesIDs = {
                    "com.android.chrome:id/terms_accept",
                    "com.android.chrome:id/negative_button",
                    "com.android.chrome:id/positive_button",
                    "com.android.chrome:id/cancel_button"
            };

            for (String id : posiblesIDs) {
                try {
                    driver.findElement(By.id(id)).click();
                    System.out.println("Popup cerrado con ID: " + id);
                    break;
                } catch (Exception ignored) {}
            }

        } catch (Exception e) {
            System.out.println("Popup no encontrado (OK).");
        }
    }


    // ------------------------------------------------------------
    // Cambiar al contexto WEBVIEW/CHROMIUM para automatizar Chrome
    // ------------------------------------------------------------
    public void cambiarAWebview() {
        try {
            Thread.sleep(1500);
            for (String ctx : driver.getContextHandles()) {
                if (ctx.contains("WEBVIEW") || ctx.contains("CHROMIUM")) {
                    driver.context(ctx);
                    System.out.println("➡ Cambiado a contexto WEB: " + ctx);
                    return;
                }
            }
            System.out.println("⚠ No se encontró contexto WebView.");
        } catch (Exception e) {
            System.out.println("❌ Error al cambiar al contexto WebView: " + e.getMessage());
        }
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    public void cambiarAIframeLogin() {
        try {
            driver.switchTo().defaultContent();

            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            System.out.println("Cantidad de iframes encontrados: " + iframes.size());

            for (int i = 0; i < iframes.size(); i++) {
                try {
                    driver.switchTo().frame(i);
                    System.out.println("Intentando iframe: " + i);

                    if (driver.findElements(By.id("i-login-email")).size() > 0) {
                        System.out.println("✔ Iframe correcto encontrado: " + i);
                        return; // nos quedamos aquí
                    }

                } catch (Exception e) {
                    // ignoramos el error y probamos siguiente iframe
                } finally {
                    driver.switchTo().defaultContent();
                }
            }

            // 👉 IMPORTANTE: YA NO TIRA EXCEPCIÓN
            System.out.println("⚠ No se encontró iframe con el login. Continuando en DOM principal...");

        } catch (Exception ex) {
            System.out.println("❌ Error general al cambiar iframe: " + ex.getMessage());
            // NO lanzamos excepción
        }
    }



}
