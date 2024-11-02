package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminPage extends BasePage {
    public AdminPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public By getRecordsTextBy() {
        return By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//span[@class='oxd-text oxd-text--span']");
    }

    public WebElement getRecordsTextElement() {
        // Locate the span element using XPath
        return driver.findElement(getRecordsTextBy());
    }

    public WebElement getAddAdminBtn() {
        return driver.findElement(By.xpath("//div[@class='orangehrm-header-container']//button[contains(@class, 'oxd-button--secondary')]"));
    }
    public WebElement getUsernameInput(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'oxd-input-group oxd-input-field-bottom-space')]//input[contains(@class, 'oxd-input oxd-input--active')]")));
    }

    public WebElement getSearchBtn(){
        return driver.findElement(By.xpath("//button[@type='submit']"));
    }

    public WebElement getResetSearchBtn() {
        return driver.findElement(By.className("oxd-button--ghost"));
    }

    public WebElement getTrashBtn(){
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[i[contains(@class, 'oxd-icon bi-trash')]]")));
    }

    public WebElement getConfirmDeleteBtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[i[contains(@class, 'oxd-icon bi-trash oxd-button-icon')]]")));
    }

    public int extractInteger(String str) {
        // Regular expression to match an integer in the string
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);

        // Check if a match is found
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }

        // Return 0 if no number is found
        return 0;
    }
}
