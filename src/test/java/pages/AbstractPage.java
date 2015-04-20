package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by sten on 18.04.2015.
 */
public abstract class AbstractPage {

    WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }
}
