package lgv.automation.steps.api;

import io.restassured.response.Response;
import lgv.automation.constants.api.BrokerMobileAppEndPoint;
import lgv.automation.database.ClearDataTest;
import lgv.automation.util.JsonHelper;
import lgv.automation.util.Log;
import lgv.automation.util.Timer;
import lgv.automation.util.api.CommonFlow;
import lgv.automation.util.api.Config;
import net.thucydides.core.annotations.Step;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.List;

public class BrokerMobileAppSteps {

    private String getFullTruckPriceLookUpUrl(String fromAreaID, String toAreaID,
                                              String fromDistrictID, String toDistrictID,
                                              String truckModelID, String truckTypeID, boolean isWholeTruck) {

        String url;
        if (truckTypeID.equals("3")) {
            url = Config.apiBaseUrl + BrokerMobileAppEndPoint.FULL_TRUCK_PRICE_LOOKUP + "?"
                    + "from_area_id=" + fromAreaID
                    + "&to_area_id=" + toAreaID
                    + "&from_district_id=" + fromDistrictID
                    + "&to_district_id=" + toDistrictID
                    + "&truck_model_id=" + truckModelID
                    + "&truck_type_id=" + truckTypeID
                    + "&is_whole_truck=" + isWholeTruck
                    + "&use_trucker_container=true";
        } else {
            url = Config.apiBaseUrl + BrokerMobileAppEndPoint.FULL_TRUCK_PRICE_LOOKUP + "?"
                    + "from_area_id=" + fromAreaID
                    + "&to_area_id=" + toAreaID
                    + "&from_district_id=" + fromDistrictID
                    + "&to_district_id=" + toDistrictID
                    + "&truck_model_id=" + truckModelID
                    + "&truck_type_id=" + truckTypeID
                    + "&is_whole_truck=" + isWholeTruck;
        }

        return url;
    }

    private String getLTLTruckPriceLookUpUrl(Double cargoWeight, Double cargoLength, Double cargoWidth,
                                             Double cargoHeight, String fromAreaID, String toAreaID,
                                             String truckModelID, String truckTypeID) {

        String url;
        if (truckTypeID.equals("3")) {
            url = Config.apiBaseUrl + BrokerMobileAppEndPoint.SHARE_TRUCK_PRICE_LOOKUP + "?"
                    + "cargo_weight=" + cargoWeight
                    + "&cargo_length=" + cargoLength
                    + "&cargo_width=" + cargoWidth
                    + "&cargo_height=" + cargoHeight
                    + "&from_area_id=" + fromAreaID
                    + "&to_area_id=" + toAreaID
                    + "&truck_model_id=" + truckModelID
                    + "&truck_type_id=" + truckTypeID
                    + "&use_trucker_container=true";
        } else {
            url = Config.apiBaseUrl + BrokerMobileAppEndPoint.SHARE_TRUCK_PRICE_LOOKUP + "?"
                    + "cargo_weight=" + cargoWeight
                    + "&cargo_length=" + cargoLength
                    + "&cargo_width=" + cargoWidth
                    + "&cargo_height=" + cargoHeight
                    + "&from_area_id=" + fromAreaID
                    + "&to_area_id=" + toAreaID
                    + "&truck_model_id=" + truckModelID
                    + "&truck_type_id=" + truckTypeID;
        }

        return url;
    }

