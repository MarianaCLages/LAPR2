package app.ui.gui;

import app.controller.App;
import app.controller.SceneController;
import app.domain.shared.Constants;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CreditsUI {
    public Label credits;
    public Button btnBack;
    private SceneController sceneController = SceneController.getInstance();

    public void Back(ActionEvent actionEvent) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(actionEvent, Constants.MAIN_SCREEN_UI);
    }
}
