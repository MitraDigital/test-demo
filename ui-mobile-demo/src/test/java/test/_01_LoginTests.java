package test;

import base.Testengine;
import com.chatapp.pages.*;
import org.testng.ITestListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class _01_LoginTests extends Testengine implements ITestListener {
    public static splashScreen spl_screen;
    public static loginPage login_screen;
    public static preRequisits preReqs;
    public static verificationPage verific_screen;
    public static SettingsPage settings_page;
    public static Comms comms;
    public static ProfilePage profilePage;


    @BeforeClass
    @Override
    public void setUpPage() {
        spl_screen = new splashScreen(driver);
        login_screen = new loginPage(driver);
        preReqs = new preRequisits(driver);
        verific_screen = new verificationPage(driver);
        settings_page = new SettingsPage(driver);
        comms = new Comms(driver);
        profilePage = new ProfilePage(driver);
    }

    @Test(description = "Validate that the user able to navigate to the Language Selection page")
    public void allowAccess() {

        preReqs.allowPermissions();
    }

    @Test(priority = 0, description = "Validate that the user able to navigate to the Language Selection page")
    public void test_TC001_verifyAppLaunch() {

        log.info("Running Step: verify_all_main_page_elements_are_present()");
        spl_screen.checkElementIsPresent();
    }


    @Test(priority = 0, description = "Validate that the user able to navigate to the Phone Number page")
    public void test_TC002_verifyThePhoneNumberPage() {

        log.info("Running Step: test_TC002_verifyThePhoneNumberPage()");
        spl_screen.clickOnLogobutton();
        spl_screen.goToMobileNUmberPage();

    }

    @Test(priority = 0, description = "Validate that the user able to login with a valid phone number")
    public void test_TC003_verifyTheVerificationPage() {

        log.info("Running Step: test_TC003_verifyTheVerificationPage()");
        login_screen.enterPhoneNumber();
        login_screen.clickOnContinueButton();
        verific_screen.goTOVerificationPage();

    }
    
}
