package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ParameterUI {

    public Button myReturnButtonParameter;
    private SceneController sceneController = SceneController.getInstance();


    public void returnToAdminMenu(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/AdministratorUI.fxml");
    }

}
