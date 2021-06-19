package app.ui.gui.employees;

import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import app.ui.console.RecordResultsUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClinicalChemistryTechnologistUI {

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();
    private Runnable ui = sceneController.getUi();

    public void returnToMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/MainScreen.fxml");
    }

    public void goToRecordResultsUI(ActionEvent event) {
       this.ui = new RecordResultsUI();
       ui.run();
    }

    public void goToConsultClientTestsUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.CONSULT_CLIENT_TESTS_UI);
    }

}
