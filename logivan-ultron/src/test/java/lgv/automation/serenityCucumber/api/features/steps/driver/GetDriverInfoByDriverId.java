package lgv.automation.serenityCucumber.api.features.steps.driver;

import io.cucumber.java.en.Then;
import lgv.automation.constants.api.HealthCheckParams;
import lgv.automation.serenityJunit.features.api.TestSuiteMaster;
import lgv.automation.steps.api.DriverSteps;
import lgv.automation.util.api.CommonFlow;
import lgv.automation.util.Log;
import lgv.automation.util.api.Config;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetDriverInfoByDriverId extends TestSuiteMaster {

    int driverID;

    @Steps
    DriverSteps driverSteps;

    @Then("engineer should be able to health check api get driver info on driver mobile app")
    public void health_check_api_get_driver_info() {

        driverSteps.shouldBeAbleToHealthCheckApiGetDriverInfo(HealthCheckParams.DRIVER_ID);
    }

    @Then("result name from get {string} driver info api must equal {string}")
    public void verify_update_name_success(String driverType, String newName) {

        driverID = CommonFlow.getDriverIDByType(driverType);

        driverSteps.verifyDriverName(driverID, newName);
    }

    @Then("verify {string} driver updates identity successful from result of get driver info api")
    public void verify_update_identity_success(String driverType) {

        driverID = CommonFlow.getDriverIDByType(driverType);

        driverSteps.verifyDriverIdentityInfo(
                driverID,
                Config.identityID,
                Config.identityDate,
                Config.identityIdUrl
        );
    }

    @Then("verify {string} driver updates license successful from result of get driver info api")
    public void verify_update_license_success(String driverType) {

        driverID = CommonFlow.getDriverIDByType(driverType);

        driverSteps.verifyDriverLicenseInfo(
                driverID,
                Config.licenseID,
                Config.licenseDate,
                Config.licenseUrl
        );
    }

    @Then("verify {string} driver updates business registration successful from result of get driver info api")
    public void verify_update_business_success(String driverType) {

        driverID = CommonFlow.getDriverIDByType(driverType);

        driverSteps.verifyDriverBusinessRegistrationInfo(
                driverID,
                Config.businessRegistrationID,
                Config.businessRegistrationUrl
        );
    }

    @Then("verify {string} driver updates transportation permit successful from result of get driver info api")
    public void verify_update_transportation_permit_success(String driverType) {

        driverID = CommonFlow.getDriverIDByType(driverType);

        driverSteps.verifyDriverTransportationPermitInfo(
                driverID,
                Config.transportationPermitID,
                Config.transportationPermitUrl
        );
    }

    @Test
    public void getDriverInfoByDriverIdSuccess() {

        Log.highlight("--------------- BEGIN TO TEST GET DRIVER INFO BY DRIVER ID API! ---------------");

        driverSteps.getDriverInfoByDriverIdTester(Config.driverID);

        Log.highlight("--------------- FINISH GET DRIVER INFO BY DRIVER ID API TESTING! ---------------");
    }

}
