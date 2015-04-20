package tests;

import models.TestPlan;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.HomePage;
import pages.TestplanEditPage;
import pages.TestplanManagementPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 08.04.2015.
 */
public class TestplanTest {

    WebDriver driver;
    TestplanManagementPage testplanManagementPage;
    TestplanEditPage editPage;
    TestPlan testPlan = new TestPlan();


    @BeforeTest
    public void userLogin() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(new User());
    }

    @Test
    public void positiveTestPlanTest() {
        manageTestPlan();
        createTestPlan();
        Assert.assertTrue(testplanManagementPage.isTestPlanPresent(testPlan));
        deleteTestPlan(testPlan);
    }

    @AfterTest
    public void shutEnv() {
        if (driver != null)
            driver.quit();
    }

    public void manageTestPlan() {
        HomePage homepage = new HomePage(driver);

        testplanManagementPage = homepage.openTestPlanManagement();
        editPage = testplanManagementPage.createNewTestPlan();
    }

    public void createTestPlan() {
        editPage.editTestPlan(testPlan);
    }

    public void deleteTestPlan(TestPlan testPlan) {
        TestplanManagementPage testplanManagementPage = new TestplanManagementPage(driver);
        testplanManagementPage.deleteTestPlan(testPlan);
    }

    public void logout() {
        HomePage homePage = new HomePage(driver);
        homePage.logout();
    }
}
