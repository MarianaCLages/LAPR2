package app.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
      //  String css = this.getClass().getResource("/Styles/Styles.css").toExternalForm();
      //  scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Many Labs APP");
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            logout(primaryStage);
        });

        primaryStage.setResizable(false);

    }

    private void logout(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you really wish to exit? ");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage.close();
        }
    }

}