    private String getFormulaPriceLookUpUrl(String fromAreaID, String toAreaID,
                                            String fromDistrictID, String toDistrictID,
                                            String fromLat, String fromLong,
                                            String toLat, String toLong,
                                            String truckModelID, String truckTypeID, boolean isWholeTruck) {

        String url;
        if (truckTypeID.equals("3")) {
            url = Config.apiBaseUrl + BrokerMobileAppEndPoint.FULL_TRUCK_PRICE_LOOKUP + "?"
                    + "from_area_id=" + fromAreaID
                    + "&to_area_id=" + toAreaID
                    + "&from_district_id=" + fromDistrictID
                    + "&to_district_id=" + toDistrictID
                    + "&from_lat=" + fromLat
                    + "&from_lng=" + fromLong
                    + "&to_lat=" + toLat
                    + "&to_lng=" + toLong
                    + "&truck_model_id=" + truckModelID
                    + "&truck_type_id=" + truckTypeID
                    + "&is_whole_truck=" + isWholeTruck
                    + "&use_trucker_container=true";
        } else {
            url = Config.apiBaseUrl + BrokerMobileAppEndPoint.FULL_TRUCK_PRICE_LOOKUP + "?"
                    + "from_area_id=" + fromAreaID
                    + "&to_area_id=" + toAreaID
                    + "&from_district_id=" + fromDistrictID
                    + "&to_district_id=" + toDistrictID
                    + "&from_lat=" + fromLat
                    + "&from_lng=" + fromLong
                    + "&to_lat=" + toLat
                    + "&to_lng=" + toLong
                    + "&truck_model_id=" + truckModelID
                    + "&truck_type_id=" + truckTypeID
                    + "&is_whole_truck=" + isWholeTruck;
        }

        return url;
    }

    private String getFullTruckPriceLookUp(String fromAreaID, String toAreaID,
                                           String fromDistrictID, String toDistrictID,
                                           String truckModelID, String truckTypeID, boolean isWholeTruck) {

        String url = getFullTruckPriceLookUpUrl(
                fromAreaID,
                toAreaID,
                fromDistrictID,
                toDistrictID,
                truckModelID,
                truckTypeID,
                isWholeTruck);

        Response response = UtilSteps.get(Config.brokerAuthorization, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (statusCode == Config.STATUS_CODE_NO_CONTENT) {
            Log.errorAndStop("Price look up error: no content " + result);
        } else if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Price look up error: " + result);
        } else {
            Log.debug("Price look up success: " + result);
        }

        return result;
    }

    private String getLTLTruckPriceLookUp(Double cargoWeight, Double cargoLength, Double cargoWidth,
                                          Double cargoHeight, String fromAreaID, String toAreaID,
                                          String truckModelID, String truckTypeID) {

        String url = getLTLTruckPriceLookUpUrl(
                cargoWeight,
                cargoLength,
                cargoWidth,
                cargoHeight,
                fromAreaID,
                toAreaID,
                truckModelID,
                truckTypeID
        );

        Response response = UtilSteps.get(Config.brokerAuthorization, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (statusCode == Config.STATUS_CODE_NO_CONTENT) {
            Log.errorAndStop("Price look up error: no content " + result);
        } else if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Price look up error: " + result);
        } else {
            Log.debug("Price look up success: " + result);
        }

        return result;
    }

    private String getFormulaPriceLookUp(String fromAreaID, String toAreaID,
                                         String fromDistrictID, String toDistrictID,
                                         String fromLat, String fromLong,
                                         String toLat, String toLong,
                                         String truckModelID, String truckTypeID, boolean isWholeTruck) {

        String url = getFormulaPriceLookUpUrl(
                fromAreaID,
                toAreaID,
                fromDistrictID,
                toDistrictID,
                fromLat,
                fromLong,
                toLat,
                toLong,
                truckModelID,
                truckTypeID,
                isWholeTruck);

        Response response = UtilSteps.get(Config.brokerAuthorization, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (statusCode == Config.STATUS_CODE_NO_CONTENT) {
            Log.errorAndStop("Price look up error: no content " + result);
        } else if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Price look up error: " + result);
        } else {
            Log.debug("Price look up success: " + result);
        }

        return result;
    }

