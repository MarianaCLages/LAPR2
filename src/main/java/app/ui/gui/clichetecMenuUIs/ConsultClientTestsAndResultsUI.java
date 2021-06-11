package app.ui.gui.clichetecMenuUIs;

import app.controller.App;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConsultClientTestsAndResultsUI {

    @FXML
    private Button returnbtn;

    private SceneController sceneController = SceneController.getInstance();

    public void returnToCCTMenu(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/ClinicalChemistryTechnologistUI.fxml");
    }
}
