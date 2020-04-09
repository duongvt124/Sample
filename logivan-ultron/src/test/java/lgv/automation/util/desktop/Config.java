package lgv.automation.util.desktop;

import lgv.automation.util.Log;
import lgv.automation.util.PropertyValues;
import lgv.automation.util.api.CommonFlow;
import org.openqa.selenium.WebDriver;

public class Config {

    public static WebDriver globalWebDriver;

    public static final int END_SCENARIO_VIEW_TIME = 0;
    public static final int WAIT_ELEMENT_TIME = 5;
    public static final int IMPLICITLY_WAIT_TIME = 15;

    public static String localOrRemoteWebDriver = "local";

    public static String remoteUrl = "http://0.0.0.0:4444/wd/hub";

    // Browser
    public static String browser = "chrome";
    public static boolean isChromeHeadless = false;
    public static boolean isFullScreen = true;

    public static String chromeDriverPath = "drivers/chromedriver_mac";

    //---------------------------------OPS TOOL--------------------------------------------------
    public static String opsToolHost = "https://logivan-salestool-testbed1.firebaseapp.com";

    public static boolean opsToolLoginFlag = false;

    public static String orderSummaryOrderType = "";


    public static String brokerPhoneNumberPrefix = "0983";


    public static void init() {

        PropertyValues propertyValues = new PropertyValues();
        String tempOpsToolHost = propertyValues.getProperty("ops.tool.host");
        String tempBrowserHeadless = propertyValues.getProperty("browser.headless");
        String os = propertyValues.getProperty("os");

        if (!tempOpsToolHost.isEmpty() && !tempOpsToolHost.contains("${ops.tool.host}")) {
            opsToolHost = tempOpsToolHost;
        }

        if (!tempBrowserHeadless.isEmpty() && !tempBrowserHeadless.contains("${browser.headless}")) {
            isChromeHeadless = Boolean.valueOf(tempBrowserHeadless);
        }

        if (!tempBrowserHeadless.isEmpty() && !tempBrowserHeadless.contains("${os}")) {

            switch (os) {

                case "linux":
                    chromeDriverPath = "drivers/chromedriver_linux";
                    break;

                case "alpine_linux":
                    chromeDriverPath = "drivers/chromedriver_alpine_linux";
                    break;

                default:
                    chromeDriverPath = "drivers/chromedriver_mac";
                    break;
            }
        }

        propertyValues.closeInit();
        Log.highlight("DESKTOP WEB CONFIG");
        Log.info("Ops tool host: " + opsToolHost);
        Log.info("Chrome driver path: " + chromeDriverPath);
        Log.info("Browser headless: " + isChromeHeadless);
        Log.printLine();
    }
}
