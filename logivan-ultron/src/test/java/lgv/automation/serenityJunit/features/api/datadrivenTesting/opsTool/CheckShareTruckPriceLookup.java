package lgv.automation.serenityJunit.features.api.datadrivenTesting.opsTool;

import lgv.automation.serenityJunit.features.api.TestSuiteMaster;
import lgv.automation.steps.api.OpsToolSteps;
import lgv.automation.util.CSVHelper;
import lgv.automation.util.Log;
import lgv.automation.util.api.Config;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(SerenityParameterizedRunner.class)
public class CheckShareTruckPriceLookup extends TestSuiteMaster {

    private final String districtIDHuyenCamLe = "134";
    private final String districtIDHuyenSocSon = "251";
    private final String districtIDHuyenBinhChanh = "643";
    private final Double volumeFactor = 3.5;
    private final Double weightFactor = 1.5;

    private final String fromAreaID;
    private String fromDistrictID;
    private final String toAreaID;
    private String toDistrictID;
    private final String pricingFactor;
    private final String unitLowerBound;
    private final String unitType;
    private Double priceLookupCargoWeight;
    private Double priceLookupCargoLength;
    private Double priceLookupCargoWidth;
    private Double priceLookupCargoHeight;

    @Steps
    OpsToolSteps opsToolSteps;

    @Before
    public void initDataTest() {

        initDistrict();
        initUnitType();
    }

    @TestData
    public static Collection<Object[]> testData() {

        Object[][] data = CSVHelper.getDataFromCSVFile("dataFile/instantPrice/ltl_pricing_factors.csv");
        return Arrays.asList(data);
    }

    //     Index of params of function = index of column in file csv
//     CheckShareTruckPriceLookup.params[0] = csvFile.columns[0].row[x]
    public CheckShareTruckPriceLookup(String fromAreaID, String toAreaID,
                                      String pricingFactor, String unitLowerBound, String unitType) {

        this.fromAreaID = fromAreaID;
        this.toAreaID = toAreaID;
        this.pricingFactor = pricingFactor;
        this.unitLowerBound = unitLowerBound;
        this.unitType = unitType;
    }

    private void initDistrict() {

        fromDistrictID = getDistrict(fromAreaID);
        toDistrictID = getDistrict(toAreaID);
    }

    private String getDistrict(String areaID) {

        switch (areaID) {
            case "2":
                return districtIDHuyenCamLe;

            case "3":
                return districtIDHuyenSocSon;

            case "5":
                return districtIDHuyenBinhChanh;

            default:
                Log.error("No price in this area!");
                return null;
        }
    }

    private void initUnitType() {
        switch (unitType) {
            case "volume":
                setDataTestForVolume();
                break;

            case "weight":
                setDataTestForWeight();
                break;

            default:
                Log.error("Unit type is wrong!");
                break;
        }
    }

    private void setDataTestForVolume() {

        //If size of truck in data file > the biggest truck => get size of the biggest truck
        int unitLowerCBM = Integer.parseInt(unitLowerBound);
        if (unitLowerCBM == 0) {
            priceLookupCargoLength = ThreadLocalRandom.current().nextDouble(0.1, 0.99);
            priceLookupCargoWidth = 1.0;
            priceLookupCargoHeight = 1.0;

        } else if (unitLowerCBM > 12) {
            priceLookupCargoLength = 12.0;
            priceLookupCargoWidth = Math.sqrt(unitLowerCBM / priceLookupCargoLength);
            priceLookupCargoHeight = priceLookupCargoWidth;

        } else {
            priceLookupCargoLength = ThreadLocalRandom.current().nextDouble(unitLowerCBM + 0.1, unitLowerCBM + 1.99);
            priceLookupCargoWidth = 1.0;
            priceLookupCargoHeight = 1.0;
        }

        priceLookupCargoWeight = (priceLookupCargoLength * priceLookupCargoWidth * priceLookupCargoHeight) / volumeFactor * 100 / 100;
    }

    private void setDataTestForWeight() {

        //If weight / cbm > 27 => get full truck price
        //So just random that weight / cbm <= 27
        int unitLowerWeight = Integer.parseInt(unitLowerBound);
        priceLookupCargoWeight = ThreadLocalRandom.current().nextDouble(unitLowerWeight + 0.1, unitLowerWeight + 4.99);

        if (priceLookupCargoWeight > 10) {
            priceLookupCargoLength = priceLookupCargoWeight * weightFactor * 30 / 100;
            priceLookupCargoWidth = priceLookupCargoWeight * weightFactor * 5 / 100;
            priceLookupCargoHeight = priceLookupCargoWeight * weightFactor * 5 / 100;

        } else {
            priceLookupCargoLength = priceLookupCargoWeight * weightFactor * 60 / 100;
            priceLookupCargoWidth = priceLookupCargoWeight * weightFactor * 10 / 100;
            priceLookupCargoHeight = priceLookupCargoWeight * weightFactor * 10 / 100;
        }
    }

    @Test
    public void checkLTLPriceLookUpSuccess(){

        Log.highlight("--------------- BEGIN TO TEST OPS TOOL PRICE LOOKUP API! ---------------");

        opsToolSteps.checkLTLLookUp(
                priceLookupCargoWeight,
                priceLookupCargoLength,
                priceLookupCargoWidth,
                priceLookupCargoHeight,
                fromAreaID,
                toAreaID,
                Config.brokerTruckModelID,
                Config.brokerTruckTypeID,
                Integer.parseInt(pricingFactor),
                unitType
        );

        opsToolSteps.shouldBeAbleToLookUpPrice(
                fromAreaID,
                toAreaID,
                fromDistrictID,
                toDistrictID,
                Config.brokerTruckModelID,
                Config.brokerTruckTypeID,
                Config.isWholeTruck
        );

        Log.highlight("--------------- FINISH OPS TOOL PRICE LOOKUP API TESTING! ---------------");
    }
}