package app.ui.gui.employees;


import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdministratorUI {

    @FXML
    private Button myReturnButton;
    @FXML
    private Button myParameterCategoryButton;
    @FXML
    private Button myParameterButton;
    @FXML
    private Button myTestTypeButton;
    @FXML
    private Button myClinicalButton;
    @FXML
    private Button myEmployeeButton;


    private SceneController sceneController = SceneController.getInstance();


    public void returnToMenu(ActionEvent event) {
        App app = sceneController.getApp();
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

    public void goToGenerateNHSReportUI(ActionEvent event){
        sceneController.switchMenu(event,Constants.NHS_REPORT_UI);
    }

}