package lgv.automation.serenityJunit.features.ui;

import lgv.automation.databuilder.DriverOnboardingTestDataBuilder;
import lgv.automation.model.Driver;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * Created by Duong Vu on 4/23/19.
 */
public class ReOnboardingCurrentDriver {

    @Managed
    WebDriver appium;

    @Steps
    DriverSteps driverSteps;

    Driver driver;

    @Before
    public void appBaseState() throws Exception {
        driver = DriverOnboardingTestDataBuilder.createDriver();
    }

    @After
    public void putAppInBackgroundOrTerminate(){

    }

    @Test
    public void driverReOnboardingTest(){
        driverSteps.reOnboardingFlow(driver);


    }


}
