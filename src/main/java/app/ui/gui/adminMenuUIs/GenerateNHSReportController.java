package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.Data;
import app.domain.shared.Constants;
import app.domain.shared.exceptions.*;
import app.ui.gui.Alerts;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class GenerateNHSReportController implements Initializable {

    private SceneController sceneController = SceneController.getInstance();
    private Company company;
    private LocalDate todayDate = LocalDate.now();

    @FXML
    private Button myReturnButtonNHS;
    @FXML
    private Label myLabelNHS;
    @FXML
    private TextField myTextFieldNHS;
    @FXML
    private TextField myTextFieldNHS2;
    @FXML
    private ChoiceBox<String> myChoiceBoxNHS;
    @FXML
    private Button myButton2NHS;
    @FXML
    private DatePicker myDatePicker1;
    @FXML
    private DatePicker myDatePicker2;
    @FXML
    private RadioButton myRadioButtonDay;
    @FXML
    private RadioButton myRadioButtonWeek;


    public GenerateNHSReportController() {
        this(App.getInstance().getCompany());
    }

    public GenerateNHSReportController(Company company) {
        this.company = company;
    }

    public void enterData(ActionEvent event) {

        try {

            if (Period.between(getStartDate(), myDatePicker1.getValue()).getDays() < 0) {
                throw new InvalidIntervalOfDatesStartException();
            }

            if (Period.between(myDatePicker2.getValue(), todayDate).getDays() < 0) {
                throw new InvalidIntervalOfDatesEndException();
            }

            if (myChoiceBoxNHS.getValue() == null) {
                throw new ChoiceBoxEmptyException();
            }

            if (!myRadioButtonDay.isSelected() && !myRadioButtonWeek.isSelected()) {
                throw new RadioButtonsNotSelectedException();
            }

            setInformation();
            changeScene(event);

        } catch (ConfidenceLevelInvalidException | InvalidIntervalOfDatesEndException | InvalidIntervalOfDatesStartException | HistoricalDaysEmptyException | DateEmptyException | DateInvalidException | ConfidenceLevelICEmptyException | ChoiceBoxEmptyException | HistoricalDaysInvalidException | RadioButtonsNotSelectedException err1) {
            Alerts.errorAlert(err1.getMessage());
        } catch (RuntimeException err2) {
            Alerts.errorAlert(Constants.ERROR_BLANK_CONTAINERS);
        }

    }

    private int getIntervalDate(LocalDate start, LocalDate end) {
        int n;
        return n = Period.between(start, end).getDays();
    }

    public void returnToAdminMenu(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, Constants.ADMINISTRATOR_UI);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {Constants.SIMPLE_LINEAR_REGRESSION, Constants.MULTI_LINEAR_REGRESSION};
        myChoiceBoxNHS.getItems().addAll(choices);
    }

    private void setInformation() throws DateEmptyException, DateInvalidException, HistoricalDaysInvalidException, HistoricalDaysEmptyException, ConfidenceLevelICEmptyException, ConfidenceLevelInvalidException {

        boolean dayReport = myRadioButtonDay.isSelected();
        boolean weekReport = myRadioButtonWeek.isSelected();

        Data data = company.getData();

        data.setIntervalDates(getIntervalDate(myDatePicker1.getValue(), myDatePicker2.getValue()));
        data.setHistoricalDays(myTextFieldNHS.getText());
        data.setConfidenceLevelIC(100 - Integer.parseInt(myTextFieldNHS2.getText()));

        if (myRadioButtonDay.isSelected() && myRadioButtonWeek.isSelected()) {

            data.setDayReport(dayReport);
            data.setWeekReport(weekReport);

        } else if (myRadioButtonDay.isSelected()) {

            data.setDayReport(dayReport);

        } else if (myRadioButtonWeek.isSelected()) {

            data.setWeekReport(weekReport);

        }

        data.setDates(myDatePicker1.getValue(), myDatePicker2.getValue());
        data.setDayReport(dayReport);
        data.setWeekReport(weekReport);

        dayReport = false;
        weekReport = false;

    }

    private void changeScene(ActionEvent event) {

        if (myChoiceBoxNHS.getValue() == Constants.SIMPLE_LINEAR_REGRESSION) {
            sceneController.switchMenu(event, Constants.SIMPLE_LINEAR_REGRESSION_UI);
        } else {
            sceneController.switchMenu(event, Constants.MULTI_LINEAR_REGRESSION_UI);
        }
    }

    private LocalDate getStartDate() {

        int n = Integer.parseInt(myTextFieldNHS.getText());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -n);
        Date toDate = cal.getTime();

        LocalDate startDate;

        return startDate = toDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

    }

    public void configDayReport(ActionEvent event) {
        myRadioButtonDay.setSelected(true);
    }

    public void configWeekReport(ActionEvent event) {
        myRadioButtonWeek.setSelected(true);
    }

}