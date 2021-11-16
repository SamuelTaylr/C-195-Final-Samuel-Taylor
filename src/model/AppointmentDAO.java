package model;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import utilities.DBConnection;

import java.sql.*;
import java.time.*;
import java.util.Optional;

/**
 * Data Access Object for the appointment model
 *
 * @author Samuel Taylor
 */
public class AppointmentDAO {


    /**
     * Gets all appointments from database for populating tableviews
     *
     * @return  allAppointments the observable list of appointments objects
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        Statement statement = DBConnection.getConnection().createStatement();

        String sqlStatement = "SELECT * FROM appointments INNER JOIN users ON appointments.User_ID = " +
                "users.User_ID INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID";

        ResultSet result = statement.executeQuery(sqlStatement);
        while (result.next()) {
            int appointmentId = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String type = result.getString("Type");
            String contact = result.getString("Contact_Name");

            Timestamp startAppt = result.getTimestamp(("Start"));
            LocalDateTime start = startAppt.toLocalDateTime();
            Timestamp endAppt = result.getTimestamp(("End"));
            LocalDateTime end = endAppt.toLocalDateTime();
            //ZonedDateTime zonedDateTimeStart = start.atZone(ZoneId.systemDefault());
            //ZonedDateTime zonedDateTimeEnd = end.atZone(ZoneId.systemDefault());
            //zonedDateTimeStart.withZoneSameInstant(ZoneId.systemDefault());
            //zonedDateTimeEnd.withZoneSameInstant(ZoneId.systemDefault());
            //LocalDateTime startFinal = zonedDateTimeStart.toLocalDateTime();
            //LocalDateTime endFinal = zonedDateTimeEnd.toLocalDateTime();
            ZonedDateTime startZone = ZonedDateTime.of(start,ZoneId.systemDefault());
            ZonedDateTime endZone = ZonedDateTime.of(end,ZoneId.systemDefault());
            LocalDateTime startFinal = startZone.toLocalDateTime();
            LocalDateTime endFinal = endZone.toLocalDateTime();
            System.out.println(start);
            System.out.println(startZone);
            int customerId = result.getInt("Customer_ID");
            int userId = result.getInt("User_ID");
            Appointment appointmentResult = new Appointment(appointmentId, title, description, type, location, contact, startFinal, endFinal, customerId, userId);
            allAppointments.add(appointmentResult);
        }
        return allAppointments;
    }

    /**
     * Gets all appointments from database for the next week for populating tableviews
     *
     * @return  allAppointments the observable list of appointment objects
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getWeekAppointments() throws SQLException, Exception {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        Statement statement = DBConnection.getConnection().createStatement();

        String sqlStatement = "select * from appointments INNER JOIN contacts ON appointments.Contact_ID = " +
                "contacts.Contact_ID WHERE Start BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 7 DAY);" ;

        ResultSet result = statement.executeQuery(sqlStatement);
        while (result.next()) {
            int appointmentId = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String type = result.getString("Type");
            String contact = result.getString("Contact_Name");
            Timestamp startAppt = result.getTimestamp(("Start"));
            LocalDateTime start = startAppt.toLocalDateTime();
            Timestamp endAppt = result.getTimestamp(("End"));
            LocalDateTime end = endAppt.toLocalDateTime();
            int customerId = result.getInt("Customer_ID");
            int userId = result.getInt("User_ID");
            Appointment appointmentResult = new Appointment(appointmentId, title, description, type, location, contact, start, end, customerId, userId);
            allAppointments.add(appointmentResult);
            }

        return allAppointments;
    }

    /**
     * Gets all appointments from database for the next month for populating tableviews
     *
     * @return  allAppointments the observable list of appointment objects
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getMonthAppointments() throws SQLException, Exception {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        Statement statement = DBConnection.getConnection().createStatement();

        String sqlStatement = "select * from appointments INNER JOIN contacts ON appointments.Contact_ID = " +
                "contacts.Contact_ID WHERE Start BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 1 MONTH);" ;

        ResultSet result = statement.executeQuery(sqlStatement);
        while (result.next()) {
            int appointmentId = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String type = result.getString("Type");
            String contact = result.getString("Contact_Name");
            Timestamp startAppt = result.getTimestamp(("Start"));
            LocalDateTime start = startAppt.toLocalDateTime();
            Timestamp endAppt = result.getTimestamp(("End"));
            LocalDateTime end = endAppt.toLocalDateTime();
            int customerId = result.getInt("Customer_ID");
            int userId = result.getInt("User_ID");
            Appointment appointmentResult = new Appointment(appointmentId, title, description, type, location,
                    contact, start, end, customerId, userId);
            allAppointments.add(appointmentResult);
            System.out.println("Going");
        }

        return allAppointments;
    }

    /**
     * Uses an INSERT Sql statement to add appointment data to the database.
     *
     * @param title the appointment title
     * @param description the appointment description
     * @param location the appointment location
     * @param type the appointment type
     * @param start the appointment start time/date
     * @param end the appointment end time/date
     * @param customer the customer ID
     * @param user the user ID
     * @param contact the contact ID
     * @throws SQLException
     */
    public static void addAppointment(String title, String description,
                                      String location, String type, LocalDateTime start, LocalDateTime end,
                                      int customer, int user, int contact) throws SQLException {
        try {

            String insertString = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertAppointment = DBConnection.getConnection().prepareStatement(insertString);
            insertAppointment.setString(1, title);
            insertAppointment.setString(2, description);
            insertAppointment.setString(3, location);
            insertAppointment.setString(4, type);
            insertAppointment.setTimestamp(5, Timestamp.valueOf(start));
            insertAppointment.setTimestamp(6, Timestamp.valueOf(end));
            insertAppointment.setInt(7, customer);
            insertAppointment.setInt(8, user);
            insertAppointment.setInt(9, contact);
            insertAppointment.executeUpdate();
        } catch (SQLException e) {
                e.printStackTrace();
        }


    }

