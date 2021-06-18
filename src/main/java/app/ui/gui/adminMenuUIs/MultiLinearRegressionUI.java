package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.controller.GenerateNHSReportController;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MultiLinearRegressionUI {

    @FXML
    private TextArea myTextAreaMulti;
    @FXML
    private Button myReturnButtonMulti;

    private SceneController sceneController = SceneController.getInstance();
    private App app = sceneController.getApp();
    private GenerateNHSReportController ctrl = SceneController.getInstance().getCtrl();

    public MultiLinearRegressionUI() {
        ctrl.multiRegression();
        //myTextAreaMulti.setText(ctrl.getSb().toString());
    }

    public void returnToGenerateNHSReport(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.NHS_REPORT_UI);
    }

}