    private String getCreateNewHybridOrderBody(int truckQuantity, JSONArray jsonArrayTrucks) {

        int weight = CommonFlow.calculateWeightByTruckQuantity(truckQuantity, Config.MAX_WEIGHT_OF_TRUCK_MODEL_44);

        String body = "\n{\n" +
                "  \"order\": {\n" +
                "\t\"order_type\":\"" + Config.orderCreationHybrid + "\",\n" +
                "\t\"images\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"url\":\"" + Config.orderCreationImageUrl + "\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "    \"pickup_location_attributes\": {\n" +
                "\t\t\t\"city\":\"" + Config.hybridOrderCreationPickUpCity + "\",\n" +
                "\t\t\t\"area_id\":\"" + Config.hybridOrderCreationPickUpAreaID + "\",\n" +
                "\t\t\t\"address\":\"" + Config.hybridOrderCreationPickUpAddress + "\",\n" +
                "\t\t\t\"description\":\"" + Config.hybridOrderCreationPickUpDescription + "\",\n" +
                "\t\t\t\"place_id\":\"" + Config.hybridOrderCreationPickUpPlaceId + "\",\n" +
                "\t\t\t\"value\":\"" + Config.hybridOrderCreationPickUpValue + "\",\n" +
                "\t\t\t\"district_id\":\"" + Config.hybridOrderCreationPickUpDistrictID + "\",\n" +
                "\t\t\t\"lat\":" + Config.hybridOrderCreationPickUpLat + ",\n" +
                "\t\t\t\"lng\":" + Config.hybridOrderCreationPickUpLong + "\n" +
                "    },\n" +
                "    \"dropoff_location_attributes\": {\n" +
                "\t\t\t\"city\":\"" + Config.hybridOrderCreationDropOffCity + "\",\n" +
                "\t\t\t\"area_id\":\"" + Config.hybridOrderCreationDropOffAreaID + "\",\n" +
                "\t\t\t\"address\":\"" + Config.hybridOrderCreationDropOffAddress + "\",\n" +
                "\t\t\t\"description\":\"" + Config.hybridOrderCreationDropOffDescription + "\",\n" +
                "\t\t\t\"place_id\":\"" + Config.hybridOrderCreationDropOffPlaceId + "\",\n" +
                "\t\t\t\"value\":\"" + Config.hybridOrderCreationDropOffValue + "\",\n" +
                "\t\t\t\"district_id\":\"" + Config.hybridOrderCreationDropOffDistrictID + "\",\n" +
                "\t\t\t\"lat\":" + Config.hybridOrderCreationDropOffLat + ",\n" +
                "\t\t\t\"lng\":" + Config.hybridOrderCreationDropOffLong + "\n" +
                "    },\n" +
                "    \"truck_quantity\": " + truckQuantity + ",\n" +
                "    \"pickup_datetime\": \"" + Config.orderCreationPickUpDateTime + "\",\n" +
                "    \"cargo_types\": \"" + Config.orderCreationCargoType + "\",\n" +
                "    \"cargo_weight\": \"" + weight + "\",\n" +
                "    \"truck_type_id\": " + Config.orderCreationTruckTypeID + ",\n" +
                "    \"truck_model_id\": " + Config.orderCreationTruckModelID + ",\n" +
                "    \"cargo_length\": " + Config.orderCreationCargoLength + ",\n" +
                "    \"cargo_width\": " + Config.orderCreationCargoWidth + ",\n" +
                "    \"cargo_height\": " + Config.orderCreationCargoHeight + ",\n" +
                "    \"is_whole_truck\": true\n" +
                "  }\n" +
                "}";

        JSONObject jsonObject = new JSONObject(body);
        JSONObject joOrder = jsonObject.getJSONObject("order");
        joOrder.put("trucks", jsonArrayTrucks);

        return jsonObject.toString();
    }

    private String getTruckSuggestionByTruckQuantity(int truckQuantity, int cargoLength, int cargoWidth, int cargoHeight) {

        int cargoWeight = CommonFlow.calculateWeightByTruckQuantity(truckQuantity, Config.MAX_WEIGHT_OF_TRUCK_MODEL_44);

        String url = Config.apiBaseUrl + BrokerMobileAppEndPoint.TRUCK_SUGGESTION +
                "?cargo_weight=" + cargoWeight +
                "&cargo_length=" + cargoLength +
                "&cargo_width=" + cargoWidth +
                "&cargo_height=" + cargoHeight;

        Response response = UtilSteps.get(Config.brokerAuthorization, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Get truck suggestion error: " + result);
        } else {
            Log.debug("Get truck suggestion success: " + result);
        }

        return result;
    }

