package app.ui.gui.Controllers;

import app.controller.AuthController;
import app.ui.console.utils.Utils;
import auth.mappers.dto.UserRoleDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class LoginController {
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField passfield;
    @FXML
    private TextField emailfield;

    private SceneController sceneController = SceneController.getInstance();
    private AuthController ctrl = sceneController.getAuthController();

    private int maxAttempts = 3;

    private boolean sucess = false;

    public void login(ActionEvent actionEvent) {

        sucess = doLogin();

        if (sucess) {
            switchM(actionEvent);
        } else if (maxAttempts == 0) {
            try {
                sceneController.switchMenu(actionEvent, "/FXML/MainScreen.fxml");
            } catch (Exception e) {

            }
        }
    }

    public void cancel(ActionEvent actionEvent) {

        try {
            sceneController.switchMenu(actionEvent, "/FXML/MainScreen.fxml");
        } catch (Exception e) {

        }

    }

    private boolean doLogin() {

        boolean success = false;

        String pwd = passfield.getText();
        String id = emailfield.getText();

        maxAttempts--;
        success = ctrl.doLogin(id, pwd);
        if (!success) {
            informationAlert(maxAttempts);
        }

        return success;
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1)
            return roles.get(0);
        else
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
    }

    private void informationAlert(int maxAttempts) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Invalid UserId and/or Password.");
        alert.setContentText("You have  " + maxAttempts + " more attempt(s).");
        alert.show();
    }

    private void switchM(ActionEvent actionEvent) {
        List<UserRoleDTO> roles = this.ctrl.getUserRoles();
        if ((roles == null) || (roles.isEmpty())) {
            System.out.println("User has not any role assigned.");
        } else {
            UserRoleDTO role = selectsRole(roles);
            if (!Objects.isNull(role)) {
                switch (role.getDescription()) {
                    case "ADMINISTRATOR":
                        sceneController.switchMenu(actionEvent, "/FXML/AdministratorUI.fxml");
                        break;
                    case "SPECIALISTDOCTOR":
                        sceneController.switchMenu(actionEvent, "/FXML/SpecialistDoctorUI.fxml");
                        break;
                    case "ClIENT":
                        sceneController.switchMenu(actionEvent, "/FXML/ClientUI.fxml");
                        break;
                    case "RECEPTIONIST":
                        sceneController.switchMenu(actionEvent, "/FXML/ReceptionistUI.fxml");
                        break;
                    case "LABORATORYCOORDINATOR":
                        sceneController.switchMenu(actionEvent, "/FXML/LaboratoryCoordinatorUI.fxml");
                        break;
                    case "MEDICALLABTECHNICIIAN":
                        sceneController.switchMenu(actionEvent, "/FXML/MedicalLabTechnicianUI.fxml");
                        break;
                    case "CLINICALCHEMISTRYTECHNOLOGIST":
                        sceneController.switchMenu(actionEvent, "/FXML/ClinicalChemistryTechnologistUI.fxml");
                        break;
                    default:
                        break;
                }

            } else {
                System.out.println("User did not select a role.");
            }
        }
    }
}

   /* private Properties getProperties() {
        Properties props = new Properties();
        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "Many Labs");


        // Read configured values
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return props;
    }*/


