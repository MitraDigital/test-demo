package com.chatapp.pages;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class preRequisits extends BasePage {

    private static By alertPermissionRequest =By.id("com.cellcard.chatapp:id/alertTitle");
    private static By btnOk =By.id("android:id/button1");

    private static By permissionMessage =By.id("com.android.packageinstaller:id/permission_message");
    private static By btnAllow =By.id("com.android.packageinstaller:id/permission_allow_button");
    private static By btnDeny =By.id("com.android.permissioncontroller:id/permission_deny_button");

//    com.android.permissioncontroller:id/permission_allow_button
//    com.android.packageinstaller:id/permission_allow_button

//    private static By permissionMessage =By.id("com.android.permissioncontroller:id/permission_message");
//    private static By btnAllow =By.id("com.android.permissioncontroller:id/permission_allow_button");
//    private static By btnDeny =By.id("com.android.permissioncontroller:id/permission_deny_button");

    public preRequisits(AppiumDriver driver) {
        super(driver);
    }

    @Step("Allow permissions")
    public void allowPermissions() {
//        if(driver.findElement(alertPermissionRequest).isDisplayed()){
//            clickElement(btnOk);
//        }
//
       do {
           try{
               driver.findElement(alertPermissionRequest).isDisplayed();
               clickElement(btnOk);
           }

           catch (NoSuchElementException e) {
               try {
                   Thread.sleep(3000);
                   driver.findElement(permissionMessage).isDisplayed();
                   clickElement(btnAllow);
               } catch (NoSuchElementException | InterruptedException ex) {
                   break;
               }
           }

       }
        while(true);

    }
}
