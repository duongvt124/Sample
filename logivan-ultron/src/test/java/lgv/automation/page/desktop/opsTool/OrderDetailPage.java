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

public class OrderDetailPage {

    WebDriver webDriver;

    @FindBy(how = How.XPATH, using = "//strong[text()='Order type']")
    private List<WebElement> lbOrderTypeTitle;

    @FindBy(how = How.XPATH, using = "//a[@class='ant-dropdown-link ant-dropdown-trigger antd-pro-pages-order-detail-order-header-status-dropdown-status-dropdown-dropdown']")
    private List<WebElement> dbOrderStatus;

    @FindBy(how = How.XPATH, using = "//strong[text()='Order type']/../../span[2]")
    private List<WebElement> lbOrderTypeValue;

    @FindBy(how = How.XPATH, using = "//strong[text()='Broker']/../../span[2]")
    private List<WebElement> lbBrokerInfo;

    @FindBy(how = How.XPATH, using = "//tr[@class='ant-table-row ant-table-row-level-0']//span[text()='Accept']")
    private List<WebElement> listBtnAcceptServiceQuotation;

    @FindBy(how = How.XPATH, using = "//div[text()='Discount']/following-sibling::div")
    private List<WebElement> dbServiceQuotationDiscount;

    @FindBy(how = How.XPATH, using = "//div[text()='Payment method']/following-sibling::div")
    private List<WebElement> dbServiceQuotationPaymentMethod;

    @FindBy(how = How.XPATH, using = "//button[@class='ant-btn antd-pro-pages-sales-order-detail-logivan-quotation-list-accept-service-quotation-form-accept-service-quotation-form-acceptButton']")
    private List<WebElement> btnSubmitAcceptServiceQuotation;

    @FindBy(how = How.XPATH, using = "//span[text()='Stake']")
    private List<WebElement> btnStake;

    @FindBy(how = How.ID, using = "amount")
    private List<WebElement> txtStakeAmount;

    @FindBy(how = How.ID, using = "note")
    private List<WebElement> txtStakeNote;

    @FindBy(how = How.XPATH, using = "//span[text()='CONFIRM']")
    private List<WebElement> btnSubmitStake;

    @FindBy(how = How.XPATH, using = "//span[text()='Create bidding']")
    private List<WebElement> btnCreateBidding;

    @FindBy(how = How.XPATH, using = "//div[@class='antd-pro-pages-order-detail-truck-operation-order-bidding-form-hybrid-bidding-form-index-fullWidth ant-select ant-select-enabled ant-select-no-arrow']/div")
    private List<WebElement> cbBiddingDriverID;

    @FindBy(how = How.XPATH, using = "//div[@class='antd-pro-pages-order-detail-truck-operation-order-bidding-form-hybrid-bidding-form-index-fullWidth ant-select ant-select-open ant-select-focused ant-select-enabled ant-select-no-arrow']/div")
    private List<WebElement> cbFocusedBiddingDriverID;

    @FindBy(how = How.XPATH, using = "//div[text()='Enter ID or Phone number']/following-sibling::div//span")
    private List<WebElement> itemSuggestedDriverID;

    @FindBy(how = How.XPATH, using = "//input[@name='priceFromBroker']")
    private List<WebElement> txtPriceFromBroker;

    @FindBy(how = How.XPATH, using = "//input[@name='priceFromLGV']")
    private List<WebElement> txtPriceFromLGV;

    @FindBy(how = How.XPATH, using = "//div[text()='Truck quantity']/following-sibling::div")
    private List<WebElement> dbTruckQuantity;

    @FindBy(how = How.XPATH, using = "//button[@class='ant-btn antd-pro-pages-order-detail-truck-operation-order-bidding-form-hybrid-bidding-form-index-createBiddingButton ant-btn-primary']")
    private List<WebElement> btnSubmitCreateBidding;

    @FindBy(how = How.XPATH, using = "//span[text()='Select']")
    private List<WebElement> listBtnSelectBidding;

