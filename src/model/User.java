package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * Model for a User object
 *
 * @author Samuel Taylor
 */
public class User {

    /**
     * The id property of a user
     */
    private int userId;

    /**
     * The username property of a user
     */
    private String userName;

    /**
     * The password property of a user
     */
    private String password;

    /**
     * The created timestamp property of a user
     */
    private Timestamp createdDate;

    /**
     * The created by property of a user
     */
    private String createdBy;

    /**
     * The last updated property of a user
     */
    private Timestamp lastUpdated;

    /**
     * The last updated property of a user
     */
    private String lastUpdatedBy;

    /**
     * Default constructor for User objects
     */
    public User() { }

    /**
     * Constructor for User objects
     */
    public User(int userId, String userName, String password,
                Timestamp createdDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy) {

        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Gets the user ID
     *
     * @return userId
     */
    public int getUserId() { return userId; }

    /**
     * Gets the user name
     *
     * @return userName
     */
    public String getUserName() { return userName; }

    /**
     * Gets the user password
     *
     * @return password
     */
    public String getPassword() { return password; }

    /**
     * Gets the created by timestamp
     *
     * @return createdDate
     */
    public Timestamp getCreatedDate() { return createdDate; }

    /**
     * Gets the created by
     *
     * @return createdBy
     */
    public String getCreatedBy() { return createdBy; }

    /**
     * Gets the last updated timestamp
     *
     * @return lastUpdated
     */
    public Timestamp getLastUpdated() { return lastUpdated; }

    /**
     * Gets the last updated by
     *
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() { return lastUpdatedBy; }

    /**
     * Sets the user ID
     *
     * @param userId the user id
     */
    public void setUserId( int userId ) { this.userId = userId; }

    /**
     * Sets the user name
     *
     * @param userName the user name
     */
    public void setUserName( String userName) { this.userName = userName; }

    /**
     * Sets the created timestamp
     *
     * @param createdDate the created timestamp
     */
    public void setCreatedDate( Timestamp createdDate) { this.createdDate = createdDate; }

    /**
     * Sets the created by value
     *
     * @param createdBy the created by value
     */
    public void setCreatedBy( String createdBy ) { this.createdBy = createdBy; }

    /**
     * Sets the last updated timestamp
     *
     * @param lastUpdated the last updated timestamp
     */
    public void setLastUpdated( Timestamp lastUpdated ) { this.lastUpdated = lastUpdated; }

    /**
     * Sets the last updated by
     *
     * @param lastUpdatedBy the last user to update
     */
    public void setLastUpdatedBy( String lastUpdatedBy ) { this.lastUpdatedBy = lastUpdatedBy; }
}
