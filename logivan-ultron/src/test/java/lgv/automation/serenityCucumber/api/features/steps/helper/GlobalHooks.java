package lgv.automation.serenityCucumber.api.features.steps.helper;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import lgv.automation.database.ClearDataTest;
import lgv.automation.steps.api.OpsToolSteps;
import lgv.automation.util.CommandPrompt;
import lgv.automation.util.Log;
import lgv.automation.util.api.Config;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.ArrayList;

public class GlobalHooks {

    @Steps
    static OpsToolSteps opsToolSteps;

    private static boolean dunit = false;

    private static void removeAllOldDataTest() {
        Config.listDriverAuthorization = new ArrayList<>();
    }

    @Before
    public static void beforeAll(){
        Log.highlight("Begin init");

        removeAllOldDataTest();

        if (!dunit) {
            lgv.automation.util.Config.init();
            lgv.automation.util.api.Config.init();
            Log.highlight("TestSuiteMaster - Begin Test Suite !");

            dunit = true;
        }
    }

    @After
    public static void afterScenario(Scenario scenario) throws IOException, InterruptedException {
        if (scenario.isFailed()) {
        }
    }

    @After("@deleteNewDriver")
    public static void deleteNewDriver(){

        // Delete all new drivers
        for (int newDriverID : Config.listNewDriverID) {
            ClearDataTest.deleteDriverByDriverID(newDriverID);
        }
    }

    @After("@deleteNewBroker")
    public static void deleteNewBroker(){

        ClearDataTest.deleteBrokerByBrokerID(Config.newBrokerID);

        opsToolSteps.verifyDeleteBrokerSuccess(Config.newBrokerID);
    }

    @After("@deleteNewOrder")
    public static void deleteNewOrder(){

        if (Config.FLAG_DELETE_ORDER) {
            for (int newOrderID : Config.listNewOrderID) {
                ClearDataTest.deleteOrderOrderID(newOrderID);
            }

        }
    }

    @After("@deleteNewBooking")
    public static void deleteNewBooking(){

        if (Config.FLAG_DELETE_BOOKING) {
            for (int newBookingID : Config.listNewBookingID) {
                ClearDataTest.deleteBookingByBookingID(newBookingID);
            }
        }
    }
}
