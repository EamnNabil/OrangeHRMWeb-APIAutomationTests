package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public By getAdminTabBy() {
        return By.xpath("//li[@class='oxd-main-menu-item-wrapper']//a[.//span[text()='Admin']]");
    }

    public WebElement getAdminTab(){
        return driver.findElement(getAdminTabBy());
    }
}
