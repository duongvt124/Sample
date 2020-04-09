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

@RunWith(SerenityParameterizedRunner.class)
//@Concurrent(threads="2")
public class CheckAllPriceLookup extends TestSuiteMaster {

    private final String fromAreaID;
    private final String toAreaID;
    private final String fromDistrictID;
    private final String toDistrictID;
    private final String truckModelID;
    private final String truckTypeID;
    private final String price;

    @TestData
    public static Collection<Object[]> testData() {

        Object[][] data = CSVHelper.getDataFromCSVFile("dataFile/instantPrice/pricing-null-locations.csv");
//        Object[][] data = CSVHelper.getDataFromCSVFile("dataFile/instantPrice/opstool_update_pricing_table.csv");
        return Arrays.asList(data);
    }

    // Index of params of function = index of column in file csv
    // CheckFullTruckPriceLookup.params[0] = csvFile.columns[0].row[x]
    public CheckAllPriceLookup(String fromAreaID, String toAreaID,
                               String fromDistrictID, String toDistrictID,
                               String truckModelID, String truckTypeID, String price) {

        this.fromAreaID = fromAreaID;
        this.toAreaID = toAreaID;
        this.fromDistrictID = fromDistrictID;
        this.toDistrictID = toDistrictID;
        this.truckModelID = truckModelID;
        this.truckTypeID = truckTypeID;
        this.price = price;
    }

    @Steps
    OpsToolSteps opsToolSteps;

    @Test
    public void checkAllPriceLookUpSuccess(){

        Log.highlight("--------------- BEGIN TO TEST BROKER PRICE LOOKUP API! ---------------");

        opsToolSteps.checkAllPriceLookUpTester(
                fromAreaID,
                toAreaID,
                fromDistrictID,
                toDistrictID,
                truckModelID,
                truckTypeID,
                Config.isWholeTruck,
                Double.parseDouble(price));

        Log.highlight("--------------- FINISH BROKER PRICE LOOKUP API TESTING! ---------------");
    }
}
