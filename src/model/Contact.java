package model;

import java.sql.Date;

/**
 * Model for a Contact object
 *
 * @author Samuel Taylor
 */
public class Contact {

    /**
     * The contact ID property of an contact
     */
    private int contactId;

    /**
     * The contact name property of an contact
     */
    private String contactName;

    /**
     * The contact email property of an contact
     */
    private String contactEmail;

    /**
     * Constructor for Contact objects
     */
    Contact(int contactId, String contactName, String contactEmail) {

        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;

    }

    /**
     * Gets the contact ID
     *
     * @return contactId represents contact ID data
     */
    public int getContactId() { return contactId; }

    /**
     * Gets the contact name
     *
     * @return contactName represents contact name data
     */
    public String getContactName() { return contactName; }

    /**
     * Gets the contact email
     *
     * @return contactEmail represents contact email data
     */
    public String getContactEmail() { return contactEmail; }

    /**
     * Sets the contact ID
     *
     * @param contactId represents contact ID
     */
    public void setContactId(int contactId) { this.contactId = contactId; }

    /**
     * Sets the contact name
     *
     * @param contactName represents contact name
     */
    public void setContactName(String contactName) { this.contactName = contactName; }

    /**
     * Sets the contact email
     *
     * @param contactEmail represents contact email
     */
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
}


