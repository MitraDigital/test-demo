package com.chatapp.pages;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class loginPage extends BasePage {

    private static By txtPhoneNumber =By.xpath("//android.widget.EditText[@text='Phone number']");
    private static By btnContinue =By.xpath("//android.widget.Button[@text='Continue']");

    public loginPage(AppiumDriver drvier){
        super(drvier);
    }

    @Step("Enter phone number")
    public void enterPhoneNumber() {

        enterText(txtPhoneNumber,"85200015");
    }

    @Step("Click Continue")
    public void clickOnContinueButton() {

        clickElement(btnContinue);
    }


}
