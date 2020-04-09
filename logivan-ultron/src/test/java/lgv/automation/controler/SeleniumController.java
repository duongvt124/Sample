package lgv.automation.controler;

import lgv.automation.util.Log;
import lgv.automation.util.desktop.Config;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SeleniumController {

    public static WebDriver configBrowserWithLocalWebDriver(boolean isFullScreen) {

        WebDriver driverWeb;

        driverWeb = setupLocalWebDriver();

        driverWeb.manage().timeouts().implicitlyWait(Config.IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);

        if (isFullScreen) {
            driverWeb.manage().window().fullscreen();
        }

        Log.highlight("Init Test Browser Done !");

        return driverWeb;
    }

    public static WebDriver setupLocalWebDriver() {

        WebDriver driverWeb;

        switch (Config.browser) {
            case "firefox":
                initFireFoxBrowserCapabilities();
                driverWeb = new FirefoxDriver();
                break;

            case "safari":
                driverWeb = new SafariDriver();
                break;

            default:

                driverWeb = new ChromeDriver(initChromeOptions(Config.isChromeHeadless));
                break;
        }

        return driverWeb;
    }

    public static WebDriver configBrowserWithRemoteWebDriver(boolean isFullScreen, String seleniumServerUrl) throws MalformedURLException {

        WebDriver driverWeb = null;

        driverWeb = setupRemoteBrowser(seleniumServerUrl);

        driverWeb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        if (isFullScreen) {
            driverWeb.manage().window().fullscreen();
        }

        Log.highlight("Init Test Browser Done !");

        return driverWeb;
    }

    public static WebDriver setupRemoteBrowser(String seleniumServerUrl) throws MalformedURLException {

        WebDriver driverWeb;

        switch (Config.browser) {
            case "firefox":
            driverWeb = new RemoteWebDriver(new URL(seleniumServerUrl), initFireFoxBrowserCapabilities());
                break;

            case "safari":
                driverWeb = new SafariDriver();
                break;

            default:
                ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingAnyFreePort()
                        .build();

                driverWeb = new ChromeDriver(service, initChromeOptions(Config.isChromeHeadless));
                break;
        }

        return driverWeb;
    }

    public static DesiredCapabilities initFirefoxProfile(){
        Log.highlight(System.getProperty("user.dir"));

        File firebug = new File(System.getProperty("user.dir") +"/src/main/resources/firebug-2.0.17.xpi");

        File netExport = new File(System.getProperty("user.dir") +"/src/main/resources/netExport-0.9b7.xpi");


        FirefoxProfile profile = new FirefoxProfile();
        try {
            profile.addExtension(firebug);
            profile.addExtension(netExport);
        } catch (Exception e) {
            Log.highlight("Cannot add exxtendsions");
        }

        profile.setPreference("app.update.enabled", false);

        //Setting Firebug preferences

        profile.setPreference("extensions.firebug.currentVersion", "2.0");
        profile.setPreference("extensions.firebug.addonBarOpened", true);
        profile.setPreference("extensions.firebug.console.enableSites", true);
        profile.setPreference("extensions.firebug.script.enableSites", true);
        profile.setPreference("extensions.firebug.net.enableSites", true);
        profile.setPreference("extensions.firebug.previousPlacement", 1);
        profile.setPreference("extensions.firebug.allPagesActivation", "on");
        profile.setPreference("extensions.firebug.onByDefault", true);
        profile.setPreference("extensions.firebug.defaultPanelName", "net");

        // Setting netExport preferences

//        profile.setPreference("extensions.netmonitor.har.enableAutomation", true);
//        profile.setPreference("extensions.netmonitor.har.autoConnect", true);


        profile.setPreference("extensions.firebug.netexport.alwaysEnableAutoExport", true);
        profile.setPreference("extensions.firebug.netexport.autoExportToFile", true);
        profile.setPreference("extensions.firebug.netexport.Automation", true);
        profile.setPreference("extensions.firebug.netexport.showPreview", false);
        profile.setPreference("extensions.firebug.netexport.defaultLogDir", "/Users/vuthaiduong/Documents/LogTest/CaptureNetworkTraffic");

//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setBrowserName("firefox");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);

        capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);


        return capabilities;

    }

//    public static DesiredCapabilities initFirefoxCap(BrowserMobProxy proxyServer){
//
//        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//
//        //set firefox profile to change user agent (Lumia) to test Mobile Lite
//        FirefoxProfile ffp = new FirefoxProfile();
//        ffp.setPreference("general.useragent.override",
//                "Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; Microsoft; Lumia 950) AppleWebKit/537.36" +
//                        " (KHTML, like Gecko) Chrome/46.0.2486.0 Mobile Safari/537.36 Edge/14.14263");
//        capabilities.setCapability(FirefoxDriver.PROFILE, ffp);
//
//
//        capabilities.setCapability("marionette", true);
//
//        capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
//
////        BrowserMobProxy proxyServer = new BrowserMobProxyServer();
////        proxyServer.setTrustAllServers(true);
////        proxyServer.start(6969);
//
//        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
//        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
//
//        return capabilities;
//    }

    public static DesiredCapabilities initFireFoxBrowserCapabilities(){
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        capability.setVersion("46.0.1");
        capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
        capability.setCapability("browserstack.debug", "true");

        return capability;
    }

    public static ChromeOptions initChromeOptions(boolean isHeadless){

        // Set User Agent Google Bot
        //String userAgent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1309.0 Safari/537.17";

        DesiredCapabilities capability = new DesiredCapabilities();
        File ChromeDriverPath;
        ChromeDriverPath = new File(Config.chromeDriverPath);
        System.setProperty("webdriver.chrome.driver", ChromeDriverPath.getAbsolutePath());

        ChromeOptions co = new ChromeOptions();
        co.addArguments("--user-agent=" + userAgent);
        co.addArguments("disable-infobars");
//        co.addArguments("--incognito");

        if (isHeadless) {
            //Set Chrome headless
            co.addArguments("--headless");
        }

        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.ACCEPT);

        co.merge(capability);

        return co;
    }
}
