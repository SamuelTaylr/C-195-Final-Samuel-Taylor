package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import utilities.Alerts;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * FXML Controller class for appointment view
 *
 * @author Samuel Taylor
 */
public class AppointmentController implements Initializable {


    /**
     * The button to add appointment to database
     */
    @FXML public Button addAppointmentButton;

    /**
     * The button to delete an appointment from the database
     */
    @FXML public Button deleteAppointmentButton;

    /**
     * The button to return to the main page
     */
    @FXML public Button appointmentsBackButton;

    /**
     * The table view that display appointment data
     */
    @FXML public TableView<Appointment> appointmentTableview;

    /**
     * The column that displays appointment ID data
     */
    @FXML public TableColumn<?, ?> appointmentId;

    /**
     * The column that displays appointment title data
     */
    @FXML public TableColumn<?, ?> appointmentTitle;

    /**
     * The column that displays appointment description data
     */
    @FXML public TableColumn<?, ?> appointmentDescription;

    /**
     * The column that displays appointment type data
     */
    @FXML public TableColumn<?, ?> appointmentType;

    /**
     * The column that displays appointment location data
     */
    @FXML public TableColumn<?, ?> appointmentLocation;

    /**
     * The column that displays appointment contact data
     */
    @FXML public TableColumn<?, ?> appointmentContact;

    /**
     * The column that displays appointment start time data
     */
    @FXML public TableColumn<?, ?> appointmentStart;

    /**
     * The column that displays appointment end time data
     */
    @FXML public TableColumn<?, ?> appointmentEnd;

    /**
     * The column that displays appointment customer ID data
     */
    @FXML public TableColumn<?, ?> appointmentCustomerId;

    /**
     * The column that displays appointment user ID data
     */
    @FXML public TableColumn<?, ?> appointmentUserId;

    /**
     * The radio button that controls the month view of the appointment table view
     */
    @FXML public RadioButton appointmentMonthViewRadioButton;

    /**
     * The radio button that controls the week view of the appointment table view
     */
    @FXML public RadioButton appointmentWeekViewRadioButton;

    /**
     * The radio button that controls the view all of the appointment table view
     */
    @FXML public RadioButton appointmentAllViewRadioButton;

    /**
     * The text field for appointment ID data
     */
    @FXML public TextField appointmentIdField;

    /**
     * The text field for appointment title data
     */
    @FXML public TextField appointmentTitleField;

    /**
     * The text field for appointment description data
     */
    @FXML public TextField appointmentDescriptionField;

    /**
     * The text field for appointment location data
     */
    @FXML public TextField appointmentLocationField;

    /**
     * The text field for appointment type data
     */
    @FXML public TextField appointmentTypeField;

    /**
     * The date picker box for the start/end date data
     */
    @FXML public DatePicker startDateBox;

    /**
     * The combo box for appointment contact data
     */
    @FXML public ComboBox<String> appointmentsContactIdBox;

    /**
     * The combo box for appointment customer data
     */
    @FXML public ComboBox<String> appointmentCustomerIdBox;

    /**
     * The combo box for appointment user data
     */
    @FXML public ComboBox<String> userIdBox;

    /**
     * The combo box for appointment start time hours data
     */
    @FXML public ComboBox<Integer> startTimeBoxHour;

    /**
     * The combo box for appointment start time minutes data
     */
    @FXML public ComboBox<Integer> endTimeBoxHour;

    /**
     * The combo box for appointment end time hours data
     */
    @FXML public ComboBox<Integer> startTimeBoxMinute;

    /**
     * The combo box for appointment end time minutes data
     */
    @FXML public ComboBox<Integer> endTimeBoxMinute;

    /**
     * The observable array list for appointment data
     */
    ObservableList<Appointment> appointment = FXCollections.observableArrayList();

    /**
     * The observable array list for appointment start hour values in the combo box
     */
    ObservableList<Integer> startHour = FXCollections.observableArrayList( 0,1,2,3,4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);

    /**
     * The observable array list for appointment end hour values in the combo box
     */
    ObservableList<Integer> endHour = FXCollections.observableArrayList( 0,1,2,3,4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);

    /**
     * The observable array list for appointment start minute values in the combo box
     */
    ObservableList<Integer> startMinute = FXCollections.observableArrayList(00, 15, 30, 45);

