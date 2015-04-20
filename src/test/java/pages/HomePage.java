package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by admin on 08.04.2015.
 */
public class HomePage extends AbstractPage {

    private static final By testPlanManagementLink = By.xpath("//*[@id='test_plan_mgmt_topics']/a[1]");
    private static final By logout = By.xpath("//img[contains(@src,'gui/themes/default/images/computer_go.png')]");
    private static final By testSuiteManagementLink = By.xpath("//*[@id='testspecification_topics']/a[1]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        Frames.switchToDefault(driver);
        Frames.switchToTitleBar(driver);
        driver.findElement(logout).click();
    }

    public TestplanManagementPage openTestPlanManagement() {
        Frames.switchToMainFrame(driver);
        driver.findElement(testPlanManagementLink).click();
        return new TestplanManagementPage(driver);
    }

    public TestSuiteManagementPage openTestSpecification() {
        Frames.switchToMainFrame(driver);
        driver.findElement(testSuiteManagementLink).click();
        return new TestSuiteManagementPage(driver);
    }
}