    private String createNewHybridOrderWithTruckQuantity(int truckQuantity) {

        String url = Config.apiBaseUrl + BrokerMobileAppEndPoint.ORDERS;
        String body = getCreateNewHybridOrderBody(truckQuantity, Config.jaSuggestionTrucks);

        Response response;
        int statusCode;
        int retryNumber = 0;
        do {
            if (retryNumber == Config.RETRY_TEST_NUMBER) Log.errorAndStop("Have error when creating new order");

            response = UtilSteps.post(Config.brokerAuthorization, body, url);

            statusCode = response.statusCode();
            String result = response.body().asString();

            
            if (UtilSteps.isStatusCodeFail(statusCode)) {
                Log.errorAndStop("Create new order error: " + result);
            } else {
                Log.debug("Create new order success: " + result);
                break;
            }

            Timer.waitFor(1,1);
            retryNumber++;
        } while (retryNumber < 10);

        return response.body().asString();
    }

    private Response signIn(String email, String password) {

        String url = Config.apiBaseUrl + BrokerMobileAppEndPoint.SIGN_IN;

        String body =
                "\n{\n" +
                "  \"credentials\": {\n" +
                "    \"email\":\"" + email + "\",\n" +
                "    \"password\": \"" + password + "\"\n" +
                "  }\n" +
                "}";

        Response response;
        int statusCode;
        int retryNumber = 0;
        do {
            if (retryNumber == Config.RETRY_TEST_NUMBER) Log.errorAndStop("Have error when driver sign in");

            response = UtilSteps.post(body, url);

            statusCode = response.statusCode();
            String authenticationToken = response.header("Authorization");
            String result = response.body().asString();

            
            if (UtilSteps.isStatusCodeFail(statusCode)) {
                Log.error("Sign in error: " + result);
            } else {
                Log.debug("Authentication token: " + authenticationToken);
                Log.debug("Sign in success: " + result);
                break;
            }

            Timer.waitFor(1,1);
            retryNumber++;
        } while (retryNumber < 10);

        return response;
    }

    //------------------Steps------------------------

    @Step
    public void shouldBeAbleToLookUpPrice(String pickUpAreaID, String dropOffAreaID,
                                          String fromDistrictID, String toDistrictID,
                                          String truckModelID, String truckTypeID, boolean isWholeTruck) {

        String result = getFullTruckPriceLookUp(
                pickUpAreaID,
                dropOffAreaID,
                fromDistrictID,
                toDistrictID,
                truckModelID,
                truckTypeID,
                isWholeTruck);

        Config.newPriceLookup = (int) Math.round(JsonHelper.getDouble(result, "route_pricing.price"));
    }

    @Step
    public void shouldBeAbleToLookUpFormulaPrice(String pickUpAreaID, String dropOffAreaID,
                                                 String fromDistrictID, String toDistrictID,
                                                 String fromLat, String fromLong,
                                                 String toLat, String toLong,
                                                 String truckModelID, String truckTypeID, boolean isWholeTruck) {

        String result = getFormulaPriceLookUp(
                pickUpAreaID,
                dropOffAreaID,
                fromDistrictID,
                toDistrictID,
                fromLat,
                fromLong,
                toLat,
                toLong,
                truckModelID,
                truckTypeID,
                isWholeTruck);
    }

    @Step
    public void checkAllPriceLookUpTester(String pickUpAreaID, String dropOffAreaID,
                                          String fromDistrictID, String toDistrictID,
                                          String truckModelID, String truckTypeID, boolean isWholeTruck, Double price) {

        String result = getFullTruckPriceLookUp(
                pickUpAreaID,
                dropOffAreaID,
                fromDistrictID,
                toDistrictID,
                truckModelID,
                truckTypeID,
                isWholeTruck);

        int responsePrice = (int) Math.round(JsonHelper.getDouble(result, "route_pricing.price"));
        int specPrice = (int) (price * 0.7 * 1.1);

        Log.info("Spec price after markup: " + specPrice);
        Log.info("Data price: " + responsePrice);

        if (isPriceTrue(specPrice, responsePrice)) {
            Log.highlight("Data price is correct");
        } else {
            Log.errorAndStop("Data price is wrong");
        }
    }

