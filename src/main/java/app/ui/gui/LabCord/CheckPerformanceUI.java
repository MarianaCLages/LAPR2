package app.ui.gui.LabCord;

import app.controller.App;
import app.controller.CheckPerformanceController;
import app.controller.SceneController;
import app.domain.shared.Constants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
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

    private final SceneController sceneController = SceneController.getInstance();
    private final CheckPerformanceController ctrl = new CheckPerformanceController();
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
        ctrl.getSubArray(dtnBeg.getValue(), dtnEnd.getValue(), myChoiceBoxSimple.getValue()); //subArray
        ctrl.getDates(ctrl.getSubArray(dtnBeg.getValue(), dtnEnd.getValue(), myChoiceBoxSimple.getValue()));// data de início e de fim do sub array
        ctrl.numberWaitingResults();
        ctrl.numberWaitingDiagnosis();
        ctrl.numberClients();
    }


}
