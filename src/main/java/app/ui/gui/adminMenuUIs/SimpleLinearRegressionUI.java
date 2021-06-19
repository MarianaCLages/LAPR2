package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.controller.GenerateNHSReportController;
import app.domain.shared.Constants;
import app.domain.shared.exceptions.ChoiceBoxEmptyException;
import app.ui.gui.Alerts;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.*;

public class SimpleLinearRegressionUI implements Initializable {

    @FXML
    private ChoiceBox<String> myChoiceBoxSimple;
    @FXML
    private TextArea myTextAreaSimple;

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();
    private final GenerateNHSReportController ctrl = new GenerateNHSReportController();

    public void returnToGenerateNHSReport(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.NHS_REPORT_UI);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {Constants.COVID_TESTS, Constants.MEAN_AGE};
        myChoiceBoxSimple.getItems().addAll(choices);
        ctrl.setDates(ctrl.getData().getHistoricalDaysInt());
    }

    public void confirm() {

        try {

            if (myChoiceBoxSimple.getValue() == null) {
                throw new ChoiceBoxEmptyException();
            }

            if (myChoiceBoxSimple.getValue().equals(Constants.COVID_TESTS)) {

                ctrl.linearRegressionWithCovidTests();
                myTextAreaSimple.setText(ctrl.getSb().toString());

            } else if (myChoiceBoxSimple.getValue().equals(Constants.MEAN_AGE)) {

                ctrl.linearRegressionWithMeanAge();
                myTextAreaSimple.setText(ctrl.getSb().toString());

            }

        } catch (ChoiceBoxEmptyException err2) {
            Alerts.errorAlert(err2.getMessage());
        } catch (RuntimeException err1) {
            Alerts.errorAlert(err1.getMessage());
        }

    }
}