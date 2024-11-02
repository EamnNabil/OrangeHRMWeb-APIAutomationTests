package Tests.web;

import Pages.AddUser;
import Pages.AdminPage;
import Pages.DashboardPage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class WebAutomationTests {
    private WebDriver driver;
    private utils.BrowserDriverFactory browserFactory;
    private WebDriverWait wait;
    private Integer initialRecordsNumber;

    @BeforeClass
    public void setup() {
        String browser = System.getProperty("browser", "chrome"); // Default to chrome if no parameter
        utils.BrowserDriverFactory.BrowserType type = utils.BrowserDriverFactory.BrowserType.valueOf(browser.toUpperCase());
        browserFactory = new utils.BrowserDriverFactory(type);

        // Create WebDriver instance
        driver = browserFactory.createDriver();
        driver.manage().window().maximize();

        // Create Wait instance
        wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Wait for up to 50 seconds
    }
    @Test
    public void testLogin() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.getUsername().sendKeys("Admin");
        loginPage.getPassword().sendKeys("admin123");
        loginPage.getLoginBtn().click();
    }

    @Test(dependsOnMethods = "testLogin")
    public  void testGetRecordsNum() {
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardPage.getAdminTabBy()));
        dashboardPage.getAdminTab().click();

        AdminPage adminPage = new AdminPage(driver, wait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(adminPage.getRecordsTextBy()));
        String recordsString = adminPage.getRecordsTextElement().getText();
        initialRecordsNumber = adminPage.extractInteger(recordsString);

        adminPage.getAddAdminBtn().click();
    }

    @Test(dependsOnMethods = "testGetRecordsNum")
    public void testAddUser() throws InterruptedException {
        AddUser addUser = new AddUser(driver, wait);
        //select role
        wait.until(ExpectedConditions.visibilityOfElementLocated(addUser.getSelectTextInputBy(1)));
        // select role list
        addUser.getSelectTextInputWith(1).click();
        //select admin from list
        addUser.getSelectDropdownOptionAt(2).click();
        //employee name
        addUser.getEmployeeName().click();
        addUser.getEmployeeName().sendKeys("e");

        // wait until auto complete list is shown
        wait.until(ExpectedConditions.visibilityOfElementLocated(addUser.getAutoCompleteDropDownBy()));
        // wait until search is done
        Thread.sleep(2000);
        // then select option#1
        addUser.getAutoCompleteDropdownOptionAt(1).click();

        //status
        addUser.getSelectTextInputWith(2).click();
        addUser.getSelectDropdownOptionAt(2).click();

        addUser.getUsernameInput().sendKeys("Emily Admin");
        addUser.getPasswordInput().sendKeys("P@ssw0rd");
        addUser.getConfirmPasswordInput().sendKeys("P@ssw0rd");
        addUser.getSaveBtn().click();
    }

    @Test(dependsOnMethods = "testAddUser")
    public void verifyAddUser() {
        AdminPage adminPage = new AdminPage(driver, wait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(adminPage.getRecordsTextBy()));
        String recordsString = adminPage.getRecordsTextElement().getText();
        Integer numberOfRecords = adminPage.extractInteger(recordsString);

        Assert.assertEquals(numberOfRecords, initialRecordsNumber + 1);
    }

    @Test(dependsOnMethods = "verifyAddUser")
    public void searchUser() {
        AdminPage adminPage = new AdminPage(driver,wait);
        adminPage.getUsernameInput().sendKeys("Emily Admin");
        adminPage.getSearchBtn().click();
        adminPage.getTrashBtn().click();
        adminPage.getConfirmDeleteBtn().click();
        adminPage.getResetSearchBtn().click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(adminPage.getRecordsTextBy()));
        String recordsString = adminPage.getRecordsTextElement().getText();
        Integer numberOfRecords = adminPage.extractInteger(recordsString);

        Assert.assertEquals(numberOfRecords, initialRecordsNumber);
    }

    @AfterClass
    public void close() {
        browserFactory.quitDriver();
    }
}
