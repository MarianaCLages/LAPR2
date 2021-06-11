package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;

public class SimpleLinearRegressionController {

    private SceneController sceneController = SceneController.getInstance();

    public void returnToGenerateNHSReport(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/NHSReportUI.fxml");
    }


}
