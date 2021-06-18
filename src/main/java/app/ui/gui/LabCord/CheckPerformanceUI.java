package app.ui.gui.LabCord;

import app.controller.App;
import app.controller.CheckPerformanceController;
import app.controller.SceneController;
import app.domain.shared.Constants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckPerformanceUI implements Initializable {
    public TextArea myTextAreaSimple;
    public ChoiceBox<String> myChoiceBoxSimple;
    public DatePicker dtnBeg;
    public DatePicker dtnEnd;
    public Button choiceAlgo;
    private SceneController sceneController = SceneController.getInstance();
    private CheckPerformanceController ctrl = new CheckPerformanceController();
    private App app = sceneController.getApp();


    @FXML
    private Button btnReturn;

    public void returnToMenu(ActionEvent actionEvent) {
        app.doLogout();
        sceneController.switchMenu(actionEvent, Constants.LABORATORY_COORDINATOR_UI);
    }

    public void confirm(ActionEvent actionEvent) {
        ctrl.getSubArray(dtnBeg.getValue(), dtnEnd.getValue());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {Constants.BENCHMARK_ALGORITHM, Constants.BRUTEFORCE_ALGORITHM};
        myChoiceBoxSimple.getItems().addAll(choices);
    }
}
