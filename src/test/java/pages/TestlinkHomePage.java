package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by admin on 08.04.2015.
 */
public class TestlinkHomePage {

    WebDriver driver;

    private static final By logout = By.linkText("Выход");

    public TestlinkHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void logout() {

    }

}
