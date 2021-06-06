package app.ui.gui.Employees;


import app.ui.gui.Controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdministratorUI {


    public Button myReturnButton;
    public Button myParameterCategoryButton;
    public Button myParameterButton;
    public Button myTestTypeButton;
    public Button myClinicalButton;
    public Button myEmployeeButton;
    private SceneController sceneController = SceneController.getInstance();


    public void returnToMenu(ActionEvent event) {
        try {
            sceneController.switchMenu(event, "/FXML/MainScreen.fxml");
        } catch (IOException e) {

        }
    }

    public void goToTestTypeUI(ActionEvent event) {
        try {
            sceneController.switchMenu(event, "/FXML/TestTypeUI.fxml");
        } catch (IOException e) {

        }
    }

    public void goToParameterCategoryUI(ActionEvent event) {
        try {
            sceneController.switchMenu(event, "/FXML/ParameterCategoryUI.fxml");
        } catch (IOException e) {

        }
    }

    public void goToParameterUI(ActionEvent event) {
        try {
            sceneController.switchMenu(event, "/FXML/ParameterUI.fxml");
        } catch (IOException e) {

        }
    }

    public void goToEmployeeUI(ActionEvent event) {
        try {
            sceneController.switchMenu(event, "/FXML/EmployeeUI.fxml");
        } catch (IOException e) {

        }
    }

    public void goToClinicalAnalysisLaboratoryUI(ActionEvent event) {
        try {
            sceneController.switchMenu(event, "/FXML/ClinicalAnalysisLaboratoryUI.fxml");
        } catch (IOException e) {

        }
    }

}

