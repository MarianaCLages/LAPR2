package app.ui.gui.employees;


import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdministratorUI {

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();


    public void returnToMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }

    public void goToTestTypeUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.TEST_TYPE_UI);
    }

    public void goToParameterCategoryUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.PARAMETER_CATEGORY_UI);
    }

    public void goToParameterUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.PARAMETER_UI);
    }

    public void goToEmployeeUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.EMPLOYEE_UI);
    }

    public void goToClinicalAnalysisLaboratoryUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.CLINICAL_ANALYSIS_LABORATORY_UI);
    }

    public void goToGenerateNHSReportUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.NHS_REPORT_UI);
    }

}