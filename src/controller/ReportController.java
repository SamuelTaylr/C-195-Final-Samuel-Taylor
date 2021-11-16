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
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * FXML Controller class for reports view
 *
 * @author Samuel Taylor
 */
public class ReportController implements Initializable {

    /**
     * The main Appointment tableview for the first tab
     */
    @FXML public TableView<Appointment> reportAppointmentTableView;

    /**
     * The table column for appointment ID data
     */
    @FXML public TableColumn<?,?> reportAppointmentId;

    /**
     * The table column for appointment title data
     */
    @FXML public TableColumn<?,?> reportAppointmentTitle;

    /**
     * The table column for appointment description data
     */
    @FXML public TableColumn<?,?> reportAppointmentDescription;

    /**
     * The table column for appointment type data
     */
    @FXML public TableColumn<?,?> reportAppointmentType;

    /**
     * The table column for appointment location data
     */
    @FXML public TableColumn<?,?> reportAppointmentLocation;

    /**
     * The table column for appointment contact data
     */
    @FXML public TableColumn<?,?> reportAppointmentContact;

    /**
     * The table column for appointment start time data
     */
    @FXML public TableColumn<?,?> reportAppointmentStart;

    /**
     * The table column for appointment end time data
     */
    @FXML public TableColumn<?,?> reportAppointmentEnd;

    /**
     * The table column for customer ID data
     */
    @FXML public TableColumn<?,?> reportAppointmentCustomerId;

    /**
     * The table column for user ID data
     */
    @FXML public TableColumn<?,?> reportAppointmentUserId;

    /**
     * The Combo box to select the contact to filter the appointment table view
     */
    @FXML public ComboBox<String> reportAppointmentsContactBox;

    /**
     * The Combo box to select the type to filter the appointment table view
     */
    @FXML public ComboBox<String> reportAppointmentTypeComboBox;

    /**
     * The main Customer tableview for the customer tab
     */
    @FXML public TableView<Customer> reportCustomerTableView;

    /**
     * The table column for customer ID data
     */
    @FXML public TableColumn<?,?> reportCustomerId;

    /**
     * The table column for customer name data
     */
    @FXML public TableColumn<?,?> reportCustomerName;

    /**
     * The table column for customer address data
     */
    @FXML public TableColumn<?,?> reportCustomerAddress;

    /**
     * The table column for customer postal code data
     */
    @FXML public TableColumn<?,?> reportCustomerPostalCode;

    /**
     * The table column for customer division data
     */
    @FXML public TableColumn<?,?> reportCustomerDivision;

    /**
     * The table column for customer country data
     */
    @FXML public TableColumn<?,?> reportCustomerCountry;

    /**
     * The table column for customer phone data
     */
    @FXML public TableColumn<?,?> reportCustomerPhone;

    /**
     * The main Contacts tableview for the contacts tab
     */
    @FXML public TableView<Contact> reportContactTableView;

    /**
     * The table column for contact ID data
     */
    @FXML public TableColumn<?,?> reportContactId;

    /**
     * The table column for contact name data
     */
    @FXML public TableColumn<?,?> reportContactName;

    /**
     * The table column for contact email data
     */
    @FXML public TableColumn<?,?> reportContactEmail;

    /**
     * The main Appointments by month tableview for the totals tab
     */
    @FXML public TableView<Report> totalMonthTableView;

    /**
     * The table column for appointments by month data
     */
    @FXML public TableColumn<?,?> totalMonth;

    /**
     * The table column for total appointments by month data
     */
    @FXML public TableColumn<?,?> totalMonthNumber;

    /**
     * The main Appointments by type tableview for the totals tab
     */
    @FXML public TableView<Report> totalTypeTableView;

    /**
     * The table column for appointments by type data
     */
    @FXML public TableColumn<?,?> totalType;

    /**
     * The table column for total appointments by type data
     */
    @FXML public TableColumn<?,?> totalTypeNumber;
    @FXML public Button reportBackButton;
    public Button appointmentsForContactsButton;

