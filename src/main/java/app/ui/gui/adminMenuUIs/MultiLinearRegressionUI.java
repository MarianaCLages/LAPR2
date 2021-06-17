package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.domain.shared.Constants;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;

public class MultiLinearRegressionUI {

    private SceneController sceneController= SceneController.getInstance();

    public void returnToGenerateNHSReport(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, Constants.NHS_REPORT_UI);
    }

}
