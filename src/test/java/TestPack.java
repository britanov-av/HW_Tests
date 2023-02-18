import actions.FormPage;
import actions.Global;
import actions.SliderPage;
import actions.WindowsPage;
import org.testng.annotations.Test;

public class TestPack extends Global {

    @Test
    public void formTest () {
            getDriver().get("https://demoqa.com/automation-practice-form"); //Open Target Page

            FormPage page = new FormPage(getDriver()); //Create new instance of storage Class

            /*Test body*/
            page.fillFormFields();
            page.setGenderSelector();
            page.setDateOfBirthField();
            page.setHobbiesSelector();
            page.setStateSelect();
            page.setCitySelect();
            page.clickSubmit();

            /*Comparison section*/
            page.resultsComparison();
    }

    @Test
    public void windowsTest () throws InterruptedException {
        getDriver().get("https://demoqa.com/browser-windows"); //Open Target Page

        WindowsPage page = new WindowsPage(getDriver()); //Create new instance of storage Class

        /*Test body*/
        page.checkOriginalWindow(); //
        /*New Tab actions*/
        page.openNewTab();
        page.switchToNew();
        page.checkContent("simple");
        page.closeActiveViewport();
        /*New Window actions*/
        page.openNewWindow();
        page.switchToNew();
        page.checkContent("simple");
        page.closeActiveViewport();
        /*New Window Message actions*/
        /*
        page.openNewMessageWindow();
        page.switchToNew();
        page.checkContent("message");
        page.closeActiveViewport();
        */
    }

    @Test
    public void sliderTest () {
        getDriver().get("https://demoqa.com/slider"); //Open Target Page

        SliderPage page = new SliderPage(getDriver()); //Create new instance of storage Class

        page.storeSliderPosition("default");
        page.moveSlider(12);
        page.compare();
        page.moveSlider(48);
        page.compare();
        page.moveSlider(83);
        page.compare();

    }
}