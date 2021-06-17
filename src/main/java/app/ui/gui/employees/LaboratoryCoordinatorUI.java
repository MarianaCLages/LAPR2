package app.ui.gui.employees;

import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LaboratoryCoordinatorUI {

    private SceneController sceneController = SceneController.getInstance();

    @FXML
    private Button myReturnButtonLaboratoryCoordinator;

    @FXML
    private Button ImportTestsButton;

    public void ImportTestsMenu(ActionEvent event) {
        App app = sceneController.getApp();

        sceneController.switchMenu(event, Constants.Import_Test_UI);
    }

    public void returnToMenu(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }


}
