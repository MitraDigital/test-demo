/*
 * Copyright 2014-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.chatapp.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.*;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static base.Testengine.log;
import static io.appium.java_client.pagefactory.AppiumFieldDecorator.DEFAULT_WAITING_TIMEOUT;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public abstract class BasePage {

    protected final AppiumDriver driver;
    private static int timeoutValue = 30;


    protected BasePage(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, DEFAULT_WAITING_TIMEOUT), this);
    }



    /**
     * This method checks for the presence of element on mobile app
     *
     * @param locator - parameter for mobile app element locator
     * @return - it returns the boolean value for visibility of element
     */
    public boolean waitForElementPresent(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue);
        boolean flag = true;
        try {
            wait.ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception Ex) {
            flag = false;
            log.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
            Assert.fail();
        }
        return flag;
    }

    public boolean waitForElementPresent(By locator, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        boolean flag = true;
        try {
            wait.ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception Ex) {
            flag = false;
            log.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
            System.out.println("Elements not present on the page: "+locator.toString());
        }
        return flag;
    }

    /**
     * This method checks for the visibility of element on mobile app using element instead of locator
     *
     * @param element - parameter for mobile app element
     * @return - it returns the boolean value for visibility of element
     */
    public boolean waitForElementVisible(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue);
        boolean flag = true;
        try {
            wait.ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.visibilityOf(element));
        } catch (Exception Ex) {
            flag = false;
            log.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
            Assert.fail();
        }
        return flag;
    }

    /**
     * This method checks for the visibility of element on mobile app using locator
     *
     * @param locator - parameter for mobile app element
     * @return - it returns the boolean value for visibility of element
     */
    public boolean waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue);
        boolean flag = true;
        try {
            wait.ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception Ex) {
            flag = false;
            log.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
            Assert.fail();

        }
        return flag;
    }

    /**
     * This method checks for the invisibility of element on mobile app
     *
     * @param locator - parameter for element locator
     * @return - it returns the boolean value for invisibility of element
     */
    public boolean waitForInvisibilityOfElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue);
        boolean flag = true;
        try {
            wait.ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception Ex) {
            flag = false;
            log.error("Exception Occurred While Checking Invisibility of The Element: " + Ex.getMessage());
            Assert.fail();
        }
        return flag;
    }

    /**
     * This method checks whether element is clickable or not
     *
     * @param locator - parameter for element locator
     * @return - it returns the boolean value for element clickable condition
     */
    public boolean waitForElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue);
        boolean flag = true;
        try {
            wait.ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception Ex) {
            System.out.println("Element cannot clickable or isn't present");
            flag = false;
            log.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
            Assert.fail();
        }
        return flag;
    }

    /**
     * This method checks for the all elements visibility on mobile app provided in the list
     *
     * @param arrayList - List of all elements needs to check on application
     * @return - boolean value for each element located on app
     */
    public boolean verifyElementsLocated(ArrayList<By> arrayList) {
        boolean flag = true;
        try {
            for (By locator : arrayList) {
                if (waitForElementPresent(locator)) {
                    log.info(locator.toString() + ": element with class property displayed.");
                } else {
                    log.error(locator.toString() + ": element with class property isn't displayed.");
                    flag = false;
                }
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Locating The Elements: " + Ex.getMessage());
            flag = false;
            Assert.fail();
        }
        return flag;
    }

    /**
     * This is a reusable method to enter text in input elements
     *
     * @param locator - parameter for element locator
     * @param value   - text values to be entered in input elements
     */
    public void enterText(By locator, String value) {
        try {
            if (waitForElementPresent(locator)) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(value);
                log.info("Entering text to element: " + locator.toString());
                System.out.println("Entering text to element: " + locator.toString()+"    Text: "+value);
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Entering The Text: " + Ex.getMessage());
            Assert.fail();

        }


    }

    public void clickAndEnterText(By locator, String value) {
        try {
            if (waitForElementPresent(locator)) {
                driver.findElement(locator).click();
                driver.findElement(locator).sendKeys(value);
                log.info("Entering text to element: " + locator.toString());
                System.out.println("Entering text to element: " + locator.toString()+"    Text: "+value);
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Entering The Text: " + Ex.getMessage());
            System.out.print("Exception Occurred While Entering The Text: " + Ex.getMessage());

        }

    }

    public void clickClearAndEnterText(By locator, String value) {
        try {
            if (waitForElementPresent(locator)) {
                driver.findElement(locator).click();
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(value);
                log.info("Entering text to element: " + locator.toString());
                System.out.println("Entering text to element: " + locator.toString()+"    Text: "+value);
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Entering The Text: " + Ex.getMessage());
            System.out.print("Exception Occurred While Entering The Text: " + Ex.getMessage());

        }

    }

    /**
     * This is a reusable method to send enter key
     *
     * @param locator - parameter for element locator
     */
    public void pressEnterKey(By locator) {
        try {
            if (waitForElementVisible(locator)) {
                Thread.sleep(500);
                MobileElement element = (MobileElement) driver.findElement(locator);
                element.sendKeys(Keys.ENTER);
                Thread.sleep(500);
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Sending Enter Key" + Ex.getMessage());
        }
    }

    /**
     * This is a reusable method to click on mobile elements
     *
     * @param locator - parameter for element locator
     */
    public void clickElement(By locator) {

        try {
            if (waitForElementClickable(locator)) {
                driver.findElement(locator).click();
                Thread.sleep(5000);
                log.info("Clicking on element: " + locator.toString());
                System.out.println("Clicking on element: " + locator.toString());
            }else {

                System.out.println("Cannot Click on the element");

            }

        } catch (Exception Ex) {
            System.out.println("Cannot Click on the element");
            System.out.println("Cannot Click on the element: " + Ex.getMessage());
            log.error("Exception Occurred While Clicking The Element: " + Ex.getMessage());
            Assert.fail();

        }


    }

    public void selectFromVisibleText(By locator, String visibeText) {

        try {
            if (waitForElementClickable(locator)) {

                Select dropDown = new Select(driver.findElement(locator));
                dropDown.selectByVisibleText(visibeText);
                log.info("Clicking on element: " + locator.toString());
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Clicking The Element: " + Ex.getMessage());
            Assert.fail();


        }

    }

    public void seclectClassAndClickElement(By locator, int classIndex) {
        try {
            if (waitForElementClickable(locator)) {
                log.info("Clicking on element: " + locator.toString());
                WebElement element = (WebElement) driver.findElements(locator).get(classIndex);
                element.click();
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Clicking The Element: " + Ex.getMessage());
            Assert.fail();

        }

    }

    /**
     * This is a reusable method to move and click on mobile elements
     *
     * @param locator - parameter for element locator
     */
    public void moveToElementAndClick(By locator, long timeout) {
        try {
            if (waitForElementClickable(locator)) {
                MobileElement element = (MobileElement) driver.findElement(locator);
                Actions action = new Actions(driver);
                action.moveToElement(element).wait(timeout);
                action.click();
                log.info("Clicking on element: " + locator.toString());
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Clicking The Element: " + Ex.getMessage());
            Assert.fail();

        }

    }
    public void longTapElement(By locator) {
        try {
            if (waitForElementClickable(locator)) {
                MobileElement element = (MobileElement) driver.findElement(locator);
                TouchAction action = new TouchAction(driver);
                action.longPress(longPressOptions().withElement(element(element)));
                action.perform();
                log.info("Long pressed on element: " + locator.toString());
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Clicking The Element: " + Ex.getMessage());
            Assert.fail();

        }


    }

    public void moveToElement(By locator, long timeout) {
        try {
            MobileElement element = (MobileElement) driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, String> scrollObject = new HashMap<String, String>();
            scrollObject.put("direction", "down");
            scrollObject.put("element", ((RemoteWebElement) element).getId());
            js.executeScript("mobile: scroll", scrollObject);
        } catch (Exception Ex) {
            log.error("Exception Occurred While Clicking The Element: " + Ex.getMessage());
            Assert.fail();

        }

    }

    /**
     * This is a reusable method to get the attribute value from mobile elements
     *
     * @param locator - parameter for element locator
     * @return - it returns the attribute value
     */
    public String getTextByAttributeValue(By locator) {
        String text = null;
        try {
            if (waitForElementPresent(locator)) {
                MobileElement element = (MobileElement) driver.findElement(locator);
                text = element.getAttribute("value");
                log.info("Getting Text From locator: " + text);
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Getting The Text: " + Ex.getMessage());
            Assert.fail();

        }
        return text;
    }

    public String getTextByAttributeText(By locator) {
        String text = null;
        try {
            if (waitForElementPresent(locator)) {
                text = driver.findElement(locator).getAttribute("text");
                log.info("Getting Text From locator: " + text);
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Getting The Text: " + Ex.getMessage());
            System.out.println("Getting Text From locator: " + locator +" failed");
            Assert.fail();

        }
        return text;
    }

    public String getTextByAttributeChecked(By locator) {
        String checked = null;
        try {
            if (waitForElementPresent(locator)) {
                checked = driver.findElement(locator).getAttribute("checked");
                log.info("Getting Text From locator: " + checked);
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Getting The Text: " + Ex.getMessage());
            Assert.fail();

        }
        return checked;
    }

    /**
     * This is a reusable method to get the inner text value from mobile elements
     *
     * @param locator - parameter for element locator
     * @return - it returns the text value
     */
    public String getTextByInnerText(By locator) {
        String text = null;
        try {
            if (waitForElementPresent(locator)) {

                text = driver.findElement(locator).getText();
                log.info("Getting Text From locator: " + text);
            }
        } catch (Exception Ex) {
            log.error("Exception Occurred While Getting The Text: " + Ex.getMessage());
            Assert.fail();

        }
        return text;

    }

    /**
     * This is a reusable methos to capture the screenshot for reporting
     *
     * @param screenshotName - parameter for name of the screenshot usually appended with timestamp
     * @return - it returns the destination file path for the captured screenshot
     */
    public String takeScreenShot(String screenshotName) {
        String destination = null;
        try {
            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);

            destination = finalDestination.getAbsolutePath();
            log.info("Saving screenshot to failed repo: " + destination);

        } catch (Exception Ex) {
            log.error("Exception Occurred While Getting The Text: " + Ex.getMessage());
            Assert.fail();

        }
        return destination;
    }

    /**
     * This method verifies the actual and expected text values
     *
     * @param actualText   - parameter for actual text value
     * @param expectedText - parameter for expected text value
     * @return - returns the boolean value for text match
     */
    public boolean verifyTextMatch(String actualText, String expectedText) {
        boolean flag = false;
        try {
            log.info("Actual Text From Application Web UI --> :: " + actualText);
            System.out.println("Actual Text From Application Web UI --> :: " + actualText);
            log.info("Expected Text From Application Web UI --> :: " + expectedText);
            System.out.println("Expected Text From Application Web UI --> :: " + expectedText);

            if (actualText.equals(expectedText)) {
                log.info("### VERIFICATION TEXT MATCHED !!!");
                System.out.println("### VERIFICATION TEXT MATCHED !!!");
                flag = true;
            } else {
                log.error("### VERIFICATION TEXT DOES NOT MATCHED !!!");
                System.out.println("### VERIFICATION TEXT DOES NOT MATCHED !!!");
            }

        } catch (Exception Ex) {
            log.error("Exception Occurred While Verifying The Text Match: " + Ex.getMessage());
            System.out.println("Exception Occurred While Verifying The Text Match: " + Ex.getMessage());
            Assert.fail();

        }
        return flag;
    }

    /**
     * Creates a File if the file does not exist, or returns reference to the File if it already exists.
     *
     * @param target   - directory path
     * @param fileName - name of the file
     * @return - it returns the file path
     */
    public String createOrRetrieveFiles(String target, String fileName) {
        File directory = new File(target);
        String filePath = null;
        if (!directory.exists()) {
            boolean dirStatus = directory.mkdirs();
            if (dirStatus) {
                log.info("Target directory /" + target + "/ will be created.");
            } else {
                log.error("Target directory \"" + target + "\" not created.");
            }
        }
        filePath = target + File.separatorChar + fileName;
        return filePath;
    }

    /**
     * This function is used for vertical scroll
     *
     * @param scrollView - class name for scrollView
     * @param className  - class name for text view
     * @param text       - text of the element
     */
    public void verticalSwipe(String scrollView, String className, String text) {

        try {
            driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)" +
                            ".className(\"" + scrollView + "\")).scrollIntoView(new UiSelector()" +
                            ".className(\"" + className + "\").text(\"" + text + "\"))"));

            log.info("Vertically scrolling into the view.");
        } catch (Exception Ex) {
            log.error("Exception occurred while vertically scrolling into the view: " + Ex.getMessage());
            Assert.fail();

        }

    }

    public void swipeDown(int howManySwipes) {
        Dimension size = driver.manage().window().getSize();
        // calculate coordinates for vertical swipe
        int startVerticalY = (int) (size.height * 0.8);
        int endVerticalY = (int) (size.height * 0.21);
        int startVerticalX = (int) (size.width / 2.1);
        try {
            for (int i = 1; i <= howManySwipes; i++) {
                new TouchAction<>(driver).press(PointOption.point(startVerticalX, startVerticalY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(startVerticalX, endVerticalY)).release()
                        .perform();
            }
        } catch (Exception e) {
            //print error or something
        }

    }

    public void swipeUp(int howManySwipes) {
        Dimension size = driver.manage().window().getSize();
        // calculate coordinates for vertical swipe
        int startVerticalY = (int) (size.height * 0.8);
        int endVerticalY = (int) (size.height * 0.21);
        int startVerticalX = (int) (size.width / 2.1);
        try {
            for (int i = 1; i <= howManySwipes; i++) {
                new TouchAction<>(driver).press(PointOption.point(startVerticalX, endVerticalY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(startVerticalX, startVerticalY))
                        .release().perform();
            }
        } catch (Exception e) {
            //print error or something
        }
    }

    public void Refresh() {
        Dimension size = driver.manage().window().getSize();
        // calculate coordinates for vertical swipe
        int startVerticalY = (int) (size.height * 0.8);
        int endVerticalY = (int) (size.height * 0.21);
        int startVerticalX = (int) (size.width / 2.1);
        try {
            for (int i = 1; i <= 1; i++) {
                new TouchAction<>(driver).press(PointOption.point(startVerticalX, endVerticalY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(startVerticalX, startVerticalY))
                        .release().perform();
            }
        } catch (Exception e) {
            //print error or something
        }
    }

    public void swipeUntilElimentIsVisible(By locator) {
        int howManySwipes = 0;
        while (!waitForElementPresent(locator, 1)) {
            swipeDown(1);
            howManySwipes++;
            System.out.println(howManySwipes);
            if(howManySwipes==15){
                break;
            }
        }
        System.out.println(howManySwipes);


    }

    /**
     * This function is used for horizontal swipe
     *
     * @param scrollView - class name for scrollView
     * @param className  - class name for text view
     * @param text       - text of the element
     */
    public void horizontalSwipe(String scrollView, String className, String text) {
        try {
            driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)" +
                            ".className(\"" + scrollView + "\")).setAsHorizontalList().scrollIntoView(new UiSelector()" +
                            ".className(\"" + className + "\").text(\"" + text + "\"))"));

            log.info("Horizontally scrolling into the view.");

        } catch (Exception Ex) {
            log.error("Exception occurred while horizontally scrolling into the view: " + Ex.getMessage());
            Assert.fail();

        }

    }


}