package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.AppointmentDAO;
import model.UserDAO;
import utilities.Alerts;
import utilities.DBConnection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

import static utilities.Alerts.loginSuccess;

/**
 * FXML Controller class for the Login View
 *
 * @author Samuel Taylor
 */
public class LoginScreenController implements Initializable {

    /**
     * The button to login
     */
    public Button loginButton;

    /**
     * The label to display location zone id
     */
    public Label languageNotification;

    /**
     * The text field for the password
     */
    public PasswordField passwordField;

    /**
     * The text field for the username
     */
    public TextField usernameField;

    /**
     * The label to display username in the users locale
     */
    @FXML private Label usernameLabel;

    /**
     * The label to display password in the users locale
     */
    @FXML private Label passwordLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String location = getUserLocationInfo();
        languageNotification.setText(location);

        Locale locale = Locale.getDefault();
        resourceBundle = ResourceBundle.getBundle("Languages/rb", locale);
        usernameLabel.setText(resourceBundle.getString("username"));
        passwordLabel.setText(resourceBundle.getString("password"));
        loginButton.setText(resourceBundle.getString("Login"));

    }

    /**
     * Button handler for the login button.  Takes entered text and compares it the username
     * and password in the database using a sql statement.  Also sets the alerts in the users default
     * locale when incorrect credentials are entered.  Also calls the method to check if there are
     * any appointments within 15 minutes.
     *
     * @param action
     * @throws IOException
     * @throws SQLException
     */
    public void loginButtonHandler(ActionEvent action) throws IOException, SQLException {

        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean valid = UserDAO.loginProcedure(username, password);
        Locale locale = Locale.getDefault();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alert2 = new Alert(Alert.AlertType.ERROR);

        if (UserDAO.loginProcedure(username, password) && (!usernameField.getText().isEmpty()) && (!passwordField.getText().isEmpty())) {

            AppointmentDAO.getAllAppointmentsIn15Minutes();

            logLoginAttempt(true);

            Parent parent = (Parent) FXMLLoader.load(this.getClass().getResource("/view/MainScreenView.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) action.getSource()).getScene().getWindow();
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        } else {
            logLoginAttempt(false);
            if (locale.getLanguage().equals("en")) {
                alert.setTitle("Login Error");
                alert.setContentText("Invalid username or password");
                Optional<ButtonType> result = alert.showAndWait();
            } else if (locale.getLanguage().equals("fr")){

                alert2.setTitle("Erreur d'identification");
                alert2.setContentText("Nom d'utilisateur ou mot de passe invalide");
                Optional<ButtonType> result = alert.showAndWait();}
            }

    }

    /**
     * Method to log any successful or unsuccessful login attempts with the username and timestamp.
     *
     * @param success
     */
    private void logLoginAttempt(boolean success) {
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        final String time = formatter.format(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        final String username = usernameField.getText();
        try {
            final FileWriter fw = new FileWriter("login_activity.txt", true);
            final BufferedWriter bw = new BufferedWriter(fw);
            bw.write("time: " + time + "\t");
            bw.write("username: " + username + "\t");
            bw.write("success: " + success + "\t");
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            System.out.println("Failed to log invalid login attempt:");
            System.out.println(ex.getMessage());
        }
    }


    /**
     * Method that checks for the users default locale and sets the label
     * on the login form to the users Zone ID
     */
    public static String getUserLocationInfo() {

        Locale currentLocale = Locale.getDefault();
        String country = currentLocale.getDisplayCountry();

        TimeZone timeZone = TimeZone.getDefault();
        String zone = timeZone.getDisplayName(true, 0);

        String location = country + " (" + zone + ")";

        return location;
    }
}