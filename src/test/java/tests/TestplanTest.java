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
import pages.TestlinkHomePage;
import pages.TestplanEditPage;
import pages.TestplanManagementPage;

/**
 * Created by admin on 08.04.2015.
 */
public class TestplanTest {

    WebDriver driver;

    @BeforeTest
    public void userLogin() {
        driver = new FirefoxDriver();
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
        //deleteTestPlan(testPlan);
        logout();
        if (driver != null)
            driver.quit();
    }

    public void deleteTestPlan(TestPlan testPlan) {
        TestplanManagementPage testplanManagementPage = new TestplanManagementPage(driver);
        testplanManagementPage.deleteTestPlan(testPlan);
    }

    public void logout() {
        TestlinkHomePage homePage = new TestlinkHomePage();
        homePage.logout();
    }
}
