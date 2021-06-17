package app.ui.gui.adminMenuUIs;

import app.controller.App;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.shared.Constants;
import app.domain.shared.LinearRegression;
import app.domain.shared.exceptions.ChoiceBoxEmptyException;
import app.domain.stores.TestStore;
import app.ui.gui.Alerts;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public class SimpleLinearRegressionUI implements Initializable {

    @FXML
    private ChoiceBox<String> myChoiceBoxSimple;

    @FXML
    private TextArea myTextAreaSimple;

    private SceneController sceneController = SceneController.getInstance();
    private StringBuilder sb;
    private App app;
  //  private GenerateNHSReportController ctrl;

    private LocalDate todayDateForCovidReport = LocalDate.now();

    public SimpleLinearRegressionUI() {
    //    this.ctrl = sceneController.getCtrl();
        this.app = sceneController.getApp();
    }

    public void returnToGenerateNHSReport(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.NHS_REPORT_UI);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {Constants.COVID_TESTS, Constants.MEAN_AGE};
        myChoiceBoxSimple.getItems().addAll(choices);
   //     ctrl.setDates();
    }

    public void confirm(ActionEvent event) {

        try {

            if (myChoiceBoxSimple.getValue() == null) {
                throw new ChoiceBoxEmptyException();
            }

            String choice = myChoiceBoxSimple.getValue();

            if (myChoiceBoxSimple.getValue().equals(Constants.COVID_TESTS)) {

    //            ctrl.linearRegressionWithCovidTests();
                myTextAreaSimple.setText(sb.toString());

            } else if (myChoiceBoxSimple.getValue().equals(Constants.MEAN_AGE)) {

      //          ctrl.linearRegressionWithMeanAge();
                myTextAreaSimple.setText(sb.toString());

            }

        } catch (ChoiceBoxEmptyException err2) {
            Alerts.errorAlert(err2.getMessage());
        } catch (RuntimeException err1) {
            Alerts.errorAlert(err1.getMessage());
            //  Alerts.errorAlert("Please enter valid information (Don't leave blank containers!)");
        }

    }
}