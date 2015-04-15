package pages;

import models.TestPlan;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Created by admin on 08.04.2015.
 */
public class TestplanManagementPage {

    private WebDriver driver;

    private static final By createButton = By.name("create_testplan");

    public TestplanManagementPage(WebDriver driver) {
        this.driver = driver;
    }

    public TestplanEditPage createTestPlan() {
        driver.findElement(createButton).click();
        return new TestplanEditPage(driver);
    }

    public boolean isTestPlanPresent(TestPlan testPlan) {
        return driver.findElement(By.linkText(testPlan.name)) != null;
    }

    public void deleteTestPlan(TestPlan testPlan) {
        driver.findElement(By.xpath("//img[contains(@onclick,'Bionic TestPlan')]")).click();
        driver.findElement(By.id("ext-gen20")).click();
    }
}
