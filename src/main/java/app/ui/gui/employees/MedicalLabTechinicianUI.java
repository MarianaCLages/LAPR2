package app.ui.gui.employees;

import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MedicalLabTechinicianUI {

    private SceneController sceneController = SceneController.getInstance();
    private App app = sceneController.getApp();

    @FXML
    private Button myReturnButtonMlt;

    public void returnToMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }

}
