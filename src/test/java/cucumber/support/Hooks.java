package cucumber.support;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.config.Config;
import cucumber.data.TestData;
import cucumber.driver.Factory;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Hooks {

    private static boolean setup = false;
    private static TestData testData;
    private static Factory factory;
    private static String baseURL = "www.google.com/";
    private static String environment;
    Config config;
    private RemoteWebDriver driver;

    public Hooks() {
        this.config = new Config();
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public TestData getTestData() {
        return testData;
    }

    @Before(order = 1)
    public void beforeAll() throws Throwable {

        if (!setup) {
            Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);
            environment = config.environment;
            testData = new TestData(config.username);
            try {
                factory = new Factory(config.url, config.deviceMap);
            } catch (MalformedURLException e) {
                throw e;
            }

            setup = true;
        }
    }

    @Before(order = 2)
    public void beforeScenario() throws Throwable {

        driver = factory.createDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        driver.get("https://" + environment + "." + baseURL);
        driver.get("https://" + baseURL);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (driver != null && scenario.isFailed()) {
            try {
                byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");

            } catch (WebDriverException webdriverException) {
                webdriverException.printStackTrace();
            }
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
