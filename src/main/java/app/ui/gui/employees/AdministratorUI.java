package app.ui.gui.employees;


import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import app.ui.console.*;
import javafx.event.ActionEvent;

public class AdministratorUI {

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();
    private Runnable ui = sceneController.getUi();

    public void returnToMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }

    public void goToTestTypeUI() {
        this.ui = new TestTypeUI();
        ui.run();
    }

    public void goToParameterCategoryUI() {
        this.ui = new ParameterCategoryUI();
        ui.run();
    }

    public void goToParameterUI() {
        this.ui = new ParameterUI();
        ui.run();
    }

    public void goToEmployeeUI() {
        this.ui = new RegisterEmployeeUI();
        ui.run();
    }

    public void goToClinicalAnalysisLaboratoryUI() {
        this.ui = new ClinicalAnalysisLabUI();
        ui.run();
    }

    public void goToGenerateNHSReportUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.NHS_REPORT_UI);
    }
}