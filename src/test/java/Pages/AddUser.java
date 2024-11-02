package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddUser extends BasePage {
    public AddUser(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }

    public By getSelectTextInputBy(Integer index) {
        return By.xpath("(//div[contains(@class, 'oxd-select-wrapper')]//div[@class='oxd-select-text-input'])[" + index + "]");
    }
//select role from drop down list#1
    public WebElement getSelectTextInputWith(Integer index) {
        return driver.findElement(getSelectTextInputBy(index));
    }

    public WebElement getSelectDropdownOptionAt(Integer index){
        return driver.findElement(By.xpath("(//div[@class='oxd-select-option'])[" + index + "]"));
    }

    public WebElement getEmployeeName() {
        return driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
    }

    public By getAutoCompleteDropDownBy() {
        return By.className("oxd-autocomplete-dropdown");
    }

    public WebElement getAutoCompleteDropdownOptionAt(Integer index) {
        return driver.findElement(By.xpath("(//div[@class='oxd-autocomplete-option'])[" + index + "]"));
    }
// dropdown list #2
    public WebElement getUsernameInput() {
        return driver.findElement(By.xpath("(//input[contains(@class, 'oxd-input')])[2]"));
    }
//dropdown list #3
    public WebElement getPasswordInput() {
        return driver.findElement(By.xpath("(//input[contains(@class, 'oxd-input')])[3]"));
    }
//dropdown list #4
    public WebElement getConfirmPasswordInput() {
        return driver.findElement(By.xpath("(//input[contains(@class, 'oxd-input')])[4]"));
    }

    public WebElement getSaveBtn() {
        return driver.findElement(By.xpath("//button[@type='submit']"));
    }
}

