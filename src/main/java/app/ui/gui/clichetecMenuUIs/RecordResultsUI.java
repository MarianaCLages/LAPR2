package app.ui.gui.clichetecMenuUIs;

import app.controller.App;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RecordResultsUI {

    @FXML
    private Button btnReturn;
    @FXML
    private Button btnEnter;
    @FXML
    public TextField sampleIDtxt;

    private SceneController sceneController = SceneController.getInstance();

    public void btnReturnAction(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/ClinicalChemistryTechnologistUI.fxml");
    }

    public void btnEnterAction(ActionEvent event) {
        String sampleID;

        sampleID = sampleIDtxt.getText();
    }
}
