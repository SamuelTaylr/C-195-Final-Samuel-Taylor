package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;
import utilities.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * FXML Controller class for customer view
 *
 * @author Samuel Taylor
 */
public class CustomerController implements Initializable {


    /**
     * The button to add customer to database
     */
    public Button customerAddButton;

    /**
     * The button to save changes to a customer to database
     */
    public Button customerModifyButton;

    /**
     * The button to delete customer and any associated appointments from database
     */
    public Button customerDeleteButton;

    /**
     * The text field for customer ID data
     */
    @FXML public TextField customerIdField;

    /**
     * The text field for customer name data
     */
    @FXML public TextField customerNameField;

    /**
     * The text field for customer address data
     */
    @FXML public TextField customerAddressField;

    /**
     * The text field for customer postal code data
     */
    @FXML public TextField customerPostalCodeField;

    /**
     * The text field for customer phone number data
     */
    @FXML public TextField customerPhoneNumberField;

    /**
     * The combo box for country selection, filters division combo box
     */
    @FXML private ComboBox<Country> customerCountryComboBox;

    /**
     * The combo box for division selection, filtered by country combo box
     */
    @FXML private ComboBox<String> customerDivisionComboBox;

    /**
     * The Table view for customer data
     */
    @FXML private TableView<Customer> customerTableView;

    /**
     * The table column for customer ID data
     */
    @FXML public TableColumn<?,?> customerId;

    /**
     * The table column for customer name data
     */
    @FXML public TableColumn<?,?> customerName;

    /**
     * The table column for customer address data
     */
    @FXML public TableColumn<?,?> customerAddress;

    /**
     * The table column for customer postal code data
     */
    @FXML public TableColumn<?,?> customerPostalCode;

    /**
     * The table column for customer division data
     */
    @FXML public TableColumn<?,?> customerDivision;

    /**
     * The table column for customer country data
     */
    @FXML public TableColumn<?,?> customerCountry;

    /**
     * The table column for customer phone data
     */
    @FXML public TableColumn<?,?> customerPhone;

    /**
     * Observable list for country options for the country combo box
     */
    ObservableList<Country> countryOptions = FXCollections.observableArrayList();

    /**
     * Observable list for division options for the division combo box
     */
    ObservableList<String> divisionOptions = FXCollections.observableArrayList();

    /**
     * Observable list for new customer objects
     */
    ObservableList<Customer> customer = FXCollections.observableArrayList();

