package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * Data Access Object for the User model
 *
 * @author Samuel Taylor
 */
public class UserDAO {

    /**
     * Creates an observable array list of users that will be used to populate tables/fields
     *
     * @return allUsers a list of all users from the database
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<User> getAllUsers() throws SQLException, Exception{
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        Statement statement = DBConnection.getConnection().createStatement();
        String sqlStatement = "SELECT * FROM users";
        ResultSet result = statement.executeQuery(sqlStatement);

        while(result.next()) {

            int userId = result.getInt("User_ID");
            String userName = result.getString("User_Name");
            String password = result.getString("Password");
            Timestamp createdDate = result.getTimestamp("Create_Date");
            String createdBy = result.getString("Created_By");
            Timestamp lastUpdated = result.getTimestamp("Last_Update");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            User userResult = new User(userId, userName, password, createdDate, createdBy, lastUpdated,lastUpdatedBy);
            allUsers.add(userResult);
            System.out.println("Going for reports");
        }
        return allUsers;
    }

    /**
     * Creates an observable array list of user names that will be used to populate tables/fields
     *
     * @return userName a list of all users names from the database
     */
    public static ObservableList<String> getUser() {
        ObservableList<String> userName = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM users";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String customer = rs.getString("User_Name");
                userName.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userName;
    }

    /**
     * Takes a username and returns the matching user ID to be used in tableviews
     *
     * @param user a variable containing the user name
     * @return id a list of all user ids that match user name
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static int getUserIdofName(String user) throws ClassNotFoundException, SQLException {
        int id = 0;
        try {
            String query = "SELECT * FROM users WHERE User_Name = ?";
            PreparedStatement selectQuery = DBConnection.getConnection().prepareStatement(query);
            selectQuery.setString(1, user);
            ResultSet rs = selectQuery.executeQuery();
            while (rs.next()) {
                id = rs.getInt("User_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Takes a user ID and returns the matching username to be used in comboboxes
     *
     * @param userId a variable containing the user ID
     * @return name the user name matching the provided user ID
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static String getUserNameofId(int userId) throws ClassNotFoundException, SQLException {
        String name = null;

        try {
            String query = "SELECT User_Name FROM users WHERE User_ID = ?";
            PreparedStatement selectQuery = DBConnection.getConnection().prepareStatement(query);
            selectQuery.setInt(1, userId);
            ResultSet rs = selectQuery.executeQuery();
            rs.next();
            name = rs.getString("User_Name");
            System.out.println(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * Takes a username and password and validates them against contents of the database
     *
     * @param username a variable containing the user name
     * @param password a variable containing the password
     * @return boolean
     * @throws ClassNotFoundException
     */
    public static Boolean loginProcedure(String username, String password) throws SQLException {
        Statement statement = DBConnection.getConnection().createStatement();
        String sqlQuery = "SELECT * FROM users WHERE User_Name = '"+username+"' AND Password = '"+password+"'";
        ResultSet rs = statement.executeQuery(sqlQuery);
        System.out.println(rs);
        if (rs.next()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
