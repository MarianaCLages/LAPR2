package app.ui.gui.AdminMenuUIs;

import app.controller.App;
import app.ui.gui.Controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ClinicalAnalysisLaboratoryUI {

    public Button myReturnButtonLab;
    private SceneController sceneController = SceneController.getInstance();


    public void returnToAdminMenu(ActionEvent event) {
        App app = App.getInstance();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/AdministratorUI.fxml");
    }
}