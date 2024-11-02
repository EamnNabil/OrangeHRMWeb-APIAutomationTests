package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserDriverFactory {

    // Enum for Browser Types
    public enum BrowserType {
        CHROME,
        FIREFOX,
        EDGE,
        SAFARI
    }

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final BrowserType browserType;

    // Constructor to initialize browser type
    public BrowserDriverFactory(BrowserType browserType) {
        this.browserType = browserType;
    }

    // Method to create WebDriver based on the specified browser
    public WebDriver createDriver() {
        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;

            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;

            case SAFARI:
                WebDriverManager.safaridriver().setup();
                driver.set(new SafariDriver());
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }

        return driver.get();
    }

    // Quit the WebDriver instance
    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}