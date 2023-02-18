package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import variables.SldVar;

public class SliderPage extends Global {
    WebDriver driver;

    public SliderPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath = "//input[@type='range']")
    private WebElement slider;
    @FindBy (id = "sliderValue")
    private WebElement sliderValue;

    public void storeSliderPosition (String type) {
        switch (type){
            case "default":
                SldVar.defaultSliderValue = sliderValue.getAttribute("value");
                SldVar.previousSliderValue = SldVar.defaultSliderValue;
                break;

            case "previous":
                SldVar.previousSliderValue = sliderValue.getAttribute("value");
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public void moveSlider (int position) {

        /*Main logic of Slider position calculation*/
        int pxPosition;
        float tempPixels = slider.getSize().width;
        tempPixels = tempPixels / (SldVar.sliderMax - SldVar.sliderMin);
        tempPixels = tempPixels * (position - SldVar.sliderMin);

        if (position <= 36){ // Calculation of the Slider position taking into account the positive error in the first section
            int correction = (int)(slider.getSize().width * SldVar.deltaMin) /100;
            pxPosition = Math.round(tempPixels) + correction;

        }else if (position >= 69){ // Calculation of the Slider position taking into account the negative error in the second section
            int correction = (int)(slider.getSize().width * SldVar.deltaMax) /100;
            pxPosition = Math.round(tempPixels) - correction;

        }else pxPosition = Math.round(tempPixels);

        /*Move of the Slider to the specified position*/
        Actions action = new Actions(driver);
        action.clickAndHold(slider)
                .moveByOffset((-(int)slider.getSize().width / 2),0)
                .moveByOffset(pxPosition, 0).release().build().perform();
    }

    public void compare () {

        String actualSliderPosition = sliderValue.getAttribute("value");
        Assert.assertNotEquals(actualSliderPosition, SldVar.defaultSliderValue, "The Actual and Default values of Slider shouldn't be equal.");
        Assert.assertNotEquals(actualSliderPosition, SldVar.previousSliderValue, "The Actual and Previous values of Slider shouldn't be equal.");
        storeSliderPosition("previous");
    }


}
