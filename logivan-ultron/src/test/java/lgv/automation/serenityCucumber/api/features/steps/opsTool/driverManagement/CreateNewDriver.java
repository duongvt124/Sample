package lgv.automation.serenityCucumber.api.features.steps.opsTool.driverManagement;

import io.cucumber.java.en.Given;
import lgv.automation.steps.api.DriverSteps;
import lgv.automation.steps.api.OpsToolSteps;
import lgv.automation.util.CommonMethod;
import lgv.automation.util.api.Config;
import net.thucydides.core.annotations.Steps;

import java.util.Random;

public class CreateNewDriver {

    String passWord = Config.password;

    @Steps
    OpsToolSteps opsToolSteps;

    @Steps
    DriverSteps driverSteps;

    @Given("ops manager creates a new driver")
    public void create_a_new_driver(){

        boolean isCreateNewDriverSuccess = true;

        while (isCreateNewDriverSuccess) {
            Config.newDriverPhoneNumber = CommonMethod.generateNewPhone();
            isCreateNewDriverSuccess = opsToolSteps.shouldBeAbleToCreateNewDriver(Config.newDriverPhoneNumber, passWord);
        }
    }

    @Given("ops manager creates and verifies a new driver")
    public void create_and_verify_a_new_driver(){

//        String phoneNumber = CommonMethod.generateNewPhone();
        String phoneNumber = "+84983911547";
        opsToolSteps.shouldBeAbleToCreateNewDriver(phoneNumber, passWord);

        driverSteps.shouldBeAbleToSignIn(phoneNumber, passWord);

        opsUpdateAndVerifyNewDriver(Config.newDriverID);
    }

    private static int randomPlateSuffix() {

        Random rd = new Random();
        return rd.nextInt(9000) + 1000;
    }

    public void opsUpdateAndVerifyNewDriver(int driverID) {

        driverSteps.shouldBeAbleToAddTruck(
                Config.certificateRegistryUrl,
                Config.truckPlatePrefix,
                Config.driverTruckModelID,
                Config.driverTruckTypeID
        );

        driverSteps.shouldBeAbleToUpdateIdentityUrl(driverID, Config.identityIdUrl);

        driverSteps.shouldBeAbleToUpdateLicense(
                driverID,
                CommonMethod.generateNewLicenseID(),
                Config.licenseDate,
                Config.licenseUrl
        );

        opsToolSteps.shouldBeAbleToGetDriverDocuments(driverID);

        opsToolSteps.shouldBeAbleToUpdateDriverIdentityInfo(
                driverID,
                Config.newIdentityIDDocumentID,
                CommonMethod.generateNewIdentifyID(),
                Config.identityDate
        );

        opsToolSteps.shouldBeAbleToAcceptDriverIdentityID(driverID, Config.newIdentityIDDocumentID);

        opsToolSteps.shouldBeAbleToAcceptDriverLicense(driverID, Config.newLicenseDocumentID);

        opsToolSteps.shouldBeAbleToAcceptDriverCertificateRegistry(driverID, Config.newCertificateRegistryDocumentID);
    }

}
