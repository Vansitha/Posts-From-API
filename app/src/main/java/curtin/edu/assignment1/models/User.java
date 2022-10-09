// User class to hold data about each user

package curtin.edu.assignment1.models;

import java.io.Serializable;

// implements serializable since we need to pass the selected user object to the display profile activity
public class User implements Serializable {

    public static String USER_OBJECT = "USER_OBJECT";
    public static String USER_ID = "userId";
    private int userId;
    private String name;
    private String username;
    private String email;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private String phone;
    private String website;
    private String companyName;
    private String companyCatchPhrase;
    private String business;

    public User(int userId, String name, String username, String email) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public User(int userId, String name, String username, String email, String street, String suite, String city, String zipcode, String phone, String website, String companyName, String companyCatchPhrase, String business) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.phone = phone;
        this.website = website;
        this.companyName = companyName;
        this.companyCatchPhrase = companyCatchPhrase;
        this.business = business;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyCatchPhrase() {
        return companyCatchPhrase;
    }

    public String getBusiness() {
        return business;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }
}
