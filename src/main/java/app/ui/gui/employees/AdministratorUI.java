package app.ui.gui.employees;


import app.controller.App;
import app.domain.model.Employee;
import app.domain.model.Test;
import app.domain.shared.Constants;
import app.controller.SceneController;
import app.ui.console.*;
import app.ui.gui.adminMenuUIs.EmployeeUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdministratorUI {

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();
    private Runnable ui = sceneController.getUi();

    public void returnToMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }

    public void goToTestTypeUI(ActionEvent event) {
        this.ui = new TestTypeUI();
        ui.run();
    }

    public void goToParameterCategoryUI(ActionEvent event) {
        this.ui = new ParameterCategoryUI();
        ui.run();
    }

    public void goToParameterUI(ActionEvent event) {
        this.ui = new ParameterUI();
        ui.run();
    }

    public void goToEmployeeUI(ActionEvent event) {
        this.ui = new RegisterEmployeeUI();
        ui.run();
    }

    public void goToClinicalAnalysisLaboratoryUI(ActionEvent event) {
        this.ui = new ClinicalAnalysisLabUI();
        ui.run();
    }

    public void goToGenerateNHSReportUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.NHS_REPORT_UI);
    }

}