package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.Data;
import app.domain.shared.exceptions.*;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class GenerateNHSReportController implements Initializable {

    private SceneController sceneController = SceneController.getInstance();
    private Company company;
    private Data data = new Data();

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

    public GenerateNHSReportController() {
        this(App.getInstance().getCompany());
    }

    public GenerateNHSReportController(Company company) {
        this.company = company;
    }

    public void enterData(ActionEvent event) {

        try {

            data.setIntervalDates(getIntervalDate(myDatePicker1.getValue(), myDatePicker2.getValue()));
            data.setHistoricalDays(myTextFieldNHS.getText());
            data.setConfidenceLevelIC(myTextFieldNHS2.getText());

            if (myChoiceBoxNHS.getValue() == null) {
                throw new ChoiceBoxEmptyException();
            }

            if (myChoiceBoxNHS.getValue() == "Simple Linear Regression") {
                sceneController.switchMenu(event, "/FXML/SimpleLinearRegression.fxml");
            } else {
                sceneController.switchMenu(event, "/FXML/MultiLinearRegression.fxml");
            }

        } catch (ConfidenceLevelInvalidException err1) {
            errorAlert(err1.getMessage());
        } catch (HistoricalDaysEmptyException err2) {
            errorAlert(err2.getMessage());
        } catch (DateEmptyException err3) {
            errorAlert(err3.getMessage());
        } catch (DateInvalidException err4) {
            errorAlert(err4.getMessage());
        } catch (ConfidenceLevelICEmptyException err5) {
            errorAlert(err5.getMessage());
        } catch (ChoiceBoxEmptyException err6) {
            errorAlert(err6.getMessage());
        } catch (HistoricalDaysInvalidException err7) {
            errorAlert(err7.getMessage());
        } catch (RuntimeException err8){
            errorAlert("Please enter valid information (Don't leave blank containers!)");
        }

    }

    private long getIntervalDate(LocalDate start, LocalDate end) {
        long n;
        return n = Period.between(start, end).getDays();
    }

    public void returnToAdminMenu(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/AdministratorUI.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {"Simple Linear Regression", "Multi Linear Regression"};
        myChoiceBoxNHS.getItems().addAll(choices);
    }

    private void errorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText("Invalid format!");
        alert.setContentText(message);
        alert.show();
    }
}