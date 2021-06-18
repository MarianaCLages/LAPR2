package app.ui.gui;

import app.controller.SceneController;
import app.domain.shared.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


public class MainUI {

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCredits;

    private SceneController sceneController = SceneController.getInstance();

    public void login(ActionEvent e) {
        sceneController.switchMenu(e, Constants.LOGIN_CONTROLLER);
    }

    public void credits(ActionEvent actionEvent) {
        sceneController.switchMenu(actionEvent, Constants.CREDITS_UI);
    }
}
