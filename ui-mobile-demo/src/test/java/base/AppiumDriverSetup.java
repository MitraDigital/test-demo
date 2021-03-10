package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;


public interface AppiumDriverSetup {

    AndroidDriver getWebDriverObject(URL appiumServerURL, DesiredCapabilities desiredCapabilities);

    DesiredCapabilities getDesiredCapabilities(String pathToAppFile, String deviceName, String platformVersion, String appPackage, String appActivity, String bundleID);


    
}