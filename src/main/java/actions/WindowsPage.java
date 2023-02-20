package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import variables.WinVar;

public class WindowsPage extends Global {
    WebDriver driver;

    public WindowsPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (id = "tabButton")
    private WebElement newTabBtn;
    @FindBy (id = "windowButton")
    private WebElement newWindowBtn;
    @FindBy (id = "messageWindowButton")
    private WebElement newMessageWindowBtn;
    @FindBy (id = "sampleHeading")
    private WebElement simpleTabAndWindowContent;

    //@FindBy (xpath = "//body")
    // private WebElement messageWindowContent;

    public void checkOriginalWindow () {

       if (driver.getWindowHandles().size() != 1) {
            System.out.println("There is more than one window\\tab is open. Check browser and try again");
       } else {
       WinVar.originalWindow = driver.getWindowHandle();
       }
    }
    public void openNewTab () { newTabBtn.click(); }
    public void openNewWindow () { newWindowBtn.click(); }
    public void openNewMessageWindow () { newMessageWindowBtn.click(); }

    public void switchToNew () {
        if (driver.getWindowHandles().size() == 2) {
            for (String windowHandle : driver.getWindowHandles()) {
                if (!WinVar.originalWindow.contentEquals(windowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
        } else System.out.println("The new window didn't open. Please check and try again");
    }

    public void checkContent (String type) {
        switch (type){
            case "simple":
                Assert.assertEquals(simpleTabAndWindowContent.getText(), "This is a sample page");
            break;

            case "message":
                //Assert.assertEquals(messageWindowContent.getText(), WinVar.newWindowText);
            break;

            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public void closeActiveViewport () throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
        driver.switchTo().window(WinVar.originalWindow);
    }
}
