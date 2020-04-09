package lgv.automation.util.api;

import lgv.automation.model.api.Driver;
import lgv.automation.util.Log;
import lgv.automation.util.PropertyValues;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

    public static final boolean FLAG_DELETE_ORDER = true;
    public static final boolean FLAG_DELETE_BOOKING = true;
    public static final int RETRY_TEST_NUMBER = 10;
    public static final int RETRY_HEALTH_CHECK_NUMBER = 5;
    public static final int RETRY_TEST_TIME = 1;

    public static String apiBaseUrl = "";

    // environment
    public static String envMock = "mock"; // Postman mock server
    public static String envPR = "pr";
    public static String envTestBed = "testbed";
    public static String envProduction = "production";

    // Header
    public static String driverAuthorization = "";
    public static List<String> listDriverAuthorization = new ArrayList<>();
    public static String brokerAuthorization = "";
    public static String opsToolAuthorization = "";

    public static String deviceID = "android";
    public static String platform = "android";


    // Status code
    public static final int STATUS_CODE_OK = 200;
    public static final int STATUS_CODE_CREATED = 201;
    public static final int STATUS_CODE_NO_CONTENT = 204;
    public static final int STATUS_CODE_BAD_REQUEST = 400;
    public static final int STATUS_CODE_UNAUTHORIZED = 401;
    public static final int STATUS_CODE_NOT_FOUND = 404;
    public static final int STATUS_CODE_INTERNAL_SERVER_ERROR = 500;
    public static final int STATUS_CODE_SERVICE_UNAVAILABLE = 503;

    // Truck
    public static final int MAX_WEIGHT_OF_TRUCK_MODEL_42 = 22; //tons
    public static final int MAX_WEIGHT_OF_TRUCK_MODEL_44 = 30; //tons

    /** BROKER **/
    public static int brokerID = 12932;
    public static int enterpriseBrokerID = 13001;
    public static int newBrokerID = 0;

    // Sign In
    public static String brokerEmailTestbed = "";
    public static String brokerEmail = "";
    public static String emailPrefix = "";
    public static String emailSuffix = "";
    public static String newBrokerPhoneNumber = "";
    public static String brokerPasswordTestbed = "111111";
    public static String brokerName = "api ultron broker";

    public static String enterpriseBrokerEmailTestbed = "";

    // Price look up
    public static String fromAreaID = "3";
    public static String toAreaId = "5";
    public static String fromDistrictID = "135";
    public static String toDistrictID = "485";
    public static String brokerTruckModelID = "42";
    public static String brokerTruckTypeID = "5";
    public static String fromLat = "16.0679814";
    public static String fromLong = "108.2119396";
    public static String toLat = "17.6102715";
    public static String toLong = "106.3487474";
    public static int priceLookupCargoWeight = 4;
    public static int priceLookupCargoLength = 3;
    public static int priceLookupCargoWidth = 1;
    public static int priceLookupCargoHeight = 1;
    public static boolean isWholeTruck = true;

    public static int newPriceLookup = 0;

    // Booking
    public static int bookingID = 12;
    public static int newBookingID = 0;
    public static List<Integer> listNewBookingID = new ArrayList<>();
    public static final String BOOKING_CREATION_SALE_PRICE_TYPE_WHOLE_TRUCK = "whole_truck";
    public static final String BOOKING_CREATION_SALE_PRICE_TYPE_SHARE_TRUCK = "share_truck";
    public static final String BOOKING_CREATION_SALE_PRICE_TYPE_PREDICTIVE = "predictive";

    public static int bookingSMEBrokerID = 12;
    public static String bookingSMEBrokerPhone = "";
    public static int bookingEnterpriseBrokerID = 13;
    public static String bookingEnterpriseBrokerEmail = "";

    public static boolean bookingCreationUseTruckContainer = true;
    public static int bookingCreationManagerID = 2;

    public static int updateBookingCargoWeight = 9;

    public static int newQuotationID = 0;
    public static int quotationAmount = 2000000;
    public static String quotationSource = "ops"; // source list: [logivan, ops, other]

    public static int quotationResponseAmount = 1000000;

    // MARKETPLACE ORDER
    public static String orderCreationMarketPlace = "marketplace";
    public static String orderCreationCorporate = "corporate";
    public static String orderCreationHybrid = "original_hybrid";
    public static String orderCreationEnterprise = "enterprise";
    public static String orderCreationImageUrl = "";
    public static boolean orderCreationInsuranceRequest = true;
    public static boolean orderCreationInHideContact = true;
    public static boolean orderCreationTaxInvoiceRequest = true;
    public static boolean orderCreationWholeTruck = true;
    public static boolean orderCreationShareTruck = true;
    public static Integer orderCreationTruckQuantity = 2;
    public static String orderCreationTruckTypeID = "5";
    public static String orderCreationTruckModelID = "44";
    public static String orderCreationPickUpDateTime = "2019-12-31T03:30:53.000Z";
    public static String orderCreationDropOffDateTime = "2019-12-31T03:30:53.000Z";
    public static int orderCreationCargoWeight = 14;
    public static String orderCreationCargoType = "Vật liệu xây dựng";
    public static int orderCreationCargoLength = 3;
    public static int orderCreationCargoWidth = 1;
    public static int orderCreationCargoHeight = 1;
    public static int orderCreationOfferPrice = 800000;
    public static int orderCreationSalesPrice = 18000000;
    public static int orderCreationCustomerID = 12667;
    public static String orderCreationNotes = "hàng của mị mắc lắm";
    public static String orderCreationInternalNotes = "Chúng tôi không có gì ngoài điều kiện";
    public static String orderCreationPickUpCity = "Hà Nội";
    public static String orderCreationPickUpPlaceId = "ChIJP8aQu-9gNDERj76ZokcaJXE";
    public static String orderCreationPickUpAddress = "Ba Vi Resort, Vườn Quốc Gia Ba Vì, Ba Vì, Hà Nội, Việt Nam";
    public static String orderCreationPickUpDescription = "Ba Vi Resort, Vườn Quốc Gia Ba Vì, Ba Vì, Hà Nội, Việt Nam";
    public static String orderCreationPickUpValue = "ChIJP8aQu-9gNDERj76ZokcaJXE";
    public static int orderCreationPickUpAreaID = 3;
    public static String orderCreationPickUpDistrictID = "231";
    public static String orderCreationPickUpLat = "21.0849806";
    public static String orderCreationPickUpLong = "105.37356380000006";
    public static String orderCreationDropOffCity = "Hồ Chí Minh";
    public static String orderCreationDropOffPlaceId = "ChIJT7f8qwIzdTER7iIC0KXQCZg";
    public static String orderCreationDropOffAddress = "Binh Chanh Market, Quốc lộ 1A, x. Bình Chánh, Bình Chánh, Hồ Chí Minh, Việt Nam";
    public static String orderCreationDropOffDescription = "Binh Chanh Market, Quốc lộ 1A, x. Bình Chánh, Bình Chánh, Hồ Chí Minh, Việt Nam";
    public static String orderCreationDropOffValue = "ChIJT7f8qwIzdTER7iIC0KXQCZg";
    public static int orderCreationDropOffAreaID = 5;
    public static String orderCreationDropOffDistrictID = "643";
    public static String orderCreationDropOffLat = "10.6658461";
    public static String orderCreationDropOffLong = "106.57159590000003";

    // HYBRID ORDER
    public static String hybridOrderCreationPickUpCity = "Hà Nội";
    public static String hybridOrderCreationPickUpPlaceId = "ChIJV1npfJKsNTERua2DiV81MOI";
    public static String hybridOrderCreationPickUpAddress = "Thanh Xuân, Hà Nội, Việt Nam";
    public static String hybridOrderCreationPickUpDescription = "Thanh Xuân, Hà Nội, Việt Nam";
    public static String hybridOrderCreationPickUpValue = "ChIJV1npfJKsNTERua2DiV81MOI";
    public static int hybridOrderCreationPickUpAreaID = 3;
    public static int hybridOrderCreationPickUpDistrictID = 257;
    public static String hybridOrderCreationPickUpLat = "20.9959819";
    public static String hybridOrderCreationPickUpLong = "105.8097244";
    public static String hybridOrderCreationDropOffCity = "Đà Nẵng";
    public static String hybridOrderCreationDropOffPlaceId = "ChIJcVII650ZQjERIk4DlGfTxYo";
    public static String hybridOrderCreationDropOffAddress = "Cẩm Lệ, Đà Nẵng, Việt Nam";
    public static String hybridOrderCreationDropOffDescription = "Cẩm Lệ, Đà Nẵng, Việt Nam";
    public static String hybridOrderCreationDropOffValue = "ChIJcVII650ZQjERIk4DlGfTxYo";
    public static int hybridOrderCreationDropOffAreaID = 2;
    public static int hybridOrderCreationDropOffDistrictID = 134;
    public static String hybridOrderCreationDropOffLat = "16.0153669";
    public static String hybridOrderCreationDropOffLong = "108.1962362";

    // ENTERPRISE ORDER
    public static String enterpriseOrderCreationPickUpCity1 = "Hà Nội";
    public static String enterpriseOrderCreationPickUpPlaceId1 = "ChIJV1npfJKsNTERua2DiV81MOI";
    public static String enterpriseOrderCreationPickUpAddress1 = "Thanh Xuân, Hà Nội, Việt Nam";
    public static String enterpriseOrderCreationPickUpDescription1 = "Thanh Xuân, Hà Nội, Việt Nam";
    public static String enterpriseOrderCreationPickUpValue1 = "ChIJV1npfJKsNTERua2DiV81MOI";
    public static int enterpriseOrderCreationPickUpAreaID1 = 3;
    public static int enterpriseOrderCreationPickUpDistrictID1 = 257;
    public static String enterpriseOrderCreationPickUpDistrictName1 = "Thanh Xuân";
    public static String enterpriseOrderCreationPickUpLat1 = "20.9959819";
    public static String enterpriseOrderCreationPickUpLong1 = "105.80972440000005";

    public static String enterpriseOrderCreationPickUpCity2 = "Đà Nẵng";
    public static String enterpriseOrderCreationPickUpPlaceId2 = "ChIJcVII650ZQjERIk4DlGfTxYo";
    public static String enterpriseOrderCreationPickUpAddress2 = "Cẩm Lệ, Đà Nẵng, Việt Nam";
    public static String enterpriseOrderCreationPickUpDescription2 = "Cẩm Lệ, Đà Nẵng, Việt Nam";
    public static String enterpriseOrderCreationPickUpValue2 = "ChIJcVII650ZQjERIk4DlGfTxYo";
    public static int enterpriseOrderCreationPickUpAreaID2 = 2;
    public static int enterpriseOrderCreationPickUpDistrictID2 = 134;
    public static String enterpriseOrderCreationPickUpDistrictName2 = "Cẩm Lệ";
    public static String enterpriseOrderCreationPickUpLat2 = "16.0153669";
    public static String enterpriseOrderCreationPickUpLong2 = "108.19623619999993";

    public static String enterpriseOrderCreationDropOffCity1 = "Khánh Hòa";
    public static String enterpriseOrderCreationDropOffPlaceId1 = "ChIJn6DURYJncDERMx8H-KRgn-I";
    public static String enterpriseOrderCreationDropOffAddress1 = "Nha Trang Center, Trần Phú, Lộc Thọ, Thành phố Nha Trang, Khánh Hòa, Việt Nam";
    public static String enterpriseOrderCreationDropOffDescription1 = "Nha Trang Center, Trần Phú, Lộc Thọ, Thành phố Nha Trang, Khánh Hòa, Việt Nam";
    public static String enterpriseOrderCreationDropOffValue1 = "ChIJn6DURYJncDERMx8H-KRgn-I";
    public static int enterpriseOrderCreationDropOffAreaID1 = 32;
    public static int enterpriseOrderCreationDropOffDistrictID1 = 332;
    public static String enterpriseOrderCreationDropOffDistrictName1 = "Nha Trang";
    public static String enterpriseOrderCreationDropOffLat1 = "12.2480883";
    public static String enterpriseOrderCreationDropOffLong1 = "109.19599500000004";

    public static String enterpriseOrderCreationDropOffCity2 = "Hồ Chí Minh";
    public static String enterpriseOrderCreationDropOffPlaceId2 = "ChIJK2OrL4bVdDERn_1n1vGrjkE";
    public static String enterpriseOrderCreationDropOffAddress2 = "Hóc Môn, Hồ Chí Minh, Việt Nam";
    public static String enterpriseOrderCreationDropOffDescription2 = "Hóc Môn, Hồ Chí Minh, Việt Nam";
    public static String enterpriseOrderCreationDropOffValue2 = "ChIJK2OrL4bVdDERn_1n1vGrjkE";
    public static int enterpriseOrderCreationDropOffAreaID2 = 5;
    public static int enterpriseOrderCreationDropOffDistrictID2 = 649;
    public static String enterpriseOrderCreationDropOffDistrictName2 = "Hóc Môn";
    public static String enterpriseOrderCreationDropOffLat2 = "10.8863934";
    public static String enterpriseOrderCreationDropOffLong2 = "106.59229240000002";

    public static boolean enterpriseDeliveryOrder = true;
    
    public static int rangeToGetDriversNearPickupLocation = 40;

    public static int newOrderID = 0;
    public static List<Integer> listNewOrderID = new ArrayList<>();
    public static int orderID = 17857;

    public static int managerID = 105; // name = "Nhut"
    public static int saleID = 105;

    public static List<Integer> listTruckRequestID = new ArrayList<>();
    public static List<Integer> listNewTruckRequestID = new ArrayList<>();
    public static List<Integer> listUnassignedTruckRequestID = new ArrayList<>();
    public static List<Integer> listAssignedTruckRequestID = new ArrayList<>();
    public static List<Integer> listAssignedTruckRequestIDByDriverID = new ArrayList<>();
    public static List<Integer> listAssignedTruckRequestDriverID = new ArrayList<>();
    public static List<Integer> listAssignedTruckRequestDriverOfferID = new ArrayList<>();
    public static int truckRequestID = 96;

    public static JSONArray jaSuggestionTrucks;
    public static int promotionID = 1;

    public static int priceSelectedServiceQuotation = 100000000;
    public static int priceDiscount = 70000000;

    // Convert order to marketplace
    public static int expectedPrice = 10000000;

    // Service quotation
    public static List<Integer> listLogivanServiceQuotationID = new ArrayList<>();
    public static int newServiceQuotationID;
    public static int serviceQuotationUnitPrice = 10000000;
    public static int serviceQuotationSubTotalPrice = 10000000;
    public static int serviceQuotationDiscountPrice = 7500000;
    public static int serviceQuotationVatPrice = 500000;
    public static int serviceQuotationTotalPrice = 8000000;

    // Bidding
    public static int newBiddingID = 0;
    public static int newLogivanBiddingID = 0;
    public static int biddingID = 44082;

    // Offer
    public static int newOfferID = 0;
    public static List<Integer> listNewOfferID = new ArrayList<>();
    public static int offerID = 1340;


    // Shipment
    public static int newShipmentID = 0;
    public static List<Integer> listNewShipmentID = new ArrayList<>();
    public static int shipmentID = 474;

    public static int newPickUpDocumentID = 0;
    public static final String PICK_UP_DOCUMENT_TYPE = "delivery_note";
    public static int newDropOffDocumentID = 0;

    // Review
    public static int reviewStar = 4;
    public static int reviewID = 86;


    /** DRIVER **/
    // Driver info
    public static List<Driver> listNewDriver = new ArrayList<>();
    public static List<Driver> listDriver = new ArrayList<>();
    public static int driverID = 24694;
    public static List<Integer> listDriverID = Arrays.asList(24694, 24693, 24695);

    public static String phoneNumber = "+84983117994";
    public static List<String> listDriverPhoneNumber = Arrays.asList("+84983117994", "+84983117228", "+84983112233");

    public static int newDriverID = 0;
    public static List<Integer> listNewDriverID = new ArrayList<>();
    public static List<String> listNewDriverPhoneNumber = new ArrayList<>();
    public static String driverName = "Mai Thu Hai";

    public static String identityID = "024";
    public static String identityDate = "2021-05-27";
    public static String identityIdUrl = "https://firebasestorage.googleapis.com/v0/b/logivan-driver-development.appspot.com/o/identity_photos%2F1557992548857?alt=media&token=b4724a05-04e8-4246-b238-a71f340f8d6e";

    public static String licenseID = "222";
    public static String licenseDate = "2022-05-27";
    public static String licenseUrl = "https://firebasestorage.googleapis.com/v0/b/logivan-driver-development.appspot.com/o/identity_photos%2F1557992551934?alt=media&token=42a4d862-fe55-4237-8fc8-cb0c5a9142af";

    public static String businessRegistrationID = "33333333";
    public static String businessRegistrationDate = "2023-05-27T07:46:02.069Z";
    public static String businessRegistrationUrl = "https://firebasestorage.googleapis.com/v0/b/logivan-driver-development.appspot.com/o/identity_photos%2F1557992548857?alt=media&token=b4724a05-04e8-4246-b238-a71f340f8d6e";

    public static String transportationPermitID = "44444444";
    public static String transportationPermitDate = "2024-05-27T07:46:02.069Z";
    public static String transportationPermitUrl = "https://firebasestorage.googleapis.com/v0/b/logivan-driver-development.appspot.com/o/identity_photos%2F1557992548857?alt=media&token=b4724a05-04e8-4246-b238-a71f340f8d6e";

    // Driver preferences
    public static String preferredRouteType = "return_trip";

    public static Integer areaIDHCM = 5;
    public static String areaNameHCM = "Hồ Chí Minh";
    public static String latHCM = "10.8230989";
    public static String lngHCM = "106.6296638";

    public static Integer areaIDHN = 3;
    public static String areaNameHN = "Hà Nội";
    public static String latHN = "21.0277644";
    public static String lngHN = "105.8341598";

    public static Integer areaIDHP = 4;
    public static String areaNameHP = "Hải Phòng";
    public static String latHP = "20.8449115";
    public static String lngHP = "106.6880841";

    // Filter
    public static String filterPickUpAreaID = "5";

    // Update truck info
    public static String certificateRegistryUrl = "https://jpeg.org/images/jpegxs-home.jpg";
    public static int newCertificateResistryID = 0;
    public static int newTruckID = 0;
    public static int truckID = 17844;
    public static String truckPlatePrefix = "50BD-";
    public static int driverTruckModelID = 42;
    public static int driverTruckTypeID = 5;
    public static double driverTruckLength = 2.0;
    public static double driverTruckHeight = 1.1;
    public static double driverTruckWeight = 1.0;
    public static double driverTruckCapacity = 1.25;

    public static List<Integer> listCertificateRegistryID = new ArrayList<>();

    public static List<Integer> listAssignedTruckID;

    // Bidding
    public static String biddingPrice = "1500000";
    public static String biddingSalesPrice = "1500000";
    public static String biddingPreSalesPrice = "1500000";
    public static String biddingPromotionID = null;
    public static String bidderTypeDriver = "driver";
    public static String bidderTypeLogivan = "logivan";
    public static int bidderDriverID = 24400;


    /** Ops tool **/

    // Driver info
    public static String updateName = "Mai thu bay";
    public static String phoneNumberPrefix = "+84983";
    public static String newDriverPhoneNumber = "";
    public static String password = "111111";

    // Documents
    public static int newIdentityIDDocumentID = 0;
    public static int identityIDDocumentID = 32286;
    public static int newLicenseDocumentID = 0;
    public static int newCertificateRegistryDocumentID = 0;

    // Confirm Payment
    public static String confirmPaymentNote = "note";

    public static void init() {
        PropertyValues propertyValues = new PropertyValues();
        String tempApiUrl = propertyValues.getProperty("api.url");
        String tempDeviceID = propertyValues.getProperty("device.id");
        String tempPlatform = propertyValues.getProperty("platform");
        String tempDriverAuthorization = propertyValues.getProperty("driver.authorization");
        String tempDriverID = propertyValues.getProperty("driver.id");
        String tempBrokerAuthorization = propertyValues.getProperty("broker.authorization");
        String tempBrokerEmail = propertyValues.getProperty("broker.email");
        String tempOpsToolAuthorization = propertyValues.getProperty("ops.tool.authorization");

        if (!tempApiUrl.isEmpty() && !tempApiUrl.contains("${api.url}")) {
            apiBaseUrl = tempApiUrl;
        }

        if (!tempDeviceID.isEmpty() && !tempDeviceID.contains("${device.id}")) {
            deviceID = tempDeviceID;
            platform = tempPlatform;
        }

        if (!tempDriverAuthorization.isEmpty() && !tempDriverAuthorization.contains("${driver.authorization}")) {
            driverAuthorization = tempDriverAuthorization;
        }

        if (!tempDriverID.isEmpty() && !tempDriverID.contains("${driver.id}")) {
            driverID = Integer.parseInt(tempDriverID);
        }

        if (!tempBrokerAuthorization.isEmpty() && !tempBrokerAuthorization.contains("${broker.authorization}")) {
            brokerAuthorization = tempBrokerAuthorization;
        }

        if (!tempBrokerEmail.isEmpty() && !tempBrokerEmail.contains("${broker.email}")) {
            brokerEmail = tempBrokerEmail;
        }

        if (!tempOpsToolAuthorization.isEmpty() && !tempOpsToolAuthorization.contains("${ops.tool.authorization}")) {
            opsToolAuthorization = tempOpsToolAuthorization;
        }

        propertyValues.closeInit();
        Log.highlight("API CONFIG");

        Log.info("API base url: " + apiBaseUrl);
        Log.info("Device id: " + deviceID);
        Log.info("Platform: " + platform);
        Log.info("Driver authorization: " + driverAuthorization);
        Log.info("Driver id: " + driverID);
        Log.info("Broker authorization: " + brokerAuthorization);
        Log.info("Broker email: " + brokerEmail);
        Log.info("Ops tool authorization: " + opsToolAuthorization);

        CommonFlow.setExistingDriver();
        Log.printLine();
    }
}
