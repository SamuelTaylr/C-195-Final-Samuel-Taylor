package utilities;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Locale;
import java.util.Optional;

public class Alerts {

    public static boolean confirmDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText("Confirm");
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public static void loginSuccess() {
        Locale currentLocale = Locale.getDefault();
        Alert alert = new Alert(Alert.AlertType.NONE);
        Alert alert2 = new Alert(Alert.AlertType.NONE);

        if(currentLocale.getLanguage().equals("en")) {
            Alert alert3 = new Alert(Alert.AlertType.ERROR, "Invalid username or password", ButtonType.OK);
            alert3.setTitle("Login Error");
            alert.showAndWait().filter(response -> response == ButtonType.OK);

        }

        else if(currentLocale.getLanguage().equals("fr")) {

            alert2.setTitle("Erreur d'identification");
            alert2.setContentText("Nom d'utilisateur ou mot de passe invalide");
            Optional<ButtonType> result = alert.showAndWait();
        }

    }
}