package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import utilities.DBConnection;

import javax.management.Query;
import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * Data Access Object for the Customer model
 *
 * @author Samuel Taylor
 */
public class CustomerDAO {

    /**
     * Gets all customers from database for populating tableviews
     *
     * @return  allUsers the observable list of customers objects
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception {

        ObservableList<Customer> allUsers = FXCollections.observableArrayList();
        Statement statement = DBConnection.getConnection().createStatement();

        String sqlStatement = "SELECT * FROM customers INNER JOIN first_level_divisions ON " +
                "customers.Division_ID = first_level_divisions.Division_ID INNER JOIN countries on " +
                "first_level_divisions.Country_ID = countries.Country_ID";
        ResultSet result = statement.executeQuery(sqlStatement);

        while (result.next()) {

            int customerId = result.getInt("Customer_ID");
            String customerName = result.getString("Customer_Name");
            String customerAddress = result.getString("Address");
            String customerPostalCode = result.getString("Postal_Code");
            String customerPhone = result.getString("Phone");
            String customerDivision = result.getString("Division");
            String customerCountry = result.getString("Country");
            Customer customerResult = new Customer(customerId, customerName, customerAddress,
                    customerPostalCode, customerPhone, customerDivision, customerCountry);
            allUsers.add(customerResult);
            System.out.println("Going");
        }
        return allUsers;
    }

    /**
     * Gets all customer Names from the database for combo boxes
     *
     * @return  customerName the observable list of customer names
     */
    public static ObservableList<String> getCustomer() {

        ObservableList<String> customerName = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM customers";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String customer = rs.getString("Customer_Name");
                customerName.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerName;
    }

    /**
     * Gets all customer IDs for a matching customer name for conversions from combo boxes to tableviews
     *
     * @param customer the customer name from the combo box
     * @return  id the customer ID
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static int getCustomerIdofName(String customer) throws ClassNotFoundException, SQLException {
        int id = 0;
        try {
            String query = "SELECT * FROM customers WHERE Customer_Name = ?";
            PreparedStatement selectQuery = DBConnection.getConnection().prepareStatement(query);
            selectQuery.setString(1, customer);
            ResultSet rs = selectQuery.executeQuery();
            while (rs.next()) {
                id = rs.getInt("Customer_ID");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Gets all customer names for a matching customer id for conversions from combo boxes to tableviews
     *
     * @param customerId the customer ID from the tableview
     * @return  name the customer name
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static String getCustomerNameofId(int customerId) throws ClassNotFoundException, SQLException {
        String name = null;

        try {
            String query = "SELECT Customer_Name FROM customers WHERE Customer_ID = ?";
            PreparedStatement selectQuery = DBConnection.getConnection().prepareStatement(query);
            selectQuery.setInt(1, customerId);
            ResultSet rs = selectQuery.executeQuery();
            rs.next();
            name = rs.getString("Customer_Name");
            System.out.println(name);



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;


    }

    /**
     * Uses a prepared statement to insert new customer objects into the database.
     *
     * @param name the customer name
     * @param address the customer address
     * @param postal the customer postal code
     * @param phone the customer phone number
     * @param divisionId the customer division id
     * @throws SQLException
     */
    public static void addCustomer(String name, String address, String postal, String phone, int divisionId) throws SQLException {

        try {

            String insertString = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertCustomer = DBConnection.getConnection().prepareStatement(insertString);
            insertCustomer.setString(1, name);
            insertCustomer.setString(2, address);
            insertCustomer.setString(3, postal);
            insertCustomer.setString(4, phone);
            insertCustomer.setInt(5, divisionId);

            insertCustomer.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * Uses a prepared statement to update customer objects in the database.
     *
     * @param custId the customer ID
     * @param name the customer name
     * @param address the customer address
     * @param postal the customer postal code
     * @param phone the customer phone number
     * @param division the customer division id
     * @throws SQLException
     */
    public static void updateCustomer(int custId, String name, String address,
                                         String postal, String phone, int division) throws SQLException {

        try {

            String insertString = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = "+custId+"";
            PreparedStatement insertAppointment = DBConnection.getConnection().prepareStatement(insertString);
            insertAppointment.setString(1, name);
            insertAppointment.setString(2, address);
            insertAppointment.setString(3, postal);
            insertAppointment.setString(4, phone);
            insertAppointment.setInt(5, division);
            insertAppointment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses a prepared statement to delete customer objects from the database.  Also deletes
     * any associated appointments with the provided customer ID then shows an alert confirming that
     * customer and appointments were deleted.
     *
     * @param id the customer ID
     * @param name the customer name
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void deleteCustomer(int id, String name) throws ClassNotFoundException, SQLException {
        // Deletes associated appointments first, if any
        String deleteString = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement deleteAppointments = DBConnection.getConnection().prepareStatement(deleteString);
        deleteAppointments.setInt(1, id);
        deleteAppointments.executeUpdate();
        // Then deletes customer second to avoid foreign key constraints
        String insertString = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement deleteCustomer = DBConnection.getConnection().prepareStatement(insertString);
        deleteCustomer.setInt(1, id);
        deleteCustomer.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer " + name + " And Any Associated Appointments Have Been Deleted ", ButtonType.OK);
        alert.showAndWait().filter(response -> response == ButtonType.OK);
    }
}

