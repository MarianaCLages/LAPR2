package app.ui.gui.Controllers;

import app.controller.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CreditsController {
    public Label credits;
    public Button btnBack;
    private SceneController sceneController = SceneController.getInstance();

    public void Back(ActionEvent actionEvent) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(actionEvent, "/FXML/MainScreen.fxml");
    }
}
