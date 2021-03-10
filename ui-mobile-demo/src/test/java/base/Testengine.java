package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;


public abstract class Testengine {

    public static Logger log;
    public static AppiumDriverType selectedDriverType;
    public static String pathToAppFile;
    public static AppiumDriver<MobileElement> driver;
    protected static Properties properties;

    /*
     * This method is used for initializing the Log4j and Config.properties

     */

    @BeforeClass
    public abstract void setUpPage();


    private static void determineEffectiveDriverType() {
        AppiumDriverType driverType = null;
        try {
            System.out.println(properties.getProperty("PLATFORM_NAME"));
            if (properties.getProperty("PLATFORM_NAME").equals("iOS")) {
                driverType = AppiumDriverType.valueOf(properties.getProperty("PLATFORM_NAME"));
            } else {
                driverType = AppiumDriverType.valueOf(properties.getProperty("PLATFORM_NAME").toUpperCase());
            }

        } catch (IllegalArgumentException ignored) {

            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");

        } catch (NullPointerException ignored) {

            System.err.println("No driver specified, defaulting to '" + driverType + "'...");

        }
        selectedDriverType = driverType;
    }

    @BeforeClass
    public void startSuite() {
        log = Logger.getLogger("devpinoyLogger");
        log.info("Test Started successfully");

        log.info("Initializing the Config.properties file");
        // Path where Config.properties file is placed
        properties = new Properties();

        try {

            properties.load(base.Testengine.class.getClassLoader().getResourceAsStream("config.properties"));
            startAppium();

        } catch (IOException e) {

            log.fatal("Unable to find/Load the Properties file ", e);

        }


    }

    /*
     * This method is used for initiate the AppiumDriver with caps and connection protocol
     */
    public void startAppium() {
        try {
            determineEffectiveDriverType();

            if (properties.getProperty("PLATFORM_NAME").contentEquals("iOS")) {

                pathToAppFile = System.getProperty("user.dir") + properties.getProperty("IPA_PATH");
                System.out.println(pathToAppFile);

            } else {

                pathToAppFile = System.getProperty("user.dir") + properties.getProperty("APK_PATH");


            }

            if (properties.getProperty("launch_mode").contentEquals("AWS")) {

                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("DEVICE_NAME"));
                capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "36000");
                capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                URL url = new URL(properties.getProperty("APPIUM_SERVER_LOCATION"));
                driver = new AppiumDriver<MobileElement>(url, capabilities);

            } else {

                DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities(pathToAppFile,
                        properties.getProperty("DEVICE_NAME"), properties.getProperty("PLATFORM_VERS"),
                        properties.getProperty("appPackage"), properties.getProperty("appActivity"), properties.getProperty("bundleID"));
                System.out.println("\n");
                System.out.println("Current Appium Config Selection: " + selectedDriverType);
                System.out.println("Current Appium Server Location: " + properties.getProperty("APPIUM_SERVER_LOCATION"));
                System.out.println("\n");
                driver = selectedDriverType.getWebDriverObject(new URL(properties.getProperty("APPIUM_SERVER_LOCATION")), desiredCapabilities);
                log.info("Driver declared successfully : " + driver);

            }

        } catch (Exception e) {
            driver = null;
            log.fatal("Driver declaration failed : " + driver);
            log.fatal(e.getStackTrace());

        }

    }


    @AfterClass
    public void closeTheSession() {
        driver.quit();
    }

}
