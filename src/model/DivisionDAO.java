package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object for the Division model
 *
 * @author Samuel Taylor
 */
public class DivisionDAO {

    /**
     * Gets division ID from a division Name
     *
     * @param divisionName the division name selected in the customer combo box
     * @return  id the division id
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static int getDivisionIdfromName(String divisionName) throws ClassNotFoundException, SQLException {
        int id = 0;

        try {
            String query = "SELECT * FROM first_level_divisions WHERE Division = ?";
            PreparedStatement selectQuery = DBConnection.getConnection().prepareStatement(query);
            selectQuery.setString(1, divisionName);
            ResultSet rs = selectQuery.executeQuery();
            while (rs.next()) { id = rs.getInt("Division_ID");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Gets division name from a country ID
     *
     * @param countryId the country ID selected in the customer combo box
     * @return  divisions the observable list of division names for the associated country id
     */
    public static ObservableList<String> getDivisionNameFromCountry(int countryId) {
        ObservableList<String> divisions = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
            PreparedStatement selectQuery = DBConnection.getConnection().prepareStatement(query);
            selectQuery.setInt(1, countryId);
            ResultSet rs = selectQuery.executeQuery();
            while (rs.next()) {
                String divisionName = rs.getString("Division");
                divisions.add(divisionName);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisions;
    }

    public static int getCountryIdFromDivisionId(int divisionId) {
        int divisionName = 0;
        try {
            String query = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
            PreparedStatement selectQuery = DBConnection.getConnection().prepareStatement(query);
            selectQuery.setInt(1, divisionId);
            ResultSet rs = selectQuery.executeQuery();
            while (rs.next()) {
                divisionName = rs.getInt("Country_ID");
                System.out.println(divisionName);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisionName;
    }

}
