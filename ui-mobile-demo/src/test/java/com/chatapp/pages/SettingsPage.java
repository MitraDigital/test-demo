package com.chatapp.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

public class SettingsPage extends BasePage {

    private static By btn_Settings = By.id("com.cellcard.chatapp:id/action_settings");
    private static By lbl_Settings = By.xpath("//android.widget.TextView[@text='Settings']");
    private static By profile = By.id("com.cellcard.chatapp:id/layout_profile");
    private static By lbl_language = By.xpath("//android.widget.TextView[@text='Language']");
    private static By lbl_blockedUsers = By.xpath("//android.widget.TextView[@text='Blocked users']");
    private static By lbl_backUpHistory = By.xpath("//android.widget.TextView[@text='Back up and restore chat history']");
    private static By lbl_help = By.xpath("//android.widget.TextView[@text='Help']");
    private static By lbl_inviteFriends = By.xpath("//android.widget.TextView[@text='Invite friends']");
    private static By btn_signout = By.id("com.cellcard.chatapp:id/layout_sign_out");
    private static By select_English = By.xpath("//android.widget.TextView[@text='English']");
    private static By select_khmr = By.xpath("//android.widget.TextView[@text='ភាសាខ្មែរ']");
    private static By btn_back = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");
    private static By txt_language = By.id("com.cellcard.chatapp:id/text_language");


    public SettingsPage(AppiumDriver driver) {
        super(driver);
    }

    public void validateSettingsPage(){
        clickOnSettings();
        waitForElementPresent(lbl_Settings);
        waitForElementPresent(lbl_language);
        waitForElementPresent(profile);
        waitForElementPresent(lbl_backUpHistory);
        waitForElementPresent(lbl_blockedUsers);
        waitForElementPresent(lbl_help);
        swipeDown(1);
        waitForElementPresent(lbl_inviteFriends);
        waitForElementClickable(btn_signout);
    }

    public void clickOnSettings(){
        clickElement(btn_Settings);
    }

    public void validateEditProfile(){
        clickOnSettings();
        clickOnProfile();
    }

    public void clickOnProfile(){
        try{
            driver.findElement(profile).isDisplayed();
        }
        catch(NoSuchElementException e){
            swipeUp(1);
        }
        clickElement(profile);
    }

    public void validateLanguageChange(String language){
        changeLanguage(language);

        String languageText = driver.findElement(By.id("com.cellcard.chatapp:id/text_language")).getText();
            Assert.assertEquals(language,languageText);
    }

    public void changeLanguage(String language){
        clickElement(lbl_language);

//        waitForElementPresent(By.xpath("//android.widget.TextView[@text='"+language+"']/following::android.widget.ImageView[1]"));

        if(language=="ENG"){
            clickElement(select_English);
            waitForElementPresent(By.xpath("//android.widget.TextView[@text='English']/following::android.widget.ImageView[1]"));
        }
        if(language=="KHM"){
            clickElement(select_khmr);
            waitForElementPresent(By.xpath("//android.widget.TextView[@text='ភាសាខ្មែរ']/following::android.widget.ImageView[1]"));
        }
        clickElement(btn_back);
    }


}
