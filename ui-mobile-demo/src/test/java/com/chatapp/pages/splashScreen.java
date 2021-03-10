package com.chatapp.pages;



import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class splashScreen extends BasePage {

    // Variables are declared as static to access directly in methods
    private static final By btnEnglish = By.xpath("//android.widget.TextView[@text='English']");
    private static final By btnKhmer = By.xpath("//android.widget.TextView[@text='ភាសាខ្មែរ']");
    private static final By imgCellcardLogo = By.xpath("(//android.widget.ImageView)[1]");
    private static final By btnLoginOrRegister = By.xpath("//android.widget.TextView[@text='Login / Register']");
    private static final By lblPhoneNumber = By.xpath("//android.widget.EditText[@text='Phone number']");


    public splashScreen(AppiumDriver driver) {
        super(driver);
    }


    @Step("Open the Cellcard App")
    public void checkElementIsPresent() {

        waitForElementPresent(btnEnglish);
        waitForElementPresent(btnKhmer);

    }

    @Step("Click on the English Tab")
    public void clickOnLogobutton() {

        clickElement(btnEnglish);
    }

    @Step("Go to Phone Number Page")
    public void goToMobileNUmberPage(){

        waitForElementPresent(lblPhoneNumber);

    }


}
