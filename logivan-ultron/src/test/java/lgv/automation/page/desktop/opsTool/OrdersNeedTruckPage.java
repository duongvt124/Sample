package lgv.automation.page.desktop.opsTool;

import lgv.automation.util.Log;
import lgv.automation.util.desktop.Timer;
import lgv.automation.util.desktop.UIComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersNeedTruckPage {


    WebDriver webDriver;

    @FindBy(how = How.ID, using = "searchText")
    private List<WebElement> txtSearch;

    @FindBy(how = How.XPATH, using = "//li[@class='ant-select-dropdown-menu-item ant-select-dropdown-menu-item-active']")
    private List<WebElement> dbActiveItem;

    @FindBy(how = How.XPATH, using = "//div[@title='ID']")
    private List<WebElement> spinFilterBy;

    @FindBy(how = How.XPATH, using = "//li[text()='ID']")
    private List<WebElement> spinItemID;

    @FindBy(how = How.XPATH, using = "//li[text()='Phone number']")
    private List<WebElement> spinItemPhoneNumber;

    @FindBy(how = How.XPATH, using = "//li[text()='Cargo type']")
    private List<WebElement> spinItemCargoType;

    @FindBy(how = How.XPATH, using = "//li[text()='Weight']")
    private List<WebElement> spinItemCargoWeight;

    @FindBy(how = How.XPATH, using = "//li[text()='Ops in charge']")
    private List<WebElement> spinItemOpsInCharge;

    @FindBy(how = How.XPATH, using = "//input[@class='ant-input ant-select-search__field']")
    private List<WebElement> listDbLocation;

    @FindBy(how = How.XPATH, using = "//div[@title='Hybrid']")
    private List<WebElement> spinOrderType;

    @FindBy(how = How.XPATH, using = "//li[text()='All']")
    private List<WebElement> itemAllOrders;

    @FindBy(how = How.XPATH, using = "//li[text()='MarketPlace']")
    private List<WebElement> itemMarketPlaceOrders;

    @FindBy(how = How.XPATH, using = "//li[text()='Corporate']")
    private List<WebElement> itemCorporateOrders;

    @FindBy(how = How.XPATH, using = "//li[text()='Hybrid']")
    private List<WebElement> itemHybridOrders;

    @FindBy(how = How.XPATH, using = "//li[text()='Enterprise']")
    private List<WebElement> itemEnterpriseOrders;

    @FindBy(how = How.ID, using = "orderStatus")
    private List<WebElement> spinOrderStatus;

    @FindBy(how = How.XPATH, using = "//li[text()='Cancelled']")
    private List<WebElement> itemStatusCancelled;

    @FindBy(how = How.XPATH, using = "//li[text()='Completed']")
    private List<WebElement> itemStatusCompleted;

    @FindBy(how = How.XPATH, using = "//li[text()='Has accepted bidding']")
    private List<WebElement> itemStatusHasAcceptedBidding;

    @FindBy(how = How.XPATH, using = "//li[text()='Has paid']")
    private List<WebElement> itemStatusHasPaid;

    @FindBy(how = How.XPATH, using = "//li[text()='Found truck']")
    private List<WebElement> itemStatusFoundTruck;

    @FindBy(how = How.XPATH, using = "//li[text()='In transit']")
    private List<WebElement> itemStatusInTransit;

    @FindBy(how = How.ID, using = "isShareTruck")
    private List<WebElement> spinIsShareTruck;

    @FindBy(how = How.XPATH, using = "//li[text()='Yes']")
    private List<WebElement> itemShareTruck;

    @FindBy(how = How.XPATH, using = "//li[text()='No']")
    private List<WebElement> itemNotShareTruck;

    @FindBy(how = How.ID, using = "pickupDatetime")
    private List<WebElement> dpPickUpDateTime;

    @FindBy(how = How.XPATH, using = "//div[@class='ant-calendar-picker-container ant-calendar-picker-container-placement-bottomLeft']//input[@placeholder='Select date']")
    private List<WebElement> inputPickUpDateTime;

    @FindBy(how = How.ID, using = "dropoffDatetime")
    private List<WebElement> dpDropOffDateTime;

    @FindBy(how = How.XPATH, using = "//div[@class='ant-calendar-picker-container ant-calendar-picker-container-placement-bottomLeft']//input[@placeholder='Select date']")
    private List<WebElement> inputDropOffDateTime;

    @FindBy(how = How.ID, using = "truckTypeId")
    private List<WebElement> spinTruckType;

    @FindBy(how = How.XPATH, using = "//li[text()='Xe thùng mui bạt']")
    private List<WebElement> itemPickUpTruck;

    @FindBy(how = How.XPATH, using = "//li[text()='Xe tải thùng kín']")
    private List<WebElement> itemBoxTruck;

    @FindBy(how = How.XPATH, using = "//li[text()='Xe cont (vỏ nhà xe)']")
    private List<WebElement> itemContainerTruck;

    @FindBy(how = How.XPATH, using = "//li[text()='Xe cont lạnh']")
    private List<WebElement> itemRefrigeratedContainerTruck;

    @FindBy(how = How.XPATH, using = "//li[text()='Tải lạnh']")
    private List<WebElement> itemColdTruck;

    @FindBy(how = How.ID, using = "truckModelId")
    private List<WebElement> spinTruckModel;

    @FindBy(how = How.XPATH, using = "//li[text()='1.0 tấn | dài 3.4m x rộng 1.65m x cao 1.83m']")
    private List<WebElement> itemPickUpTruckOneTon;

    @FindBy(how = How.XPATH, using = "//li[text()='1.0 tấn | dài 3.35m x rộng 1.65m x cao 1.65m']")
    private List<WebElement> itemBoxTruckOneTon;

    @FindBy(how = How.XPATH, using = "//li[text()='28.0 tấn | Cont 40' (dài 12.0m x rộng 2.3m x cao 2.3m)']")
    private List<WebElement> itemContainerTruckFortyFeet;

    @FindBy(how = How.XPATH, using = "//li[text()='28.0 tấn | 48' (dài 14.456m x rộng 2.33m x cao 2.655m)']")
    private List<WebElement> itemRefrigeratedContainerTruckFortyEightFeet;

    @FindBy(how = How.XPATH, using = "//li[text()='1.0 tấn | dài 3.02m x rộng 1.61m x cao 1.55m")
    private List<WebElement> itemColdTruckOneTon;

    @FindBy(how = How.XPATH, using = "//span[text()='Filter']")
    private List<WebElement> btnSubmitFilter;

    @FindBy(how = How.XPATH, using = "//tbody[@class='ant-table-tbody']/tr/td[1]/a")
    private List<WebElement> listLnkOrderID;

    @FindBy(how = How.XPATH, using = "//tbody[@class='ant-table-tbody']/tr/td[2]/a")
    private List<WebElement> listLbBrokerInfo;

    @FindBy(how = How.XPATH, using = "//tbody[@class='ant-table-tbody']/tr/td[3]/strong")
    private List<WebElement> listLbPickUpLocation;

    @FindBy(how = How.XPATH, using = "//tbody[@class='ant-table-tbody']/tr/td[4]/strong")
    private List<WebElement> listLbDropOffLocation;

    @FindBy(how = How.XPATH, using = "//tbody[@class='ant-table-tbody']/tr/td[7]")
    private List<WebElement> listLbCargoInfo;

    @FindBy(how = How.XPATH, using = "//tbody[@class='ant-table-tbody']/tr/td[11]//span/span")
    private List<WebElement> listLbOrderStatus;

    @FindBy(how = How.XPATH, using = "//tbody[@class='ant-table-tbody']/tr/td[9]")
    private List<WebElement> listLbIsShareTruck;

    @FindBy(how = How.XPATH, using = "//tbody[@class='ant-table-tbody']/tr/td[13]/div/span")
    private List<WebElement> listLbAssignedPersonShortName;


    public OrdersNeedTruckPage(WebDriver webDriver) {

        this.webDriver = webDriver;

        //This initElements method will create all WebElements
        PageFactory.initElements(webDriver, this);
    }

    public OrdersNeedTruckPage fillTextOnSearchTextBox(String text) {

        UIComponent.fillText(webDriver, txtSearch.get(0), text);
        Log.debug("Fill search text box with text '" + text + "' success");

        return this;
    }

    public OrdersNeedTruckPage selectOpsInChargeOnSearchBox(String opsInChargeName) {

        Log.debug("Fill search text box with text '" + opsInChargeName + "'");
        UIComponent.fillText(webDriver, txtSearch.get(0), opsInChargeName);
        Log.debug("Fill search text box with text '" + opsInChargeName + "' success");

        Log.debug("Press key Enter");
        UIComponent.pressKeyboardKeys(webDriver, txtSearch.get(0), Keys.ENTER);
        Log.debug("Press key Enter success");

        return this;
    }

    public OrdersNeedTruckPage selectFilterByPhone() {

        Log.debug("Click on filter by spin");
        UIComponent.clickOnElement(webDriver, spinFilterBy.get(0));
        Log.debug("Click on filter by spin success");

        Log.debug("Click on phone number item");
        UIComponent.clickOnElement(webDriver, spinItemPhoneNumber.get(0));
        Log.debug("Click on phone number item success");

        return this;
    }

    public OrdersNeedTruckPage selectFilterByCargoType() {

        Log.debug("Click on filter by spin");
        UIComponent.clickOnElement(webDriver, spinFilterBy.get(0));
        Log.debug("Click on filter by spin success");

        Log.debug("Click on cargo type item");
        UIComponent.clickOnElement(webDriver, spinItemCargoType.get(0));
        Log.debug("Click on cargo type item success");

        return this;
    }

    public OrdersNeedTruckPage selectFilterByCargoWeight() {

        Log.debug("Click on filter by spin");
        UIComponent.clickOnElement(webDriver, spinFilterBy.get(0));
        Log.debug("Click on filter by spin success");

        Log.debug("Click on cargo weight item");
        UIComponent.clickOnElement(webDriver, spinItemCargoWeight.get(0));
        Log.debug("Click on cargo weight item success");

        return this;
    }

    public OrdersNeedTruckPage selectPickupLocation(String areaName) {

        UIComponent.clickOnElement(webDriver, listDbLocation.get(0));
        Log.debug("Click on pick up list box success");

        List<WebElement> itemPickUpLocation = webDriver.findElements(By.xpath("//li[text()='" + areaName + "']"));

        Timer.waitForListElementExist(webDriver, itemPickUpLocation, 3);
        UIComponent.clickOnElement(webDriver, itemPickUpLocation.get(0));
        Log.debug("Click on pick up location item success");

        return this;
    }

    public OrdersNeedTruckPage selectDropOffLocation(String areaName) {

        UIComponent.clickOnElement(webDriver, listDbLocation.get(1));
        Log.debug("Click on drop off list box success");

        List<WebElement> itemDropOffLocation = webDriver.findElements(By.xpath("//li[text()='" + areaName + "']"));

        Timer.waitForListElementExist(webDriver, itemDropOffLocation, 3);
        UIComponent.clickOnElement(webDriver, itemDropOffLocation.get(0));
        Log.debug("Click on drop off location item success");

        return this;
    }

    public OrdersNeedTruckPage selectFilterByOpsInCharge() {

        Log.debug("Click on filter by spin");
        UIComponent.clickOnElement(webDriver, spinFilterBy.get(0));
        Log.debug("Click on filter by spin success");

        Log.debug("Click on ops in charge item success");
        UIComponent.clickOnElement(webDriver, spinItemOpsInCharge.get(0));
        Log.debug("Click on ops in charge item success");

        return this;
    }

    public OrdersNeedTruckPage clickOnOrderTypeSpin() {

        Log.debug("Click on order type spin");
        UIComponent.clickOnElement(webDriver, spinOrderType.get(0));
        Log.debug("Click on order type spin success");

        return this;
    }

    public OrdersNeedTruckPage clickOnMarketPlaceOrdersItem() {

        Log.debug("Click on marketplace orders item");
        UIComponent.clickOnElement(webDriver, itemMarketPlaceOrders.get(0));
        Log.debug("Click on marketplace orders item success");

        return this;
    }

    public OrdersNeedTruckPage clickOnCorporateOrdersItem() {

        Log.debug("Click on corporate orders item");
        UIComponent.clickOnElement(webDriver, itemCorporateOrders.get(0));
        Log.debug("Click on corporate orders item success");

        return this;
    }

    public OrdersNeedTruckPage clickOnHybridOrdersItem() {

        Log.debug("Click on hybrid orders item");
        UIComponent.clickOnElement(webDriver, itemHybridOrders.get(0));
        Log.debug("Click on hybrid orders item success");

        return this;
    }

    public OrdersNeedTruckPage clickOnEnterpriseOrdersItem() {

        Log.debug("Click on enterprise orders item");
        UIComponent.clickOnElement(webDriver, itemEnterpriseOrders.get(0));
        Log.debug("Click on enterprise orders item success");

        return this;
    }

    public OrdersNeedTruckPage clickOnOrderStatusSpin() {

        Log.debug("Click on order status spin");
        UIComponent.clickOnElement(webDriver, spinOrderStatus.get(0));
        Log.debug("Click on order status spin success");

        return this;
    }

    public OrdersNeedTruckPage clickOnCancelledItem() {

        Log.debug("Click on cancelled item");
        UIComponent.clickOnElement(webDriver, itemStatusCancelled.get(0));
        Log.debug("Click on cancelled item success");

        return this;
    }

    public OrdersNeedTruckPage clickOnCompletedItem() {

        Log.debug("Click on completed item");
        UIComponent.clickOnElement(webDriver, itemStatusCompleted.get(0));
        Log.debug("Click on completed item success");

        return this;
    }

    public OrdersNeedTruckPage clickOnFoundTruckItem() {

        Log.debug("Click on found truck item");
        UIComponent.clickOnElement(webDriver, itemStatusFoundTruck.get(0));
        Log.debug("Click on found truck item success");

        return this;
    }

    public OrdersNeedTruckPage clickOnHasAcceptedBiddingItem() {

        Log.debug("Click on has accepted bidding item");
        UIComponent.clickOnElement(webDriver, itemStatusHasAcceptedBidding.get(0));
        Log.debug("Click on has accepted bidding item success");

        return this;
    }

    public OrdersNeedTruckPage clickOnHasPaidItem() {

        Log.debug("Click on has paid item");
        UIComponent.clickOnElement(webDriver, itemStatusHasPaid.get(0));
        Log.debug("Click on has paid item success");

        return this;
    }

    public OrdersNeedTruckPage clickOnInTransitItem() {

        Log.debug("Click on in transit item");
        UIComponent.clickOnElement(webDriver, itemStatusInTransit.get(0));
        Log.debug("Click on in transit item success");

        return this;
    }

    public OrdersNeedTruckPage clickOnIsShareTruckSpin() {

        Log.debug("Click on is share truck spin");
        UIComponent.clickOnElement(webDriver, spinIsShareTruck.get(0));
        Log.debug("Click on is share truck spin success");

        return this;
    }

    public OrdersNeedTruckPage clickOnShareTruckItem() {

        Log.debug("Click on share truck item");
        UIComponent.clickOnElement(webDriver, itemShareTruck.get(0));
        Log.debug("Click on share truck item success");

        return this;
    }

    public OrdersNeedTruckPage clickOnNotShareTruckItem() {

        Log.debug("Click on not share truck item");
        UIComponent.clickOnElement(webDriver, itemNotShareTruck.get(0));
        Log.debug("Click on not share truck item success");

        return this;
    }

    public OrdersNeedTruckPage submitFilter() {

        Log.debug("Click on filter button");
        UIComponent.clickOnElement(webDriver, btnSubmitFilter.get(0));
        Log.debug("Click on filter button success");

        return this;
    }

    public String getBrokerNameOfOrder(int orderIndex) {

        Log.debug("Get broker info");
        String brokerInfo = UIComponent.getElementInnerText(webDriver, listLbBrokerInfo.get(orderIndex));
        Log.debug("Get broker info: " + brokerInfo);

        return brokerInfo;
    }

    public String getCargoTypeOfOrder(int orderIndex) {

        Log.debug("Get cargo info");
        String cargoInfo = UIComponent.getElementInnerText(webDriver, listLbCargoInfo.get(orderIndex));
        Log.debug("Get cargo info: " + cargoInfo);

        return cargoInfo;
    }

    public String getPickupLocationOfOrder(int orderIndex) {

        String pickupLocation = UIComponent.getElementInnerText(webDriver, listLbPickUpLocation.get(orderIndex));
        Log.debug("Get pick up location: " + pickupLocation);

        return pickupLocation;
    }

    public String getDropOffLocationOfOrder(int orderIndex) {

        String pickupLocation = UIComponent.getElementInnerText(webDriver, listLbDropOffLocation.get(orderIndex));
        Log.debug("Get drop off location: " + pickupLocation);

        return pickupLocation;
    }

    public String getOrderStatus(int orderIndex) {

        Log.debug("Get order status");
        String orderStatus = UIComponent.getElementInnerText(webDriver, listLbOrderStatus.get(orderIndex));
        Log.debug("Get order status: " + orderStatus);

        return orderStatus;
    }

    public String getOrderIsShareTruck(int orderIndex) {

        String isShareTruck = UIComponent.getElementInnerText(webDriver, listLbIsShareTruck.get(orderIndex));
        Log.debug("Get order status: " + isShareTruck);

        return isShareTruck;
    }

    public String getIDOfOrder(int orderIndex) {

        String orderID = UIComponent.getElementInnerText(webDriver, listLnkOrderID.get(orderIndex));
        Log.debug("Get id of order success");

        return orderID;
    }

    public String getAssignedPersonNameOfOrder(String assignedPersonName, int orderIndex) {

        UIComponent.doubleClickElement(webDriver, listLbAssignedPersonShortName.get(orderIndex));
        Log.debug("Double click the short name label");

        List<WebElement> lbAssignedPerson = webDriver.findElements(By.xpath("//li[@title='" + assignedPersonName + "']"));
        Timer.waitForListElementExist(webDriver, lbAssignedPerson, 2);

        // first item assigned person is in assigned person spin that using to filter
        String listOrderAssignedPersonName = UIComponent.getElementInnerText(webDriver, lbAssignedPerson.get(1));
        Log.debug("Get assigned person name: " + listOrderAssignedPersonName);

        return listOrderAssignedPersonName;
    }

    public OrdersNeedTruckPage clickOnIDOfOrder(int orderIndex) {

        UIComponent.clickOnElement(webDriver, listLnkOrderID.get(orderIndex));
        Log.debug("Click on id of order success");

        return this;
    }
}