    @Step
    public void checkLTLLookUp(Double cargoWeight, Double cargoLength, Double cargoWidth,
                               Double cargoHeight, String fromAreaID, String toAreaID,
                               String truckModelID, String truckTypeID, int pricingFactor, String unitType) {

        Double markUp = 1.05;

        String result = getLTLTruckPriceLookUp(
                cargoWeight,
                cargoLength,
                cargoWidth,
                cargoHeight,
                fromAreaID,
                toAreaID,
                truckModelID,
                truckTypeID
                );

        int responsePrice = (int) Math.round(JsonHelper.getDouble(result, "ltl_pricing.price"));

        int truePrice = getTruePrice(
                cargoWeight,
                cargoLength,
                cargoWidth,
                cargoHeight,
                pricingFactor,
                unitType,
                markUp
        );

        Log.info("True price after markup: " + truePrice);
        Log.info("Data price: " + responsePrice);

        Assert.assertEquals(unitType, JsonHelper.getString(result, "ltl_pricing.unit_type"));
        if (isPriceTrue(truePrice, responsePrice)) {
            Log.highlight("Data price is correct");
        } else {
            Log.errorAndStop("Data price is wrong");
        }
    }

    private int getTruePrice(Double cargoWeight, Double cargoLength, Double cargoWidth,
                             Double cargoHeight, int pricingFactor, String unitType, Double markup) {

        int truePrice = 0;
        switch (unitType) {

            case "weight":
                Log.debug("get weight price");
                truePrice = (int) (cargoWeight * pricingFactor * markup);
                break;

            case "volume":
                Log.debug("get volume price");
                Double cbm = getCBM(cargoLength, cargoWidth, cargoHeight);
                truePrice = (int) (cbm * pricingFactor * markup);
                break;

            default:
                Log.error("Unit type is wrong!");
                break;
        }

        return truePrice;
    }

    private Double getCBM(Double cargoLength, Double cargoWidth, Double cargoHeight) {
        return cargoLength * cargoWidth * cargoHeight;
    }

    @Step
    public int shouldBeAbleToSignIn(String brokerEmail, String brokerPassword) {

        Response response = signIn(brokerEmail, brokerPassword);

        int brokerID = JsonHelper.getInt(response.body().asString(), "broker.id");
        Config.brokerAuthorization = "Bearer " + response.header("Authorization");

        Log.debug("Set global brokerID = " + brokerID);
        Log.debug("Set global brokerAuthorization = " + Config.brokerAuthorization);

        return brokerID;
    }

    @Step
    public JSONArray shouldBeAbleToBeSuggestedTrucksByTruckQuantity(int truckQuantity, int cargoLength, int cargoWidth, int cargoHeight) {
        String result = getTruckSuggestionByTruckQuantity(truckQuantity, cargoLength, cargoWidth, cargoHeight);

        JSONArray jaTruck = JsonHelper.getJsonArray(result, "truck_models");
        Config.jaSuggestionTrucks = jaTruck;

        return jaTruck;
    }

    @Step
    public void shouldBeAbleToCreateNewHybridOrderWithTruckQuantity(int truckQuantity) {
        String result = createNewHybridOrderWithTruckQuantity(truckQuantity);

        Config.newOrderID = JsonHelper.getInt(result, "order.id");
        Assert.assertEquals("has_bidding", JsonHelper.getString(result, "order.status"));
        Assert.assertEquals("original_hybrid", JsonHelper.getString(result, "order.order_type"));

        Config.orderCreationTruckQuantity = truckQuantity;
        CommonFlow.addListNewOrder(Config.newOrderID);
    }

}
