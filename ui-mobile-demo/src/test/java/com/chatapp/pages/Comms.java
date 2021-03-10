package com.chatapp.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

public class Comms extends BasePage{
    private static By lbl_chooseLang = By.xpath("//android.widget.TextView[@text='ជ្រើសរើសភាសា']");
    private static By btnEnglish = By.xpath("//android.widget.TextView[@text='English']");
    private static By btnKhmer = By.xpath("//android.widget.TextView[@text='ភាសាខ្មែរ']");
    private static By lblChatScreen = By.xpath("//android.widget.TextView[@text='Recent chat']");
    private static By lblChatScreenKhm = By.xpath("//android.widget.TextView[@text='ការសន្ទនាថ្មីៗ']");
    private static By btn_Settings = By.id("com.cellcard.chatapp:id/action_settings");
    private static By btn_SettingsKhm = By.id("com.cellcard.chatapp:id/action_settings");
    private static By btn_signout = By.id("com.cellcard.chatapp:id/layout_sign_out");
    private static By btn_signoutConfirm = By.xpath("//android.widget.Button[@text='SIGN OUT']");
    private static By txtPhoneNumber =By.xpath("//android.widget.EditText[@text='Phone number']");
    private static By btnContinue =By.xpath("//android.widget.Button[@text='Continue']");
    private static By lblPhoneNumber = By.xpath("//android.widget.EditText[@text='Phone number']");
    private static By otp = By.id("com.cellcard.chatapp:id/input_otp");
    private static By lbl_languageKhm = By.xpath("//android.widget.TextView[@text='ភាសា']");
    private static By select_English = By.xpath("//android.widget.TextView[@text='English']");
    private static By btn_back = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

    public Comms(AppiumDriver driver) {
        super(driver);
    }

    public boolean verifyUserLoggedIn() {

        boolean isLoggedIn = true;
        try {
            Thread.sleep(3000);
            driver.findElement(lbl_chooseLang).isDisplayed();
            System.out.println("Need to log in");
            isLoggedIn = false;
        } catch (NoSuchElementException | InterruptedException ex) {
            try {
                Thread.sleep(3000);
                driver.findElement(lblChatScreen).isDisplayed();
                System.out.println("Already logged in");
                isLoggedIn = true;
            } catch (NoSuchElementException | InterruptedException ey) {
                try {
                    Thread.sleep(3000);
                    driver.findElement(lblChatScreenKhm).isDisplayed();
                    System.out.println("Already logged in KHM");
                    isLoggedIn = true;
                }
                catch (NoSuchElementException | InterruptedException ee) {
                    System.out.println("In Verify user login, Element Not Found");
                    Assert.fail();
                }
            }
        }

        return isLoggedIn;
    }

    public void logOut(){
        clickElement(btn_Settings);
        swipeDown(1);
        clickElement(btn_signout);
        clickElement(btn_signoutConfirm);

    }

    public void login(){
        clickElement(btnEnglish);
        waitForElementPresent(lblPhoneNumber);
        enterText(txtPhoneNumber,"85200015");
        clickElement(btnContinue);
        clickClearAndEnterText(otp,"123456");
        waitForElementPresent(lblChatScreen);
    }

    public String validateLanguage(){
        String language ="Language?";
        try {
            driver.findElement(lblChatScreenKhm).isDisplayed();
            System.out.println("KHM");
            language="KHM";
        }
        catch (NoSuchElementException e){
            try {
                driver.findElement(lblChatScreen).isDisplayed();
            }
            catch (NoSuchElementException ex){
                System.out.println("English");
                language="English";
            }
        }
              return language;
    }
    public void changeLanguageToEnglish(){

            clickElement(btn_SettingsKhm);
            clickElement(lbl_languageKhm);
            clickElement(select_English);
            clickElement(btn_back);
    }
}
