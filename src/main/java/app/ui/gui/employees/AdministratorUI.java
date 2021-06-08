package app.ui.gui.employees;


import app.controller.App;
import app.ui.gui.controllers.SceneController;
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
        sceneController.switchMenu(event, "/FXML/MainScreen.fxml");
    }

    public void goToTestTypeUI(ActionEvent event) {
        sceneController.switchMenu(event, "/FXML/TestTypeUI.fxml");
    }

    public void goToParameterCategoryUI(ActionEvent event) {
        sceneController.switchMenu(event, "/FXML/ParameterCategoryUI.fxml");
    }

    public void goToParameterUI(ActionEvent event) {
        sceneController.switchMenu(event, "/FXML/ParameterUI.fxml");
    }

    public void goToEmployeeUI(ActionEvent event) {
        sceneController.switchMenu(event, "/FXML/EmployeeUI.fxml");
    }

    public void goToClinicalAnalysisLaboratoryUI(ActionEvent event) {
        sceneController.switchMenu(event, "/FXML/ClinicalAnalysisLaboratoryUI.fxml");
    }
}