package lgv.automation.model.api;

public class Driver {

    int id;

    String name;

    String phoneNumber;

    String password;

    String email;

    String address;

    String dateOfBirth;

    int licenseID;

    int identityID;

    String avatarUrl;

    String authenticationToken;

    public int getID() { return id; }

    public void setID(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public int getLicenseID() { return licenseID; }

    public void setLicenseID(int licenseID) { this.licenseID = licenseID; }

    public int getIdentityID() { return identityID; }

    public void setIdentityID(int identityID) { this.identityID = identityID; }

    public String getAvatarUrl() { return avatarUrl; }

    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public String getAuthenticationToken() { return authenticationToken; }

    public void setAuthenticationToken(String authenticationToken) { this.authenticationToken = authenticationToken; }

    public void Driver() {

    }

    public void Driver(int id, String phoneNumber, String authenticationToken) {

        this.id = id;

        this.phoneNumber = phoneNumber;

        this.authenticationToken = authenticationToken;
    }

}
