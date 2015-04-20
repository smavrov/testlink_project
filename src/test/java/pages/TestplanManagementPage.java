package pages;

import models.TestPlan;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Created by admin on 08.04.2015.
 */
public class TestplanManagementPage extends AbstractPage {

    private static final By createButton = By.name("create_testplan");
    private static final By yesButton = By.id("ext-gen20");

    public TestplanManagementPage(WebDriver driver) {
        super(driver);
    }

    public TestplanEditPage createNewTestPlan() {
        driver.findElement(createButton).click();
        return new TestplanEditPage(driver);
    }

    public boolean isTestPlanPresent(TestPlan testPlan) {
        return driver.findElements(By.linkText(testPlan.name)).size() != 0;
    }

    public void deleteTestPlan(TestPlan testPlan) {
        By deleteButton = By.xpath(String.format("//img[contains(@onclick,'%s')]", testPlan.name));
        driver.findElement(deleteButton).click();
        driver.findElement(yesButton).click();
    }
}
