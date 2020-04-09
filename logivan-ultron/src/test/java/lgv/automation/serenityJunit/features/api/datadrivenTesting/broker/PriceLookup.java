package lgv.automation.serenityJunit.features.api.datadrivenTesting.broker;

import lgv.automation.serenityJunit.features.api.TestSuiteMaster;
import lgv.automation.steps.api.BrokerMobileAppSteps;
import lgv.automation.util.Log;
import lgv.automation.util.api.Config;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;

public class PriceLookup extends TestSuiteMaster {
    @Steps
    BrokerMobileAppSteps brokerSteps;

    @Test
    public void priceLookupSuccess(){

        Log.highlight("--------------- BEGIN TO TEST BROKER PRICE LOOKUP API! ---------------");

        brokerSteps.shouldBeAbleToLookUpPrice(
                Config.fromAreaID,
                Config.toAreaId,
                Config.fromDistrictID,
                Config.toDistrictID,
                Config.brokerTruckModelID,
                Config.brokerTruckTypeID,
                Config.isWholeTruck
        );

        Log.highlight("--------------- FINISH BROKER PRICE LOOKUP API TESTING! ---------------");
    }
}
