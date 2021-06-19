package app.controller;

import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import app.ui.gui.Alerts;
import auth.mappers.dto.UserRoleDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
            sceneController.switchMenu(actionEvent, Constants.MAIN_SCREEN_UI);
        }
    }

    public void cancel(ActionEvent actionEvent) {
        sceneController.switchMenu(actionEvent, Constants.MAIN_SCREEN_UI);
    }

    private boolean doLogin() {

        boolean success = false;

        String pwd = passfield.getText();
        String id = emailfield.getText();

        maxAttempts--;
        success = ctrl.doLogin(id, pwd);
        if (!success && maxAttempts != 0) {
            Alerts.informationAlert(maxAttempts);
        }

        return success;
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1)
            return roles.get(0);
        else
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
    }

    private void switchM(ActionEvent actionEvent) {
        List<UserRoleDTO> roles = this.ctrl.getUserRoles();
        if ((roles == null) || (roles.isEmpty())) {
            Alerts.errorAlert("User has not any role assigned.");
        } else {
            UserRoleDTO role = selectsRole(roles);
            if (!Objects.isNull(role)) {
                switch (role.getDescription()) {
                    case "ADMINISTRATOR":
                        sceneController.switchMenu(actionEvent, Constants.ADMINISTRATOR_UI);
                        break;
                    case "SPECIALISTDOCTOR":
                        sceneController.switchMenu(actionEvent, Constants.SPECIALIST_DOCTOR_UI);
                        break;
                    case "ClIENT":
                        sceneController.switchMenu(actionEvent, Constants.CLIENT_UI);
                        break;
                    case "RECEPTIONIST":
                        sceneController.switchMenu(actionEvent, Constants.RECEPTIONIST_UI);
                        break;
                    case "LABORATORYCOORDINATOR":
                        sceneController.switchMenu(actionEvent, Constants.LABORATORY_COORDINATOR_UI);
                        break;
                    case "MEDICALLABTECHNICIIAN":
                        sceneController.switchMenu(actionEvent, Constants.MEDICAL_LAB_TECHINICIAN_UI);
                        break;
                    case "CLINICALCHEMISTRYTECHNOLOGIST":
                        sceneController.switchMenu(actionEvent, Constants.CLINICAL_CHEMISTRY_TECHNOLOGIST_UI);
                        break;
                    case "CLIENT":
                        sceneController.switchMenu(actionEvent, Constants.CLIENT_UI);
                        break;
                    default:
                        break;
                }

            } else {
                Alerts.errorAlert("User did not select a role.");
            }
        }
    }
}