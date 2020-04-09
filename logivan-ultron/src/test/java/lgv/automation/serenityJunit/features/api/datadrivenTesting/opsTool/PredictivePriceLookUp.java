package lgv.automation.serenityJunit.features.api.datadrivenTesting.opsTool;

import lgv.automation.serenityJunit.features.api.TestSuiteMaster;
import lgv.automation.steps.api.OpsToolSteps;
import lgv.automation.util.Log;
import lgv.automation.util.Timer;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class PredictivePriceLookUp extends TestSuiteMaster {

    @Steps
    OpsToolSteps opsToolSteps;

    @Test
    public void checkAllPriceLookUpSuccess(){

        Log.highlight("--------------- BEGIN TO TEST OPS TOOL PREDICTIVE PRICE LOOKUP API! ---------------");

        Log.info("Get predictive price lookup of share truck");

        for (int i = 0; i <= 100; i++) {
            opsToolSteps.shouldBeAbleToGetPredictivePriceLookup(
                    "5",
                    "3",
                    "8",
                    "1",
                    "2",
                    "1.5",
                    1656073,
                    115297,
                    5,
                    40,
                    1,
                    "2019-11-28T16:33:00+07:00",
                    false
            );

            Timer.waitFor(2, 2);
        }

        Log.highlight("--------------- FINISH OPS TOOL PREDICTIVE PRICE LOOKUP API TESTING! ---------------");
    }
}
