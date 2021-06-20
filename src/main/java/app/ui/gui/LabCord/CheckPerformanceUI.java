package app.ui.gui.LabCord;

import app.controller.App;
import app.controller.CheckPerformanceController;
import app.controller.SceneController;
import app.domain.shared.Constants;

import app.domain.shared.exceptions.ChoiceBoxEmptyException;
import app.domain.shared.exceptions.InvalidIntervalOfDatesEndException;
import app.ui.gui.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class CheckPerformanceUI implements Initializable {

    @FXML
    private ChoiceBox<String> myChoiceBoxSimple;
    @FXML
    private DatePicker dtnBeg;
    @FXML
    private DatePicker dtnEnd;
    @FXML
    private ChoiceBox<String> myChoiceBoxInformation;
    @FXML
    private TextArea trackpad;

    private final SceneController sceneController = SceneController.getInstance();
    private final CheckPerformanceController ctrl = sceneController.getCtrl();
    private final App app = sceneController.getApp();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {Constants.BENCHMARK_ALGORITHM, Constants.BRUTEFORCE_ALGORITHM};
        String[] information = {Constants.DAY, Constants.WEEK, Constants.MONTH, Constants.YEAR};
        myChoiceBoxSimple.getItems().addAll(choices);
        myChoiceBoxInformation.getItems().addAll(information);
    }

    public void returnToMenu(ActionEvent actionEvent) {
        app.doLogout();
        sceneController.switchMenu(actionEvent, Constants.LABORATORY_COORDINATOR_UI);
    }

    public void goToGraph(ActionEvent event) {
        sceneController.switchMenu(event, Constants.PERFORMANCE_GRAPH_UI);
    }


    public void confirm() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        try {
            if (myChoiceBoxInformation.getValue().equals(null) || myChoiceBoxSimple.getValue().equals(null)) {
                throw new ChoiceBoxEmptyException();
            }

            if ((int) ChronoUnit.DAYS.between(dtnBeg.getValue(), dtnEnd.getValue()) < 0) {
                throw new InvalidIntervalOfDatesEndException();
            }

            ctrl.getSubArray(dtnBeg.getValue(), dtnEnd.getValue(), myChoiceBoxSimple.getValue());
            ctrl.getDates(ctrl.getSubArray(dtnBeg.getValue(), dtnEnd.getValue(), myChoiceBoxSimple.getValue()));
            ctrl.numberWaitingResults();
            ctrl.numberWaitingDiagnosis();
            ctrl.numberClients();

            if (myChoiceBoxInformation.getValue().equals(Constants.DAY)) {
                ctrl.setInformation(myChoiceBoxInformation.getValue());
            } else if (myChoiceBoxInformation.getValue().equals(Constants.WEEK)) {
                ctrl.setInformation(Constants.WEEK);
            } else if (myChoiceBoxInformation.getValue().equals(Constants.MONTH)) {
                ctrl.setInformation(myChoiceBoxInformation.getValue());
            } else if (myChoiceBoxInformation.getValue().equals(Constants.YEAR)) {
                ctrl.setInformation(myChoiceBoxInformation.getValue());
            }

            trackpad.setText(ctrl.constructPerformanceReport());

        } catch (ChoiceBoxEmptyException | InvalidIntervalOfDatesEndException err1) {
            Alerts.errorAlert(err1.getMessage());
        } catch (NullPointerException err2) {
           Alerts.errorAlert("Please type all the information necessary! Don't leave blank spots!");
        }
    }
}
