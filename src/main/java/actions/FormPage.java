package actions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import variables.FrmVar;

public class FormPage extends Global {

    public FormPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Define of Form Locators
    @FindBy(id = "firstName")
    private WebElement firstNameField;
    @FindBy(id = "lastName")
    private WebElement lastNameField;
    @FindBy(id = "userEmail")
    private WebElement emailField;
    @FindBy(id = "userNumber")
    private WebElement mobileField;
    @FindBy(id = "uploadPicture")
    private WebElement pictureInput;
    @FindBy(id = "currentAddress")
    private WebElement currentAddressArea;
    @FindBy(css = "div.custom-radio:nth-child(1)")
    private WebElement genderRadioSelector;
    @FindBy(css = "div.custom-checkbox:nth-child(1)")
    private WebElement hobbiesCheckboxSelector;
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthField;
    @FindBy(css = "select.react-datepicker__month-select")
    private WebElement dpMonthSelect;
    @FindBy(css = "select.react-datepicker__year-select")
    private WebElement dpYearSelect;
    @FindBy(css = "div.react-datepicker__day--019")
    private WebElement dpDay;
    @FindBy(id = "subjectsInput")
    private WebElement subjectsField;
    @FindBy(id = "state")
    private WebElement selectState;
    @FindBy(id = "react-select-3-option-2")
    private WebElement stateName;
    @FindBy(id = "city")
    private WebElement selectCity;
    @FindBy(id = "react-select-4-option-1")
    private WebElement cityName;
    @FindBy(id = "userForm")
    private WebElement formSubmit;

    // Define of Results Table Locators
    @FindBy (xpath = "//tbody/tr[1]/td[2]")
    WebElement studentName;
    @FindBy (xpath = "//tbody/tr[2]/td[2]")
    WebElement studentEmail;
    @FindBy (xpath = "//tbody/tr[3]/td[2]")
    WebElement studentGender;
    @FindBy (xpath = "//tbody/tr[4]/td[2]")
    WebElement studentMobile;
    @FindBy (xpath = "//tbody/tr[5]/td[2]")
    WebElement studentDateOfBirth;
    @FindBy (xpath = "//tbody/tr[6]/td[2]")
    WebElement studentSubjects;
    @FindBy (xpath = "//tbody/tr[7]/td[2]")
    WebElement studentHobbies;
    @FindBy (xpath = "//tbody/tr[8]/td[2]")
    WebElement studentPicture;
    @FindBy (xpath = "//tbody/tr[9]/td[2]")
    WebElement studentAddress;
    @FindBy (xpath = "//tbody/tr[10]/td[2]")
    WebElement studentStateAndCity;

    // Methods interactions with Form
    public void fillFormFields() {
        firstNameField.sendKeys(FrmVar.firstName);
        lastNameField.sendKeys(FrmVar.lastName);
        emailField.sendKeys(FrmVar.email);
        mobileField.sendKeys(FrmVar.mobile);
        subjectsField.sendKeys(FrmVar.subjects);
        subjectsField.sendKeys(Keys.TAB);
        pictureInput.sendKeys(FrmVar.picturePath + FrmVar.pictureName);
        currentAddressArea.sendKeys(FrmVar.address);
    }
    public void setGenderSelector() {
        genderRadioSelector.click();
        FrmVar.gender = genderRadioSelector.getText();
    }
    public void setHobbiesSelector() {
        hobbiesCheckboxSelector.click();
        FrmVar.hobbies = hobbiesCheckboxSelector.getText();
    }
    public void setDateOfBirthField () {
        dateOfBirthField.click();
        Select dpMonth = new Select(dpMonthSelect);
        dpMonth.selectByVisibleText(FrmVar.monthBirth);
        Select dpYear = new Select(dpYearSelect);
        dpYear.selectByValue(FrmVar.yearBirth);
        dpDay.click();
    }
    public void setStateSelect(){
        selectState.click();
        FrmVar.state = stateName.getText();
        stateName.click();
    }
    public void setCitySelect() {
        selectCity.click();
        FrmVar.city = cityName.getText();
        cityName.click();
    }
    public void clickSubmit() {
        formSubmit.submit();
    }

    public void resultsComparison () {
        Assert.assertEquals(studentName.getText(), FrmVar.firstName +" " +FrmVar.lastName,"The values of Student Name doesn't match");
        Assert.assertEquals(studentEmail.getText(), FrmVar.email,"The values of Student Email doesn't match");
        Assert.assertEquals(studentGender.getText(), FrmVar.gender,"The values of Student Gender doesn't match");
        Assert.assertEquals(studentMobile.getText(), FrmVar.mobile,"The values of Student Mobile doesn't match");
        Assert.assertEquals(studentDateOfBirth.getText(), FrmVar.dayBirth+" "+ FrmVar.monthBirth+","+ FrmVar.yearBirth,"The values of Student Date of Birth doesn't match");
        Assert.assertEquals(studentSubjects.getText(), FrmVar.subjects,"The values of Student Subjects doesn't match");
        Assert.assertEquals(studentHobbies.getText(), FrmVar.hobbies,"The values of Student Hobbies doesn't match");
        Assert.assertEquals(studentPicture.getText(), FrmVar.pictureName,"The values of Student Picture doesn't match");
        Assert.assertEquals(studentAddress.getText(), FrmVar.address,"The values of Student Address doesn't match");
        Assert.assertEquals(studentStateAndCity.getText(), FrmVar.state+" "+ FrmVar.city,"The values of Student State and City doesn't match");
    }


}
