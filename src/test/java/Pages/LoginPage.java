package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getUsername() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
    }

    public WebElement getPassword(){
        return driver.findElement(By.name("password"));
    }

    public WebElement getLoginBtn(){
        return driver.findElement(By.xpath("//button[text()=' Login ']"));
    }
}
