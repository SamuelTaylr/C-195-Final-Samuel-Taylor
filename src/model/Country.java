package model;

import java.sql.Date;

/**
 * Model for a Country object
 *
 * @author Samuel Taylor
 */
public class Country {

    /**
     * The country ID property of a country object
     */
    private int countryId;

    /**
     * The country name property of an country
     */
    private String countryName;

    /**
     * Constructor for Country objects
     */
    public Country( String countryName) { this.countryName = countryName; }

    /**
     * Override for toString method for country name combo box
     *
     * @return countryName the country name String
     */
    @Override
    public String toString() {
        return countryName;
    }

    /**
     * Constructor for Country objects
     */
    public Country(int countryId) { this.countryId = countryId; }

    /**
     * Gets the country ID
     *
     * @return countryId represents country ID data
     */
    public int getCountryId() { return countryId; }

    /**
     * Gets the country name
     *
     * @return countryName represents country name data
     */
    public String getCountryName() { return countryName; }

    /**
     * Sets the country ID
     *
     * @param countryId represents country ID
     */
    public void setCountryId(int countryId) { this.countryId = countryId; }

    /**
     * Sets the country name
     *
     * @param countryName represents the country name
     */
    public void setCountryName(String countryName) { this.countryName = countryName; }
}

