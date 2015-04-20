package pages;

import models.TestCase;
import models.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by sten on 19.04.2015.
 */
public class TestCaseEditPage extends AbstractPage {

    private static final By nameField = By.id("testcase_name");
    private static final By descriptionField = By.xpath("/html/body");
    private static final By createButton = By.id("do_create_button");
    private static final By createStepButton = By.name("create_step");
    private static final String xpandBtnPathPlus = "//ul[@class='x-tree-node-ct']//*[contains(text(), '%s')]/ancestor::div/img[@class='x-tree-ec-icon x-tree-elbow-end-plus']";
    private static final String xpandBtnPathMinus = "//ul[@class='x-tree-node-ct']//*[contains(text(), '%s')]/ancestor::div/img[@class='x-tree-ec-icon x-tree-elbow-end-minus']";
    private static final String testCasePath = "//*[contains(text(), '%s')]";
    private static final By saveStepButton = By.id("do_update_step");
    private static final String stepText = "//*[@id='step_row_1']//p[contains(text(), '%s')]";
    private static final By summaryFrame = By.xpath("//td[@id='cke_contents_summary']/iframe");
    private static final By preconditionsFrame = By.xpath("//td[@id='cke_contents_preconditions']/iframe");
    private static final By stepsFrame = By.xpath("//td[@id='cke_contents_steps']/iframe");

    public TestCaseEditPage(WebDriver driver) {
        super(driver);
    }

    public void editTestCase(TestCase testCase) {
        driver.findElement(nameField).sendKeys(testCase.name);
        driver.switchTo().frame(driver.findElement(summaryFrame));
        driver.findElement(descriptionField).sendKeys(testCase.summary);
        Frames.switchToParentFrame(driver);
        driver.switchTo().frame(driver.findElement(preconditionsFrame));
        driver.findElement(descriptionField).sendKeys(testCase.preconditions);
        Frames.switchToParentFrame(driver);
        driver.findElement(createButton).click();
    }

    public void createNewStep(TestCase testCase, TestSuite testSuite) {
        By xpandButtonPlus = By.xpath(String.format(xpandBtnPathPlus, testSuite.name));
        By xpandButtonMinus = By.xpath(String.format(xpandBtnPathMinus, testSuite.name));
        By testCaseLink = By.xpath(String.format(testCasePath, testCase.name));
        if (driver.findElements(xpandButtonMinus).size() == 0)
            driver.findElement(xpandButtonPlus).click();
        driver.findElement(testCaseLink).click();
        Frames.switchToParentFrame(driver);
        Frames.switchToWorkFrame(driver);
        driver.findElement(createStepButton).click();
    }

    public void addStep(TestCase testCase) {
        try {
            driver.switchTo().frame(driver.findElement(stepsFrame));
            Thread.sleep(500);
            driver.findElement(descriptionField).sendKeys(testCase.step1);
            Frames.switchToParentFrame(driver);
            driver.findElement(saveStepButton).click();
        } catch (InterruptedException e) {

        }
    }

    public boolean isStepPresent(TestCase testCase) {
        By stepField = By.xpath(String.format(stepText, testCase.step1));
        return driver.findElements(stepField).size() != 0;
    }
}
