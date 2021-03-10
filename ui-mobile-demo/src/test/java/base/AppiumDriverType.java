package base;



import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.URL;

public enum AppiumDriverType implements AppiumDriverSetup {

    ANDROID {

        public DesiredCapabilities getDesiredCapabilities(String pathToAppFile, String deviceName, String platformVersion, String appPackage, String appActivity, String bundleID) {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
//            capabilities.setCapability(MobileCapabilityType.UDID, System.getenv("ANDROID_DEVICE_UUID"));
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            capabilities.setCapability(MobileCapabilityType.APP, pathToAppFile);
            capabilities.setCapability("appPackage", appPackage);
            capabilities.setCapability("appActivity", appActivity);
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "36000");

            return capabilities;

        }

        public AndroidDriver<WebElement> getWebDriverObject(URL appiumServerURL, DesiredCapabilities capabilities) {

            return new AndroidDriver<>(appiumServerURL, capabilities);
        }

    },
    IPHONE {
        public DesiredCapabilities getDesiredCapabilities(String pathToAppFile, String deviceName, String platformVersion, String appPackage, String appActivity, String bundleID) {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
//            capabilities.setCapability(MobileCapabilityType.UDID, System.getenv("ios_udid"));
            capabilities.setCapability(MobileCapabilityType.APP, pathToAppFile);
            capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, bundleID);
            capabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, System.getenv("xcodeOrgId"));
            capabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "iPhone Developer");
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3600");

            return capabilities;
        }

        public AndroidDriver<WebElement> getWebDriverObject(URL appiumServerURL, DesiredCapabilities capabilities) {

            return new AndroidDriver<>(appiumServerURL, capabilities);
        }
    }
}