    /**
     * The observable array list for appointment end minute values in the combo box
     */
    ObservableList<Integer> endMinute = FXCollections.observableArrayList(00, 15, 30, 45);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Disables ID field and populates combo boxes, sets default radio button
        appointmentIdField.setDisable(true);
        appointmentCustomerIdBox.setItems(CustomerDAO.getCustomer());
        appointmentsContactIdBox.setItems(ContactDAO.getContact());
        userIdBox.setItems(UserDAO.getUser());
        startTimeBoxHour.setItems(startHour);
        startTimeBoxMinute.setItems(startMinute);
        endTimeBoxHour.setItems(endHour);
        endTimeBoxMinute.setItems(endMinute);
        startDateBox.setValue(LocalDate.now());
        final ToggleGroup toggle = new ToggleGroup();
        appointmentAllViewRadioButton.setSelected(true);
        appointmentAllViewRadioButton.setToggleGroup(toggle);
        appointmentMonthViewRadioButton.setToggleGroup(toggle);
        appointmentWeekViewRadioButton.setToggleGroup(toggle);

        //Listener for week view radio button, changes table view to this weeks appts.
        appointmentWeekViewRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>()  {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasSelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    ObservableList<Appointment> appointmentList;
                    try {
                        appointmentList = AppointmentDAO.getWeekAppointments();
                        appointmentTableview.setItems(appointmentList);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //Listener for month view radio button, changes table view to this months appts.
        appointmentMonthViewRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasSelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    ObservableList<Appointment> appointmentList;
                    try {
                        appointmentList = AppointmentDAO.getMonthAppointments();
                        appointmentTableview.setItems(appointmentList);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
        //Listener for all view table view, is selected by default.
        appointmentAllViewRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasSelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    ObservableList<Appointment> appointmentList;
                    try {
                        appointmentList = AppointmentDAO.getAllAppointments();
                        appointmentTableview.setItems(appointmentList);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
        //Populates appt. table view with values from the DB
        appointmentId.setCellValueFactory((new PropertyValueFactory<>("appointmentId")));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        appointmentStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointmentEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        try {
            appointment.addAll(AppointmentDAO.getAllAppointments());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appointmentTableview.setItems(appointment);


        /**
         * Lambda expression which functions like a button handler for the delete appointment button.
         * When pressed it creates an appointment object of the selection in the tableview and passes that
         * information to the delete appointment method in the appoint DAO model.
         *
         */
        deleteAppointmentButton.setOnAction(event -> {
            Appointment appointment = appointmentTableview.getSelectionModel().getSelectedItem();
            int id = appointment.getAppointmentId();
            String type = appointment.getType();
            try {
                AppointmentDAO.deleteAppointment(id, type);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            ObservableList<Appointment> appointmentList = null;
            try {
                appointmentList = AppointmentDAO.getAllAppointments();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            appointmentTableview.setItems(appointmentList);
        });
    }


    /**
     * Handles the add appointment button press. Validates the input data and adds the new appointment
     * data to the database.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    void addAppointmentButtonHandler(ActionEvent event) throws Exception {
        if ((appointmentTitleField.getText().isEmpty()) || (appointmentDescriptionField.getText().isEmpty()) || (appointmentLocationField.getText().isEmpty()) || (appointmentTypeField.getText().isEmpty()
                && (startTimeBoxMinute.getSelectionModel().isEmpty()) || (startTimeBoxHour.getSelectionModel().isEmpty()) || (endTimeBoxMinute.getSelectionModel().isEmpty()) || (endTimeBoxHour.getSelectionModel().isEmpty()) &&
                (appointmentsContactIdBox.getSelectionModel().isEmpty()) || (appointmentCustomerIdBox.getSelectionModel().isEmpty()) || (userIdBox.getSelectionModel().isEmpty()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incomplete/Empty Fields Present");
            alert.setContentText("All fields must be completed before adding an appointment.");
            Optional<ButtonType> result = alert.showAndWait();
        } else {

            try {
                System.out.println("Method enterered");
                String title = appointmentTitleField.getText();
                String description = appointmentDescriptionField.getText();
                String location = appointmentLocationField.getText();
                String type = appointmentTypeField.getText();
                Integer startNeedFormatHour = startTimeBoxHour.getValue();
                Integer startNeedFormatMinute = startTimeBoxMinute.getValue();
                Integer endNeedFormatHour = endTimeBoxHour.getValue();
                Integer endNeedFormatMinute = endTimeBoxMinute.getValue();
                String contact = appointmentsContactIdBox.getValue();
                String customer = appointmentCustomerIdBox.getValue();
                String user = userIdBox.getValue();
                LocalTime localTimeStart = LocalTime.of(startNeedFormatHour, startNeedFormatMinute);
                LocalTime localTimeEnd = LocalTime.of(endNeedFormatHour, endNeedFormatMinute);
                LocalDate startDate = startDateBox.getValue();
                LocalDateTime start = LocalDateTime.of(startDate, localTimeStart);
                LocalDateTime end = LocalDateTime.of(startDate, localTimeEnd);
                ZonedDateTime zonedDateTimeStart = start.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC);
                ZonedDateTime zonedDateTimeEnd = end.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC);
                LocalDateTime startAdd = zonedDateTimeStart.toLocalDateTime();
                LocalDateTime endAdd = zonedDateTimeEnd.toLocalDateTime();
                int contactId = ContactDAO.getContactIdofName(contact);
                int customerId = CustomerDAO.getCustomerIdofName(customer);
                int userId = UserDAO.getUserIdofName(user);
                boolean conflict = AppointmentDAO.appointmentOverlapCheck(customerId, startAdd, endAdd);
                boolean businessHoursOk = withinBusinessHours(start, end);
                System.out.println(conflict);
                System.out.println(businessHoursOk);

                if ((conflict == false) && (businessHoursOk == true)) {
                    AppointmentDAO.addAppointment(title, description, location, type, start, end, customerId, userId, contactId);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Appointment Times");
                    alert.setContentText("Customers can't have overlapping appointments, or appointments outside of business hours (0800-2200 EST");
                    Optional<ButtonType> result = alert.showAndWait();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ObservableList<Appointment> appointmentList;
            appointmentList = AppointmentDAO.getAllAppointments();
            appointmentTableview.setItems(appointmentList);


        }
    }

        /**
        * Handles the back button press. Returns the user to the main menu.
        *
        * @param action
        * @throws IOException
        */
        public void appointmentsBackButtonHandler (ActionEvent action) throws IOException {

            Parent parent = (Parent) FXMLLoader.load(this.getClass().getResource("/view/MainScreenView.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) action.getSource()).getScene().getWindow();
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }


    /**
     * Handles the delete appointment button press.  Deletes the selected appointment in the tableview from the DB.
     * Generates an alert stating the appointment and appointment type deleted.
     *
     * @param action
     * @throws Exception
     */


    /**
     * Handles the mouse click on the appointment table view.  Populates text fields/combo boxes with
     * data from the tableview.
     *
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
        public void mouseClickOnTableView (MouseEvent event) throws SQLException, ClassNotFoundException {
            if (event.getClickCount() == 1) {
                addAppointmentButton.setDisable(true);
                //Initializing list variables
                Appointment appt = appointmentTableview.getSelectionModel().getSelectedItem();
                ObservableList<Integer> startHour = FXCollections.observableArrayList();
                ObservableList<Integer> endHour = FXCollections.observableArrayList();
                ObservableList<Integer> startMinute = FXCollections.observableArrayList();
                ObservableList<Integer> endMinute = FXCollections.observableArrayList();
                ObservableList<Integer> startHours = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
                ObservableList<Integer> endHours = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
                ObservableList<Integer> startMinutes = FXCollections.observableArrayList(00, 15, 30, 45);
                ObservableList<Integer> endMinutes = FXCollections.observableArrayList(00, 15, 30, 45);
                ObservableList<String> contactName = FXCollections.observableArrayList();
                //getting selected values from tableview
                int userId = appt.getUserId();
                int apptId = appt.getAppointmentId();
                int customerId = appt.getCustomerId();
                String title = appt.getTitle();
                String description = appt.getDescription();
                String location = appt.getLocation();
                String type = appt.getType();
                String contactId = appt.getContact();
                String user = UserDAO.getUserNameofId(userId);
                String customer = CustomerDAO.getCustomerNameofId(customerId);
                //Converting LocalDateTime to EST then converting back into a LocalDateTime
                LocalDateTime start = appt.getStart();
                LocalDateTime end = appt.getEnd();
                //ZonedDateTime zonedDateTimeStart = start.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
                //ZonedDateTime zonedDateTimeEnd = start.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
                ZonedDateTime zonedDateTimeStart = start.atZone(ZoneId.systemDefault());
                ZonedDateTime zonedDateTimeEnd = end.atZone(ZoneId.systemDefault());
                LocalDateTime startFinal = zonedDateTimeStart.toLocalDateTime();
                LocalDateTime endFinal = zonedDateTimeEnd.toLocalDateTime();
                //Changing LocalDateTime into a LocalDate and LocalTime object
                LocalDate date = startFinal.toLocalDate();
                LocalTime timeStart = startFinal.toLocalTime();
                LocalTime timeEnd = endFinal.toLocalTime();
                //Changing LocalTime into separate hour and minute variables
                Integer startHourFormat = timeStart.getHour();
                Integer startMinuteFormat = timeStart.getMinute();
                Integer endHourFormat = timeEnd.getHour();
                Integer endMinuteFormat = timeEnd.getMinute();
                //Adding hour/minute variables to their respective lists
                startHour.add(startHourFormat);
                startMinute.add(startMinuteFormat);
                endHour.add(endHourFormat);
                endMinute.add(endMinuteFormat);
                //Populating textfields and combo boxes with values from selection in table
                appointmentIdField.setText(Integer.toString(apptId));
                appointmentTitleField.setText(title);
                appointmentDescriptionField.setText(description);
                appointmentLocationField.setText(location);
                appointmentTypeField.setText(type);
                appointmentsContactIdBox.setValue(contactId);
                userIdBox.setValue(user);
                appointmentCustomerIdBox.setValue(customer);
                startTimeBoxHour.setItems(startHours);
                startTimeBoxHour.setValue(startHourFormat);
                startTimeBoxMinute.setItems(startMinutes);
                startTimeBoxMinute.setValue(startMinuteFormat);
                endTimeBoxHour.setItems(endHours);
                endTimeBoxHour.setValue(endHourFormat);
                endTimeBoxMinute.setItems(endMinutes);
                endTimeBoxMinute.setValue(endMinuteFormat);
                startDateBox.setValue(date);
            }
        }

    /**
     * Handles the save button press.  Validates text fields/combo boxes and uses an update SQL statement to update the
     * data in the database.  Also verifies that customers dont have conflicting appointments.
     *
     * @param event
     * @throws Exception
     */
        public void saveAppointmentButtonHandler (ActionEvent event) throws Exception {
            if ((appointmentTitleField.getText().isEmpty()) || (appointmentDescriptionField.getText().isEmpty()) || (appointmentLocationField.getText().isEmpty()) || (appointmentTypeField.getText().isEmpty()
                    && (startTimeBoxMinute.getSelectionModel().isEmpty()) || (startTimeBoxHour.getSelectionModel().isEmpty()) || (endTimeBoxMinute.getSelectionModel().isEmpty()) || (endTimeBoxHour.getSelectionModel().isEmpty()) &&
                    (appointmentsContactIdBox.getSelectionModel().isEmpty()) || (appointmentCustomerIdBox.getSelectionModel().isEmpty()) || (userIdBox.getSelectionModel().isEmpty()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incomplete/Empty Fields Present");
                alert.setContentText("All fields must be completed before saving any changes.");
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                try {
                    //Taking values entered into Text fields and combo boxes and making new variables with them
                    String appointmentId = appointmentIdField.getText();
                    String title = appointmentTitleField.getText();
                    String description = appointmentDescriptionField.getText();
                    String location = appointmentLocationField.getText();
                    String type = appointmentTypeField.getText();
                    String contact = appointmentsContactIdBox.getValue();
                    String customer = appointmentCustomerIdBox.getValue();
                    String user = userIdBox.getValue();
                    //Starting process of changing times into UTC before insertion into DB
                    Integer startNeedFormatHour = startTimeBoxHour.getValue();
                    Integer startNeedFormatMinute = startTimeBoxMinute.getValue();
                    Integer endNeedFormatHour = endTimeBoxHour.getValue();
                    Integer endNeedFormatMinute = endTimeBoxMinute.getValue();
                    LocalTime localTimeStart = LocalTime.of(startNeedFormatHour, startNeedFormatMinute);
                    LocalTime localTimeEnd = LocalTime.of(endNeedFormatHour, endNeedFormatMinute);
                    LocalDate startDate = startDateBox.getValue();
                    LocalDateTime start = LocalDateTime.of(startDate, localTimeStart);
                    LocalDateTime end = LocalDateTime.of(startDate, localTimeEnd);
                    ZonedDateTime startSystem = start.atZone(ZoneId.systemDefault());
                    ZonedDateTime endSystem = end.atZone(ZoneId.systemDefault());
                    startSystem.withZoneSameInstant(ZoneOffset.UTC);
                    endSystem.withZoneSameInstant(ZoneOffset.UTC);
                    LocalDateTime startAdd = startSystem.toLocalDateTime();
                    LocalDateTime endAdd = endSystem.toLocalDateTime();
                    //Getting ID's from Name's in combo boxes for Contact, customer and user.
                    int contactId = ContactDAO.getContactIdofName(contact);
                    int customerId = CustomerDAO.getCustomerIdofName(customer);
                    int userId = UserDAO.getUserIdofName(user);
                    boolean conflict = AppointmentDAO.appointmentOverlapCheck(customerId, startAdd, endAdd);
                    boolean businessHoursOk = withinBusinessHours(startAdd, endAdd);
                    System.out.println(conflict);
                    System.out.println(businessHoursOk);

                    if ((conflict == false) && (businessHoursOk == true)) {
                        AppointmentDAO.updateAppointment(appointmentId, title, description, location, type, startAdd, endAdd, customerId, userId, contactId);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Appointment Times");
                        alert.setContentText("Appointments must be within business hours and customers can't have overlapping appointments.");
                        Optional<ButtonType> result = alert.showAndWait();
                    }

                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                //Refreshing list with updated appointment
                ObservableList<Appointment> appointmentList = AppointmentDAO.getAllAppointments();
                appointmentTableview.setItems(appointmentList);
            }
        }

    /**
     * Handles the refresh button press.  Clears text fields and combo boxes and repopulates the time boxes with appropriate
     * times.  Un-disables the add appointment button.
     *
     * @param event
     * @throws Exception
     */
        public void refreshButtonHandler (ActionEvent event) throws Exception {
            //Creating arrays of numbers for Time combo boxes
            ObservableList<Integer> startHours = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
            ObservableList<Integer> endHours = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
            ObservableList<Integer> startMinutes = FXCollections.observableArrayList(00, 15, 30, 45);
            ObservableList<Integer> endMinutes = FXCollections.observableArrayList(00, 15, 30, 45);
            //Renabling add button and clearing all Text fields
            addAppointmentButton.setDisable(false);
            appointmentIdField.clear();
            appointmentTitleField.clear();
            appointmentDescriptionField.clear();
            appointmentLocationField.clear();
            appointmentTypeField.clear();
            appointmentsContactIdBox.getSelectionModel().clearSelection();
            userIdBox.getSelectionModel().clearSelection();
            appointmentCustomerIdBox.getSelectionModel().clearSelection();
            startTimeBoxHour.setItems(startHours);
            startTimeBoxHour.getSelectionModel().clearSelection();
            startTimeBoxMinute.setItems(startMinutes);
            startTimeBoxMinute.getSelectionModel().clearSelection();
            endTimeBoxHour.setItems(endHours);
            endTimeBoxHour.getSelectionModel().clearSelection();
            endTimeBoxMinute.setItems(endMinutes);
            endTimeBoxMinute.getSelectionModel().clearSelection();
            ObservableList<Appointment> appointmentList;
            appointmentList = AppointmentDAO.getAllAppointments();
            appointmentTableview.setItems(appointmentList);

        }

    /**
     * Method to check if the added appointment time falls within business hours in EST.  Converts the time to EST then compares
     * the times to business hours.
     *
     * @param startPreConversion a LocalDateTime from the combo boxes, needs conversion to EST
     * @param endPreConversion a LocalDateTime from the combo boxes, needs conversion to EST
     * @return boolean true if appt. times fall within business hours
     */
        public boolean withinBusinessHours (LocalDateTime startPreConversion, LocalDateTime endPreConversion){

            ZonedDateTime startDateTime = startPreConversion.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
            ZonedDateTime endDateTime = endPreConversion.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
            LocalDateTime start = startDateTime.toLocalDateTime();
            LocalDateTime end = endDateTime.toLocalDateTime();
            int startHourEst = start.getHour();
            int startMinuteEst = start.getMinute();
            int endHourEst = end.getHour();
            int endMinuteEst = end.getMinute();
            System.out.println(startHourEst);

            if (startHourEst >= 8 && startHourEst < 22 && endHourEst >= 8 && endHourEst <= 22) {
                if (endHourEst == 22 && endMinuteEst > 0) {
                    return false;
                }
                return true;
            }
            return false;
        }


}
