package com.chatapp.pages;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class verificationPage extends BasePage {
    private static By lblVerification = By.xpath("//android.widget.TextView[@text='Verification']");
    private static By textEditPhone = By.id("com.cellcard.chatapp:id/text_edit_phone");
    private static By textCOunter = By.id("com.cellcard.chatapp:id/text_counter");
    private static By textResend = By.id("com.cellcard.chatapp:id/text_resend");
    private static By otp = By.id("com.cellcard.chatapp:id/input_otp");
    private static By btnContinue =By.id("com.cellcard.chatapp:id/button_continue");
    private static By errorMessage = By.xpath("//android.widget.TextView[@text='Verification code not match']");
    private static By lblChatScreen = By.xpath("//android.widget.TextView[@text='Recent chat']");


    public verificationPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Go to verification Page")
    public void goTOVerificationPage() {
        waitForElementPresent(lblVerification);
        waitForElementPresent(textEditPhone);
        waitForElementPresent(textCOunter);
        waitForElementPresent(textResend);
    }

    @Step("Enter valid verification code")
    public void validateVerificationCode() {
        clickClearAndEnterText(otp,"123456");
        waitForElementPresent(lblChatScreen);
    }

    @Step("Enter invalid verification code")
    public void validateInvalidVerificationCode() {
        clickAndEnterText(otp,"444444");
//        clickElement(btnContinue);

        try{
           driver.findElement(errorMessage).isDisplayed();
        }
        catch(NoSuchElementException  e){
            System.out.println(e);
            Assert.fail();
        }
    }

}
