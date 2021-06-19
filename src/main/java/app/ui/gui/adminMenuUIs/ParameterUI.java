package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ParameterUI {

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();

    public void returnToAdminMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.ADMINISTRATOR_UI);
    }

}
