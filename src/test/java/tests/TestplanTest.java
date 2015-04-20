package tests;

import models.TestPlan;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TestlinkHomePage;
import pages.TestplanEditPage;
import pages.TestplanManagementPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 08.04.2015.
 */
public class TestplanTest {

    WebDriver driver;

    @BeforeTest
    public void userLogin() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(new User());
    }

    @Test
    public void positiveTestPlanTest() {
        TestlinkHomePage homepage = new TestlinkHomePage(driver);

        TestplanManagementPage testplanManagementPage = homepage.openTestPlanManagement();
        TestplanEditPage editPage = testplanManagementPage.createTestPlan();

        TestPlan testPlan = new TestPlan();
        editPage.createTestPlan(testPlan);

        Assert.assertTrue(testplanManagementPage.isTestPlanPresent(testPlan));
        deleteTestPlan(testPlan);
    }

    @AfterTest
    public void shutEnv() {
        if (driver != null)
            driver.quit();
    }

    public void deleteTestPlan(TestPlan testPlan) {
        TestplanManagementPage testplanManagementPage = new TestplanManagementPage(driver);
        testplanManagementPage.deleteTestPlan(testPlan);
    }

    public void logout() {
        TestlinkHomePage homePage = new TestlinkHomePage(driver);
        homePage.logout();
    }
}
