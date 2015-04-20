package pages;

import models.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by sten on 19.04.2015.
 */
public class TestSuiteEditPage extends AbstractPage {

    private static final By nameField = By.id("name");
    private static final By saveButton = By.name("add_testsuite_button");

    public TestSuiteEditPage(WebDriver driver) { super(driver);}

    public void editTestSuite(TestSuite testSuite) {
        driver.findElement(nameField).sendKeys(testSuite.name);
        driver.findElement(saveButton).click();
    }
}
