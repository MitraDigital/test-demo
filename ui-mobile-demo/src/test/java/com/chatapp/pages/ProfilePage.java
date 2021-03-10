package com.chatapp.pages;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Arrays;

public class ProfilePage extends BasePage{
    private static By lbl_profile = By.xpath("//android.widget.TextView[@text='Profile']");
    private static By lbl_Name = By.xpath("//android.widget.TextView[@text='Name']");
    private static By lbk_status = By.xpath("//android.widget.TextView[@text='Status']");
    private static By lbl_phone = By.xpath("//android.widget.TextView[@text='Phone']");
    private static By txt_Name = By.id("com.cellcard.chatapp:id/tv_username");
//    private static By txt_Name = By.xpath("//android.widget.TextView[@text='TestName 1']");
    private static By txt_status = By.id("com.cellcard.chatapp:id/tv_status");
    private static By txt_phone = By.id("com.cellcard.chatapp:id/tv_phone");
    private static By btn_Name = By.id("com.cellcard.chatapp:id/image_button_edit_username");
    private static By btn_status = By.id("com.cellcard.chatapp:id/iv_status_edit");
    private static By txt_enterName = By.xpath("//android.widget.EditText");
    private static By txt_enterStatus = By.xpath("//android.widget.EditText");
    private static By btn_OK = By.xpath("//android.widget.Button[@text='OK']");
    private static By btn_cancel = By.xpath("//android.widget.Button[@text='CANCEL']");
    private static By btn_back = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

    public ProfilePage(AppiumDriver driver) {
        super(driver);
    }

    public void validateProfilePageElements(){
        waitForElementPresent(lbl_profile);
        waitForElementPresent(lbl_Name);
        waitForElementPresent(lbk_status);
        waitForElementPresent(lbl_phone);
        waitForElementPresent(btn_Name);
        waitForElementPresent(btn_status);
        waitForElementPresent(txt_Name);
        waitForElementPresent(txt_phone);
        waitForElementPresent(txt_status);

    }
    public void validateNameChange() {
        String name = driver.findElement(txt_Name).getText();
        System.out.println(name);
        String[] splited = name.split("\\s+");
        Integer no = Integer.valueOf(splited[1]);
        no = no + 1;
        splited[1] = String.valueOf(no);
        System.out.println(Arrays.toString(splited));
        String newName = StringUtils.join(splited, " ");
        System.out.println(newName);

        clickElement(btn_Name);
        enterText(txt_enterName,newName);
        clickElement(btn_OK);

        Assert.assertEquals(driver.findElement(txt_Name).getText(),newName);
    }

    public void validateStatusChange() {
        String status = driver.findElement(txt_status).getText();
        System.out.println(status);
        String[] splited = status.split("\\s+");
        Integer no = Integer.valueOf(splited[1]);
        no = no + 1;
        splited[1] = String.valueOf(no);
        System.out.println(Arrays.toString(splited));
        String newStatus = StringUtils.join(splited, " ");
        System.out.println(newStatus);

        clickElement(btn_status);
        enterText(txt_enterStatus,newStatus);
        clickElement(btn_OK);

        Assert.assertEquals(driver.findElement(txt_status).getText(),newStatus);

        clickElement(btn_back);
    }

}
