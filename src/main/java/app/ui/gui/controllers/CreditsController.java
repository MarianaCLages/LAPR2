package app.ui.gui.controllers;

import app.controller.App;
import app.domain.shared.Constants;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CreditsController {
    public Label credits;
    public Button btnBack;
    private SceneController sceneController = SceneController.getInstance();

    public void Back(ActionEvent actionEvent) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(actionEvent, Constants.MAIN_SCREEN_UI);
    }
}
