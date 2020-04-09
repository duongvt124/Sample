package lgv.automation.serenityJunit.features.ui;

import lgv.automation.databuilder.DriverOnboardingTestDataBuilder;
import lgv.automation.model.Driver;
import lgv.automation.model.Truck;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * Created by Duong Vu on 4/11/19.
 */

@RunWith(SerenityRunner.class)
public class DriverOnboarding {

    @Managed
    WebDriver appium;

    @Steps
    DriverSteps driverSteps;

    Driver driver;
    Truck truck_1;
    Truck truck_2;

    @Before
    public void appBaseState() throws Exception {
        driver = DriverOnboardingTestDataBuilder.createDriver();
        truck_1 = DriverOnboardingTestDataBuilder.createTruck_1();
        truck_2 = DriverOnboardingTestDataBuilder.createTruck_2();
    }

    @After
    public void putAppInBackgroundOrTerminate(){

    }

    @Test
    public void driverOnboardingTest(){
        driverSteps.driverOnboardingFlowTest(driver, truck_1);
        driverSteps.switchToOrderScreen();
        driverSteps.checkOrderScreenAfterOnboarding();
        driverSteps.switchToAccountScreen();
        driverSteps.checkAccountScreenAfterOnboarding();
        driverSteps.switchToPersonalInfoScreenAndCheck();
        driverSteps.addAndEditPersonalInfoImages();
        driverSteps.checkPopupSuccess();
        driverSteps.switchToTruckInfoScreenAndCheck();
        driverSteps.switchToAddTruckScreenAndCheck();
        driverSteps.addTruck(truck_2, false);
        driverSteps.checkPopupSuccess();
        driverSteps.checkTruckInfoScreenAfterAddTruck();  // need implement later
        driverSteps.backToAccountScreen();
        driverSteps.switchToPrioritizeOrderTypeScreenAndCheck();
        driverSteps.backToAccountScreen();
        driverSteps.switchToBusinessRegistration();
        driverSteps.checkBusinessRegistrationScreen();

    }
}