    @FindBy(how = How.XPATH, using = "//span[text()='Delete']")
    private List<WebElement> listBtnDeleteBidding;

    @FindBy(how = How.XPATH, using = "//span[text()='Assign driver']")
    private List<WebElement> listBtnAssignDriver;

    @FindBy(how = How.XPATH, using = "//div[text()='List driver']/../following-sibling::div//tbody[@class='ant-table-tbody']/tr")
    private List<WebElement> listRowBiddingSelectedDriver;

    @FindBy(how = How.XPATH, using = "//span[text()='Send offer to driver']")
    private List<WebElement> listBtnSendOffer;

    @FindBy(how = How.XPATH, using = "//span[text()='Accept offer for driver']")
    private List<WebElement> btnAcceptOffer;

    @FindBy(how = How.XPATH, using = "//div[@class='antd-pro-utils-utils-fullWidth ant-select ant-select-enabled']/div")
    private List<WebElement> listTruckAndDriver;

    @FindBy(how = How.XPATH, using = "//div[@class='antd-pro-utils-utils-fullWidth ant-select ant-select-enabled']/div")
    private List<WebElement> listTruck;

    @FindBy(how = How.XPATH, using = "//div[@class='ant-modal-footer']/button[@class='ant-btn ant-btn-primary']")
    private List<WebElement> btnSubmitAcceptOffer;

    @FindBy(how = How.XPATH, using = "//span[text()='Follow trip']")
    private List<WebElement> btnGoToShipmentDetailPage;


    public OrderDetailPage(WebDriver webDriver) {

        this.webDriver = webDriver;

        //This initElements method will create all WebElements
        PageFactory.initElements(webDriver, this);
    }

    public void loadOrderSummarySuccess() {

        Log.info("Wait for loading label order type title complete");
        Timer.waitForListElementExist(webDriver, lbOrderTypeTitle, 10);
    }

    public String getOrderStatus() {

        String orderDetailPageOrderStatus;

        orderDetailPageOrderStatus = UIComponent.getElementInnerText(webDriver, dbOrderStatus.get(0));

        Log.debug("Get order status of order: " + orderDetailPageOrderStatus);

        return orderDetailPageOrderStatus;
    }

    public String getOrderType() {

        String orderDetailPageOrderType;

        orderDetailPageOrderType = UIComponent.getElementInnerText(webDriver, lbOrderTypeValue.get(0));

        Log.debug("Get order type of order: " + orderDetailPageOrderType);

        return orderDetailPageOrderType;
    }

    public String getBrokerInfoOfOrder() {

        Log.debug("Get broker info");
        String brokerInfo = UIComponent.getElementInnerText(webDriver, lbBrokerInfo.get(0));
        Log.debug("Get broker info: " + brokerInfo);

        return brokerInfo;
    }

    public OrderDetailPage clickOnAcceptFirstServiceQuotation() {

        Log.info("Click on the first accept service quotation button");

        UIComponent.clickOnElement(webDriver, listBtnAcceptServiceQuotation.get(0));
        Log.debug("Click on the first accept service quotation button success");

        return this;
    }

    public OrderDetailPage selectServiceQuotationDiscount(String discountType) {

        Log.info("Click on service quotation discount drop down");

        UIComponent.clickOnElement(webDriver, dbServiceQuotationDiscount.get(0));
        Log.debug("Click on service quotation discount drop down success");

        Log.info("Select service quotation discount item '" + discountType + "'" );
        lgv.automation.util.Timer.waitFor(1, 1);

        WebElement itemServiceQuotationDiscount = webDriver.findElement(By.xpath("//li[text()='" + discountType + "']"));
        UIComponent.clickOnElement(webDriver, itemServiceQuotationDiscount);
        Log.debug("Select service quotation discount item '" + discountType + "' success");

        return this;
    }

