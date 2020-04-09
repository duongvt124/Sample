package lgv.automation.serenityCucumber.api.features.steps.driver;

import io.cucumber.java.en.When;
import lgv.automation.steps.api.DriverSteps;
import lgv.automation.util.api.Config;
import net.thucydides.core.annotations.Steps;

public class AddTruck {

    @Steps
    DriverSteps driverSteps;

    @When("driver adds new truck that has truck_type_id = {int}, truck_model_id = {int}")
    public void driver_adds_new_truck(int truckTypeID, int truckModelID) {

        driverSteps.shouldBeAbleToAddTruck(
                Config.certificateRegistryUrl,
                Config.truckPlatePrefix,
                truckModelID,
                truckTypeID
        );
    }

    @When("result truck from get certificate registries api must contain truck_type_id = {int}, truck_model_id = {int}")
    public void verify_add_truck_success(int truckTypeID, int truckModelID) {
        driverSteps.verifyAddTruckSuccess(
                Config.newCertificateResistryID,
                Config.newTruckID,
                truckModelID,
                truckTypeID
        );
    }

}
