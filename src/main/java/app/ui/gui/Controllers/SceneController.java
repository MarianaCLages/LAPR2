package app.ui.gui.Controllers;

import app.controller.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneController {

    private static SceneController singleton = null;

    public static SceneController getInstance() {
        if (singleton == null) {
            synchronized (App.class) {
                singleton = new SceneController();
            }
        }
        return singleton;
    }

     public void switchMenu(ActionEvent event, String path) throws IOException {

         FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
         Parent root = loader.load();

         Scene scene = new Scene(root);
         //String css = this.getClass().getResource("/Styles/Styles.css").toExternalForm();
         //scene.getStylesheets().add(css);
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();

     }

}
