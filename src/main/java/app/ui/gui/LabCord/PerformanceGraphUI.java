package app.ui.gui.LabCord;

import app.controller.SceneController;
import app.domain.shared.Constants;
import javafx.event.ActionEvent;

public class PerformanceGraphUI {

    private SceneController sceneController = SceneController.getInstance();

    public void returnToPerformanceUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.COMPANY_PERFORMANCE_UI);
    }

}
