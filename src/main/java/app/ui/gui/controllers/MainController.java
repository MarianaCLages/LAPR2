package app.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