    /**
     * variable to hold the selection of country ID in the country combo box
     */
    int countryIdResult = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Disables customer ID field
        customerIdField.setDisable(true);
        //Populates country combo box with values from the DB
        try {
            countryOptions.addAll(CountryDAO.getAllCountries());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        customerCountryComboBox.setItems(countryOptions);

        //Initializing table view columns
        customerId.setCellValueFactory((new PropertyValueFactory<>("customerId")));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        customerDivision.setCellValueFactory(new PropertyValueFactory<>("customerDivision"));
        customerCountry.setCellValueFactory(new PropertyValueFactory<>("customerCountry"));
        //Populating tableview with customer values from the DB
        try{
            customer.addAll( CustomerDAO.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        customerTableView.setItems(customer);
    }

    /**
     * Button handler for the add customer button.  Validates field entries then uses a sql statement
     * to add customer data to the database.
     *
     * @param event
     */
    public void addCustomerButtonHandler(ActionEvent event) throws Exception {

        if((customerNameField.getText().isEmpty() || (customerAddressField.getText().isEmpty()) || (customerPostalCodeField.getText().isEmpty()) ||
                (customerPhoneNumberField.getText().isEmpty()) || customerDivisionComboBox.getSelectionModel().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incomplete/Empty Fields Present");
            alert.setContentText("All fields must be completed before adding a customer.");
            Optional<ButtonType> result = alert.showAndWait();
        } else {

            String name = customerNameField.getText();
            String address = customerAddressField.getText();
            String postal = customerPostalCodeField.getText();
            String phone = customerPhoneNumberField.getText();
            String divisionName = customerDivisionComboBox.getValue();
            int divisionId = DivisionDAO.getDivisionIdfromName(divisionName);
            CustomerDAO.addCustomer(name, address, postal, phone, divisionId);
            ObservableList<Customer> customerList = CustomerDAO.getAllCustomers();
            customerTableView.setItems(customerList);
        }
    }

    /**
     * Button handler for the back button.  Returns user to the main page.
     *
     * @param action
     */
    public void customerBackButtonHandler(ActionEvent action) throws IOException {

        Parent parent = (Parent) FXMLLoader.load(this.getClass().getResource("/view/MainScreenView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)action.getSource()).getScene().getWindow();
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Button handler for the delete button.  Takes tableview selection and uses a
     * sql statement to delete it from the database.  Also deletes any associated appointments for
     * the customer.
     *
     * @param event
     * @throws Exception
     */
    public void deleteCustomerButtonHandler(ActionEvent event) throws Exception {
        Customer customer = customerTableView.getSelectionModel().getSelectedItem();
        int id = customer.getCustomerId();
        String name = customer.getCustomerName();
        CustomerDAO.deleteCustomer(id, name);
        ObservableList<Customer> customerList = CustomerDAO.getAllCustomers();
        customerTableView.setItems(customerList);
    }

    /**
     * Button handler for the get division button.  Filters the selections in the
     * division combo box based on the selection in the country combo box.
     *
     * @param event
     */
    public void getDivision(ActionEvent event) {

        Country countryId = customerCountryComboBox.getValue();
        countryIdResult = CountryDAO.getCountryIdFromName(countryId);
        System.out.println(countryIdResult);
        ObservableList<String> divisions = DivisionDAO.getDivisionNameFromCountry(countryIdResult);
        customerDivisionComboBox.setItems(divisions);
    }

    /**
     * Button handler for the save customer button.  Validates field entries then uses a sql statement
     * to update customer data in the database.
     *
     * @param event
     * @throws Exception
     */
    //Button handler for the save cust. button.  Takes entered modifications in the text fields/combo boxes
    //and uses an UPDATE sql statement to change the DB row.  Then refreshes tableview with changes.
    public void customerSaveButtonHandler(ActionEvent event) throws Exception {
        if((customerNameField.getText().isEmpty() || (customerAddressField.getText().isEmpty()) || (customerPostalCodeField.getText().isEmpty()) ||
                (customerPhoneNumberField.getText().isEmpty()) || customerDivisionComboBox.getSelectionModel().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incomplete/Empty Fields Present");
            alert.setContentText("All fields must be completed before adding a customer.");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            try{
                String custIdString = customerIdField.getText();
                int custId = Integer.parseInt(custIdString);
                String custName = customerNameField.getText();
                String custAddress = customerAddressField.getText();
                String custPostal =customerPostalCodeField.getText();
                String custPhone = customerPhoneNumberField.getText();
                String divisionName = customerDivisionComboBox.getValue();
                int divisionId = DivisionDAO.getDivisionIdfromName(divisionName);
                CustomerDAO.updateCustomer(custId, custName, custAddress, custPostal, custPhone, divisionId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ObservableList<Customer> customer = CustomerDAO.getAllCustomers();
            customerTableView.setItems(customer);
        }
    }


    /**
     * Mouse click handler for the customer Tableview.  Populates text fields with selection from
     * the customer table and disables the add customer button.
     *
     * @param event
     */
    public void customerTableViewMouseClick(MouseEvent event) throws Exception {
        if (event.getClickCount() == 1){
            customerAddButton.setDisable(true);
            ObservableList<Country> countryList = FXCollections.observableArrayList();
            ObservableList<String> divisionList = FXCollections.observableArrayList();
            Customer cust = customerTableView.getSelectionModel().getSelectedItem();
            int custId = cust.getCustomerId();
            String name = cust.getCustomerName();
            String address = cust.getCustomerAddress();
            String postal = cust.getCustomerPostalCode();
            String phone = cust.getCustomerPhone();
            String division = cust.getCustomerDivision();
            String country = cust.getCustomerCountry();
            System.out.println(division);
            int divisionId = DivisionDAO.getDivisionIdfromName(division);
            int countryId = DivisionDAO.getCountryIdFromDivisionId(divisionId);
            Country countryNew = CountryDAO.getCountryNameFromId(countryId);

            customerIdField.setText(Integer.toString(custId));
            customerNameField.setText(name);
            customerAddressField.setText(address);
            customerPostalCodeField.setText(postal);
            customerPhoneNumberField.setText(phone);
            customerCountryComboBox.setValue(countryNew);
            customerDivisionComboBox.setValue(division);

        }
    }

    /**
     * Button handler for the refresh button. Clears the text fields and reenables
     * the add customer button.
     *
     * @param event
     */
    public void customerRefreshButtonHandler(ActionEvent event) throws Exception {
        customerAddButton.setDisable(false);
        ObservableList<Customer> customerList = CustomerDAO.getAllCustomers();
        customerTableView.setItems(customerList);
        customerIdField.clear();
        customerNameField.clear();
        customerAddressField.clear();
        customerPostalCodeField.clear();
        customerPhoneNumberField.clear();
        customerCountryComboBox.getSelectionModel().isEmpty();
        customerDivisionComboBox.getSelectionModel().clearSelection();
    }
}
