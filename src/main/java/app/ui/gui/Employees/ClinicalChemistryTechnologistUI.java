package app.ui.gui.Employees;

import app.ui.gui.Controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ClinicalChemistryTechnologistUI {

    private SceneController sceneController = SceneController.getInstance();

    @FXML
    private Button myReturnButtonCht;

    public void returnToMenu(ActionEvent event) {
        sceneController.switchMenu(event, "/FXML/MainScreen.fxml");
    }

}
