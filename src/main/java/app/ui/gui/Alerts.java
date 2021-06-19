package app.ui.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Alerts {

    private Alerts() {

    }

    public static void informationAlert(int maxAttempts) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Invalid UserId and/or Password.");
        alert.setContentText("You have  " + maxAttempts + " more attempt(s).");
        alert.show();
    }

    public static void errorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText("Invalid format!");
        alert.setContentText(message);
        alert.show();
    }

    public static void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you really wish to exit? ");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

}
