package app.ui.gui;

import app.controller.App;
import app.controller.SceneController;
import app.domain.shared.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CreditsUI {
    @FXML
    private Label credits;
    @FXML
    private Button btnBack;


    private SceneController sceneController = SceneController.getInstance();
    private App app = sceneController.getApp();

    public void Back(ActionEvent actionEvent) {
        app.doLogout();
        sceneController.switchMenu(actionEvent, Constants.MAIN_SCREEN_UI);
    }
}
