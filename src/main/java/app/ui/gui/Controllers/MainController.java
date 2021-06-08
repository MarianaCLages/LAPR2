package app.ui.gui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController {

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCredits;

    private SceneController sceneController = SceneController.getInstance();

    public void login(ActionEvent e) throws IOException {
        sceneController.switchMenu(e, "/FXML/Login.fxml");
    }

    public void credits(ActionEvent actionEvent) throws IOException {
        sceneController.switchMenu(actionEvent, "/FXML/Credits.fxml");
    }
}
