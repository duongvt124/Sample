package lgv.automation.steps.api;

import com.jayway.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lgv.automation.constants.api.OpsToolEndPoint;
import lgv.automation.util.JsonHelper;
import lgv.automation.util.Log;
import lgv.automation.util.Timer;
import lgv.automation.util.api.CommonFlow;
import lgv.automation.util.api.Config;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class OpsToolSteps {

    private String getLTLTruckPriceLookUpUrl(Double cargoWeight, Double cargoLength, Double cargoWidth,
                                             Double cargoHeight, String fromAreaID, String toAreaID,
                                             String truckModelID, String truckTypeID) {

        String url;
        if (truckTypeID.equals("3")) {
            url = Config.apiBaseUrl + OpsToolEndPoint.SHARE_TRUCK_PRICE_LOOKUP + "?"
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
            url = Config.apiBaseUrl + OpsToolEndPoint.SHARE_TRUCK_PRICE_LOOKUP + "?"
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

    private String getPriceLookUpUrl(String fromAreaID, String toAreaID,
                                     String fromDistrictID, String toDistrictID,
                                     String truckModelID, String truckTypeID, boolean isWholeTruck) {

        String url;
        if (truckTypeID.equals("3")) {
            url = Config.apiBaseUrl + OpsToolEndPoint.FULL_TRUCK_PRICE_LOOKUP + "?"
                    + "from_area_id=" + fromAreaID
                    + "&to_area_id=" + toAreaID
                    + "&from_district_id=" + fromDistrictID
                    + "&to_district_id=" + toDistrictID
                    + "&truck_model_id=" + truckModelID
                    + "&truck_type_id=" + truckTypeID
                    + "&is_whole_truck=" + isWholeTruck
                    + "&use_trucker_container=true";
        } else {
            url = Config.apiBaseUrl + OpsToolEndPoint.FULL_TRUCK_PRICE_LOOKUP + "?"
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

    private String getPredictivePriceLookBody(String fromAreaID, String toAreaID, String cargoWeight,
                                               String cargoHeight, String cargoLength, String cargoWidth,
                                               int distance, int duration, int truckTypeID, int truckModelID,
                                               int truckQuantity, String pickupDateTime, boolean isWholeTruck) {

        return "{\n" +
                "    \"from_area_id\": " + fromAreaID + ",\n" +
                "    \"to_area_id\": " + toAreaID + ",\n" +
                "    \"cargo_height\": \"" + cargoHeight + "\",\n" +
                "    \"cargo_length\": \"" + cargoLength + "\",\n" +
                "    \"cargo_width\": \"" + cargoWidth + "\",\n" +
                "    \"cargo_weight\": \"" + cargoWeight + "\",\n" +
                "    \"distance\": " + distance + ",\n" +
                "    \"duration\": " + duration + ",\n" +
                "    \"truck_model_id\": " + truckModelID + ",\n" +
                "    \"truck_type_id\": " + truckTypeID + ",\n" +
                "    \"truck_quantity\": \"" + truckQuantity + "\",\n" +
                "    \"is_whole_truck\": " + isWholeTruck + ",\n" +
                "    \"is_tax_invoice_requested\": true,\n" +
                "    \"pickup_datetime\": \"" + pickupDateTime + "\",\n" +
                "    \"dropoff_datetime\": \"\"\n" +
                "}";
    }

    private String getPredictPriceBody(String fromAreaID, String toAreaID, String cargoWeight,
                                              String cargoHeight, String cargoLength, String cargoWidth,
                                              int distance, int duration, List<Integer> listTruckModelID, int truckQuantity,
                                                String pickupDateTime, boolean isWholeTruck) {

        return "\n{\n" +
                "    \"from_area_id\": " + fromAreaID + ",\n" +
                "    \"to_area_id\": " + toAreaID + ",\n" +
                "    \"cargo_height\": \"" + cargoHeight + "\",\n" +
                "    \"cargo_length\": \"" + cargoLength + "\",\n" +
                "    \"cargo_width\": \"" + cargoWidth + "\",\n" +
                "    \"cargo_weight\": \"" + cargoWeight + "\",\n" +
                "    \"distance\": " + distance + ",\n" +
                "    \"duration\": " + duration + ",\n" +
                "    \"truck_quantity\": \"" + truckQuantity + "\",\n" +
                "    \"is_whole_truck\": " + isWholeTruck + ",\n" +
                "    \"pickup_datetime\": \"" + pickupDateTime + "\",\n" +
                "    \"dropoff_datetime\": \"\",\n" +
                "    \"trucks\": [\n" +
                "      {\n" +
                "        \"truck_type_id\": 1,\n" +
                "        \"truck_model_id\": " + listTruckModelID.get(0) + "\n" +
                "      },\n" +
                "      {\n" +
                "        \"truck_type_id\": 2,\n" +
                "        \"truck_model_id\": " + listTruckModelID.get(1) + "\n" +
                "      },\n" +
                "      {\n" +
                "        \"truck_type_id\": 3,\n" +
                "        \"truck_model_id\": " + listTruckModelID.get(2) + "\n" +
                "      },\n" +
                "      {\n" +
                "        \"truck_type_id\": 5,\n" +
                "        \"truck_model_id\": " + listTruckModelID.get(3) + "\n" +
                "      },\n" +
                "      {\n" +
                "        \"truck_type_id\": 6,\n" +
                "        \"truck_model_id\": " + listTruckModelID.get(4) + "\n" +
                "      },\n" +
                "      {\n" +
                "        \"truck_type_id\": 7,\n" +
                "        \"truck_model_id\": " + listTruckModelID.get(5) + "\n" +
                "      }\n" +
                "    ]\n" +
                "}";
    }

    private String getPriceLookUp(String fromAreaID, String toAreaID,
                                  String fromDistrictID, String toDistrictID,
                                  String truckModelID, String truckTypeID, boolean isWholeTruck) {

        String url = getPriceLookUpUrl(
                fromAreaID,
                toAreaID,
                fromDistrictID,
                toDistrictID,
                truckModelID,
                truckTypeID,
                isWholeTruck);

        Response response = UtilSteps.get(Config.opsToolAuthorization, url);

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

        Response response = UtilSteps.get(Config.opsToolAuthorization, url);

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

    private boolean isPriceTrue(int specPrice, int responsePrice) {

        int agreementDifferencePrice = 50000;
        int differencePrice = Math.abs(specPrice - responsePrice);

        if (differencePrice < agreementDifferencePrice) return true;
        else return false;
    }

    private String getPredictivePriceLookUp(String fromAreaID, String toAreaID, String cargoWeight,
                                            String cargoHeight, String cargoLength, String cargoWidth,
                                            int distance, int duration, int truckTypeID, int truckModelID,
                                            int truckQuantity, String pickupDateTime, boolean isWholeTruck) {

        String url = Config.apiBaseUrl + OpsToolEndPoint.PREDICTIVE_PRICE_LOOKUP;
        String body = getPredictivePriceLookBody(
                fromAreaID,
                toAreaID,
                cargoWeight,
                cargoHeight,
                cargoLength,
                cargoWidth,
                distance,
                duration,
                truckTypeID,
                truckModelID,
                truckQuantity,
                pickupDateTime,
                isWholeTruck
        );

        Response response = UtilSteps.post(Config.opsToolAuthorization, body, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (statusCode == Config.STATUS_CODE_NO_CONTENT) {
            Log.error("Get predictive price look up error: no content " + result);
        } else if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Get predictive price look up error: " + result);
        } else {
            Log.debug("Get predictive price look up success: " + result);
        }

        return result;
    }

    private String getPredictPrice(String fromAreaID, String toAreaID, String cargoWeight,
                                            String cargoHeight, String cargoLength, String cargoWidth,
                                            int distance, int duration, List<Integer> listTruckModelID, int truckQuantity,
                                            String pickupDateTime, boolean isWholeTruck) {

        String url = Config.apiBaseUrl + OpsToolEndPoint.PREDICT_PRICE;
        String body = getPredictPriceBody(
                fromAreaID,
                toAreaID,
                cargoWeight,
                cargoHeight,
                cargoLength,
                cargoWidth,
                distance,
                duration,
                listTruckModelID,
                truckQuantity,
                pickupDateTime,
                isWholeTruck
        );

        Response response = UtilSteps.post(Config.opsToolAuthorization, body, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (statusCode == Config.STATUS_CODE_NO_CONTENT) {
            Log.errorAndStop("Get predict price error: no content " + result);
        } else if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Get predict price error: " + result);
        } else {
            Log.debug("Get predict price success: " + result);
        }

        return result;
    }

    private String confirmPayment(int orderID, int price) {

        Log.info("Confirm payment on Ops tool");
        String url = Config.apiBaseUrl + OpsToolEndPoint.ORDERS + orderID + "/payment_confirmation";

        String body = "\n{ \n" +
                "\t\"paid_order\": {\n" +
                "\t\t\"amount\": " + price + ", \n" +
                "\t\t\"note\": \"" + Config.confirmPaymentNote + "\" \n" +
                "\t} \n" +
                "}";
        Response response = UtilSteps.post(Config.opsToolAuthorization, body, url);

        Integer statusCode = response.statusCode();
        String result = response.body().asString();

        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Confirm payment error: " + result);
        } else {
            Log.debug("Confirm payment success: " + result);
        }

        return result;
    }

    private String sendOfferToSingleDriver(int truckRequestID) {

        Log.info("Send offer to single driver on Ops tool");
        String url = Config.apiBaseUrl + OpsToolEndPoint.TRUCK_REQUEST +
                "/" + truckRequestID + "/send_offer";

        Response response = UtilSteps.postWithoutBody(Config.opsToolAuthorization, url);

        Integer statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Send offer to single driver error: " + result);
        } else {
            Log.debug("Send offer to single driver success: " + result);
        }

        return result;
    }

    private String getDriverInfoByDriverID(int driverID) {

        Log.info("Start get driver info by driver id");
        String url = Config.apiBaseUrl + OpsToolEndPoint.GET_DRIVER + "/" + driverID;

        Response response = UtilSteps.get(Config.opsToolAuthorization, url);

        Integer statusCode = response.statusCode();
        String result = response.body().asString();

        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Get driver info by driver id error: " + result);
        } else {
            Log.debug("Get driver info by driver id success: " + result);
        }

        return result;
    }

    private String healthCheckGetDriverInfoByDriverID(int driverID) {

        Log.info("Start get driver info by driver id");
        String url = Config.apiBaseUrl + OpsToolEndPoint.GET_DRIVER + "/" + driverID;

        Response response = UtilSteps.get(Config.opsToolAuthorization, url);

        Integer statusCode = response.statusCode();
        String result = response.body().asString();

        if (UtilSteps.isServerError(statusCode)) {
            Log.errorAndStop("Get driver info by driver id error: " + result);
        } else {
            Log.debug("Get driver info by driver id success: " + result);
        }

        return result;
    }

    private String getDriverDocumentsByDriverID(int driverID) {

        Log.info("Start get driver documents by driver id");
        String url = Config.apiBaseUrl + OpsToolEndPoint.DRIVER_MANAGEMENT + "/" + driverID + "/documents";

        Response response = UtilSteps.get(Config.opsToolAuthorization, url);

        Integer statusCode = response.statusCode();
        String result = response.body().asString();

        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Get driver documents by driver id error: " + result);
        } else {
            Log.debug("Get driver documents by driver id success: " + result);
        }

        return result;
    }

    private String healthCheckApiGetDriverDocumentsByDriverID(int driverID) {

        Log.info("Start get driver documents by driver id");
        String url = Config.apiBaseUrl + OpsToolEndPoint.DRIVER_MANAGEMENT + "/" + driverID + "/documents";

        Response response = UtilSteps.get(Config.opsToolAuthorization, url);

        Integer statusCode = response.statusCode();
        String result = response.body().asString();

        if (UtilSteps.isServerError(statusCode)) {
            Log.errorAndStop("Get driver documents by driver id error: " + result);
        } else {
            Log.debug("Get driver documents by driver id success: " + result);
        }

        return result;
    }

    private String acceptDocument(int driverID, int documentID) {

        Log.info("Start accept driver document");
        String url = Config.apiBaseUrl + OpsToolEndPoint.DRIVER_MANAGEMENT +
                "/" + driverID + "/documents/" + documentID + "/accept";

        Response response = UtilSteps.get(Config.opsToolAuthorization, url);

        Integer statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Accept driver document error: " + result);
        } else {
            Log.debug("Accept driver document success: " + result);
        }

        return result;
    }

    private String rejectDocument(int driverID, int documentID) {

        Log.info("Start reject driver document");
        String url = Config.apiBaseUrl + OpsToolEndPoint.DRIVER_MANAGEMENT +
                "/" + driverID + "/documents/" + documentID + "/reject";

        Response response = UtilSteps.patch(Config.opsToolAuthorization, url);

        Integer statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Reject driver document error: " + result);
        } else {
            Log.debug("Reject driver document success: " + result);
        }

        return result;
    }

    //------------------Steps------------------------

    @Step
    public List<Integer> shouldBeAbleToGetDriverDocuments(int driverID) {

        String result = getDriverDocumentsByDriverID(driverID);

        List<String> listDocumentType = JsonHelper.getListString(result, "documents.document_type");
        List<Integer> listDocumentID = JsonHelper.getListInt(result, "documents.id");

        setNewDocumentID(listDocumentType, listDocumentID);

        return listDocumentID;
    }

    @Step
    public void shouldBeAbleToHealthCheckApiGetDriverDocuments(int driverID) {

        String result = healthCheckApiGetDriverDocumentsByDriverID(driverID);
    }

    @Step
    public void shouldBeAbleToGetDriverInfo(Integer driverID) {

        String result = getDriverInfoByDriverID(driverID);

        Assert.assertEquals(driverID, JsonHelper.getInt(result, "driver.id"));
    }

    @Step
    public void shouldBeAbleToHealthCheckApiGetDriverInfo(Integer driverID) {

        String result = healthCheckGetDriverInfoByDriverID(driverID);
    }

    private void setOrderTypeOfFistOrder(String orderType) {

        String uiOrderType = lgv.automation.util.desktop.CommonFlow.parseAPIOrderTypeToUIOrderType(orderType);
        lgv.automation.util.desktop.Config.orderSummaryOrderType = uiOrderType;
    }

    @Step
    public void verifyDriverApproved(Integer driverID) {

        String result = getDriverInfoByDriverID(driverID);

        Assert.assertEquals(driverID, JsonHelper.getInt(result, "driver.id"));
        Assert.assertEquals("accepted", JsonHelper.getString(result, "driver.identity_id_status"));
        Assert.assertEquals("accepted", JsonHelper.getString(result, "driver.license_status"));
        Assert.assertEquals("approved", JsonHelper.getString(result, "driver.status"));
    }

    private void setNewDocumentID(List<String> listDocumentType, List<Integer> listDocumentID) {

        int documentNumber = 0;
        for (int i = 0; i < listDocumentType.size(); i++) {

            if (listDocumentType.get(i).equals("identity_id")) {
                Config.newIdentityIDDocumentID = listDocumentID.get(i);
                documentNumber++;

            } else if (listDocumentType.get(i).equals("license")) {
                Config.newLicenseDocumentID = listDocumentID.get(i);
                documentNumber++;

            } else if (listDocumentType.get(i).equals("certificate_registry")) {
                Config.newCertificateRegistryDocumentID = listDocumentID.get(i);
                documentNumber++;
            }

            if (documentNumber == 3) break;
        }
    }

    @Step
    public void shouldBeAbleToAcceptDriverIdentityID(int driverID, int driverIdentityIDDocumentID) {

        String result = acceptDocument(driverID, driverIdentityIDDocumentID);
    }

    @Step
    public void shouldBeAbleToAcceptDriverLicense(int driverID, int driverLicenseDocumentID) {

        String result = acceptDocument(driverID, driverLicenseDocumentID);
    }

    @Step
    public void shouldBeAbleToAcceptDriverCertificateRegistry(int driverID, int driverCertificateRegistryDocumentID) {

        String result = acceptDocument(driverID, driverCertificateRegistryDocumentID);
    }

    @Step
    public void shouldBeAbleToRejectDriverIdentityID(int driverID, int driverIdentityIDDocumentID) {

        String result = rejectDocument(driverID, driverIdentityIDDocumentID);
    }

    @Step
    public void shouldBeAbleToRejectDriverLicense(int driverID, int driverLicenseDocumentID) {

        String result = rejectDocument(driverID, driverLicenseDocumentID);
    }

    @Step
    public void shouldBeAbleToRejectDriverCertificateRegistry(int driverID, int driverCertificateRegistryDocumentID) {

        String result = rejectDocument(driverID, driverCertificateRegistryDocumentID);
    }

    @Step
    public void checkAllPriceLookUpTester(String pickUpAreaID, String dropOffAreaID,
                                          String fromDistrictID, String toDistrictID,
                                          String truckModelID, String truckTypeID, boolean isWholeTruck, Double price) {

        String result = getPriceLookUp(
                pickUpAreaID,
                dropOffAreaID,
                fromDistrictID,
                toDistrictID,
                truckModelID,
                truckTypeID,
                isWholeTruck);

        int responsePrice = (int) Math.round(JsonHelper.getDouble(result, "route_pricing.price"));

        Log.info("Spec price after markup: " + price);
        Log.info("Data price: " + responsePrice);

        if (isPriceTrue(price.intValue(), responsePrice)) {
            Log.highlight("Data price is correct");
        } else {
            Log.errorAndStop("Data price is wrong");
        }
    }

    @Step
    public void shouldBeAbleToLookUpPrice(String pickUpAreaID, String dropOffAreaID,
                                          String fromDistrictID, String toDistrictID,
                                          String truckModelID, String truckTypeID, boolean isWholeTruck) {

        String result = getPriceLookUp(
                pickUpAreaID,
                dropOffAreaID,
                fromDistrictID,
                toDistrictID,
                truckModelID,
                truckTypeID,
                isWholeTruck);
    }

    @Step
    public void checkLTLLookUp(Double cargoWeight, Double cargoLength, Double cargoWidth,
                               Double cargoHeight, String fromAreaID, String toAreaID,
                               String truckModelID, String truckTypeID, int pricingFactor, String unitType) {

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
                unitType
        );

        Log.info("True price: " + truePrice);
        Log.info("Data price: " + responsePrice);

        Assert.assertEquals(unitType, JsonHelper.getString(result, "ltl_pricing.unit_type"));
        if (isPriceTrue(truePrice, responsePrice)) {
            Log.highlight("Data price is correct");
        } else {
            Log.errorAndStop("Data price is wrong");
        }
    }

    @Step
    public void shouldBeAbleToGetPredictivePriceLookup(String fromAreaID, String toAreaID, String cargoWeight,
                                                       String cargoHeight, String cargoLength, String cargoWidth,
                                                       int distance, int duration, int truckTypeID, int truckModelID,
                                                       int truckQuantity, String pickupDateTime, boolean isWholeTruck) {

        String result = getPredictivePriceLookUp(
                fromAreaID,
                toAreaID,
                cargoWeight,
                cargoHeight,
                cargoLength,
                cargoWidth,
                distance,
                duration,
                truckTypeID,
                truckModelID,
                truckQuantity,
                pickupDateTime,
                isWholeTruck
        );
    }

    @Step
    public void shouldBeAbleToGetPredictPrice(String fromAreaID, String toAreaID, String cargoWeight,
                                              String cargoHeight, String cargoLength, String cargoWidth,
                                              int distance, int duration, List<Integer> listTruckModelID, int truckQuantity,
                                              String pickupDateTime, boolean isWholeTruck) {

        String result = getPredictPrice(
                fromAreaID,
                toAreaID,
                cargoWeight,
                cargoHeight,
                cargoLength,
                cargoWidth,
                distance,
                duration,
                listTruckModelID,
                truckQuantity,
                pickupDateTime,
                isWholeTruck
        );
    }

    private int getTruePrice(Double cargoWeight, Double cargoLength, Double cargoWidth,
                             Double cargoHeight, int pricingFactor, String unitType) {

        int truePrice = 0;
        switch (unitType) {

            case "weight":
                Log.debug("get weight price");
                truePrice = (int) (cargoWeight * pricingFactor);
                break;

            case "volume":
                Log.debug("get volume price");
                Double cbm = getCBM(cargoLength, cargoWidth, cargoHeight);
                truePrice = (int) (cbm * pricingFactor);
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

    private String bidding(int orderId, int driverID, int truckQuantity) {
        Log.info("Bidding on Opstool");
        String url = Config.apiBaseUrl + OpsToolEndPoint.ORDERS + orderId + "/biddings";

        String body =
                "\n{\n" +
                "\t\"bidding\": {\n" +
                "\t\t\"amount\": " + truckQuantity + ", \n" +
                "\t\t\"price\": \"" + Config.biddingPrice + "\", \n" +
                "\t\t\"bidder_type\": \"" + Config.bidderTypeDriver + "\", \n" +
                "\t\t\"driver_id\": " + driverID + "\n" +
                "\t}\n" +
                "}";

        Response response = UtilSteps.post(Config.opsToolAuthorization, body, url);

        Integer statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Bidding error: " + result);
        } else {
            Log.debug("Bidding success: " + result);
        }

        return result;
    }

//  STEP
    @Step
    public void shouldBeAbleToViewAssignedTruckInfo(Integer driverID, int numberOfTruck) {
        String result = getTrucksInfo(driverID);

        List<Integer> listTruckID =  JsonHelper.getListInt(result, "trucks.id");

        Config.listAssignedTruckID = new ArrayList<>();
        for(int i = 0; i < numberOfTruck; i++){
            Config.listAssignedTruckID.add(listTruckID.get(i));
            Config.listAssignedTruckRequestDriverID.add(driverID);
        }
    }

    @Step
    public int shouldBeAbleToBiddingOnHydra(int orderId, int driverID, int truckQuantity) {

        String result = bidding(orderId, driverID, truckQuantity);

        int biddingId = JsonPath.from(result).getInt("bidding.id");
        Assert.assertTrue(biddingId > 0);
        Assert.assertTrue(JsonPath.from(result).getString("bidding.status").equalsIgnoreCase("pending"));

        Config.newBiddingID = biddingId;
        Log.info("Bidding Id: " + biddingId);
        return biddingId;
    }

    private String createNewDriver(String phoneNumber, String password) {

        Log.info("Create new driver on ops tool");

        String url = Config.apiBaseUrl + OpsToolEndPoint.CREATE_DRIVER;
        String body = "\n{ \n" +
                        "  \"driver\": {\n" +
                        "    \"phone_number\": \"" + phoneNumber + "\",\n" +
                        "    \"password\": \"" + password + "\"\n" +
                        "  }\n" +
                        "}";

        Response response = UtilSteps.post(body, url);

        Integer statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.error("Create new driver error: " + result);
        } else {
            Log.debug("Create new driver success: " + result);
        }

        return result;
    }

    private String acceptBidding(int orderID, int biddingID) {

        Log.info("Accept Bidding on Opstool");
        String url = Config.apiBaseUrl + OpsToolEndPoint.ACCEPT_BIDDING + "/" + orderID +
                "/biddings/" + biddingID + "/accept";

        String body = "{\"bidding\":{\"selected\":true}}";

        Response response = UtilSteps.patch(Config.opsToolAuthorization, body, url);

        Integer statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Accept Bidding error: " + result);
        } else {
            Log.debug("Accept Bidding success: " + result);
        }

        return result;
    }

    @Step
    public boolean shouldBeAbleToCreateNewDriver(String phoneNumber, String password) {

        String result = createNewDriver(phoneNumber, password);

        if (result.equalsIgnoreCase("\"phone_number\":[\"đã có\"]")) {
            return true;
        } else {
            int driverID = JsonHelper.getInt(result, "driver.id");
            CommonFlow.addListNewDriver(phoneNumber, driverID);
            return false;
        }
    }

    @Step
    public void shouldBeAbleToAcceptBidding(Integer orderID, Integer biddingID) {
        String result = acceptBidding(orderID, biddingID);
    }

    @Step
    public void shouldBeAbleToConfirmPayment(int orderID, int price) {
        String result = confirmPayment(orderID, price);

        Assert.assertTrue(JsonPath.from(result).getInt("paid_order.order.id") == orderID);
        Assert.assertTrue(JsonPath.from(result).getString("paid_order.order.status").equalsIgnoreCase("paid"));
        Log.info("Confirm payment success");
    }

    @Step
    public void shouldBeAbleToSendOfferToSingleDriver(int truckRequestID) {
        String result = sendOfferToSingleDriver(truckRequestID);

        Log.info("Send offer to driver success");
    }

    private String getServiceQuotationListing(int orderID) {

        String url = Config.apiBaseUrl + OpsToolEndPoint.ORDERS + orderID + "/service_quotations";

        Response response = UtilSteps.get(Config.opsToolAuthorization, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Get service quotation listing error: " + result);
        } else {
            Log.debug("Get service quotation listing success: " + result);
        }

        return result;
    }

    private String acceptServiceQuotation(int serviceQuotationID, int unitPrice, int subTotalPrice,
                                          int vat, int totalPrice, int promotionID) {

        String url = Config.apiBaseUrl + OpsToolEndPoint.SERVICE_QUOTATION + "/" + serviceQuotationID + "/accept";
        String body = "\n{\n" +
                    "   \"payment_method_id\":2,\n" +
                    "   \"sub_total_price\":" + subTotalPrice + ",\n" +
                    "   \"total_price\":" + totalPrice + ",\n" +
                    "   \"vat\":" + vat + ",\n" +
                    "   \"unit_price\":" + unitPrice + "\n" +
//                    "   \"unit_price\":" + unitPrice + ",\n" +
//                    "   \"promotion\":{\n" +
//                    "       \"id\":" + promotionID + "\n" +
//                    "   }\n" +
                    "}";

        Response response = UtilSteps.patch(Config.opsToolAuthorization, body, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Accept service quotation error: " + result);
        } else {
            Log.debug("Accept service quotation success: " + result);
        }

        return result;
    }

    private String getTruckRequestInfo(int orderID) {
        String url = Config.apiBaseUrl + OpsToolEndPoint.ORDERS + orderID + "/truck_requests";

        Response response = UtilSteps.get(Config.opsToolAuthorization, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Get truck requests info error: " + result);
        } else {
            Log.debug("Get truck requests info success: " + result);
        }

        return result;
    }

    private String updateDriverIdentityImage(int driverID, String identityUrl) {
        String url = Config.apiBaseUrl + OpsToolEndPoint.DRIVER_MANAGEMENT + "/" + driverID + "/documents";

        String body = "\n{\n" +
                "\t\"document\": {\n" +
                "\t\t\"document_type\": \"identity_id\",\n" +
                "\t\t\"images\": [{\n" +
                "\t\t\t\"url\": \"" + identityUrl + "\"\n" +
                "\t\t}]\n" +
                "\t}\n" +
                "}";

        Response response = UtilSteps.post(Config.opsToolAuthorization, body, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Update driver identity image error: " + result);
        } else {
            Log.debug("Update driver identity image success: " + result);
        }

        return result;
    }

    private String updateDriverIdentityInfo(int driverID, int documentID, String identityID, String identityDate) {
        String url = Config.apiBaseUrl + OpsToolEndPoint.DRIVER_MANAGEMENT + "/" + driverID + "/documents/" + documentID;

        String body = "\n{\n" +
                "\t\"document\": {\n" +
                "\t\t\"document_type\": \"identity_id\",\n" +
                "\t\t\"data\": {\n" +
                "\t\t\t\"identity_id\": \"" + identityID + "\",\n" +
                "\t\t\t\"identity_date\": \"" + identityDate + "\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";

        Response response = UtilSteps.patch(Config.opsToolAuthorization, body, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Update driver identity info error: " + result);
        } else {
            Log.debug("Update driver identity info success: " + result);
        }

        return result;
    }

    private String updateDriverLicenseImage(int driverID, String licenseUrl) {
        String url = Config.apiBaseUrl + OpsToolEndPoint.DRIVER_MANAGEMENT + "/" + driverID + "/documents";

        String body = "\n{\n" +
                "\t\"document\": {\n" +
                "\t\t\"document_type\": \"license\",\n" +
                "\t\t\"images\": [{\n" +
                "\t\t\t\"url\": \"" + licenseUrl + "\"\n" +
                "\t\t}]\n" +
                "\t}\n" +
                "}";

        Response response = UtilSteps.post(Config.opsToolAuthorization, body, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Update driver license image error: " + result);
        } else {
            Log.debug("Update driver license image success: " + result);
        }

        return result;
    }

    private String updateDriverLicenseInfo(int driverID, int documentID, String licenseID, String licenseDate) {
        String url = Config.apiBaseUrl + OpsToolEndPoint.DRIVER_MANAGEMENT + "/" + driverID + "/documents/" + documentID;

        String body = "\n{\n" +
                "\t\"document\": {\n" +
                "\t\t\"document_type\": \"license\",\n" +
                "\t\t\"data\": {\n" +
                "\t\t\t\"license_id\": \"" + licenseID + "\",\n" +
                "\t\t\t\"license_date\": \"" + licenseDate + "\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";

        Response response = UtilSteps.patch(Config.opsToolAuthorization, body, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Update driver license info error: " + result);
        } else {
            Log.debug("Update driver license info success: " + result);
        }

        return result;
    }

    @Step
    public void shouldBeAbleToViewServiceQuotationListing(int orderID) {

        String result = getServiceQuotationListing(orderID);

        Config.listLogivanServiceQuotationID = JsonHelper.getListInt(result, "service_quotations.id");
    }

    @Step
    public void shouldBeAbleToAcceptServiceQuotation(Integer serviceQuotationID, int unitPrice, int subTotalPrice,
                                                     int vat, int totalPrice, int promotionID) {

        String result = acceptServiceQuotation(serviceQuotationID, unitPrice, subTotalPrice, vat, totalPrice, promotionID);

        Assert.assertEquals(serviceQuotationID, JsonHelper.getInt(result, "service_quotation.id"));
        Assert.assertEquals(true, JsonHelper.getBoolean(result, "service_quotation.selected"));
    }

    @Step
    public void shouldBeAbleToGetTruckRequestInfo(int orderID) {

        String result = getTruckRequestInfo(orderID);

        List<Integer> listUnassignedTruckRequestID = new ArrayList<>();
        List<Integer> listTruckRequestID = JsonHelper.getListInt(result, "truck_requests.id");
        List<String> listTruckRequestDriver = JsonHelper.getListString(result, "truck_requests.truck_request_driver");

        for (int i = 0; i < listTruckRequestDriver.size(); i++) {

            if (listTruckRequestDriver.get(i) == null) {
                listUnassignedTruckRequestID.add(listTruckRequestID.get(i));
            }
        }

        Config.listNewTruckRequestID = listTruckRequestID;
        Config.listUnassignedTruckRequestID = listUnassignedTruckRequestID;
    }

    @Step
    public void shouldBeAbleToGetTruckRequestDriverOfferID(int orderID) {

        String result = getTruckRequestInfo(orderID);

        Config.listAssignedTruckRequestDriverOfferID = JsonHelper.getListInt(result, "truck_requests.truck_request_driver.offer.id");

    }

    @Step
    public void shouldBeAbleToUpdateDriverIdentityImage(int driverID, String identityImage) {

        String result = updateDriverIdentityImage(driverID, identityImage);
    }

    @Step
    public void shouldBeAbleToUpdateDriverIdentityInfo(int driverID, int documentID, String identityID, String identityDate) {

        String result = updateDriverIdentityInfo(driverID, documentID, identityID, identityDate);
    }

    @Step
    public void shouldBeAbleToUpdateDriverLicenseImage(int driverID, String licenseImage) {

        String result = updateDriverLicenseImage(driverID, licenseImage);
    }

    @Step
    public void shouldBeAbleToUpdateDriverLicenseInfo(int driverID, int documentID, String licenseID, String licenseDate) {

        String result = updateDriverLicenseInfo(driverID, documentID, licenseID, licenseDate);
    }

}
