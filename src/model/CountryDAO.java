package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Data Access Object for the Country model
 *
 * @author Samuel Taylor
 */
public class CountryDAO {

    /**
     * Gets all countries from database for populating tableviews
     *
     * @return  allCountries the observable list of countries objects
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Country> getAllCountries() throws SQLException, Exception {

        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        Statement statement = DBConnection.getConnection().createStatement();

        String sqlStatement = "SELECT * FROM countries";
        ResultSet result = statement.executeQuery(sqlStatement);

        while (result.next()) {

            String countryName = result.getString("Country");
            Country countryResult = new Country(countryName);
            allCountries.add(countryResult);

        }
        return allCountries;
    }

    /**
     * Gets all country IDs from the country name from database for populating tableviews
     *
     * @param name the country name from the combo box
     * @return  id the country ID associated with the country name
     */
    public static int getCountryIdFromName(Country name) {

        int id = 0;
        try {
            String query = "SELECT * FROM countries WHERE Country = ?";
            PreparedStatement selectQuery = DBConnection.getConnection().prepareStatement(query);
            selectQuery.setString(1, String.valueOf(name));
            ResultSet rs = selectQuery.executeQuery();
            while (rs.next()) {
                id = rs.getInt("Country_ID");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public static Country getCountryNameFromId(int countryId) throws SQLException, Exception {

        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        Country countryResult = null;
        String query = "SELECT * FROM countries WHERE Country_ID = ?";
        PreparedStatement selectQuery = DBConnection.getConnection().prepareStatement(query);
        selectQuery.setInt(1, countryId);
        ResultSet rs = selectQuery.executeQuery();
        while (rs.next()) {
            String countryName = rs.getString("Country");
            countryResult = new Country(countryName);
            allCountries.add(countryResult);
            System.out.println(countryResult);

        }

        return countryResult;
    }


}
