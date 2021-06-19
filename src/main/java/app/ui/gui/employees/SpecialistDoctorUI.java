package app.ui.gui.employees;

import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SpecialistDoctorUI {

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();

    public void returnToMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }

}
