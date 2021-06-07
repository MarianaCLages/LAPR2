package app.ui.gui.Employees;

import app.ui.gui.Controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SpecialistDoctorUI {

    private SceneController sceneController=SceneController.getInstance();

    @FXML
    private Button myReturnButtonSpecialistDoctor;

    public void returnToMenu(ActionEvent event) {
        try {
            sceneController.switchMenu(event, "/FXML/MainScreen.fxml");
        } catch (IOException e) {

        }
    }

}
