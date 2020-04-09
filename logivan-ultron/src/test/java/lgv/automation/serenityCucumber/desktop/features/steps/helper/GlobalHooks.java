package lgv.automation.serenityCucumber.desktop.features.steps.helper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lgv.automation.controler.SeleniumController;
import lgv.automation.steps.desktop.OpsToolSteps;
import lgv.automation.util.CommandPrompt;
import lgv.automation.util.Log;
import lgv.automation.util.Timer;
import lgv.automation.util.desktop.Config;
import lgv.automation.util.desktop.UIComponent;

import java.io.IOException;
import java.net.MalformedURLException;

public class GlobalHooks {

    private static boolean dunit = false;

    @Before
    public static void beforeAll() throws MalformedURLException {
        Log.highlight("Begin init");

        if (!dunit) {
            lgv.automation.util.Config.init();
            lgv.automation.util.api.Config.init();
            Config.init();

            configBrowser(Config.localOrRemoteWebDriver);

            Log.highlight("TestSuiteMaster - Begin Test Suite !");

            dunit = true;
        }
    }

    public static void configBrowser(String webDriverType) throws MalformedURLException {

        switch (webDriverType) {

            case "remote":
                Config.globalWebDriver = SeleniumController.configBrowserWithRemoteWebDriver(Config.isFullScreen, Config.remoteUrl);
                break;

            default:
                Config.globalWebDriver = SeleniumController.configBrowserWithLocalWebDriver(Config.isFullScreen);
                break;
        }

    }

    @After("@opsTool")
    public static void afterOpsToolScenario() {

        Config.globalWebDriver = OpsToolSteps.opsToolWebDriver;

        Timer.waitFor(Config.END_SCENARIO_VIEW_TIME, 1);

        UIComponent.reloadPage(Config.globalWebDriver);
    }

    @After("@quitSelenium")
    public static void afterScenario() throws IOException, InterruptedException {

        if (OpsToolSteps.opsToolWebDriver != null) OpsToolSteps.opsToolWebDriver.quit();

        //kill chromedriver_mac process
        String os = System.getProperty("os.name");
        CommandPrompt commandPrompt = new CommandPrompt();
        if(os.contains("Windows"))
        {
            commandPrompt.runCommand("taskkill /im chromedriver_mac.exe /f");
        }
        else {
            //Mac, Linux
            commandPrompt.runCommand("killall -9 chromedriver_mac");
        }
    }
}
