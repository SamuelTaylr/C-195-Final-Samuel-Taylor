package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Data Access Object for the Report model
 *
 * @author Samuel Taylor
 */
public class ReportDAO {

    /**
     * Creates an observable array list of the month and total appts for that month
     * that will be used to populate tables/fields.
     *
     * @return monthReports a list of all appts. counted by month
     * @throws SQLException
     */
    public static ObservableList<Report> reportsByMonth() throws SQLException {

        ObservableList<Report> monthReports = FXCollections.observableArrayList();
        String query = "SELECT MONTH(`Start`) as `Month`, COUNT(*) as `Count` FROM appointments GROUP BY MONTH(`Start`) ORDER BY MONTH(`Start`)";
        Statement statement = DBConnection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        String monthName = "";

        while(rs.next()) {
            int month = rs.getInt("Month");
            int count = rs.getInt("Count");
            if(month == 1) {
                monthName = "January";
            }
            if(month == 2) {
                monthName = "February";
            }
            if(month == 3) {
                monthName = "March";
            }
            if(month == 4) {
                monthName = "April";
            }
            if(month == 5) {
                monthName = "May";
            }
            if(month == 6) {
                monthName = "June";
            }
            if(month == 7) {
                monthName = "July";
            }
            if(month == 8) {
                monthName = "August";
            }
            if(month == 9) {
                monthName = "September";
            }
            if(month == 10) {
                monthName = "October";
            }
            if(month == 11) {
                monthName = "November";
            }
            if(month == 12) {
                monthName = "December";
            }
            Report reportResult = new Report(monthName, count, month);
            monthReports.add(reportResult);
            System.out.println(monthName);
            System.out.println(count);

        }
        return monthReports;
    }

    /**
     * Creates an observable array list of the type and total appts for that type
     * that will be used to populate tables/fields.
     *
     * @return typeReports a list of all appts. counted by type
     * @throws SQLException
     */
    public static ObservableList<Report> reportsByType() throws SQLException {

        ObservableList<Report> typeReports = FXCollections.observableArrayList();
        String query = "SELECT `Type`, COUNT(*) as `Count` FROM appointments GROUP BY `Type` ORDER BY `Type`";
        Statement statement = DBConnection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            String type = rs.getString("Type");
            int count = rs.getInt("Count");

            Report reportResult = new Report(type, count);
            typeReports.add(reportResult);
            System.out.println(type);
            System.out.println(count);

        }
        return typeReports;
    }
}
