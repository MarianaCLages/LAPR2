package app.ui.gui.employees;

import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClinicalChemistryTechnologistUI {

    @FXML
    private Button recordresultsbtn;
    @FXML
    private Button consultclienttestsbtn;

    private SceneController sceneController = SceneController.getInstance();

    @FXML
    private Button myReturnButtonCht;

    public void returnToMenu(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/MainScreen.fxml");
    }

    public void goToRecordResultsUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.RECORD_RESULTS_UI);
    }

    public void goToConsultClientTestsUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.CONSULT_CLIENT_TESTS_UI);
    }

}
