package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import utilities.Alerts;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class for main screen view
 *
 * @author Samuel Taylor
 */
public class MainScreenController implements Initializable {
    public Button mainScreenAppointmentsButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Button handler for the appointments button, loads the appointments view.
     *
     * @param action
     * @throws IOException
     */
    public void mainScreenAppointmentsButtonHandler(ActionEvent action) throws IOException {

        Parent parent = (Parent) FXMLLoader.load(this.getClass().getResource("/view/AppointmentView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)action.getSource()).getScene().getWindow();
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Button handler for the customers button, loads the customers view.
     *
     * @param action
     * @throws IOException
     */
    public void mainScreenCustomersButtonHandler(ActionEvent action) throws IOException {

        Parent parent = (Parent) FXMLLoader.load(this.getClass().getResource("/view/CustomerView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)action.getSource()).getScene().getWindow();
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Button handler for the reports button, loads the reports view.
     *
     * @param action
     * @throws IOException
     */
    public void mainScreenReportsButtonHandler(ActionEvent action) throws IOException {

        Parent parent = (Parent) FXMLLoader.load(this.getClass().getResource("/view/ReportView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)action.getSource()).getScene().getWindow();
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Button handler for the logout button, allows the user to close the program.
     *
     * @param action
     */
    public void mainScreenLogoutButtonHandler(ActionEvent action) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure you want to exit the program?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {

        }
    }
}
