package lgv.automation.databuilder;

import lgv.automation.model.Driver;
import lgv.automation.model.Truck;

public class DriverOnboardingTestDataBuilder {

    // Driver
    private static final String NAME = "Tester 1";
    private static final String HOMEBASE = "Ho Chi Minh";
    private static final String DESTINATION_1 = "Bình Dương";
    private static final String DESTINATION_2 = "An Giang";
    private static final int PRIORITIZE = 1;

    // Truck
    private static final String TYPE_1 = "Xe thùng mui bạt";
    private static final String TYPE_2 = "Xe tải thùng kín";
    private static final String CAPACITY_1 = "5";
    private static final String CAPACITY_2 = "8";
    private static final String LENGTH_1 = "4";
    private static final String LENGTH_2 = "5";
    private static final String WIDTH_1 = "6";
    private static final String WIDTH_2 = "6";
    private static final String HEIGHT_1 = "7";
    private static final String HEIGHT_2 = "8";
    private static final String REGISTRATION_PLATE_1 = "54G-11111";
    private static final String REGISTRATION_PLATE_2 = "54G-22222";

    public static Driver createDriver(){
        Driver driver = new Driver();
        driver.setName(NAME);
        driver.setHomeBase(HOMEBASE);
        driver.setDestination_1(DESTINATION_1);
        driver.setDestination_2(DESTINATION_2);
        driver.setPrioritize(PRIORITIZE);

        return driver;
    }

    public static Truck createTruck_1(){
        Truck truck = new Truck();
        truck.setType(TYPE_1);
        truck.setCapacity(CAPACITY_1);
        truck.setLength(LENGTH_1);
        truck.setWidth(WIDTH_1);
        truck.setHeight(HEIGHT_1);
        truck.setRegistrationPlate(REGISTRATION_PLATE_1);

        return truck;
    }

    public static Truck createTruck_2(){
        Truck truck = new Truck();
        truck.setType(TYPE_2);
        truck.setCapacity(CAPACITY_2);
        truck.setLength(LENGTH_2);
        truck.setWidth(WIDTH_2);
        truck.setHeight(HEIGHT_2);
        truck.setRegistrationPlate(REGISTRATION_PLATE_2);

        return truck;
    }
}
