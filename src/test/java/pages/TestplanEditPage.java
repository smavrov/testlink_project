package pages;

import models.TestPlan;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by admin on 08.04.2015.
 */
public class TestplanEditPage extends AbstractPage {

    private static final By nameField = By.name("testplan_name");
    private static final By descriptionField = By.xpath("/html/body");
    private static final By activeCheckBox = By.name("active");
    private static final By isPublicCheckBox = By.name("is_public");
    private static final By createButton = By.name("do_create");
    private static final By iFrame = By.xpath("//*[@id='cke_contents_notes']/iframe");

    public TestplanEditPage(WebDriver driver) {
        super(driver);
    }


    public void editTestPlan(TestPlan testPlan) {
        driver.findElement(nameField).sendKeys(testPlan.name);
        driver.switchTo().frame(driver.findElement(iFrame));
        driver.findElement(descriptionField).sendKeys(testPlan.description);
        Frames.switchToDefault(driver);
        Frames.switchToMainFrame(driver);
        if (testPlan.active)
            driver.findElement(activeCheckBox).click();
        if (testPlan.pub)
            driver.findElement(isPublicCheckBox).click();
        driver.findElement(createButton).click();
    }
}
