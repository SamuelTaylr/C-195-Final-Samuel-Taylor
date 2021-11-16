package model;

import java.sql.Date;

/**
 * Model for a Division object
 *
 * @author Samuel Taylor
 */
public class Division {

    /**
     * The division ID property of a division
     */
    private int divisionId;

    /**
     * The division name property of a division
     */
    private String division;

    /**
     * The country ID property of a division
     */
    private int countryId;

    /**
     * Constructor for Division objects
     */
    public Division(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * Gets the division ID
     *
     * @return divisionId
     */
    public int getDivisionId() { return divisionId; }

    /**
     * Gets the division name
     *
     * @return division
     */
    public String getDivision() { return division; }

    /**
     * Gets the country ID
     *
     * @return countryId
     */
    public int getCountryId() { return countryId; }

    /**
     * Sets the division ID
     *
     * @param divisionId represents the division ID
     */
    public void setDivisionId(int divisionId) { this.divisionId = divisionId; }

    /**
     * Sets the division name
     *
     * @param division represents the division name
     */
    public void setDivision(String division) { this.division = division; }

    /**
     * Sets the country ID
     *
     * @param countryId represents the country ID
     */
    public void setCountryId(int countryId) { this.countryId = countryId; }
}
