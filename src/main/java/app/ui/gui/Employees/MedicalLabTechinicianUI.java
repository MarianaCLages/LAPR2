package app.ui.gui.Employees;

import app.ui.gui.Controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MedicalLabTechinicianUI {

    private SceneController sceneController = SceneController.getInstance();

    @FXML
    private Button myReturnButtonMlt;

    public void returnToMenu(ActionEvent event) {
        sceneController.switchMenu(event, "/FXML/MainScreen.fxml");
    }

}
