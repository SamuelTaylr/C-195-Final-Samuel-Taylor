package model;

import java.sql.Date;

/**
 * Model for a Customer object
 *
 * @author Samuel Taylor
 */
public class Customer {

    /**
     * The customer ID property of a customer object
     */
    private int customerId;

    /**
     * The customer name property of a customer object
     */
    private String customerName;

    private int active;
    private Date createDate;
    private String createdBy;

    /**
     * The customer address property of a customer object
     */
    private String address;
    private String address2;

    /**
     * The customer division property of a customer object
     */
    private String division;

    /**
     * The customer postal code property of a customer object
     */
    private String postalCode;

    /**
     * The customer phone number property of a customer object
     */
    private String phone;

    /**
     * The customer country property of a customer object
     */
    private String country;

    private Date lastUpdate;
    private String lastUpdateBy;

    /**
     * Default Constructor for Customer objects
     */
    public Customer() { }

    /**
     * Constructor for Customer objects
     */
    public Customer(int customerId, String customerName, int active, String address, String address2,
                    String division, String postalCode, String phone, String country, Date lastUpdate, String lastUpdateBy) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.active = active;
        this.address = address;
        this.address2 = address2;
        this.division = division;
        this.postalCode = postalCode;
        this.phone = phone;
        this.country = country;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * Constructor for Customer objects
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phone, String division, String country) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
        this.country = country;
    }

    /**
     * Constructor for Customer objects
     */
    public Customer(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the customer ID
     *
     * @return customerId represents customer ID data
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Gets the customer name
     *
     * @return customerName represents customer name data
     */
    public String getCustomerName() {
        return customerName;
    }
    public int getCustomerActive() {
        return active;
    }

    /**
     * Gets the customer address
     *
     * @return address represents customer address data
     */
    public String getCustomerAddress() {
        return address;
    }
    public String getCustomerAddress2() {
        return address2;
    }

    /**
     * Gets the customer division
     *
     * @return division represents customer division data
     */
    public String getCustomerDivision() {
        return division;
    }

    /**
     * Gets the customer postal code
     *
     * @return postalCode represents customer postal code data
     */
    public String getCustomerPostalCode() {
        return postalCode;
    }

    /**
     * Gets the customer phone number
     *
     * @return phone represents customer phone number data
     */
    public String getCustomerPhone() {
        return phone;
    }

    /**
     * Gets the customer country
     *
     * @return country represents customer country data
     */
    public String getCustomerCountry() { return country; }
    public Date getCustomerLastUpdate() {
        return lastUpdate;
    }
    public String getCustomerLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * Sets the customer ID
     *
     * @param customerId represents customer Id data
     */
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    /**
     * Sets the customer name
     *
     * @param customerName represents customer name data
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public void setCustomerActive(int active) {
        this.active = active;
    }

    /**
     * Sets the customer address
     *
     * @param address represents customer address data
     */
    public void setCustomerAddress(String address) {
        this.address = address;
    }
    public void setCustomerAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Sets the customer division
     *
     * @param division represents customer division data
     */
    public void setCustomerDivision(String division) {
        this.division = division;
    }

    /**
     * Sets the customer postal code
     *
     * @param postalCode represents customer postal code data
     */
    public void setCustomerPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Sets the customer phone number
     *
     * @param phone represents customer phone number data
     */
    public void setCustomerPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the customer country
     *
     * @param country represents customer country data
     */
    public void setCustomerCountry(String country) {
        this.country = country;
    }

    public void setCustomerLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public void setCustomerLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
}