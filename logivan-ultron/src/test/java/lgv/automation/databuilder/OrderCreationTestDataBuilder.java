package lgv.automation.databuilder;

import lgv.automation.model.Order;

public class OrderCreationTestDataBuilder {


    private static final String PICKUP_POINT = "Ha Noi";
    private static final String DROPOFF_POINT = "Ho Chi Minh";
    private static final String PICKUP_DAY = "30";
    private static final String TYPE = "Ban ghe";
    private static final String LENGTH = "1";
    private static final String WIDTH = "1";
    private static final String HEIGHT = "1";
    private static final String CAPACITY = "8";
    private static final String FORM_OF_TRANSPORTATION = "nguyen xe";
    private static final String TYPE_OF_TRUCK = "xethung";
    private static final boolean INSURANCE = true;
    private static final boolean RED_BILL = true;
    private static final boolean HIDE_CONTACT = false;
    private static final String NOTE = "hang de vo";
    private static final String PRICE = "";


    public static Order prepareOrderInfo() throws Exception{
        Order order = new Order();
        order.setPickupPoint(PICKUP_POINT);
        order.setDropoffPoint(DROPOFF_POINT);
        order.setPickupDay(PICKUP_DAY);
        order.setType(TYPE);
        order.setLength(LENGTH);
        order.setWidth(WIDTH);
        order.setHeight(HEIGHT);
        order.setCapacity(CAPACITY);
        order.setFormOfTransportation(FORM_OF_TRANSPORTATION);
        order.setTypeOfTruck(TYPE_OF_TRUCK);
        order.setInsurance(INSURANCE);
        order.setRedBill(RED_BILL);
        order.setHideContact(HIDE_CONTACT);
        order.setNote(NOTE);
        order.setPrice(PRICE);

        return order;
    }

}
