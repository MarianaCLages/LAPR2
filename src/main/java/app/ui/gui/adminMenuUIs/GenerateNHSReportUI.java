//package app.ui.gui.adminMenuUIs;
//
//import app.controller.App;
////import app.controller.GenerateNHSReportController;
////import app.domain.model.Company;
//import app.domain.model.Data;
//import app.domain.shared.Constants;
//import app.domain.shared.exceptions.*;
//import app.ui.gui.Alerts;
//import app.ui.gui.controllers.SceneController;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//
//import java.net.URL;
//import java.time.LocalDate;
//import java.time.Period;
//import java.time.ZoneId;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.ResourceBundle;
//
//public class GenerateNHSReportUI implements Initializable {
//
//    private SceneController sceneController = SceneController.getInstance();
//    private LocalDate todayDate = LocalDate.now();
//
////    private GenerateNHSReportController ctrl = sceneController.getCtrl();
//
//    @FXML
//    private Button myReturnButtonNHS;
//    @FXML
//    private Label myLabelNHS;
//    @FXML
//    private TextField myTextFieldNHS;
//    @FXML
//    private TextField myTextFieldNHS2;
//    @FXML
//    private ChoiceBox<String> myChoiceBoxNHS;
//    @FXML
//    private Button myButton2NHS;
//    @FXML
//    private DatePicker myDatePicker1;
//    @FXML
//    private DatePicker myDatePicker2;
//    @FXML
//    private RadioButton myRadioButtonDay;
//    @FXML
//    private RadioButton myRadioButtonWeek;
//    @FXML
//    private RadioButton myRadioButtonMonthly;
//
//    public GenerateNHSReportUI() {
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        String[] choices = {Constants.SIMPLE_LINEAR_REGRESSION, Constants.MULTI_LINEAR_REGRESSION};
//        myChoiceBoxNHS.getItems().addAll(choices);
//    }
//
//    public void returnToAdminMenu(ActionEvent event) {
//        App app = sceneController.getApp();
//        app.doLogout();
//        sceneController.switchMenu(event, Constants.ADMINISTRATOR_UI);
//    }
//
//    public void enterData(ActionEvent event) {
//
//        try {
//
//            if (Period.between(ctrl.getStartDate(myTextFieldNHS.getText()), myDatePicker1.getValue()).getDays() < 0) {
//                throw new InvalidIntervalOfDatesStartException();
//            }
//
//            if (Period.between(myDatePicker2.getValue(), todayDate).getDays() < 0) {
//                throw new InvalidIntervalOfDatesEndException();
//            }
//
//            if (myChoiceBoxNHS.getValue() == null) {
//                throw new ChoiceBoxEmptyException();
//            }
//
//            if (!myRadioButtonDay.isSelected() && !myRadioButtonWeek.isSelected() && !myRadioButtonMonthly.isSelected()) {
//                throw new RadioButtonsNotSelectedException();
//            }
//
//            setInformation();
//            changeScene(event);
//
//        } catch (ConfidenceLevelInvalidException | InvalidIntervalOfDatesEndException | InvalidIntervalOfDatesStartException | HistoricalDaysEmptyException | DateEmptyException | DateInvalidException | ConfidenceLevelICEmptyException | ChoiceBoxEmptyException | HistoricalDaysInvalidException | RadioButtonsNotSelectedException err1) {
//            Alerts.errorAlert(err1.getMessage());
//        } catch (RuntimeException err2) {
//            Alerts.errorAlert(Constants.ERROR_BLANK_CONTAINERS);
//        }
//
//    }
//
//    private void setInformation() throws DateEmptyException, DateInvalidException, HistoricalDaysInvalidException, HistoricalDaysEmptyException, ConfidenceLevelICEmptyException, ConfidenceLevelInvalidException {
//
//        boolean dayReport = myRadioButtonDay.isSelected();
//        boolean weekReport = myRadioButtonWeek.isSelected();
//        boolean monthlyReport = myRadioButtonMonthly.isSelected();
//
//        Data data = ctrl.getData();
//
//        data.setIntervalDates(ctrl.getIntervalDate(myDatePicker1.getValue(), myDatePicker2.getValue()));
//        data.setHistoricalDays(myTextFieldNHS.getText());
//        data.setConfidenceLevelIC(100 - Integer.parseInt(myTextFieldNHS2.getText()));
//
//        setReport(dayReport, weekReport, monthlyReport, data);
//
//        data.setDates(myDatePicker1.getValue(), myDatePicker2.getValue());
//        data.setDayReport(dayReport);
//        data.setWeekReport(weekReport);
//        data.setMonthlyReport(monthlyReport);
//
//        dayReport = false;
//        weekReport = false;
//        monthlyReport = false;
//
//    }
//
//    private void changeScene(ActionEvent event) {
//
//        if (myChoiceBoxNHS.getValue() == Constants.SIMPLE_LINEAR_REGRESSION) {
//            sceneController.switchMenu(event, Constants.SIMPLE_LINEAR_REGRESSION_UI);
//        } else {
//            sceneController.switchMenu(event, Constants.MULTI_LINEAR_REGRESSION_UI);
//        }
//    }
//
//    public void setReport(boolean dayReport, boolean weekReport, boolean monthlyReport, Data data) {
//
//        if (myRadioButtonDay.isSelected() && myRadioButtonWeek.isSelected() && myRadioButtonMonthly.isSelected()) {
//
//            data.setDayReport(dayReport);
//            data.setWeekReport(weekReport);
//            data.setMonthlyReport(monthlyReport);
//
//        } else if (myRadioButtonWeek.isSelected() && myRadioButtonDay.isSelected()) {
//
//            data.setDayReport(dayReport);
//            data.setWeekReport(weekReport);
//
//        } else if (myRadioButtonDay.isSelected() && myRadioButtonMonthly.isSelected()) {
//
//            data.setMonthlyReport(monthlyReport);
//            data.setDayReport(dayReport);
//
//        } else if (myRadioButtonWeek.isSelected() && myRadioButtonMonthly.isSelected()) {
//
//            data.setMonthlyReport(monthlyReport);
//            data.setWeekReport(weekReport);
//
//        } else if (myRadioButtonMonthly.isSelected()) {
//
//            data.setMonthlyReport(monthlyReport);
//
//        } else if (myRadioButtonDay.isSelected()) {
//
//            data.setDayReport(dayReport);
//
//        } else if (myRadioButtonWeek.isSelected()) {
//
//            data.setWeekReport(weekReport);
//
//        }
//
//    }
//
//}