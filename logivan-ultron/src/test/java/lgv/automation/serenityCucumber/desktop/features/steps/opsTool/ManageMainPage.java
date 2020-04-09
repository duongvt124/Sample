package lgv.automation.serenityCucumber.desktop.features.steps.opsTool;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lgv.automation.steps.desktop.OpsToolSteps;
import lgv.automation.util.api.CommonFlow;
import lgv.automation.util.api.Config;
import net.thucydides.core.annotations.Steps;

public class ManageMainPage {

    @Steps
    OpsToolSteps opsToolSteps;

    @Steps
    lgv.automation.steps.api.OpsToolSteps apiOpsToolSteps;

    @Given("ops manager should be able to allow notification on ops tool")
    public void allow_notification() {

        opsToolSteps.allowNotification();
    }

    @Given("ops manager should be able to change language to English on ops tool")
    public void configure_english_language() {

        opsToolSteps.changeToEnglishLanguage();
    }

    @Given("ops manager should be able to change backend url = {string} on ops tool")
    public void setting_backend(String backEndUrl) {

        opsToolSteps.settingBackEnd(backEndUrl);
    }

    @When("ops manager should be able to go to booking creation page on ops tool")
    public void go_to_booking_creation_page() {

        opsToolSteps.goToBookingCreationPage();
    }

    @When("ops should be able to go to order need truck page on ops tool")
    public void go_to_order_need_truck_page() {

        opsToolSteps.goToOrderNeedTruckPage();
    }

    @When("create a valid combination order by api and go to order need truck page on ops tool")
    public void create_a_valid_combination_order_by_api_and_go_to_order_need_truck_page() {

        CommonFlow.createCombinationOrder(Config.brokerEmail);

        opsToolSteps.goToOrderNeedTruckPage();
    }

    @When("ops manager should be able to go to broker management page on ops tool")
    public void go_to_shipper_management_page() {

        opsToolSteps.goToBrokerManagementPage();
    }

}
