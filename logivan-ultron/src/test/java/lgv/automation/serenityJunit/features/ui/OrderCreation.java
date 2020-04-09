package lgv.automation.serenityJunit.features.ui;


import lgv.automation.databuilder.OrderCreationTestDataBuilder;
import lgv.automation.model.Order;
import lgv.automation.util.Log;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;


/**
 * Created by Duong Vu on 3/7/19.
 */

@RunWith(SerenityRunner.class)
public class OrderCreation {

    @Managed
    WebDriver appium;

    @Steps
//    CustomerSteps customerSteps;
    BrokerSteps broker;

    Order orderInfo;

    @Before
    public void appBaseState() throws Exception {
        orderInfo = OrderCreationTestDataBuilder.prepareOrderInfo();
    }

    @After
    public void putAppInBackgroundOrTerminate(){

    }

//    @Test
//    public void test(){
//
//        Log.highlight("Begin To Create Order");
//        customerSteps.customerCreatOrder(Config.pickupPoint, Config.dropoffPoint, Config.goodType, Config.capacity);
//        customerSteps.verifyCreateOrderSuccess();
//        Log.highlight("Create Order Success!");
//
//    }

    @Test
    public void test() throws InterruptedException {

        Log.highlight("Begin To Create Order");
        broker.customerCreatOrder(orderInfo, true);
        Thread.sleep(3000);
        Log.highlight("Create Order Success!");

    }
}
