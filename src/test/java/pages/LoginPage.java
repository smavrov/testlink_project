package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by admin on 08.04.2015.
 */
public class LoginPage {

    private static final String URL = "http://demo.testlink.org/latest/login.php";

    private static final By loginField = By.id("login");
    private static final By passField = By.name("tl_password");
    private static final By loginButton = By.name("login_submit");

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public TestlinkHomePage login(User user) {
        driver.findElement(loginField).sendKeys(user.name);
        driver.findElement(passField).sendKeys(user.pass);
        driver.findElement(loginButton).click();
        return new TestlinkHomePage(driver);
    }
}
