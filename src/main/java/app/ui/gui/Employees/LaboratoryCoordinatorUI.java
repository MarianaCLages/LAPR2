package app.ui.gui.Employees;

import app.controller.App;
import app.ui.gui.Controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LaboratoryCoordinatorUI {

    private SceneController sceneController = SceneController.getInstance();

    @FXML
    private Button myReturnButtonLaboratoryCoordinator;

    public void returnToMenu(ActionEvent event) {
        App app = App.getInstance();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/MainScreen.fxml");
    }


}
