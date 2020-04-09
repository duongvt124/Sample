package lgv.automation.model;

public class Order {

    private String pickupPoint;
    private String dropoffPoint;
    private String pickupDay;
    private String type;
    private String length;
    private String width;
    private String height;
    private String capacity;
    private String formOfTransportation;
    private String typeOfTruck;
    private boolean insurance;
    private boolean redBill;
    private boolean hideContact;
    private String note;
    private String price;


    public String getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public String getDropoffPoint() {
        return dropoffPoint;
    }

    public void setDropoffPoint(String dropoffPoint) {
        this.dropoffPoint = dropoffPoint;
    }

    public String getPickupDay() {
        return pickupDay;
    }

    public void setPickupDay(String pickupDay) {
        this.pickupDay = pickupDay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getFormOfTransportation() {
        return formOfTransportation;
    }

    public void setFormOfTransportation(String formOfTransportation) {
        this.formOfTransportation = formOfTransportation;
    }

    public String getTypeOfTruck() {
        return typeOfTruck;
    }

    public void setTypeOfTruck(String typeOfTruck) {
        this.typeOfTruck = typeOfTruck;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public boolean isRedBill() {
        return redBill;
    }

    public void setRedBill(boolean redBill) {
        this.redBill = redBill;
    }

    public boolean isHideContact() {
        return hideContact;
    }

    public void setHideContact(boolean hideContact) {
        this.hideContact = hideContact;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
