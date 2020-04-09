package lgv.automation.serenityCucumber.desktop.features.steps.opsTool.order;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lgv.automation.steps.desktop.OpsToolSteps;
import lgv.automation.util.Log;
import lgv.automation.util.desktop.Config;
import net.thucydides.core.annotations.Steps;

public class FilterOrders {

    @Steps
    OpsToolSteps opsToolSteps;

    @Given("ops should be able to go to order list page by url")
    public void go_to_order_list_page_by_url() {

        opsToolSteps.openOrderNeedTruckPage();
    }

    @When("ops should be able to filter orders by order type = {string}")
    public void filter_orders_by_order_type(String orderType) {

        opsToolSteps.filterOrderByOrderType(orderType);
    }


    @When("ops should be able to filter orders by order status = {string}")
    public void filter_orders_by_order_status(String orderStatus) {

        opsToolSteps.filterOrderByOrderStatus(orderStatus);
    }

    @When("ops should be able to filter orders by is share truck = {string}")
    public void filter_orders_by_is_share_truck(String orderStatus) {

        opsToolSteps.filterOrderByIsShareTruck(orderStatus);
    }

    @When("ops should be able to view order detail by clicking on order id")
    public void view_order_detail() {

        opsToolSteps.viewOrderDetailByClickOnOrderID();
    }

    @When("ops should be able to filter orders by broker phone = {string}")
    public void filter_orders_by_broker_phone(String brokerPhone) {

        opsToolSteps.filterOrderByBrokerPhone(brokerPhone);
    }

    @When("ops should be able to filter orders by order id = {int}")
    public void filter_orders_by_broker_phone(int orderID) {

        opsToolSteps.filterOrderByOrderID(orderID);
    }

    @When("ops should be able to filter orders by cargo type = {string}")
    public void filter_orders_by_cargo_type(String cargoType) {

        opsToolSteps.filterOrderByCargoType(cargoType);
    }

    @When("ops should be able to filter orders by cargo weight = {string}")
    public void filter_orders_by_cargo_weight(String cargoWeight) {

        opsToolSteps.filterOrderByCargoWeight(cargoWeight);
    }

    @When("ops should be able to filter orders by pick up location = {string}")
    public void filter_orders_by_pick_up_location(String areaName) {

        opsToolSteps.filterOrderByPickupLocation(areaName);
    }

    @When("ops should be able to filter orders by drop off location = {string}")
    public void filter_orders_by_drop_off_location(String areaName) {

        opsToolSteps.filterOrderByDropOffLocation(areaName);
    }

    @When("ops should be able to filter orders by pick up date = {string}")
    public void filter_orders_by_pick_up_date(String pickUpDate) {

        opsToolSteps.filterOrderByPickupLocation(pickUpDate);
    }

    @When("ops should be able to filter orders by drop off date = {string}")
    public void filter_orders_by_drop_off_date(String dropOffDate) {

        opsToolSteps.filterOrderByDropOffLocation(dropOffDate);
    }

    @When("ops should be able to filter orders by assigned person name = {string}")
    public void filter_orders_by_assigned_person_name(String assignedPersonName) {

        opsToolSteps.filterOrderByOpsInCharge(assignedPersonName);
    }

    @Then("ops should be able to verify broker name of order = {string} on order list page")
    public void verify_order_broker_name(String brokerName) {

        opsToolSteps.verifyBrokerNameOfFirstOrder(brokerName);
    }

    @Then("ops should be able to verify cargo type of order = {string} on order list page")
    public void verify_order_cargo_type(String cargoType) {

        opsToolSteps.verifyCargoTypeOfFirstOrder(cargoType);
    }

    @Then("ops should be able to verify cargo weight of order = {string} on order list page")
    public void verify_order_cargo_weight(String cargoWeight) {

        opsToolSteps.verifyCargoWeightOfFirstOrder(cargoWeight);
    }

    @Then("ops should be able to verify pick up location of order = {string}")
    public void verify_order_pick_up_location(String areaName) {

        opsToolSteps.verifyPickUpLocationOfFirstOrder(areaName);
    }

    @Then("ops should be able to verify drop off location of order = {string}")
    public void verify_order_drop_off_location(String areaName) {

        opsToolSteps.verifyDropOffLocationOfFirstOrder(areaName);
    }

    @Then("ops should be able to verify order status = {string} on order list page")
    public void verify_order_status(String orderStatus) {

        opsToolSteps.verifyOrderStatusOfFirstOrder(orderStatus);
    }

    @Then("ops should be able to verify order is share truck = {string} on order list page")
    public void verify_order_is_share_truck(String isShareTruck) {

        opsToolSteps.verifyOrderIsShareTruckOfFirstOrder(isShareTruck);
    }

    @Then("ops should be able to verify assigned person name of order = {string}")
    public void verify_order_assigned_person(String assignedPersonName) {

        opsToolSteps.verifyAssignedPersonNameOfFirstOrder(assignedPersonName);
    }
}