    public OrderDetailPage selectServiceQuotationPaymentMethod(String paymentMethod) {

        Log.info("Click on service quotation payment method drop down");

        UIComponent.clickOnElement(webDriver, dbServiceQuotationPaymentMethod.get(0));
        Log.debug("Click on service quotation payment method drop down success");

        Log.info("Select service quotation payment method item '" + paymentMethod + "'" );
        lgv.automation.util.Timer.waitFor(1, 1);

        WebElement itemServiceQuotationPaymentMethod = webDriver.findElement(By.xpath("//li[text()='" + paymentMethod + "']"));
        UIComponent.clickOnElement(webDriver, itemServiceQuotationPaymentMethod);
        Log.debug("Select service quotation payment method item '" + paymentMethod + "' success");

        return this;
    }

    public OrderDetailPage submitAcceptServiceQuotation() {

        Log.info("Click on submit accept service quotation button");

        UIComponent.clickOnElement(webDriver, btnSubmitAcceptServiceQuotation.get(0));
        Log.debug("Click on submit accept service quotation button success");

        return this;
    }


    public OrderDetailPage clickOnStakeButton() {

        Log.info("Click on stake button");

        UIComponent.clickOnElement(webDriver, btnStake.get(0));
        Log.debug("Click on stake button success");

        return this;
    }

    public OrderDetailPage fillStakeAmount(String stakeAmount) {

        Log.info("Fill stake amount text box with text '" + stakeAmount + "'");

        UIComponent.fillText(webDriver, txtStakeAmount.get(0), stakeAmount);
        Log.debug("Fill stake amount text box with text '" + stakeAmount + "' success");

        return this;
    }

    public OrderDetailPage fillStakeNote(String stakeNote) {

        Log.info("Fill stake note text box with text '" + stakeNote + "'");

        UIComponent.fillText(webDriver, txtStakeNote.get(0), stakeNote);
        Log.debug("Fill stake note text box with text '" + stakeNote + "' success");

        return this;
    }

    public OrderDetailPage submitStake() {

        Log.info("Click on submit stake button");

        UIComponent.clickOnElement(webDriver, btnSubmitStake.get(0));
        Log.debug("Click on submit stake button success");

        return this;
    }

    public OrderDetailPage clickOnCreateBidding() {

        Log.info("Click on create bidding button");

        UIComponent.clickOnElement(webDriver, btnCreateBidding.get(0));
        Log.debug("Click on create bidding button success");

        return this;
    }

    public OrderDetailPage selectBiddingDriver(String driverPhoneNumber) {

        Log.info("Fill bidding driver combo box with text '" + driverPhoneNumber + "'");

        UIComponent.fillText(webDriver, cbBiddingDriverID.get(0), driverPhoneNumber);
        Log.debug("Fill bidding driver combo box with text '" + driverPhoneNumber + "' success");

        int waitForAutoCompleteDriverTime = 5;
        Log.debug("Waiting for auto complete driver in " + waitForAutoCompleteDriverTime + " seconds");
        lgv.automation.util.Timer.waitFor(waitForAutoCompleteDriverTime, 1);

        Log.debug("Press key board DOWN");
        UIComponent.pressKeyboardKeysOnDropdown(webDriver, cbFocusedBiddingDriverID.get(0), Keys.ARROW_DOWN);
        Log.debug("Press key board DOWN success");

        lgv.automation.util.Timer.waitFor(1, 1);

        Log.debug("Press key board ENTER");
        UIComponent.pressKeyboardKeysOnDropdown(webDriver, cbFocusedBiddingDriverID.get(0), Keys.ENTER);
        Log.debug("Press key board ENTER success");
        
        return this;
    }

    public OrderDetailPage fillPriceFromBroker(String priceFromBroker) {

        Log.info("Fill price from broker text box with text '" + priceFromBroker + "'");

        UIComponent.fillText(webDriver, txtPriceFromBroker.get(0), priceFromBroker);
        Log.debug("Fill price from broker text box with text '" + priceFromBroker + "' success");

        return this;
    }

