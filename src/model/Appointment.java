package model;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * Model for an appointment object
 *
 * @author Samuel Taylor
 */
public class Appointment {

    /**
     * The appointment ID property of an appointment
     */
    private int appointmentId;

    /**
     * The appointment title property of an appointment
     */
    private String title;

    /**
     * The appointment description property of an appointment
     */
    private String description;

    /**
     * The appointment location property of an appointment
     */
    private String location;

    /**
     * The appointment type property of an appointment
     */
    private String type;

    /**
     * The appointment start time/date property of an appointment
     */
    private LocalDateTime start;

    /**
     * The appointment end time/date property of an appointment
     */
    private LocalDateTime end;

    private Date createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdateBy;

    /**
     * The customer ID property of an appointment
     */
    private int customerId;

    /**
     * The user ID property of an appointment
     */
    private int userId;

    /**
     * The contact property of an appointment
     */
    private String contact;

    /**
     * The contact ID property of an appointment
     */
    private int contactId;

    /**
     * Constructor for Appointment objects
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start,
                       LocalDateTime end, Date createDate, String createdBy, String lastUpdate, String lastUpdateBy, int customerId,
                       int userId, String contact) {

        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contact = contact;
    }

    /**
     * Constructor for Appointment objects
     */
    public Appointment(int appointmentId, String title, String description, String location,
                       String type, String contact, LocalDateTime start, LocalDateTime end, int customerId, int userId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.contact = contact;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
    }

    /**
     * Constructor for Appointment objects
     */
    public Appointment(int appointmentId, String title, String description, String location, String type,
                       LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId){
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * Constructor for Appointment objects
     */
    public Appointment(int appointmentId, String title, String description, String type,
                       String location, LocalDateTime start, LocalDateTime end, int customerId, int userId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
    }

    /**
     * Gets the appointment ID
     *
     * @return appointmentId
     */
    public int getAppointmentId() { return appointmentId; }

    /**
     * Gets the appointment title
     *
     * @return title
     */
    public String getTitle() { return title; }

    /**
     * Gets the appointment description
     *
     * @return description
     */
    public String getDescription() { return description; }

    /**
     * Gets the appointment location
     *
     * @return location
     */
    public String getLocation() { return location; }

    /**
     * Gets the appointment type
     *
     * @return type
     */
    public String getType() { return type; }

    /**
     * Gets the appointment start time/date
     *
     * @return start
     */
    public LocalDateTime getStart() { return start; }

    /**
     * Gets the appointment end time/date
     *
     * @return start
     */
    public LocalDateTime getEnd() { return end; }

    public Date getCreateDate() { return createDate; }
    public String getCreatedBy() { return createdBy; }
    public String getLastUpdate() { return lastUpdate; }
    public String getLastUpdateBy() { return lastUpdateBy; }

    /**
     * Gets the customer ID
     *
     * @return customerId
     */
    public int getCustomerId() { return customerId; }

    /**
     * Gets the user ID
     *
     * @return userId
     */
    public int getUserId() { return userId; }

    /**
     * Gets the contact data
     *
     * @return contact
     */
    public String getContact() { return contact; }

    /**
     * Gets the contact ID
     *
     * @return contactId
     */
    public int getContactID() { return contactId; }

    /**
     * Sets the appointment ID
     *
     * @param appointmentId represents appointment ID
     */
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    /**
     * Sets the appointment title
     *
     * @param title represents appointment title
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Sets the appointment description
     *
     * @param description represents appointment description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Sets the appointment location
     *
     * @param location represents appointment location
     */
    public void setLocation(String location) { this.location = location; }

    /**
     * Sets the appointment type
     *
     * @param type represents appointment type
     */
    public void setType(String type) { this.type = type; }

    /**
     * Sets the appointment start time/date
     *
     * @param start represents appointment start time/date
     */
    public void setStart(LocalDateTime start) { this.start = start; }

    /**
     * Sets the appointment end time/date
     *
     * @param end represents appointment end time/date
     */
    public void setEnd(LocalDateTime end) { this.end = end; }

    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public void setLastUpdate(String lastUpdate) { this.lastUpdate = lastUpdate; }
    public void setLastUpdateBy(String lastUpdateBy) { this.lastUpdateBy = lastUpdateBy; }

    /**
     * Sets the customer ID
     *
     * @param customerId represents customer ID
     */
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    /**
     * Sets the user ID
     *
     * @param userId represents user ID
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Sets the contact data
     *
     * @param contact represents contact data
     */
    public void setContact(String contact) { this.contact = contact; }

    /**
     * Sets the contact ID
     *
     * @param contactId represents contact ID
     */
    public void setContactId(int contactId) { this.contactId = contactId; }

}