    /**
     * Uses an UPDATE Sql statement to update an appointment in the database.
     *
     * @param appointmentId the appointment ID
     * @param title the appointment title
     * @param description the appointment description
     * @param location the appointment location
     * @param type the appointment type
     * @param start the appointment start time/date
     * @param end the appointment end time/date
     * @param customer the customer ID
     * @param user the user ID
     * @param contact the contact ID
     * @throws SQLException
     */
    public static void updateAppointment(String appointmentId, String title, String description,
                                      String location, String type, LocalDateTime start, LocalDateTime end, int customer, int user, int contact) throws SQLException {

        try {

            String insertString = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = "+appointmentId+"";
            PreparedStatement insertAppointment = DBConnection.getConnection().prepareStatement(insertString);
            insertAppointment.setString(1, title);
            insertAppointment.setString(2, description);
            insertAppointment.setString(3, location);
            insertAppointment.setString(4, type);
            insertAppointment.setTimestamp(5, Timestamp.valueOf(start));
            insertAppointment.setTimestamp(6, Timestamp.valueOf(end));
            insertAppointment.setInt(7, customer);
            insertAppointment.setInt(8, user);
            insertAppointment.setInt(9, contact);
            insertAppointment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses DELETE sql statement to remove an appt. from the database, shows a descriptive alert after removal.
     *
     * @param id the appointment ID
     * @param type the appointment type
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void deleteAppointment(int id, String type) throws ClassNotFoundException, SQLException {

        String insertString = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement deleteAppointment = DBConnection.getConnection().prepareStatement(insertString);
        deleteAppointment.setInt(1, id);
        deleteAppointment.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment ID " + id + " Of Type " + type + " Has been deleted", ButtonType.OK);
        alert.showAndWait().filter(response -> response == ButtonType.OK);
    }

    /**
     * Uses sql statement and a provided contact ID to search for associated appointments and
     * add them to an observable list, then return that list.
     *
     * @param contactId the contact ID used to search for associated appointments
     * @return allAppointmentsFromContactId the list of appts. associated with the contact ID passed to the method
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getAllAppointmentsFromContactId(int contactId) throws SQLException, Exception {
        ObservableList<Appointment> allAppointmentsFromContactId = FXCollections.observableArrayList();
        Statement statement = DBConnection.getConnection().createStatement();

        String sqlStatement = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE contacts.Contact_ID = "+contactId+"";

        ResultSet result = statement.executeQuery(sqlStatement);
        while (result.next()) {
            int appointmentId = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String type = result.getString("Type");
            Timestamp startAppt = result.getTimestamp(("Start"));
            LocalDateTime start = startAppt.toLocalDateTime();
            Timestamp endAppt = result.getTimestamp(("End"));
            LocalDateTime end = endAppt.toLocalDateTime();
            int customerId = result.getInt("Customer_ID");
            int userId = result.getInt("User_ID");
            String contact = result.getString("Contact_Name");

            Appointment appointmentResult = new Appointment(appointmentId, title, description, type, location, contact, start, end, customerId, userId);
            allAppointmentsFromContactId.add(appointmentResult);
            System.out.println("Going");
        }
        return allAppointmentsFromContactId;
    }

    /**
     * Uses sql statement to search the database for all distinct appointment types and add
     * them to an observable array list.
     *
     * @return appointmentType the list of types present in the database for appointments
     */
    public static ObservableList<String> getType() {

        ObservableList<String> appointmentType = FXCollections.observableArrayList();

        try {
            String query = "SELECT DISTINCT Type FROM appointments";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String type = rs.getString("Type");
                appointmentType.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentType;
    }

    /**
     * Uses sql statement and a provided type to search for associated appointments and
     * add them to an observable list, then return that list.
     *
     * @param type the appointment type, used to search for associated appointments
     * @return allAppointments the list of appts. associated with the type passed to the method
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getAllAppointmentsFromType(String type) throws SQLException, Exception {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        Statement statement = DBConnection.getConnection().createStatement();
        System.out.println(type);

        String sqlStatement = "SELECT * FROM appointments RIGHT JOIN Contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE appointments.Type = "+type+"";

        ResultSet result = statement.executeQuery(sqlStatement);
        while (result.next()) {
            int appointmentId = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String typeResult = result.getString("Type");
            String contact = result.getString("Contact_Name");
            Timestamp startAppt = result.getTimestamp(("Start"));
            LocalDateTime start = startAppt.toLocalDateTime();
            Timestamp endAppt = result.getTimestamp(("End"));
            LocalDateTime end = endAppt.toLocalDateTime();
            int customerId = result.getInt("Customer_ID");
            int userId = result.getInt("User_ID");
            Appointment appointmentResult = new Appointment(appointmentId, title, description, typeResult, location, contact, start, end, customerId, userId);
            allAppointments.add(appointmentResult);
            System.out.println("Going");


        }
        return allAppointments;
    }

    /**
     * Uses sql statement to search the database for any appointment times that fall between now and 15 minutes from now
     *
     * @return allAppointments the list of appts. with times in the next 15 minutes on login
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointmentsIn15Minutes() throws SQLException {
        String query = "select * from appointments WHERE Start BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 15 MINUTE)";
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        Statement statement = DBConnection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp startAppt = rs.getTimestamp(("Start"));
            LocalDateTime start = startAppt.toLocalDateTime();
            Timestamp endAppt = rs.getTimestamp(("End"));
            LocalDateTime end = endAppt.toLocalDateTime();
            ZonedDateTime startZone = ZonedDateTime.of(start,ZoneId.systemDefault());
            ZonedDateTime endZone = ZonedDateTime.of(end,ZoneId.systemDefault());
            LocalDateTime startFinal = startZone.toLocalDateTime();
            LocalDateTime endFinal = endZone.toLocalDateTime();
            LocalTime startAlert = startFinal.toLocalTime();
            LocalDate startAlertDate = startFinal.toLocalDate();

            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            Appointment appointmentResult = new Appointment(appointmentId, title, description, type, location, startFinal, endFinal, customerId, userId);
            allAppointments.add(appointmentResult);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Appointment Alert");
            alert.setHeaderText("Confirm");
            alert.setContentText("You have a " + type + " appointment ID #" + appointmentId + " in the next 15 minutes at " + startAlert + " on " + startAlertDate );
            Optional<ButtonType> result = alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Appointment Alert");
            alert.setHeaderText("Confirm");
            alert.setContentText("No appointments in the next 15 minutes");
            Optional<ButtonType> result = alert.showAndWait();
        }
        return allAppointments;
    }

    /**
     * Uses sql statement and a provided customer ID, start and end time to search for appointments that
     * overlap with the appointment to be added.
     *
     * @param customerId the customer ID to check for overlapping appts
     * @param start the new appt start time
     * @param end the new appt end time
     * @return boolean
     * @throws SQLException
     */
    public static boolean appointmentOverlapCheck(int customerId, LocalDateTime start, LocalDateTime end) throws SQLException {
        String sqlQuery = "SELECT COUNT(*) as Duplicate_Count FROM appointments " +
        "WHERE Customer_ID = "+customerId+" " +
                "AND (('"+start+"' BETWEEN Start AND End) " +
                "OR ('"+end+"' BETWEEN Start AND End) " +
                "OR (Start BETWEEN '"+start+"' AND '"+end+"') " +
                "OR (End BETWEEN '"+start+"' AND '"+end+"'))";
        Statement statement = DBConnection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);
        int count = 0;
        System.out.println(sqlQuery);
        if (rs.next()) {
            count = rs.getInt("Duplicate_Count");
            System.out.println(count);
        }

        if (count == 0) {
            return false;
        }else{
            return true;
        }
    }

}