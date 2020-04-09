package lgv.automation.serenityJunit.features.api.datadrivenTesting.opsTool;

import lgv.automation.serenityJunit.features.api.TestSuiteMaster;
import lgv.automation.steps.api.OpsToolSteps;
import lgv.automation.util.CSVHelper;
import lgv.automation.util.CommonMethod;
import lgv.automation.util.Log;
import lgv.automation.util.api.Config;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SerenityRunner.class)
public class PriceLookup extends TestSuiteMaster {
    @Steps
    OpsToolSteps opsToolSteps;


    @Test
    public void priceLookupSuccess(){

        Log.highlight("--------------- API TESTING - BEGIN TO TEST OPS TOOL PRICE LOOKUP API! ---------------");

//        opsToolSteps.shouldBeAbleToLookUpPrice(
//                Config.fromAreaID,
//                Config.toAreaId,
//                Config.fromDistrictID,
//                Config.toDistrictID,
//                Config.brokerTruckModelID,
//                Config.brokerTruckTypeID,
//                Config.isWholeTruck
//        );

//        opsToolSteps.shouldBeAbleToLookUpFormulaPrice(
//                Config.fromAreaID,
//                Config.toAreaId,
//                Config.fromDistrictID,
//                Config.toDistrictID,
//                Config.fromLat,
//                Config.fromLong,
//                Config.toLat,
//                Config.toLong,
//                Config.brokerTruckModelID,
//                Config.brokerTruckTypeID,
//                Config.isWholeTruck
//        );

        Log.highlight("--------------- FINISH OPS TOOL PRICE LOOKUP API TESTING! ---------------");
    }
}
