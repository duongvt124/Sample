package lgv.automation.util.api;

import lgv.automation.model.api.Driver;
import lgv.automation.steps.api.BrokerMobileAppSteps;
import lgv.automation.steps.api.DriverSteps;
import lgv.automation.steps.api.OpsToolSteps;
import lgv.automation.util.CSVHelper;
import lgv.automation.util.CommonMethod;
import lgv.automation.util.Log;
import lgv.automation.util.Timer;

import java.util.ArrayList;
import java.util.List;

public class CommonFlow {

    static OpsToolSteps opsToolSteps = new OpsToolSteps();
    static DriverSteps driverSteps = new DriverSteps();
    static BrokerMobileAppSteps brokerMobileAppSteps = new BrokerMobileAppSteps();

    public static int getDriverIDByType(String driverType) {

        Log.debug("Driver type: " + driverType);
        if (driverType.equalsIgnoreCase("new")) {
            return Config.newDriverID;
        } else if (driverType.equalsIgnoreCase("existing")) {
            return Config.driverID;
        } else {
            return 0;
        }
    }

    public static int getBrokerIDByType(String brokerType) {

        if (brokerType.equalsIgnoreCase("new")) {
            return Config.newBrokerID;
        } else if (brokerType.equalsIgnoreCase("existing")) {
            return Config.brokerID;
        } else {
            return 0;
        }
    }

    public static int getOrderIDByType(String orderType) {

        if (orderType.equalsIgnoreCase("new")) {
            return Config.newOrderID;
        } else if (orderType.equalsIgnoreCase("existing")) {
            return Config.orderID;
        } else {
            return 0;
        }
    }

    public static int getBookingIDByType(String bookingType) {

        if (bookingType.equalsIgnoreCase("new")) {
            return Config.newBookingID;
        } else if (bookingType.equalsIgnoreCase("existing")) {
            return Config.bookingID;
        } else {
            return 0;
        }
    }

    public static List<Integer> getListTruckRequestIDByType(String truckRequestType) {

        if (truckRequestType.equalsIgnoreCase("new")) {
            return Config.listNewTruckRequestID;
        } else if (truckRequestType.equalsIgnoreCase("existing")) {
            return Config.listTruckRequestID;
        } else {
            return null;
        }
    }

    public static List<Integer> getListUnassignedTruckRequestIDByType(String truckRequestType) {

        if (truckRequestType.equalsIgnoreCase("new")) {
            return Config.listUnassignedTruckRequestID;
        } else if (truckRequestType.equalsIgnoreCase("existing")) {
            return Config.listUnassignedTruckRequestID;
        } else {
            return null;
        }
    }

    public static int getBiddingIDByType(String biddingType) {

        if (biddingType.equalsIgnoreCase("new")) {
            return Config.newBiddingID;
        } else if (biddingType.equalsIgnoreCase("existing")) {
            return Config.biddingID;
        } else {
            return 0;
        }
    }

    public static int getOfferIDByType(String offerType) {

        if (offerType.equalsIgnoreCase("new")) {
            return Config.newOfferID;
        } else if (offerType.equalsIgnoreCase("existing")) {
            return Config.offerID;
        } else {
            return 0;
        }
    }

    public static int getShipmentIDByType(String shipmentType) {

        if (shipmentType.equalsIgnoreCase("new")) {
            return Config.newShipmentID;
        } else if (shipmentType.equalsIgnoreCase("existing")) {
            return Config.shipmentID;
        } else {
            return 0;
        }
    }

    public static Driver getDriverByIndex(int driverIndex) {
        return Config.listDriver.get(driverIndex - 1);
    }

    public static String getDriverPhoneNumberByIndex(int driverIndex) {
        return Config.listDriverPhoneNumber.get(driverIndex - 1);
    }

    public static void setDriverAuthorizationByIndex(int driverIndex) {

        Config.driverAuthorization = Config.listDriverAuthorization.get(driverIndex - 1);

        Log.debug("Using driver authorization of driver " + driverIndex);
    }

    public static void addListNewDriver(String phoneNumber, int driverID) {

        Config.listNewDriverPhoneNumber.add(phoneNumber);

        Config.listNewDriverID.add(driverID);

        Driver driver = new Driver();
        driver.setID(driverID);
        driver.setPhoneNumber(phoneNumber);
        Config.listNewDriver.add(driver);
    }

    public static void addListDriverAuthorization(String authorization) {

        Config.listDriverAuthorization.add(authorization);
    }

    public static void addListNewOrder(int orderID) {
        Config.listNewOrderID.add(orderID);
    }

    public static void addListNewBooking(int bookingID) {
        Config.listNewBookingID.add(bookingID);
    }

    public static void setExistingDriver() {

        int i = 0;
        for ( int driverID : Config.listDriverID) {

            Driver driver = new Driver();
            driver.setID(driverID);
            driver.setPhoneNumber(Config.listDriverPhoneNumber.get(i));

            Config.listDriver.add(driver);
            i++;
        }

//        Log.highlight("List existing driver");
//        int index = 0;
//        for ( Driver driver : Config.listDriver) {
//
//            Log.info("Driver " + index + " id :" + driver.getID());
//            Log.info("Driver " + index + " phone number :" + driver.getPhoneNumber());
//            Log.info("Driver " + index + " password :" + driver.getPassword());
//            Log.info("Driver " + index + " authentication token :" + driver.getAuthenticationToken());
//            index++;
//        }

        Log.highlight("Set existing drivers success");
    }

