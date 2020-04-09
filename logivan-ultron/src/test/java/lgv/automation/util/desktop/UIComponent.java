package lgv.automation.util.desktop;

import lgv.automation.util.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UIComponent {

    static int waitTime = 5;

    public static void reloadPage(WebDriver driver){
        Log.debug("Reload page");
        driver.navigate().refresh();
    }

    public static void switchTab(WebDriver driver, int tabIndex){

        Log.debug("Switch to tab " + tabIndex);
        ArrayList<String> windowTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windowTabs.get(tabIndex));
    }

    public static void switchTab(WebDriver driver, String title){
        Set<String> windows = driver.getWindowHandles();
        Log.info("Number of tab : " + windows.size() + ", need to switch to : " +
                "" + title);
        for (String window : windows) {
            Log.info("In checking tab with name : " + window);
            driver.switchTo().window(window);
            if (driver.getTitle().contains(title)) {
                return;
            }
        }
    }

    public static void switchToFinalTab(WebDriver driver){

        Log.debug("Switch to final tab");
        ArrayList<String> windowTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windowTabs.get(windowTabs.size() - 1));
    }

    public static void removeFinalTab(WebDriver driver){

        ArrayList<String> windowTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windowTabs.get(windowTabs.size() - 1));
        driver.close();
    }

    public static void removeTab(WebDriver driver, String tabName){
        try{
            Log.info("Tab that need to remove : " + tabName);
            ArrayList<String> windowTabs = new ArrayList<String>(driver.getWindowHandles());
            for(String tab : windowTabs){
                Log.info("Tab : " + tab);
            }
            driver.switchTo().window(tabName);
            driver.close();
            driver.switchTo().window(windowTabs.get(1));

        } catch(Exception ex){
            Log.errorAndStop("Have errror when remove first tab : " + ex.getMessage());
        }

    }

    public static boolean selectDropDownBoxItemByIndex(WebElement dropDownBox,
                                                       int index) {
        try {
            Select dropDownSelecter = new Select(dropDownBox);
            dropDownSelecter.selectByIndex(index);
            return true;
        } catch (Exception ex) {
            Log.errorAndStop("ERROR : have exception in selectDropDownBoxItemByIndex" +
                    " funtion with WebElement : "
                    + dropDownBox.getAttribute("id")
                    + " / Error msg : "
                    + ex.getMessage());
            return false;
        }

    }

    public static boolean selectDropDownBoxItemByVisibleText(WebElement dropDownBox,
                                                             String visibleText) {
        try {
            Select dropDownSelecter = new Select(dropDownBox);
            dropDownSelecter.selectByVisibleText(visibleText);
            return true;
        } catch (Exception ex) {
            Log.errorAndStop("ERROR : have exception in selectDropDownBoxItemByText" +
                    " funtion with WebElement : "
                    + dropDownBox.getAttribute("id")
                    + " / Error msg : "
                    + ex.getMessage());
            return false;
        }

    }

    public static boolean selectSuggestionItem(WebDriver driver,
                                               String parentDivId) {
        try {

            List<WebElement> suggestionList = driver.findElements(By
                    .xpath("//div[@id='" + parentDivId + "']/div/ul/li"));
            Log.info("Number of suggestion: " + +suggestionList.size());
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(By
                    .id(parentDivId)));
            for (WebElement suggestion : suggestionList) {
                Log.info("Choose suggestion : " + suggestion.getText());
                suggestion.click();
                break;
            }
            return true;
        } catch (Exception ex) {
            Log.errorAndStop("ERROR : have exception in selectSuggestionItem funtion " +
                    "with parentDivId : "
                    + parentDivId + " / Error msg : " + ex.getMessage());
            return false;
        }

    }

    public static void fillText(WebDriver driver, WebElement element, String text ){

        Log.info("Fill : " + text + " in web element");
        try{
            Timer.waitForVisible(driver, element, waitTime);
            element.clear();
            element.sendKeys(text);
        } catch(Exception ex1){

            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(element);
                actions.click();
                actions.sendKeys(text);
                actions.build().perform();
            } catch (Exception ex2) {

                Log.errorAndStop("Have error when input : " + text + " into : "
                        + element.getAttribute("name") + " | Error message : "
                        + ex2.getMessage());
            }
        }
    }

    public static void pressKeyboardKeys(WebDriver driver, WebElement element, CharSequence charSequence){

        Log.info("Press keyboard keys in web element");
        try{
            Timer.waitForVisible(driver, element, waitTime);
            element.clear();
            element.sendKeys(charSequence);
        } catch(Exception ex1){

            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(element);
                actions.click();
                actions.sendKeys(charSequence);
                actions.build().perform();
            } catch (Exception ex2) {

                Log.errorAndStop("Have error when press keyboard keys |" +
                        " Error message : " + ex2.getMessage());
            }
        }
    }

    public static void pressKeyboardKeysOnDropdown(WebDriver driver, WebElement element, CharSequence charSequence){

        Log.info("Press keyboard keys in web element");

        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element);
            actions.sendKeys(charSequence);
            actions.build().perform();
        } catch (Exception ex2) {

            Log.errorAndStop("Have error when press keyboard keys |" +
                    " Error message : " + ex2.getMessage());
        }
    }

    public static void fillTextWithoutClear(WebDriver driver, WebElement element, String text ){

        Log.info("Fill : " + text + " in web element");
        try{
            Timer.waitForVisible(driver, element, waitTime);
            element.sendKeys(text);
        } catch(Exception ex1){

            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(element);
                actions.click();
                actions.sendKeys(text);
                actions.build().perform();
            } catch (Exception ex2) {

                Log.errorAndStop("Have error when input : " + text + " into : "
                        + element.getAttribute("name") + " | Error message : "
                        + ex2.getMessage());
            }
        }
    }

    public static void clickOnElement(WebDriver driver, WebElement element) {

        Log.info("Click on web element");
        try {
            Timer.waitForVisible(driver, element, waitTime);
            element.click();

        } catch (Exception ex1){

            try {
                JavascriptExecutor je = (JavascriptExecutor)driver;
                je.executeScript("arguments[0].click();", element);

            } catch (Exception ex2){

                Log.errorAndStop("Have error when click : "
                        + element.getAttribute("name") + " | Error message : "
                        + ex2.getMessage());
            }

        }
    }

    public static void doubleClickElement(WebDriver driver, WebElement element) {

        Log.info("Double click on web element");

        Actions actions = new Actions(driver);

        try {
            Timer.waitForVisible(driver, element, waitTime);
            actions.doubleClick(element).perform();

        } catch (Exception ex){

                Log.errorAndStop("Have error when double click : "
                        + element.getAttribute("name") + " | Error message : "
                        + ex.getMessage());
        }
    }

    // context click = right click
    public static void rightClickElement(WebDriver driver, WebElement element) {

        Log.info("Right click on web element");

        Actions actions = new Actions(driver);

        try {
            Timer.waitForVisible(driver, element, waitTime);
            actions.contextClick(element).perform();

        } catch (Exception ex){

            Log.errorAndStop("Have error when right click : "
                    + element.getAttribute("name") + " | Error message : "
                    + ex.getMessage());
        }
    }

    public static String getElementInnerText(WebDriver driver, WebElement element) {
        try {
            Timer.waitForVisible(driver, element, waitTime);

            String elementInnerText = element.getText();
            Log.debug("Element inner text: " + elementInnerText);
            return elementInnerText;

        } catch(Exception ex){
            Log.errorAndStop("Have error when click : "
                    + element.getAttribute("name") + " | Error message : "
                    + ex.getMessage());

            return null;
        }

    }

    public static WebElement getFirstOptionFromSelect( WebElement selectElement){
        Select select = new Select(selectElement);
        WebElement option = select.getFirstSelectedOption();

        return option;
    }

    public void scrollIntoView(WebDriver driver, WebElement webElement){

        JavascriptExecutor je = (JavascriptExecutor)driver;
        je.executeScript("arguments[0].scrollIntoView(true);",webElement);
    }

    public static boolean isDisable(WebElement element) {

        // If element is disabled => return "true"
        return  Boolean.valueOf(element.getAttribute("disabled"));
    }

    public static void backPreviousPage( WebDriver driver){
        driver.navigate().back();
    }
}
