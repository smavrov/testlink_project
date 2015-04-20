package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created with IntelliJ IDEA.
 * User: smavrov
 * Date: 4/20/15
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class WebDriverFactory {

    static {
        try {
            if ("Linux".equals(System.getProperty("os.name")))
                System.setProperty("webdriver.chrome.driver", WebDriverFactory.class.getClassLoader().getResource("drivers/linux/chromedriver").getPath());
            if ("Windows".equals(System.getProperty("os.name")))
                System.setProperty("webdriver.chrome.driver", WebDriverFactory.class.getClassLoader().getResource("drivers/windows/chromedriver.exe").getPath());
        } catch (Exception e) {

            System.out.println("Cannot launch Chrome driver \n" + e.getMessage());
        }
    }

    public static WebDriver getWebDriver(Browser browser) {
        switch (browser) {
            case FIREFOX:
                return new FirefoxDriver();
            case CHROME:
                return new ChromeDriver();
            case IE:
                return new InternetExplorerDriver();
            default:
                throw new IllegalArgumentException("Browser is not supported: " + browser);
        }
    }
}
