package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.controller.GenerateNHSReportController;
import app.domain.shared.Constants;
import app.domain.shared.exceptions.*;
import app.ui.gui.Alerts;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.Period;
import java.util.ResourceBundle;

public class GenerateNHSReportUI implements Initializable {

    private final SceneController sceneController = SceneController.getInstance();
    private final GenerateNHSReportController ctrl = new GenerateNHSReportController();
    private final App app = sceneController.getApp();

    @FXML
    private TextField myTextFieldNHS;
    @FXML
    private ChoiceBox<String> myChoiceBoxNHS;
    @FXML
    private DatePicker myDatePicker1;
    @FXML
    private DatePicker myDatePicker2;
    @FXML
    private ChoiceBox<String> myChoiceBoxNHS2;
    @FXML
    private TextField myTextFieldNHSIC1;
    @FXML
    private TextField myTextFieldNHSIC2;
    @FXML
    private TextField myTextFieldNHSIC3;

    public GenerateNHSReportUI() {
        //Generate NHS Report UI
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {Constants.SIMPLE_LINEAR_REGRESSION, Constants.MULTI_LINEAR_REGRESSION};
        myChoiceBoxNHS.getItems().addAll(choices);
        String[] information = {Constants.DAY, Constants.WEEK, Constants.MONTH};
        myChoiceBoxNHS2.getItems().addAll(information);
    }

    public void returnToAdminMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.ADMINISTRATOR_UI);
    }

    public void enterData(ActionEvent event) {

        try {

            if (Period.between(ctrl.getStartDate(myTextFieldNHS.getText()), myDatePicker1.getValue()).getDays() < 0) {
                throw new InvalidIntervalOfDatesStartException();
            }

            if (Period.between(myDatePicker2.getValue(), ctrl.getTodayDate()).getDays() < 0) {
                throw new InvalidIntervalOfDatesEndException();
            }

            if (myChoiceBoxNHS.getValue() == null || myChoiceBoxNHS2.getValue() == null) {
                throw new ChoiceBoxEmptyException();
            }

            setInformation();
            changeScene(event);

        } catch (ConfidenceLevelInvalidException | InvalidIntervalOfDatesEndException | InvalidIntervalOfDatesStartException | HistoricalDaysEmptyException | DateEmptyException | DateInvalidException | ConfidenceLevelICEmptyException | ChoiceBoxEmptyException | HistoricalDaysInvalidException err1) {
            Alerts.errorAlert(err1.getMessage());
        } catch (RuntimeException err2) {
            Alerts.errorAlert(Constants.ERROR_BLANK_CONTAINERS);
        }

    }

    private void setInformation() throws DateEmptyException, DateInvalidException, HistoricalDaysInvalidException, HistoricalDaysEmptyException, ConfidenceLevelICEmptyException, ConfidenceLevelInvalidException {

        this.ctrl.setInformation(myDatePicker1.getValue(), myDatePicker2.getValue(), myTextFieldNHS.getText(), myTextFieldNHSIC1.getText(), myChoiceBoxNHS2.getValue(), myTextFieldNHSIC2.getText(), myTextFieldNHSIC3.getText());

    }

    private void changeScene(ActionEvent event) {

        if (myChoiceBoxNHS.getValue().equals(Constants.SIMPLE_LINEAR_REGRESSION)) {
            sceneController.switchMenu(event, Constants.SIMPLE_LINEAR_REGRESSION_UI);
        } else {
            sceneController.switchMenu(event, Constants.MULTI_LINEAR_REGRESSION_UI);
        }
    }

}