    public OrderDetailPage fillPriceFromLGV(String priceFromLGV) {

        Log.info("Fill price from LGV text box with text '" + priceFromLGV + "'");

        UIComponent.fillText(webDriver, txtPriceFromLGV.get(0), priceFromLGV);
        Log.debug("Fill price from LGV text box with text '" + priceFromLGV + "' success");

        return this;
    }

    public OrderDetailPage submitCreateBidding() {

        Log.info("Click on submit create bidding button");

        UIComponent.clickOnElement(webDriver, btnSubmitCreateBidding.get(0));
        Log.debug("Click on submit create bidding button success");

        lgv.automation.util.Timer.waitFor(2, 1);
        Log.debug("Reload page to load biddings on truck requests");
        UIComponent.reloadPage(webDriver);

        return this;
    }


    public OrderDetailPage selectFirstBidding() {

        Log.info("Click on the first bidding button");

        UIComponent.clickOnElement(webDriver, listBtnSelectBidding.get(0));
        Log.debug("Click on the first bidding button success");

        return this;
    }

    public OrderDetailPage clickOnAssignDriverButton() {

        Log.info("Click on the first assign driver button");

        UIComponent.clickOnElement(webDriver, listBtnAssignDriver.get(0));
        Log.debug("Click on the first assign driver button success");

        return this;
    }

    public OrderDetailPage selectAssignDriver(int assignDriverIndex) {

        Log.info("Click on assign driver row " + assignDriverIndex);

        UIComponent.clickOnElement(webDriver, listRowBiddingSelectedDriver.get(assignDriverIndex - 1));
        Log.debug("Click on assign driver row " + assignDriverIndex + " success");

        return this;
    }

    public OrderDetailPage sendOffer() {

        Log.info("Click on send offer to driver button");
        UIComponent.clickOnElement(webDriver, listBtnSendOffer.get(0));
        Log.debug("Click on send offer to driver button success");

        return this;
    }

    public OrderDetailPage clickOnAcceptOfferButton() {

        Log.info("Click on accept offer button");

        UIComponent.clickOnElement(webDriver, btnAcceptOffer.get(0));
        Log.debug("Click on accept offer button success");

        return this;
    }

    public OrderDetailPage selectTheFirstDriver() {

        Log.debug("Count list truck and driver: " + listTruckAndDriver.size());

        Log.info("Click on list of driver drop down");
        UIComponent.clickOnElement(webDriver, listTruckAndDriver.get(0));
        Log.debug("Click on list of driver drop down success");

        lgv.automation.util.Timer.waitFor(2, 1);

        Log.info("Press keyboard Enter");
        UIComponent.pressKeyboardKeysOnDropdown(webDriver, listTruckAndDriver.get(0), Keys.ENTER);
        Log.debug("Press keyboard Enter success");

        return this;
    }

    public OrderDetailPage selectTheFirstTruckOfDriver() {

        Log.info("Click on list of trucks drop down");
        UIComponent.clickOnElement(webDriver, listTruck.get(0));
        Log.debug("Click on list of trucks drop down success");

        lgv.automation.util.Timer.waitFor(2, 1);

        Log.info("Press keyboard Enter");
        UIComponent.pressKeyboardKeysOnDropdown(webDriver, listTruck.get(0), Keys.ENTER);
        Log.debug("Press keyboard Enter success");

        return this;
    }

    public OrderDetailPage submitAcceptOffer() {

        Log.info("Click on submit accept offer button");
        UIComponent.clickOnElement(webDriver, btnSubmitAcceptOffer.get(0));
        Log.debug("Click on submit accept offer button success");

        lgv.automation.util.Timer.waitFor(2, 1);
        Log.debug("Reload page to load shipment");
        UIComponent.reloadPage(webDriver);

        return this;
    }

    public OrderDetailPage goToShipmentDetailPage() {

        Log.info("Click on Follow trip button");
        UIComponent.clickOnElement(webDriver, btnGoToShipmentDetailPage.get(0));
        Log.debug("Click on Follow trip button success");

        lgv.automation.util.Timer.waitFor(4, 1);
        UIComponent.switchToFinalTab(webDriver);

        return this;
    }
}
