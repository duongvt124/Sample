package lgv.automation.serenityCucumber.api.features.steps.driver;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lgv.automation.serenityJunit.features.api.TestSuiteMaster;
import lgv.automation.steps.api.DriverSteps;
import lgv.automation.util.Log;
import lgv.automation.util.api.Config;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class AddPreferences extends TestSuiteMaster {
    @Steps
    DriverSteps driverSteps;

    @When("driver adds preferred route type = {string}, pick up area id = {int} and preferred dropoff area id = {int}")
    public void driver_adds_preferences(String preferredRouteType, int fromAreaID, int toAreaID) {

        driverSteps.shouldBeAbleToAddPreferences(
                preferredRouteType,
                fromAreaID,
                Config.areaNameHN,
                Config.latHN,
                Config.lngHN,
                toAreaID,
                Config.areaNameHCM,
                Config.latHCM,
                Config.lngHCM
        );
    }

    @Test
    public void addPreferencesSuccess() {

        Log.highlight("--------------- API TESTING - BEGIN TO ADD PREFERENCES ! ---------------");

        driverSteps.shouldBeAbleToAddPreferences(
                Config.preferredRouteType,
                Config.areaIDHCM,
                Config.areaNameHCM,
                Config.latHCM,
                Config.lngHCM,
                Config.areaIDHP,
                Config.areaNameHP,
                Config.latHP,
                Config.lngHP
        );

        driverSteps.verifyPreferences(Config.preferredRouteType,
                Config.areaIDHCM,
                Config.areaNameHCM,
                Config.areaIDHP,
                Config.areaNameHP
        );
    }

}
