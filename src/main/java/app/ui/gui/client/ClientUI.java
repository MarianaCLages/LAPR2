package app.ui.gui.client;

import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;

public class ClientUI {

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();
    
    public void ViewResultsMenu(ActionEvent event) {
        sceneController.switchMenu(event, Constants.VIEW_RESULTS_UI);
    }

    public void returnToMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }

}
