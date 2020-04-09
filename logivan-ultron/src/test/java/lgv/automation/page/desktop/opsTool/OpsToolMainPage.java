package lgv.automation.page.desktop.opsTool;

import lgv.automation.util.Log;
import lgv.automation.util.desktop.UIComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OpsToolMainPage {


    WebDriver webDriver;

    @FindBy(how = How.ID, using = "onesignal-popover-cancel-button")
    private List<WebElement> btnDoNotAllowNotification;

    @FindBy(how = How.ID, using = "onesignal-popover-allow-button")
    private List<WebElement> btnAllowNotification;

    @FindBy(how = How.XPATH, using = "//i[@title='Languages']")
    private List<WebElement> iconLanguages;

    @FindBy(how = How.XPATH, using = "//span[@aria-label='Tiếng việt']")
    private List<WebElement> itemVietnamese;

    @FindBy(how = How.XPATH, using = "//span[@aria-label='English']")
    private List<WebElement> itemEnglish;

    @FindBy(how = How.XPATH, using = "//i[@aria-label='icon: setting']")
    private List<WebElement> btnSettingBackEnd;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Change URL']")
    private List<WebElement> txtBackEndUrl;

    @FindBy(how = How.XPATH, using = "//span[text()='Submit']")
    private List<WebElement> btnSubmitBackEndUrl;

    @FindBy(how = How.XPATH, using = "//span[text()='Orders need truck']")
    private List<WebElement> menuOrderNeedTruck;

    @FindBy(how = How.XPATH, using = "//span[text()='Management']")
    private List<WebElement> menuManagement;

    @FindBy(how = How.XPATH, using = "//a[@href='/management/brokers']")
    private List<WebElement> itemBrokerManagement;

    @FindBy(how = How.XPATH, using = "//a[@href='/management/drivers']")
    private List<WebElement> itemDriverManagement;

    @FindBy(how = How.XPATH, using = "//a[@href='/management/broker-company']")
    private List<WebElement> itemCompanyManagement;

    @FindBy(how = How.XPATH, using = "//a[@href='/management/promotions']")
    private List<WebElement> itemPromotionManagement;

    @FindBy(how = How.XPATH, using = "//a[@href='/management/managers']")
    private List<WebElement> itemManagerManagement;

    @FindBy(how = How.XPATH, using = "//span[text()='Create order']")
    private List<WebElement> itemOrderCreation;

    public OpsToolMainPage(WebDriver webDriver) {

        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this);
    }

    public OpsToolMainPage clickOnButtonAllowNotification() {

        try {
            UIComponent.clickOnElement(webDriver, btnAllowNotification.get(0));
            Log.debug("Click on allow notification button success");
        } catch (Exception ex) {
            Log.debug("There is no notification popup");
        }

        return this;
    }

    public OpsToolMainPage clickOnIconLanguage() {

        UIComponent.clickOnElement(webDriver, iconLanguages.get(0));
        Log.debug("Click on Language icon success");

        return this;
    }

    public OpsToolMainPage clickOnItemEnglish() {

        UIComponent.clickOnElement(webDriver, itemEnglish.get(0));
        Log.debug("Click on English item success");

        return this;
    }

    public OpsToolMainPage clickOnMenuOrderNeedTruck() {

        UIComponent.clickOnElement(webDriver, menuOrderNeedTruck.get(0));
        Log.debug("Click on Order need truck menu success");

        return this;
    }

    public OpsToolMainPage clickOnButtonSettingBackEnd() {

        UIComponent.clickOnElement(webDriver, btnSettingBackEnd.get(0));
        Log.debug("Click on setting back end button success");

        return this;
    }

    public OpsToolMainPage fillBackEndUrl(String backEndUrl) {

        UIComponent.fillText(webDriver, txtBackEndUrl.get(0), backEndUrl);
        Log.debug("Fill backend url text box with text '" + backEndUrl + "' success");

        return this;
    }

    public OpsToolMainPage clickOnButtonSubmitChangeBackEndUrl() {

        UIComponent.clickOnElement(webDriver, btnSubmitBackEndUrl.get(0));
        Log.debug("Click on submit setting back end button success");

        return this;
    }

    public OpsToolMainPage clickOnItemOrderCreation() {

        UIComponent.clickOnElement(webDriver, itemOrderCreation.get(0));
        Log.debug("Click on Order Creation item success");

        return this;
    }

    public OpsToolMainPage clickOnManagementMenu() {

        UIComponent.clickOnElement(webDriver, menuManagement.get(0));
        Log.debug("Click on management menu success");

        return this;
    }

    public OpsToolMainPage clickOnBrokerManagementItem() {

        UIComponent.clickOnElement(webDriver, itemBrokerManagement.get(0));
        Log.debug("Click on broker management item success");

        return this;
    }
}