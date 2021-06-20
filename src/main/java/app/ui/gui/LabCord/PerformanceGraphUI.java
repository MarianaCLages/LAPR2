package app.ui.gui.LabCord;

import app.controller.App;
import app.controller.CheckPerformanceController;
import app.controller.SceneController;
import app.domain.shared.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class PerformanceGraphUI implements Initializable {
    @FXML
    public LineChart graph;
    public CategoryAxis yaxis;
    public NumberAxis xaxis;

    private SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();
    private final CheckPerformanceController ctrl = sceneController.getCtrl();

    public void returnToPerformanceUI(ActionEvent event) {
        sceneController.switchMenu(event, Constants.COMPANY_PERFORMANCE_UI);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series series = new XYChart.Series();
        int[] difference = ctrl.getDifferenceArray();
        LocalDateTime[] times = ctrl.getTimes();

        try {
            for (int i = 0; i < difference.length; i++) {
                series.getData().add(new XYChart.Data(times[i].toString(), difference[i]));
            }
        } catch (Exception e) {

        }
        graph.getData().addAll(series);
    }
}
