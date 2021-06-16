package app.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

       /* try {
            primaryStage.getIcons().add(new Image("Logo1.jpg"));
        } catch (Exception error){
            System.out.println("NAO DA");
        }*/

        primaryStage.setScene(scene);
        primaryStage.setTitle("Many Labs APP");
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            Alerts.logout(primaryStage);
        });

        primaryStage.setResizable(false);

    }

}