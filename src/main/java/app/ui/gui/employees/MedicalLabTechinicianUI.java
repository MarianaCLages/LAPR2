package app.ui.gui.employees;

import app.controller.App;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MedicalLabTechinicianUI {

    private SceneController sceneController = SceneController.getInstance();

    @FXML
    private Button myReturnButtonMlt;

    public void returnToMenu(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/MainScreen.fxml");
    }

}
