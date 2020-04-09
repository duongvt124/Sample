package lgv.automation.serenityJunit.features.api.datadrivenTesting.opsTool;

import lgv.automation.serenityJunit.features.api.TestSuiteMaster;
import lgv.automation.steps.api.OpsToolSteps;
import lgv.automation.util.CSVHelper;
import lgv.automation.util.Log;
import lgv.automation.util.api.Config;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SerenityParameterizedRunner.class)
public class PredictPriceMultipleTruck extends TestSuiteMaster {

    private final String fromAreaID;
    private final String toAreaID;

    @TestData
    public static Collection<Object[]> testData() {

        Object[][] data = CSVHelper.getDataFromCSVFile("dataFile/all_routes_area_id.csv");
        return Arrays.asList(data);
    }

    // Index of params of function = index of column in file csv
    // CheckFullTruckPriceLookup.params[0] = csvFile.columns[0].row[x]
    public PredictPriceMultipleTruck(String fromAreaID, String toAreaID) {

        this.fromAreaID = fromAreaID;
        this.toAreaID = toAreaID;
    }

    @Steps
    OpsToolSteps opsToolSteps;

    @Test
    public void checkAllPriceLookUpSuccess(){

        Log.highlight("--------------- BEGIN TO TEST OPS TOOL PREDICTIVE PRICE LOOKUP API! ---------------");

        List<Integer> listTruckModelID = Arrays.asList(71, 17, 25, 79, 45, 69);

        Log.info("Get predict price whole truck");
        opsToolSteps.shouldBeAbleToGetPredictPrice(
                fromAreaID,
                toAreaID,
                String.valueOf(Config.priceLookupCargoWeight),
                String.valueOf(Config.priceLookupCargoHeight),
                String.valueOf(Config.priceLookupCargoLength),
                String.valueOf(Config.priceLookupCargoWidth),
                769934,
                49982,
                listTruckModelID,
                2,
                Config.orderCreationPickUpDateTime,
                true
        );

        Log.info("Get predict price share truck");
        opsToolSteps.shouldBeAbleToGetPredictPrice(
                fromAreaID,
                toAreaID,
                String.valueOf(Config.priceLookupCargoWeight),
                String.valueOf(Config.priceLookupCargoHeight),
                String.valueOf(Config.priceLookupCargoLength),
                String.valueOf(Config.priceLookupCargoWidth),
                769934,
                49982,
                listTruckModelID,
                1,
                Config.orderCreationPickUpDateTime,
                false
        );

        Log.highlight("--------------- FINISH OPS TOOL PREDICTIVE PRICE LOOKUP API TESTING! ---------------");
    }
}
