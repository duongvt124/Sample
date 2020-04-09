package lgv.automation.serenityCucumber.api.features.steps.driver;

import io.cucumber.java.en.Then;
import lgv.automation.steps.api.DriverSteps;
import lgv.automation.util.api.Config;
import net.thucydides.core.annotations.Steps;

public class GetPreferences {

    @Steps
    DriverSteps driverSteps;

    @Then("verify driver adds preferred successful from get preferences info api")
    public void verify_driver_adds_preferences_success() {

        driverSteps.verifyPreferences(Config.preferredRouteType,
                Config.areaIDHN,
                Config.areaNameHN,
                Config.areaIDHCM,
                Config.areaNameHCM
        );
    }

    @Then("engineer should be able to health check api get preferences info on driver mobile app")
    public void health_check_api_get_preferences_info() {
        driverSteps.shouldBeAbleToHealthCheckApiGetDriverPreferencesInfo();
    }

}
