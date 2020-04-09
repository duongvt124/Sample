package lgv.automation.steps.api;

import io.restassured.response.Response;
import lgv.automation.constants.api.DriverEndPoint;
import lgv.automation.util.JsonHelper;
import lgv.automation.util.Log;
import lgv.automation.util.Timer;
import lgv.automation.util.api.CommonFlow;
import lgv.automation.util.api.Config;
import net.thucydides.core.annotations.Step;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DriverSteps {

    private Response signIn(String phoneNumber, String password) {
        Log.highlight("Start driver sign in!");

        String url = Config.apiBaseUrl + DriverEndPoint.DRIVER_SIGN_IN;

        String body = "\n{\n" +
                "  \"credentials\": {\n" +
                "    \"phone_number\": \"" + phoneNumber + "\",\n" +
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
            String result = response.body().asString();

            
            if (UtilSteps.isStatusCodeFail(statusCode)) {
                Log.error("Driver sign in error: " + result);
            } else {
                Log.debug("Driver sign in success: " + result);
                break;
            }

            Timer.waitFor(Config.RETRY_TEST_TIME,1);
            retryNumber++;
        } while (retryNumber < 10);

        return response;
    }

    private String updateIdentityUrl(int driverID, String identityUrl) {
        Log.highlight("Start to update driver identity!");

        String url = Config.apiBaseUrl + DriverEndPoint.DRIVER_INFO + "/" + driverID;

        String body = "\n{\n" +
                "  \"driver\": {\n" +
                "    \"identity_id_url\": \"" + identityUrl + "\"\n" +
                "  }\n" +
                "}";

        Response response = UtilSteps.put(Config.driverAuthorization, body, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Update driver identity error: " + result);
        } else {
            Log.debug("Update driver identity success: " + result);
        }

        return result;
    }

    private String updateLicense(int driverID, String licenseID, String licenseDate, String licenseUrl) {
        Log.highlight("Start to update driver license!");

        String url = Config.apiBaseUrl + DriverEndPoint.DRIVER_INFO + "/" + driverID;

        String body = "{\n" +
                "  \"driver\": {\n" +
                "    \"license_id\": \"" + licenseID + "\",\n" +
                "    \"license_date\": \"" + licenseDate + "\",\n" +
                "    \"license_url\": \"" + licenseUrl + "\"\n" +
                "  }\n" +
                "}";

        Response response = UtilSteps.patch(Config.driverAuthorization, body, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Update driver license error: " + result);
        } else {
            Log.debug("Update driver license success: " + result);
        }

        return result;
    }

    private String getDriverPreferencesInfo() {
        Log.highlight("Start to get preferences info!");

        String url = Config.apiBaseUrl + DriverEndPoint.PREFERENCES;

        Response response = UtilSteps.get(Config.driverAuthorization, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Get preferences info error: " + result);
        } else {
            Log.debug("Get preferences info success: " + result);
        }

        return result;
    }

    private String healthCheckApiGetDriverPreferencesInfo() {
        Log.highlight("Start to get preferences info!");

        String url = Config.apiBaseUrl + DriverEndPoint.PREFERENCES;

        Response response = UtilSteps.get(Config.driverAuthorization, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        if (UtilSteps.isServerError(statusCode)) {
            Log.errorAndStop("Get preferences info error: " + result);
        } else {
            Log.debug("Get preferences info success: " + result);
        }

        return result;
    }

    private String addPreferences(String preferredRouteType,
                                  int fromAreaID, String fromName, String fromLat, String fromLng,
                                  int toAreaID, String toName, String toLat, String toLng) {
        Log.highlight("Start to add preferences!");

        String url = Config.apiBaseUrl + DriverEndPoint.PREFERENCES;

        String body = "\n{\n" +
                "  \"driver_preferences\": {\n" +
                "    \"preferred_route_type\": \"" + preferredRouteType + "\",\n" +
                "    \"preferred_pickup_place\": {\n" +
                "      \"area_id\": " + fromAreaID + ",\n" +
                "      \"name\": \"" + fromName + "\",\n" +
                "      \"lat\": " + fromLat + ",\n" +
                "      \"lng\": " + fromLng + "\n" +
                "    },\n" +
                "    \"preferred_dropoff_places\": [\n" +
                "      {\n" +
                "      \"area_id\": " + toAreaID + ",\n" +
                "      \"name\": \"" + toName + "\",\n" +
                "      \"lat\": " + toLat + ",\n" +
                "      \"lng\": " + toLng + "\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        Response response = UtilSteps.post(Config.driverAuthorization, body, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Add preferences error: " + result);
        } else {
            Log.debug("Add preferences success: " + result);
        }

        return result;
    }

    private String addTruck(String certificateRegistryUrl, String truckPlate,
                            int driverTruckModelID, int driverTruckTypeID) {
        Log.highlight("Start to add truck!");

        String url = Config.apiBaseUrl + DriverEndPoint.DRIVER_UPDATE_TRUCK_INFO;

        Response response;
        int statusCode;
        int retryNumber = 0;
        do {
            if (retryNumber == Config.RETRY_TEST_NUMBER) Log.errorAndStop("Have error when driver add truck");

            String body = "\n{\n" +
                    "  \"certificate_registry\": {\n" +
                    "    \"images\": [\n" +
                    "      {\n" +
                    "        \"url\": \"" + certificateRegistryUrl + "\"\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"truck\": {\n" +
                    "      \"plate\": \"" + truckPlate + randomPlateSuffix() + "\",\n" +
                    "      \"truck_model_id\": " + driverTruckModelID + ",\n" +
                    "      \"truck_type_id\": " + driverTruckTypeID + "\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";

            response = UtilSteps.post(Config.driverAuthorization, body, url);

            statusCode = response.statusCode();
            String result = response.body().asString();

            
            if (UtilSteps.isStatusCodeFail(statusCode)) {
                Log.error("Add truck error: " + result);
            } else {
                Log.debug("Add truck success: " + result);
                break;
            }

            Timer.waitFor(1,1);
            retryNumber++;
        } while (retryNumber < 10);

        return response.body().asString();
    }

    private String getSelfTruckInfo() {
        Log.highlight("Start to get truck info!");

        String url = Config.apiBaseUrl + DriverEndPoint.CERTIFICATE_REGISTRY;

        Response response = UtilSteps.get(Config.driverAuthorization, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Get truck info error: " + result);
        } else {
            Log.debug("Get truck info success: " + result);
        }

        return result;
    }

    private String getDriverInfoByDriverId(int driverID) {
        Log.highlight("Start to get driver info by driver id!");

        String url = Config.apiBaseUrl
                + DriverEndPoint.DRIVER_INFO + "/" + driverID;

        Response response = UtilSteps.get(Config.driverAuthorization, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Get driver info error: " + result);
        } else {
            Log.debug("Get driver info success: " + result);
        }

        return result;
    }

    private String healthCheckApiGetDriverInfoByDriverId(int driverID) {
        Log.highlight("Start to get driver info by driver id!");

        String url = Config.apiBaseUrl
                + DriverEndPoint.DRIVER_INFO + "/" + driverID;

        Response response = UtilSteps.get(Config.driverAuthorization, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        if (UtilSteps.isServerError(statusCode)) {
            Log.errorAndStop("Get driver info error: " + result);
        } else {
            Log.debug("Get driver info success: " + result);
        }

        return result;
    }

    private String acceptOffer(int offerID, int truckNumber, List<Integer> listTruckID, List<Integer> listDriverID) {

        Log.info("Accept offer on driver app");
        String url = Config.apiBaseUrl + DriverEndPoint.OFFER + "/" + offerID + "/accept";
        String body = getAcceptOfferBody(truckNumber, listTruckID, listDriverID);

        Response response = UtilSteps.put(Config.driverAuthorization, body, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        
        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Accept offer error: " + result);
        } else {
            Log.debug("Accept offer success: " + result);
        }

        return result;
    }

    private String getAcceptOfferBody(int truckNumber, List<Integer> listTruckID, List<Integer> listDriverID) {

        JSONArray jaTruckID = new JSONArray();
        for(int  i = 0; i< truckNumber; i++) {
            jaTruckID.put(listTruckID.get(i));
        }

        JSONArray jaDriverID = new JSONArray();
        for(int  i = 0; i< truckNumber; i++) {
            jaDriverID.put(listDriverID.get(i));
        }

        JSONObject jo = new JSONObject();
        jo.put("truck_ids", jaTruckID);
        jo.put("driver_ids", jaDriverID);

        return jo.toString();
    }

    private String getShipmentInfo() {

        String url = Config.apiBaseUrl + DriverEndPoint.SHIPMENT;

        Response response = UtilSteps.get(Config.driverAuthorization, url);

        int statusCode = response.statusCode();
        String result = response.body().asString();

        if (UtilSteps.isStatusCodeFail(statusCode)) {
            Log.errorAndStop("Get shipment info error: " + result);
        } else {
            Log.debug("Get shipment info success: " + result);
        }

        return result;
    }

    //----------------------------------------STEPS--------------------------------------------


    private void setGlobalDriver(int driverID, Response response) {

        setGlobalDriverID(driverID);

        Config.driverAuthorization = "Bearer " + response.header("Authorization");
        Log.debug("Set global driverAuthorization = " + Config.driverAuthorization);

        CommonFlow.addListDriverAuthorization(Config.driverAuthorization);

        Config.listDriver.get(0).setAuthenticationToken(Config.driverAuthorization);
    }

    private void setGlobalDriver(int driverID, Response response, int driverIndex) {

        setGlobalDriverID(driverID);

        Config.driverAuthorization = "Bearer " + response.header("Authorization");
        Log.debug("Set global driverAuthorization = " + Config.driverAuthorization);

        CommonFlow.addListDriverAuthorization(Config.driverAuthorization);

        Config.listDriver.get(driverIndex).setAuthenticationToken(Config.driverAuthorization);
    }

    @Step
    public void shouldBeAbleToSignIn(String phoneNumber, String password) {

        Response response = signIn(phoneNumber, password);

        int driverID = JsonHelper.getInt(response.body().asString(), "driver.data.user.id");

        setGlobalDriver(driverID, response);
    }

    @Step
    public void shouldBeAbleToSignIn(String phoneNumber, String password, int driverIndex) {

        Response response = signIn(phoneNumber, password);

        int driverID = JsonHelper.getInt(response.body().asString(), "driver.data.user.id");

        setGlobalDriver(driverID, response, driverIndex);
    }

    private void setGlobalDriverID(int driverID) {

        Config.newDriverID = driverID;
        Log.debug("Set global new driverID = " + Config.newDriverID);

        Config.driverID = driverID;
        Log.debug("Set global driverID = " + Config.driverID);
    }

    @Step
    public void shouldBeAbleToUpdateIdentityUrl(int driverID, String identityUrl) {
        String result = updateIdentityUrl(driverID, identityUrl);

    }

    @Step
    public void shouldBeAbleToUpdateLicense(int driverID, String licenseID, String licenseDate, String licenseUrl) {
        String result = updateLicense(driverID, licenseID, licenseDate, licenseUrl);

    }

    @Step
    public void verifyDriverName(Integer driverID, String driverName) {
        String result = getDriverInfoByDriverId(driverID);

        Assert.assertEquals(driverID, JsonHelper.getInt(result, "driver.id"));
        Assert.assertEquals(driverName, JsonHelper.getString(result, "driver.name"));

        Log.highlight("Driver name is true! Driver name: " + driverName);
    }

    @Step
    public void shouldBeAbleToHealthCheckApiGetDriverInfo(Integer driverID) {
        String result = healthCheckApiGetDriverInfoByDriverId(driverID);
    }

    @Step
    public void verifyDriverIdentityInfo(Integer driverID, String identityID, String identityDate, String identityUrl) {
        String result = getDriverInfoByDriverId(driverID);

        Assert.assertEquals(driverID, JsonHelper.getInt(result, "driver.id"));
        Assert.assertTrue(JsonHelper.getString(result, "driver.identity_id").contains(identityID));
        Assert.assertEquals(identityDate, getDateFromDateTime(JsonHelper.getString(result, "driver.identity_date")));
        Assert.assertEquals(identityUrl, JsonHelper.getString(result, "driver.identity_id_url"));

        Log.highlight("Driver identity info is true!");
    }

    @Step
    public void verifyDriverLicenseInfo(Integer driverID, String licenseID, String licenseDate, String licenseUrl) {
        String result = getDriverInfoByDriverId(driverID);

        Assert.assertEquals(driverID, JsonHelper.getInt(result, "driver.id"));
        Assert.assertEquals(licenseID, JsonHelper.getString(result, "driver.license_id"));
        // No license date in response?
//        Assert.assertEquals(getDateFromDateTime(licenseDate), getDateFromDateTime(JsonHelper.getString(result, "driver.license_date")));
        Assert.assertEquals(licenseUrl, JsonHelper.getString(result, "driver.license_url"));

        Log.highlight("Driver license info is true!");
    }

    public String getDateFromDateTime(String dateTime) {
        return dateTime.substring(0, 10);
    }

    @Step
    public void verifyDriverBusinessRegistrationInfo(Integer driverID, String businessRegistrationID,
                                                 String businessRegistrationUrl) {
        String result = getDriverInfoByDriverId(driverID);

        Assert.assertEquals(driverID, JsonHelper.getInt(result, "driver.id"));
        Assert.assertEquals(businessRegistrationID, JsonHelper.getString(result, "driver.business_registration"));
        Assert.assertEquals(businessRegistrationUrl, JsonHelper.getString(result, "driver.business_registration_url"));

        Log.highlight("Driver business registration info is true!");
    }

    @Step
    public void verifyDriverTransportationPermitInfo(Integer driverID, String transportationPermit,
                                                     String transportationPermitUrl) {
        String result = getDriverInfoByDriverId(driverID);

        Assert.assertEquals(driverID, JsonHelper.getInt(result, "driver.id"));
        Assert.assertEquals(transportationPermit, JsonHelper.getString(result, "driver.transportation_permit"));
        Assert.assertEquals(transportationPermitUrl, JsonHelper.getString(result, "driver.transportation_permit_url"));

        Log.highlight("Driver transportation permit info is true!");
    }

    @Step
    public void shouldBeAbleToAddPreferences(String preferredRouteType,
                                             int fromAreaID, String fromName, String fromLat, String fromLng,
                                             int toAreaID, String toName, String toLat, String toLng) {
        String result = addPreferences(
                preferredRouteType,
                fromAreaID,
                fromName,
                fromLat,
                fromLng,
                toAreaID,
                toName,
                toLat,
                toLng
        );

    }

    public void assertPreferencesInfo(String result, String preferredRouteType,
                                      Integer fromAreaID, String fromName,
                                      Integer toAreaID, String toName) {

        Assert.assertEquals(preferredRouteType, JsonHelper.getString(result, "driver_preferences.preferred_route_type"));
        Assert.assertEquals(fromAreaID, JsonHelper.getInt(result, "driver_preferences.preferred_pickup_place.area_id"));
        Assert.assertTrue(JsonHelper.getString(result, "driver_preferences.preferred_pickup_place.name").contains(fromName));
        Assert.assertTrue(JsonHelper.getListInt(result, "driver_preferences.preferred_dropoff_places.area_id").contains(toAreaID));
        Assert.assertTrue(JsonHelper.getListString(result, "driver_preferences.preferred_dropoff_places.name").contains(toName));

        Log.highlight("--------------- API TESTING - FINISH TO ADD PREFERENCES ! ---------------");
    }

    @Step
    public void verifyPreferences(String preferredRouteType,
                                  Integer fromAreaID, String fromName,
                                  Integer toAreaID, String toName) {
        String result = getDriverPreferencesInfo();

        assertPreferencesInfo(result,
                preferredRouteType,
                fromAreaID,
                fromName,
                toAreaID,
                toName
        );

        Log.highlight("Preferences info is correct!");
    }

    @Step
    public void shouldBeAbleToHealthCheckApiGetDriverPreferencesInfo() {
        String result = healthCheckApiGetDriverPreferencesInfo();
    }

    private int randomPlateSuffix() {
        Random rd = new Random();
        return rd.nextInt(9000) + 1000;
    }

    @Step
    public void shouldBeAbleToAddTruck(String certificateRegistryUrl, String truckPlate,
                                       int driverTruckModelID, int driverTruckTypeID) {
        String result = addTruck(
                certificateRegistryUrl,
                truckPlate,
                driverTruckModelID,
                driverTruckTypeID
        );

        Config.newCertificateResistryID = JsonHelper.getInt(result, "certificate_registry.id");
        Config.newTruckID = JsonHelper.getInt(result, "certificate_registry.truck.id");
    }

    @Step
    public void getDriverInfoByDriverIdTester(int driverID) {
        String result = getDriverInfoByDriverId(driverID);
    }

    @Step
    public boolean verifyDriverApproved(Integer driverID) {
        String result = getDriverInfoByDriverId(driverID);

        Assert.assertEquals(driverID, JsonHelper.getInt(result, "driver.id"));
        Assert.assertEquals("accepted", JsonHelper.getString(result, "driver.identity_id_status"));
        Assert.assertEquals("accepted", JsonHelper.getString(result, "driver.license_status"));
        Assert.assertEquals("approved", JsonHelper.getString(result, "driver.status"));

        return true;
    }

    @Step
    public void verifyAddTruckSuccess(Integer newCertificateRegistryID, Integer newTruckID,
                                      Integer driverTruckModelID, Integer driverTruckTypeID) {

        String result = getSelfTruckInfo();

        List<Integer> listCertificateRegistryID = JsonHelper.getListInt(result, "certificate_registries.id");
        List<Integer> listTruckID = JsonHelper.getListInt(result, "certificate_registries.truck.id");
        List<Integer> listTruckModelID = JsonHelper.getListInt(result, "certificate_registries.truck.truck_model_id");
        List<Integer> listTruckTypeID = JsonHelper.getListInt(result, "certificate_registries.truck.truck_type_id");

        int i = 0;
        for (int certificateRegistryID : listCertificateRegistryID) {

            if (certificateRegistryID == newCertificateRegistryID) {

                Assert.assertEquals(newTruckID, listTruckID.get(i));
                Assert.assertEquals(driverTruckModelID, listTruckModelID.get(i));
                Assert.assertEquals(driverTruckTypeID, listTruckTypeID.get(i));
                Log.highlight("Verify add truck success!");

                break;
            }

            i++;
        }
    }

    @Step
    public void shouldBeAbleToAcceptOfferOnHydra(int offerID, int truckNumber, List<Integer> listTruckID, List<Integer> listDriverID) {
        String result = acceptOffer(offerID, truckNumber, listTruckID, listDriverID);

        Config.newShipmentID = JsonHelper.getInt(result, "offer.order.shipment.id");
        Log.debug("New shipment id: " + Config.newShipmentID);
    }

    @Step
    public void shouldBeAbleToGetShipmentByOrderID(int orderID) {

        String result = getShipmentInfo();

        setListNewShipmentIDByOrder(result, orderID);
    }

    private void setListNewShipmentIDByOrder(String result, int orderID) {

        List<Integer> listCreatedShipmentID = new ArrayList<>();
        List<String> listShipmentStatus = JsonHelper.getListString(result, "shipments.status");
        List<Integer> listShipmentID = JsonHelper.getListInt(result, "shipments.id");
        List<Integer> listOrderID = JsonHelper.getListInt(result, "shipments.order.id");

        int index = 0;
        for (String shipmentStatus : listShipmentStatus) {

            if (shipmentStatus.equals("created") && listOrderID.get(index).equals(orderID)) {
                listCreatedShipmentID.add(listShipmentID.get(index));
            }

            index++;
        }

        Config.listNewShipmentID = listCreatedShipmentID;
    }

}
