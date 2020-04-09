package lgv.automation.util.desktop;

import com.google.common.base.Predicate;
import lgv.automation.util.Log;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.List;

public class Timer {

    public static void waitForVisible(WebDriver driver, WebElement element, int waitSeconds ){
        WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
        Log.debug("Element that you're waiting for : " + element.getAttribute
                ("value") + " is visible !");
    }

    public static void waitForPageTitleContains( WebDriver driver,
                                                 final String startTitle, int waitSeconds ){
        Log.debug("Wait for page with title contains : '" + startTitle + "' to be load in : "
                + waitSeconds + "s !" );
        (new WebDriverWait(driver, waitSeconds)).until(new ExpectedCondition
                <Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(startTitle
                        .toLowerCase());
            }
        });
        Log.debug("Page with title contains : '" + startTitle + "' has been loaded ! ");
    }

    public static boolean waitForPageUrlContainsUrl(WebDriver driver,
                                                    final String url, int waitSeconds ){

        try {
            (new WebDriverWait(driver, waitSeconds)).until(new ExpectedCondition
                    <Boolean>() {
                public Boolean apply(WebDriver d) {
                    Log.debug("Current url : " + d.getCurrentUrl());
                    return d.getCurrentUrl().toLowerCase().contains(url
                            .toLowerCase());
                }
            });
            Log.debug("Page load with url : " + url);

            return true;
        } catch (Exception ex) {
            Log.error("Current page url does not contain " + url);
            return false;
        }
    }

    public static boolean waitForPageUrlContainsUrlAndStop(WebDriver driver,
                                                    final String url, int waitSeconds ){

        try {
            (new WebDriverWait(driver, waitSeconds)).until(new ExpectedCondition
                    <Boolean>() {
                public Boolean apply(WebDriver d) {
                    Log.debug("Current url : " + d.getCurrentUrl());
                    return d.getCurrentUrl().toLowerCase().contains(url
                            .toLowerCase());
                }
            });
            Log.debug("Page load with url : " + url);

            return true;
        } catch (Exception ex) {
            Log.errorAndStop("Current page url does not contain " + url);
            return false;
        }
    }

    public static boolean waitForPageUrlEqualUrl(WebDriver driver,
                                                  final String url, int waitSeconds ){

        try {
            (new WebDriverWait(driver, waitSeconds)).until(new ExpectedCondition
                    <Boolean>() {
                public Boolean apply(WebDriver d) {
                    Log.debug("Current url : " + d.getCurrentUrl());
                    return d.getCurrentUrl().toLowerCase().equals(url
                            .toLowerCase());
                }
            });
            Log.debug("Page load with url : " + url);

            return true;
        } catch (Exception ex) {
            Log.error("Current page url does not equal " + url);
            return false;
        }
    }

    public static boolean waitForPageUrlEqualUrlAndStop(WebDriver driver,
                                                 final String url, int waitSeconds ){

        try {
            (new WebDriverWait(driver, waitSeconds)).until(new ExpectedCondition
                    <Boolean>() {
                public Boolean apply(WebDriver d) {
                    Log.debug("Current url : " + d.getCurrentUrl());
                    return d.getCurrentUrl().toLowerCase().equals(url
                            .toLowerCase());
                }
            });
            Log.debug("Page load with url : " + url);

            return true;
        } catch (Exception ex) {
            Log.errorAndStop("Current page url does not equal " + url);
            return false;
        }
    }

    public static void waitForPageTitle( WebDriver driver,
                                         final String title, int waitSeconds ){
        Log.debug("Wait for page with title : '" + title + "' to be load in : "
                + waitSeconds + "s !");
        (new WebDriverWait(driver, waitSeconds)).until(new ExpectedCondition
                <Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains(title
                        .toLowerCase());
            }
        });
    }

    public static boolean waitForNewPageJSLoadComplete( WebDriver driver,
                                         final String title, int waitSeconds ){
        Log.debug("Wait for page with title : '" + title + "' to be load in : "
                + waitSeconds + "s !");
        WebDriverWait wait = new WebDriverWait(driver, waitSeconds);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((JavascriptExecutor)driver).executeScript("return jQuery.active")
                            .toString().equals("0");
                }
                catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public static void waitForListElementExist(WebDriver driver,
                                          List<WebElement> listElement, int waitSeconds ) {
        Log.debug("Wait for elements exist in : " + waitSeconds + "s !");
        (new WebDriverWait(driver, waitSeconds)).until(new ExpectedCondition
                <Boolean>() {
            public Boolean apply(WebDriver driver) {
                Log.debug("Wait for load list elements");
                if (listElement.size() > 0) {
                    Log.debug("List elements size: " + listElement.size());
                    return true;
                } else {
                    Log.debug("List elements size: " + listElement.size());
                    return false;
                }
            }
        });
    }

    public static void waitForDisplay( WebDriver driver,
                                       final String id, int waitSeconds  ){
        Log.debug("Wait for : " + id + " to display in : " + waitSeconds + "s");
        (new WebDriverWait(driver, waitSeconds)).until(new ExpectedCondition
                <Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id(id)).isDisplayed();
            }
        });
        Log.debug("Component with id : " + id + " has been displayed !");
    }

    public static void waitForWebTitleAppear(WebDriver webDriver, String webTitle, int waitSeconds){
        for (int second = 0;; second++) {
            if (second <= waitSeconds) {
                Log.debug("Waiting for web title appear in : " + second);
                try {
                    Assert.assertEquals(webDriver.getTitle(),webTitle);
                    break;
                } catch (Exception e) {

                }
            } else {
                Log.error("Timeout when waiting for web title appear");
            }
        }
    }

    public static void waitForCompValue( WebDriver driver,
                                         final String tagName, final String value, int waitSeconds  ){
        WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
        wait.until(ExpectedConditions.textToBePresentInElement((WebElement) By.tagName(tagName),value));
    }
}
