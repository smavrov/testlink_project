package pages;

import models.TestPlan;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by admin on 08.04.2015.
 */
public class TestplanEditPage {

    private WebDriver driver;

    private static final By nameField = By.name("testplan_name");
    private static final By descriptionField = By.id("cke_contents_notes");
    private static final By activeCheckBox = By.name("active");
    private static final By isPublicCheckBox = By.name("is_public");
    private static final By createButton = By.name("do_create");

    public TestplanEditPage(WebDriver driver) {
        this.driver = driver;
    }


    public void createTestPlan(TestPlan testPlan) {
        driver.findElement(nameField).sendKeys(testPlan.name);
        driver.findElement(descriptionField).sendKeys(testPlan.description);
        if (testPlan.active)
            driver.findElement(activeCheckBox).click();
        if (testPlan.pub)
            driver.findElement(isPublicCheckBox).click();
        driver.findElement(createButton).click();
    }
}
