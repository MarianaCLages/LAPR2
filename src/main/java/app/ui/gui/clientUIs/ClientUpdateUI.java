package app.ui.gui.clientUIs;

import app.controller.App;
import app.controller.SceneController;
import app.controller.UpdateClientDataController;
import app.domain.shared.Constants;
import javafx.event.ActionEvent;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;

import java.io.IOException;
import java.text.ParseException;


public class ClientUpdateUI {


    private UpdateClientDataController clientDataController = new UpdateClientDataController();

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();


    @FXML
    private TextArea informationArea;

    @FXML
    private TextField attributeField;

    @FXML
    private TextField newField;


    @FXML
    void showInfo(ActionEvent actionEvent) {
        informationArea.setText(clientDataController.getClient());
    }

    @FXML
    void updateInfo(ActionEvent actionEvent) {
        int i = Integer.parseInt(attributeField.getText());
        if (i > 0 && i <= 8) {
            String info = newField.getText();
            try {
                clientDataController.changeData(i, info);
                Alert updated = new Alert(Alert.AlertType.INFORMATION, "Your personal information was updated\n" +
                        "For security reasons restart the login session");
                updated.showAndWait();
            } catch (IllegalArgumentException | ParseException e) {
                Alert invalid = new Alert(Alert.AlertType.WARNING, "The information is invalid");
                invalid.showAndWait();
            }
        } else {
            Alert canceled = new Alert(Alert.AlertType.WARNING, "Invalid attribute number");
            canceled.showAndWait();
        }
        showInfo(null);
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }
}