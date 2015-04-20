package tests;

import models.TestCase;
import models.TestSuite;
import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;
import webdriver.Browser;
import webdriver.WebDriverFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by sten on 18.04.2015.
 */
public class TestSuiteTest {
    WebDriver driver;
    TestSuite testSuite = new TestSuite();
    TestCase testCase = new TestCase();
    TestSuiteManagementPage testSuiteManagementPage;
    TestSuiteEditPage testSuiteEditPage;
    TestCaseEditPage testCaseEditPage;

    @BeforeTest
    public void userLogin() {
        driver = WebDriverFactory.getWebDriver(Browser.FIREFOX);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(new User());
    }

    @Test
    public void positiveTestSuiteTest() {
        Assert.assertTrue(createTestSuite(testSuite));
        Assert.assertTrue(createTestCase(testSuite, testCase));
        Assert.assertTrue(addTestStep(testSuite, testCase));
    }

    @AfterTest
    public void shutEnv() {
        deleteTestSuite(testSuite);
        if (driver != null)
            driver.quit();
    }

    public boolean createTestSuite(TestSuite testSuite) {
        HomePage homePage = new HomePage(driver);

        testSuiteManagementPage = homePage.openTestSpecification();
        testSuiteEditPage = testSuiteManagementPage.createNewTestSuite();
        testSuiteEditPage.editTestSuite(testSuite);
        return testSuiteManagementPage.isTestSuitePresent(testSuite);
    }

    public boolean createTestCase(TestSuite testSuite, TestCase testCase) {
        testCaseEditPage = testSuiteManagementPage.createTestCase(testSuite);
        testCaseEditPage.editTestCase(testCase);
        return testSuiteManagementPage.isTestCasePresent(testCase, testSuite);
    }

    public boolean addTestStep(TestSuite testSuite, TestCase testCase) {
        testCaseEditPage.createNewStep(testCase, testSuite);
        testCaseEditPage.addStep(testCase);
        return testCaseEditPage.isStepPresent(testCase);
    }

    public void deleteTestSuite(TestSuite testSuite) {
        TestSuiteManagementPage testSuiteManagementPage = new TestSuiteManagementPage(driver);
        testSuiteManagementPage.deleteTestSuite(testSuite);
    }
}
