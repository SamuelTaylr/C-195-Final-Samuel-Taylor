package model;

/**
 * Model for a Report object
 *
 * @author Samuel Taylor
 */
public class Report {

    /**
     * The month property of a report
     */
    private int month;

    /**
     * The count property of a report
     */
    private int count;

    /**
     * The type property of a report
     */
    private String type;

    /**
     * The month name property of a report
     */
    private String monthName;

    /**
     * The count as a string property of a report
     */
    private String stringCount;

    /**
     * Constructor for Report objects
     */
    public Report(int month, int count, String type, String monthName) {
        this.month = month;
        this.count = count;
        this.type = type;
        this.monthName = monthName;
    }

    /**
     * Constructor for Report objects
     */
    public Report(int month, int count) {
        this.month = month;
        this.count = count;
    }

    /**
     * Constructor for Report objects
     */
    public Report(String monthName, int count, int month) {
        this.monthName = monthName;
        this.count = count;
        this.month = month;
    }

    /**
     * Constructor for Report objects
     */
    public Report(String type, int count) {
        this.type = type;
        this.count = count;
    }


    /**
     * Gets the month
     *
     * @return month
     */
    public int getMonth() { return month; }

    /**
     * Gets the count
     *
     * @return count
     */
    public int getCount() { return count; }

    /**
     * Gets the type
     *
     * @return type
     */
    public String getType() { return type; }

    /**
     * Gets the month name
     *
     * @return monthName
     */
    public String getMonthName() { return monthName; }

    /**
     * Sets the month
     *
     * @param month the int representation of a month
     */
    public void setMonth( int month ) { this.month = month; }

    /**
     * Sets the count
     *
     * @param count represents the total appointments per month/type
     */
    public void setCount( int count ) { this.count = count; }

    /**
     * Sets the type
     *
     * @param type the string representation of a type
     */
    public void setType( String type ) { this.type = type; }

    /**
     * Sets the month name
     *
     * @param monthName the String representation of a month
     */
    public void setMonthName( String monthName) { this.monthName = monthName; }
}
