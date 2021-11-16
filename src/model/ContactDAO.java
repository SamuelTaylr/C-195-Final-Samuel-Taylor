package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DBConnection;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

/**
 * Data Access Object for the contact model
 *
 * @author Samuel Taylor
 */
public class ContactDAO {

    /**
     * Gets an observable array list of all contact names from contacts database
     *
     * @return  contactName the observable list of contact names
     */
    public static ObservableList<String> getContact() {

        ObservableList<String> contactName = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM contacts";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String customer = rs.getString("Contact_Name");
                contactName.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactName;
    }

    /**
     * Gets contact ID from a contact name from the database for use by other methods.
     *
     * @param name the contact name from the combo box
     * @return  id the contact id associated with provided name
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static int getContactIdofName(String name) throws ClassNotFoundException, SQLException {


            int id = 0;
            try {
                String query = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ?";
                PreparedStatement selectQuery = DBConnection.getConnection().prepareStatement(query);
                selectQuery.setString(1, name);
                ResultSet rs = selectQuery.executeQuery();
                if (rs.next()) { id = rs.getInt("Contact_ID");

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return id;

    }

    /**
     * Gets contact name from a contact ID from the database for use by other methods.
     *
     * @param contactId the contact id from the tableview
     * @return  name the contact name associated with the contact ID provided
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static String getContactNameofId(String contactId) throws ClassNotFoundException, SQLException {
        String name = null;

        try {
            String query = "SELECT Contact_Name FROM contacts WHERE Contact_ID = ?";
            PreparedStatement selectQuery = DBConnection.getConnection().prepareStatement(query);
            selectQuery.setString(1, contactId);
            ResultSet rs = selectQuery.executeQuery();
            rs.next();
            name = rs.getString("Contact_Name");
                System.out.println(name);



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;


    }

    /**
     * Gets all contacts from database for populating tableviews
     *
     * @return  allContacts the observable list of contacts objects
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException, Exception{

        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        Statement statement = DBConnection.getConnection().createStatement();
        String sqlStatement = "SELECT * FROM contacts";
        ResultSet result = statement.executeQuery(sqlStatement);

        while(result.next()) {

            int contactId = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            String email = result.getString("Email");
            Contact contactResult = new Contact(contactId, contactName, email);
            allContacts.add(contactResult);
            System.out.println("Going for contacts");
        }
        return allContacts;
    }
}

