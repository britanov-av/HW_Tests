package actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

public class Global {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeTest
    public void initDriver () {

        /*WebDriverManager Initialisation*/
        WebDriverManager.chromedriver().setup();
        /*Setup of specific Browser parameters for startup*/
        ChromeOptions options = new ChromeOptions ();
        options.addExtensions (new File("C:/WebDriver/add-on/AdBlocker.crx")); //Install ADBlocker plug-in
        options.addArguments("start-maximized"); //Launch the Browser in a maximized window
        options.addArguments("force-device-scale-factor=0.95"); //Launch the Browser in 95% scale mode
        options.addArguments("high-dpi-support=0.95");
        /*Create instance of WebDriver with specific Browser parameters*/
        driver = new ChromeDriver(options);
        /*Setting implicit delays between method executions*/
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        /*Manage of opened Tabs*/
        setTabs();
    }

    @AfterTest
    public void closeDriver() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    public void setTabs() {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles()); //Getting a list of tabs
        getDriver().switchTo().window(tabs.get(0)); //Switch to the first tab in the browser
        getDriver().close(); //Close active tab
        getDriver().switchTo().window(tabs.get(1)); //Switching to the second tab in the browser
    }
}
