package app.ui.gui.client;

import app.controller.App;
import app.domain.shared.Constants;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClientUI {


    private SceneController sceneController = SceneController.getInstance();

    @FXML
    private Button myReturnButtonClient;

    public void returnToMenu(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }

}
