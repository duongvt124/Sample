package lgv.automation.serenityCucumber.api.features.steps.opsTool.driverManagement;

import io.cucumber.java.en.Given;
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
public class GetDriverDocuments extends TestSuiteMaster {

    int driverID;

    @Steps
    OpsToolSteps opsToolSteps;

    @Given("ops manager gets {string} driver document")
    public void verify_update_name_success(String driverType) {

        driverID = CommonFlow.getDriverIDByType(driverType);

        opsToolSteps.shouldBeAbleToGetDriverDocuments(driverID);
    }

    @Given("engineer should be able to health check api get driver document on ops tool")
    public void health_check_api_get_driver_document() {

        opsToolSteps.shouldBeAbleToHealthCheckApiGetDriverDocuments(HealthCheckParams.DRIVER_ID);
    }

    @Test
    public void getDriverDocumentsSuccess(){

        Log.highlight("--------------- API TESTING - BEGIN TO GET DRIVER DOCUMENTS ! ---------------");

        opsToolSteps.shouldBeAbleToGetDriverDocuments(Config.driverID);

        Log.highlight("--------------- API TESTING - FINISH GET DRIVER DOCUMENTS ! ---------------");
    }


}
