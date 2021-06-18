package app.ui.gui.client;

import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClientUI {


    private SceneController sceneController = SceneController.getInstance();
    private App app = sceneController.getApp();

    @FXML
    private Button myReturnButtonClient;

    @FXML
    private Button ResultsButton;

    public void ViewResultsMenu(ActionEvent event) {
        sceneController.switchMenu(event, Constants.VIEW_RESULTS_UI);
    }

    public void returnToMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }

}
