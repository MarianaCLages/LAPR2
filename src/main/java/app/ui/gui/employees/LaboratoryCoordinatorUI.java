package app.ui.gui.employees;

import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;

public class LaboratoryCoordinatorUI {

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();

    public void ImportTestsMenu(ActionEvent event) {
        sceneController.switchMenu(event, Constants.IMPORT_TEST_UI);
    }

    public void returnToMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }


    public void performanceMenu(ActionEvent actionEvent) {
        app.doLogout();
        sceneController.switchMenu(actionEvent, Constants.COMPANY_PERFORMANCE_UI);
    }
}
