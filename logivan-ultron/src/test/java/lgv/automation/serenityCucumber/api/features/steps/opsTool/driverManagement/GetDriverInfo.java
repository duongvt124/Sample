package lgv.automation.serenityCucumber.api.features.steps.opsTool.driverManagement;

import io.cucumber.java.en.Then;
import lgv.automation.constants.api.HealthCheckParams;
import lgv.automation.serenityJunit.features.api.TestSuiteMaster;
import lgv.automation.steps.api.OpsToolSteps;
import lgv.automation.util.api.CommonFlow;
import lgv.automation.util.Log;
import lgv.automation.util.api.Config;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetDriverInfo extends TestSuiteMaster {

    int driverID;

    @Steps
    OpsToolSteps opsToolSteps;

    @Then("ops manager verifies that {string} driver is approved from result of get driver info api")
    public void verify_update_name_success(String driverType) {

        driverID = CommonFlow.getDriverIDByType(driverType);

        opsToolSteps.verifyDriverApproved(driverID);
    }

    @Then("engineer should be able to health check api get driver info on ops tool")
    public void health_check_api_get_driver_info() {

        opsToolSteps.shouldBeAbleToHealthCheckApiGetDriverInfo(HealthCheckParams.DRIVER_ID);
    }

    @Test
    public void getDriverDocumentsSuccess(){

        Log.highlight("--------------- API TESTING - BEGIN TO GET DRIVER INFO ! ---------------");

        opsToolSteps.shouldBeAbleToGetDriverInfo(Config.driverID);

        Log.highlight("--------------- API TESTING - FINISH GET DRIVER INFO ! ---------------");
    }


}