    /**
     * Observable array list of customer objects
     */
    ObservableList<Customer> customer = FXCollections.observableArrayList();

    /**
     * Observable array list of appointment objects
     */
    ObservableList<Appointment> appointment = FXCollections.observableArrayList();

    /**
     * Observable array list of contact objects
     */
    ObservableList<Contact> contact = FXCollections.observableArrayList();

    /**
     * Observable array list of appointments sorted by month
     */
    ObservableList<Report> reportMonth = FXCollections.observableArrayList();

    /**
     * Observable array list of appointments sorted by type
     */
    ObservableList<Report> reportType = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        reportBackButton.setOnAction(event -> {
            Parent parent = null;
            try {
                parent = (Parent) FXMLLoader.load(this.getClass().getResource("/view/MainScreenView.fxml"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Scene scene = new Scene(parent);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();

        });

        /**
         * Lambda expression which functions like a button handler for the contacts button.
         * When pressed it filters the appointments tableview to display only appointments for
         * the chosen contact.
         *
         */
        appointmentsForContactsButton.setOnAction(event -> {
            String contact = reportAppointmentsContactBox.getValue();
            int contactId = 0;
            try {
                contactId = ContactDAO.getContactIdofName(contact);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                appointment = AppointmentDAO.getAllAppointmentsFromContactId(contactId);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            reportAppointmentTableView.setItems(appointment);
        });


        try {
            reportAppointmentsContactBox.setItems(ContactDAO.getContact());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        reportAppointmentTypeComboBox.setItems(AppointmentDAO.getType());


        //Populating All Appointments Tab
        reportAppointmentId.setCellValueFactory((new PropertyValueFactory<>("appointmentId")));
        reportAppointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        reportAppointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        reportAppointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        reportAppointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        reportAppointmentContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        reportAppointmentStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        reportAppointmentEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        reportAppointmentCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        reportAppointmentUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        try{
            appointment.addAll( AppointmentDAO.getAllAppointments());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        reportAppointmentTableView.setItems(appointment);

        //Populating Customers Tab
        reportCustomerId.setCellValueFactory((new PropertyValueFactory<>("customerId")));
        reportCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        reportCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        reportCustomerPostalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        reportCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        reportCustomerDivision.setCellValueFactory(new PropertyValueFactory<>("customerDivision"));
        reportCustomerCountry.setCellValueFactory(new PropertyValueFactory<>("customerCountry"));

        try{
            customer.addAll( CustomerDAO.getAllCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        reportCustomerTableView.setItems(customer);

        //Populating Users Tab
        reportContactId.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        reportContactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        reportContactEmail.setCellValueFactory(new PropertyValueFactory<>("contactEmail"));

        try{
            contact.addAll( ContactDAO.getAllContacts());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        reportContactTableView.setItems(contact);

        totalMonth.setCellValueFactory((new PropertyValueFactory<>("monthName")));
        totalMonthNumber.setCellValueFactory((new PropertyValueFactory<>("Count")));
        try {
            reportMonth.addAll(ReportDAO.reportsByMonth());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        totalMonthTableView.setItems(reportMonth);

        totalType.setCellValueFactory((new PropertyValueFactory<>("type")));
        totalTypeNumber.setCellValueFactory((new PropertyValueFactory<>("Count")));
        try {
            reportType.addAll(ReportDAO.reportsByType());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        totalTypeTableView.setItems(reportType);
    }


    /**
     * Button handler for the update type button.  Updates the appointment table view to be
     * filtered by the type selected in the combo box.
     *
     * @param event
     * @throws Exception
     */
    public void reportUpdateTypeButton(ActionEvent event) throws Exception {
        String type ="'" + reportAppointmentTypeComboBox.getValue() + "'";
        appointment = AppointmentDAO.getAllAppointmentsFromType(type);
        reportAppointmentTableView.setItems(appointment);
    }

}
