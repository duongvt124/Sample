package lgv.automation.serenityCucumber.api.features.steps.opsTool.driverManagement;

import io.cucumber.java.en.When;
import lgv.automation.serenityJunit.features.api.TestSuiteMaster;
import lgv.automation.steps.api.OpsToolSteps;
import lgv.automation.util.CommonMethod;
import lgv.automation.util.api.CommonFlow;
import lgv.automation.util.api.Config;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UpdateDriverInfo extends TestSuiteMaster {

    @Steps
    OpsToolSteps opsToolSteps;

    @When("ops manager updates {string} driver identity image")
    public void ops_updates_driver_identify_image(String driverType) {

        int driverID = CommonFlow.getDriverIDByType(driverType);

        opsToolSteps.shouldBeAbleToUpdateDriverIdentityImage(driverID, Config.identityIdUrl);
    }

    @When("ops manager updates {string} driver identity info")
    public void ops_updates_driver_identify_info(String driverType) {

        int driverID = CommonFlow.getDriverIDByType(driverType);

        opsToolSteps.shouldBeAbleToUpdateDriverIdentityInfo(
                driverID,
                Config.newIdentityIDDocumentID,
                CommonMethod.generateNewIdentifyID(),
                Config.identityDate
        );
    }

    @When("ops manager updates {string} driver license image")
    public void ops_updates_driver_license_image(String driverType) {

        int driverID = CommonFlow.getDriverIDByType(driverType);

        opsToolSteps.shouldBeAbleToUpdateDriverLicenseImage(driverID, Config.licenseUrl);
    }

    @When("ops manager updates {string} driver license info")
    public void ops_updates_driver_license_info(String driverType) {

        int driverID = CommonFlow.getDriverIDByType(driverType);

        opsToolSteps.shouldBeAbleToUpdateDriverLicenseInfo(
                driverID,
                Config.newLicenseDocumentID,
                CommonMethod.generateNewLicenseID(),
                Config.licenseDate);
    }

    @Test
    public void updates_driver_identify_image() {

        opsToolSteps.shouldBeAbleToUpdateDriverIdentityImage(Config.driverID, Config.identityIdUrl);
    }

}
