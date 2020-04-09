package lgv.automation.serenityCucumber.api.features.steps.opsTool.driverManagement;

import io.cucumber.java.en.When;
import lgv.automation.serenityJunit.features.api.TestSuiteMaster;
import lgv.automation.steps.api.DriverSteps;
import lgv.automation.steps.api.OpsToolSteps;
import lgv.automation.util.api.CommonFlow;
import lgv.automation.util.Log;
import lgv.automation.util.api.Config;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class AcceptDriverDocument extends TestSuiteMaster {

    int driverID = Config.driverID;

    @Steps
    OpsToolSteps opsToolSteps;

    @Steps
    DriverSteps driverSteps;

    @When("ops manager accepts {string} driver identity")
    public void accept_driver_identify(String driverType) {

        driverID = CommonFlow.getDriverIDByType(driverType);

        opsToolSteps.shouldBeAbleToAcceptDriverIdentityID(driverID, Config.newIdentityIDDocumentID);
    }

    @When("ops manager accepts {string} driver license")
    public void accept_driver_license(String driverType) {

        driverID = CommonFlow.getDriverIDByType(driverType);

        opsToolSteps.shouldBeAbleToAcceptDriverLicense(driverID, Config.newLicenseDocumentID);
    }

    @When("ops manager accepts {string} driver certificate registry")
    public void accept_driver_certificate_registry(String driverType) {

        driverID = CommonFlow.getDriverIDByType(driverType);

        opsToolSteps.shouldBeAbleToAcceptDriverCertificateRegistry(driverID, Config.newCertificateRegistryDocumentID);
    }

    @Before
    public void initTest() {
        opsToolSteps.shouldBeAbleToGetDriverDocuments(driverID);

         // Have to reject the documents before accepting them
        opsToolSteps.shouldBeAbleToRejectDriverIdentityID(driverID, Config.newIdentityIDDocumentID);

        opsToolSteps.shouldBeAbleToRejectDriverLicense(driverID, Config.newLicenseDocumentID);

        opsToolSteps.shouldBeAbleToRejectDriverCertificateRegistry(driverID, Config.newCertificateRegistryDocumentID);
    }

    @Test
    public void acceptDriverDocumentSuccess(){

        Log.highlight("--------------- API TESTING - BEGIN TO ACCEPT DRIVER DOCUMENT ! ---------------");

        opsToolSteps.shouldBeAbleToGetDriverDocuments(driverID);

        opsToolSteps.shouldBeAbleToAcceptDriverIdentityID(driverID, Config.newIdentityIDDocumentID);

        opsToolSteps.shouldBeAbleToAcceptDriverLicense(driverID, Config.newLicenseDocumentID);

        opsToolSteps.shouldBeAbleToAcceptDriverCertificateRegistry(driverID, Config.newCertificateRegistryDocumentID);

        opsToolSteps.verifyDriverApproved(driverID);

        driverSteps.verifyDriverApproved(driverID);

        Log.highlight("--------------- API TESTING - FINISH ACCEPT DRIVER DOCUMENT ! ---------------");
    }



}
