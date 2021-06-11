package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.shared.exceptions.ChoiceBoxEmptyException;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SimpleLinearRegressionController implements Initializable {

    @FXML
    private ChoiceBox<String> myChoiceBoxSimple;

    private SceneController sceneController = SceneController.getInstance();

    private Company company = App.getInstance().getCompany();

    public void returnToGenerateNHSReport(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/NHSReportUI.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {"Covid-19 tests", "Mean age"};
        myChoiceBoxSimple.getItems().addAll(choices);
    }

    public void confirm(ActionEvent event) {

        try {

            if (myChoiceBoxSimple.getValue() == null) {
                throw new ChoiceBoxEmptyException();
            }

            String choice = myChoiceBoxSimple.getValue();

            if(myChoiceBoxSimple.getValue()=="Covid-19 tests"){

            } else if(myChoiceBoxSimple.getValue()=="Mean age"){

               List<Client> clientList = company.getClientArrayList();

            }

        } catch (ChoiceBoxEmptyException err1) {
            errorAlert(err1.getMessage());
        } catch (RuntimeException err2){
            errorAlert("Please enter valid information (Don't leave blank containers!)");
        }


    }

    private void errorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText("Invalid format!");
        alert.setContentText(message);
        alert.show();
    }

}