    public static void createCSVFileRoutesAreaID(String filePath) {

        List<String[]> listRoutes = new ArrayList<>();

        for (Integer fromAreaID = 1; fromAreaID <= 74; fromAreaID++) {
            for (Integer toAreaID = 1; toAreaID <= 74; toAreaID++) {

                if (fromAreaID != toAreaID)
                    listRoutes.add( new String[] { fromAreaID.toString(), toAreaID.toString() });
            }
        }

        for (String[] route : listRoutes) {
            Log.debug("Route: " + route[0] + "\t" + route[1]);
        }

        List<String[]> data = CommonMethod.setListArrayForCSVFile("from_area_id", "to_area_id", listRoutes);

        CSVHelper.writeDataAtOnce(filePath, data);
    }

    public static int calculateWeightByTruckQuantity(int truckQuantity, int maxWeightOfTruck) {

        int overWeight = 10;

        return (maxWeightOfTruck * (truckQuantity - 1)) + overWeight;
    }

    public static void createOrderAcceptedOffer(String brokerEmail) {

        int truckQuantity = 1;

        driverSteps.shouldBeAbleToSignIn(Config.phoneNumber, Config.password);

        brokerMobileAppSteps.shouldBeAbleToSignIn(brokerEmail, Config.password);

        brokerMobileAppSteps.shouldBeAbleToBeSuggestedTrucksByTruckQuantity(
                truckQuantity,
                Config.orderCreationCargoLength,
                Config.orderCreationCargoWidth,
                Config.orderCreationCargoHeight
        );

        brokerMobileAppSteps.shouldBeAbleToCreateNewHybridOrderWithTruckQuantity(truckQuantity);

        opsToolSteps.shouldBeAbleToViewServiceQuotationListing(Config.newOrderID);

        opsToolSteps.shouldBeAbleToAcceptServiceQuotation(
                Config.listLogivanServiceQuotationID.get(0),
                Config.serviceQuotationUnitPrice,
                Config.serviceQuotationSubTotalPrice,
                Config.serviceQuotationVatPrice,
                Config.serviceQuotationTotalPrice,
                Config.promotionID
        );

        opsToolSteps.shouldBeAbleToConfirmPayment(Config.newOrderID, Config.orderCreationSalesPrice);

        opsToolSteps.shouldBeAbleToBiddingOnHydra(Config.newOrderID, Config.driverID, truckQuantity);

        opsToolSteps.shouldBeAbleToAcceptBidding(Config.newOrderID, Config.newBiddingID);

        opsToolSteps.shouldBeAbleToGetTruckRequestInfo(Config.newOrderID);

        opsToolSteps.shouldBeAbleToViewAssignedTruckInfo(Config.driverID, truckQuantity);

        Timer.waitFor(8, 1);

        List<Integer> listTruckRequestID = CommonFlow.getListTruckRequestIDByType("new");

        for (int truckRequestID : listTruckRequestID) {
            opsToolSteps.shouldBeAbleToSendOfferToSingleDriver(truckRequestID);
        }

//        opsToolSteps.shouldBeAbleToSendOfferToSingleDriver(39749);

        opsToolSteps.shouldBeAbleToGetTruckRequestDriverOfferID(Config.newOrderID);

        for (int offerID: Config.listAssignedTruckRequestDriverOfferID) {
            driverSteps.shouldBeAbleToAcceptOfferOnHydra(offerID, truckQuantity, Config.listAssignedTruckID, Config.listAssignedTruckRequestDriverID);
        }

        driverSteps.shouldBeAbleToGetShipmentByOrderID(Config.newOrderID);
    }

    public static void createCombinationOrder(String brokerEmail) {

        int truckQuantity = 1;

        driverSteps.shouldBeAbleToSignIn(Config.phoneNumber, Config.password);

        brokerMobileAppSteps.shouldBeAbleToSignIn(brokerEmail, Config.password);

        brokerMobileAppSteps.shouldBeAbleToBeSuggestedTrucksByTruckQuantity(
                truckQuantity,
                Config.orderCreationCargoLength,
                Config.orderCreationCargoWidth,
                Config.orderCreationCargoHeight
        );

        brokerMobileAppSteps.shouldBeAbleToCreateNewHybridOrderWithTruckQuantity(truckQuantity);

        opsToolSteps.shouldBeAbleToViewServiceQuotationListing(Config.newOrderID);

        opsToolSteps.shouldBeAbleToAcceptServiceQuotation(
                Config.listLogivanServiceQuotationID.get(0),
                Config.serviceQuotationUnitPrice,
                Config.serviceQuotationSubTotalPrice,
                Config.serviceQuotationVatPrice,
                Config.serviceQuotationTotalPrice,
                Config.promotionID
        );

        opsToolSteps.shouldBeAbleToConfirmPayment(Config.newOrderID, Config.orderCreationSalesPrice);
    }
}
