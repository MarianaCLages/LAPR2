package app.ui.gui.clichetecMenuUIs;

import app.controller.App;
import app.controller.ConsultClientTestsAndResultsController;
import app.domain.model.Client;
import app.domain.model.Test;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultClientTestsAndResultsUI implements Initializable {

    @FXML
    private Button btnReturn;
    @FXML
    private Button btnShowTests;
    @FXML
    private ListView<String> lvwClientList;
    @FXML
    private ListView<String> lvwTestList;
    @FXML
    private Label lblClientTin;
    @FXML
    private TextArea lblTestResults;

    ConsultClientTestsAndResultsController ctrl = new ConsultClientTestsAndResultsController();

    List<Client> clientList;
    List<Test> testList;
    Client client;

    private final SceneController sceneController = SceneController.getInstance();

    public void returnToCCTMenu(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/ClinicalChemistryTechnologistUI.fxml");
    }

    public boolean fillClientList() {
        clientList = ctrl.getClientListTin();

        for (Client client : clientList) {
            lvwClientList.getItems().add(client.getTinNumber());
        }

        if (lvwClientList.getItems().isEmpty() || lvwClientList.getItems() == null) {
            lvwClientList.getItems().add("The client list is empty! Try adding a client to the system.");
            return false;
        }
        return true;
    }

    public void selectedClient() {
        lvwClientList.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> lblClientTin.setText(clientList.get(t1.intValue()).getTinNumber()));
    }

    public void fillTestList() {
        lvwTestList.getItems().clear();
        client = ctrl.selectedClient(lblClientTin.getText());
        testList = ctrl.getValidatedTestList();

        for (Test test : testList) {
            lvwTestList.getItems().add(test.getTestNhsNumber());
        }

        if (lvwTestList.getItems().isEmpty()) {
            lvwTestList.getItems().add("The test list is empty!");
            return;
        }
        selectedTest();
    }

    public void selectedTest(){
        lvwTestList.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> lblTestResults.setText(ctrl.toString(testList.get(t1.intValue()))));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (fillClientList()) {
            selectedClient();
        }
    }
}
