package lgv.automation.steps.desktop;

import lgv.automation.constants.desktop.OpsToolPath;
import lgv.automation.page.desktop.opsTool.*;
import lgv.automation.page.desktop.opsTool.booking.BookingCreationPage;
import lgv.automation.page.desktop.opsTool.booking.BookingDetailPage;
import lgv.automation.page.desktop.opsTool.booking.CreateNewBrokerPage;
import lgv.automation.util.Log;
import lgv.automation.util.desktop.CommonFlow;
import lgv.automation.util.desktop.Config;
import lgv.automation.util.desktop.Timer;
import lgv.automation.util.desktop.UIComponent;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class OpsToolSteps {

    public static WebDriver opsToolWebDriver = Config.globalWebDriver;

    @Step
    public void openLoginPage() {

        String loginPageUrl = Config.opsToolHost + OpsToolPath.LOGIN;

        Log.debug("Login page url: " + loginPageUrl);
        CommonFlow.openBrowser(opsToolWebDriver, loginPageUrl);

        isLogin(loginPageUrl);
    }

    private void isLogin(String loginPageUrl) {

        String mainPageUrl = Config.opsToolHost + OpsToolPath.ORDER_NEED_TRUCK;

        if (!Timer.waitForPageUrlContainsUrl(opsToolWebDriver, loginPageUrl, 3)) {
            if (Timer.waitForPageUrlContainsUrl(opsToolWebDriver, mainPageUrl, 1)) {
                Log.debug("Login already, do what you want!");
                Config.opsToolLoginFlag = true;
            }
        }
    }

    @Step
    public void openOrderNeedTruckPage() {

        String orderNeedTruckPageUrl = Config.opsToolHost + OpsToolPath.ORDER_NEED_TRUCK;

        Log.debug("Order need truck page url: " + orderNeedTruckPageUrl);
        CommonFlow.openBrowser(opsToolWebDriver, orderNeedTruckPageUrl);
    }

    @Step
    public void login(String email, String password) {

        Log.debug("ops tool login flag = " + opsToolWebDriver);
        if (Config.opsToolLoginFlag == false) {
            LoginPage loginPage = new LoginPage(opsToolWebDriver);

            loginPage.fillEmail(email)
                    .fillPassword(password)
                    .submitLogin();

            String mainPageUrl = Config.opsToolHost + OpsToolPath.ORDER_NEED_TRUCK;
            Timer.waitForPageUrlContainsUrlAndStop(opsToolWebDriver, mainPageUrl, 15);
        }

    }

    @Step
    public void allowNotification() {

        if (Config.opsToolLoginFlag == false) {
            OpsToolMainPage opsToolMainPage = new OpsToolMainPage(opsToolWebDriver);

            opsToolMainPage.clickOnButtonAllowNotification();
        }
    }

    @Step
    public void changeToEnglishLanguage() {

        if (Config.opsToolLoginFlag == false) {
            OpsToolMainPage opsToolMainPage = new OpsToolMainPage(opsToolWebDriver);

            opsToolMainPage.clickOnIconLanguage()
                    .clickOnItemEnglish();
        }
    }

    @Step
    public void settingBackEnd(String backendUrl) {

        if (Config.opsToolLoginFlag == false) {
            OpsToolMainPage opsToolMainPage = new OpsToolMainPage(opsToolWebDriver);

            opsToolMainPage.clickOnButtonSettingBackEnd()
                    .fillBackEndUrl(backendUrl)
                    .clickOnButtonSubmitChangeBackEndUrl();
        }
    }

    @Step
    public void goToBookingCreationPage() {

        OpsToolMainPage opsToolMainPage = new OpsToolMainPage(opsToolWebDriver);

        opsToolMainPage.clickOnItemOrderCreation();
    }

    @Step
    public void goToOrderNeedTruckPage() {

        OpsToolMainPage opsToolMainPage = new OpsToolMainPage(opsToolWebDriver);

        opsToolMainPage.clickOnMenuOrderNeedTruck();
    }

    @Step
    public void goToBrokerManagementPage() {

        OpsToolMainPage opsToolMainPage = new OpsToolMainPage(opsToolWebDriver);

        opsToolMainPage.clickOnManagementMenu()
                .clickOnBrokerManagementItem();
    }

    @Step
    public void filterOrderByOrderType(String orderType) {

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        ordersNeedTruckPage.clickOnOrderTypeSpin();

        switch (orderType.toLowerCase()) {

            case "marketplace":
                ordersNeedTruckPage.clickOnMarketPlaceOrdersItem();

                break;

            case "corporate":
                ordersNeedTruckPage.clickOnCorporateOrdersItem();

                break;

            case "hybrid":
                ordersNeedTruckPage.clickOnHybridOrdersItem();

                break;

            case "enterprise":
                ordersNeedTruckPage.clickOnEnterpriseOrdersItem();

                break;

            default:
                Log.errorAndStop("order type '" + orderType + "' is wrong");
                Log.debug("There are only 4 order types: marketplace | corporate | hybrid | enterprise");
                break;
        }

        ordersNeedTruckPage.submitFilter();
    }

    @Step
    public void filterOrderByOrderStatus(String orderStatus) {

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        ordersNeedTruckPage.clickOnOrderStatusSpin();

        switch (orderStatus.toLowerCase()) {

            case "cancelled":
                ordersNeedTruckPage.clickOnCancelledItem();
                break;

            case "completed":
                ordersNeedTruckPage.clickOnCompletedItem();
                break;

            case "has paid":
                ordersNeedTruckPage.clickOnHasPaidItem();
                break;

            case "found truck":
                ordersNeedTruckPage.clickOnFoundTruckItem();
                break;

            case "in transit":
                ordersNeedTruckPage.clickOnInTransitItem();
                break;

            default:
                Log.error("Order status '" + orderStatus + "' is wrong");
                Log.errorAndStop("There are only 5 order status: " +
                        "cancelled | completed | has paid | found truck | in transit");
                break;
        }

        ordersNeedTruckPage.submitFilter();
    }

    @Step
    public void filterOrderByIsShareTruck(String isShareTruck) {

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        ordersNeedTruckPage.clickOnIsShareTruckSpin();

        if (isShareTruck.equalsIgnoreCase("Yes")) {
            ordersNeedTruckPage.clickOnShareTruckItem();
        } else {
            ordersNeedTruckPage.clickOnNotShareTruckItem();
        }

        ordersNeedTruckPage.submitFilter();
    }

    @Step
    public void viewOrderDetailByClickOnOrderID() {

        String orderID;

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        for (int orderIndex = 0; orderIndex <= 20; orderIndex++) {
            orderID = ordersNeedTruckPage.getIDOfOrder(orderIndex);
            // Browser open new tab when clicking on id of order
            ordersNeedTruckPage.clickOnIDOfOrder(orderIndex);

            UIComponent.switchToFinalTab(opsToolWebDriver);

            if (Timer.waitForPageUrlEqualUrl(opsToolWebDriver, Config.opsToolHost + "/order/" + orderID, 5)) {
                break;
            } else {
                UIComponent.removeFinalTab(opsToolWebDriver);
            }
        }
    }

    @Step
    public void filterOrderByOrderID(Integer orderID) {

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        ordersNeedTruckPage.fillTextOnSearchTextBox(orderID.toString())
                .submitFilter();
    }

    @Step
    public void filterOrderByBrokerPhone(String brokerPhone) {

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        ordersNeedTruckPage.fillTextOnSearchTextBox(brokerPhone)
                .selectFilterByPhone()
                .submitFilter();
    }

    @Step
    public void filterOrderByCargoType(String cargoType) {

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        ordersNeedTruckPage.fillTextOnSearchTextBox(cargoType)
                .selectFilterByCargoType()
                .submitFilter();
    }

    @Step
    public void filterOrderByCargoWeight(String cargoWeight) {

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        ordersNeedTruckPage.fillTextOnSearchTextBox(cargoWeight)
                .selectFilterByCargoWeight()
                .submitFilter();
    }

    @Step
    public void filterOrderByPickupLocation(String areaName) {

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        ordersNeedTruckPage.selectPickupLocation(areaName)
                .submitFilter();
    }

    @Step
    public void filterOrderByDropOffLocation(String areaName) {

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        ordersNeedTruckPage.selectDropOffLocation(areaName)
                .submitFilter();
    }

    @Step
    public void filterOrderByOpsInCharge(String opsInChargeName) {

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        ordersNeedTruckPage.selectOpsInChargeOnSearchBox(opsInChargeName)
                .selectFilterByOpsInCharge()
                .submitFilter();
    }

    @Step
    public void verifyBrokerNameOfFirstOrder(String brokerName) {

        String brokerNameOfFirstOrder;

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        String[] temp = ordersNeedTruckPage.getBrokerNameOfOrder(0).trim().split("\n");

        //Broker name is first of broker info
        brokerNameOfFirstOrder = temp[0];
        Log.debug("Broker name of first order: " + brokerNameOfFirstOrder);

        Assert.assertEquals(brokerName, brokerNameOfFirstOrder);
    }

    @Step
    public void verifyCargoTypeOfFirstOrder(String cargoType) {

        String cargoTypeOfFirstOrder;

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        String[] temp = ordersNeedTruckPage.getCargoTypeOfOrder(0).split("tons");

        //Cargo type is last of cargo info
        cargoTypeOfFirstOrder = temp[temp.length - 1].trim();
        Log.debug("Cargo type of first order: " + cargoTypeOfFirstOrder);

        Assert.assertEquals(cargoType, cargoTypeOfFirstOrder);
    }

    @Step
    public void verifyCargoWeightOfFirstOrder(String cargoWeight) {

        String cargoWeightOfFirstOrder;

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        String[] temp = ordersNeedTruckPage.getCargoTypeOfOrder(0).split("tons");

        //Cargo weight is first of cargo info
        cargoWeightOfFirstOrder = temp[0].trim();
        Log.debug("Cargo weight of first order: " + cargoWeightOfFirstOrder);

        Assert.assertEquals(cargoWeight, cargoWeightOfFirstOrder);
    }

    @Step
    public void verifyPickUpLocationOfFirstOrder(String areaName) {

        String pickupLocationOfFirstOrder;

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        pickupLocationOfFirstOrder = ordersNeedTruckPage.getPickupLocationOfOrder(0);

        Assert.assertTrue(pickupLocationOfFirstOrder.contains(areaName));
    }

    @Step
    public void verifyDropOffLocationOfFirstOrder(String areaName) {

        String dropOffLocationOfFirstOrder;

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        dropOffLocationOfFirstOrder = ordersNeedTruckPage.getDropOffLocationOfOrder(0);

        Assert.assertTrue(dropOffLocationOfFirstOrder.contains(areaName));
    }

    @Step
    public void verifyOrderStatusOfFirstOrder(String orderStatus) {

        String orderStatusOfFirstOrder;

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        orderStatusOfFirstOrder = ordersNeedTruckPage.getOrderStatus(0);

        Assert.assertEquals(orderStatusOfFirstOrder.toLowerCase(), orderStatus.toLowerCase());
    }

    @Step
    public void verifyOrderIsShareTruckOfFirstOrder(String orderStatus) {

        String orderIsShareTruckOfFirstOrder;

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        orderIsShareTruckOfFirstOrder = ordersNeedTruckPage.getOrderIsShareTruck(0);

        Assert.assertEquals(orderIsShareTruckOfFirstOrder.toLowerCase(), orderStatus.toLowerCase());
    }

    @Step
    public void verifyAssignedPersonNameOfFirstOrder(String assignedPersonName) {

        String assignedPersonNameOfFirstOrder;

        OrdersNeedTruckPage ordersNeedTruckPage = new OrdersNeedTruckPage(opsToolWebDriver);

        assignedPersonNameOfFirstOrder = ordersNeedTruckPage.getAssignedPersonNameOfOrder(assignedPersonName, 0);

        Assert.assertEquals(assignedPersonName.toLowerCase(), assignedPersonNameOfFirstOrder.toLowerCase());
    }
}
