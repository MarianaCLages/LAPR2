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
    @FXML
    private Button myReturnButtonCht;

    private SceneController sceneController = SceneController.getInstance();
    private App app = sceneController.getApp();

    public void returnToMenu(ActionEvent event) {
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
