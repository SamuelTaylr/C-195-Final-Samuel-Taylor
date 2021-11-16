package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.DBConnection;

/**
 * The main class of the program.
 *
 * @author Samuel Taylor
 */
public class Main extends Application {

    /**
     * Loads the login view.
     *
     * @param primaryStage  the stage to be initialized
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreenView.fxml"));
        primaryStage.setTitle("C-195 Scheduling App");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    /**
     * Loads arguments and initializes then finally closes the database connection when the program is closed.
     *
     * @param args
     */
    public static void main(String[] args) {

        DBConnection.startConnection();
        launch(args);
        DBConnection.closeConnection();
    }
}
