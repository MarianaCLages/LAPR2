package app.ui.gui.clichetecMenuUIs;

import app.controller.App;
import app.controller.ConsultClientTestsAndResultsController;
import app.domain.model.Client;
import app.domain.model.Test;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultClientTestsAndResultsUI implements Initializable {

    @FXML
    private ChoiceBox<String> cbxListOrder;
    @FXML
    private Button btnReturn;
    @FXML
    private Button btnShowTests;
    @FXML
    private ListView<String> lvwClientList;
    @FXML
    private ListView<String> lvwTestList;
    @FXML
    private Label lblClient;
    @FXML
    private TextArea txtTestResults;

    ConsultClientTestsAndResultsController ctrl = new ConsultClientTestsAndResultsController();

    List<Client> clientList;
    List<Test> testList;
    Client client;

    private final SceneController sceneController = SceneController.getInstance();

    public void returnToCCTMenu(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, Constants.CLINICAL_CHEMISTRY_TECHNOLOGIST_UI);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {Constants.CLIENT_LIST_TIN, Constants.CLIENT_LIST_NAME};
        cbxListOrder.getItems().addAll(choices);
        selectedOrder();
    }

    public void selectedOrder() {
        cbxListOrder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (fillClientList(t1)) {
                    selectedClient();
                }
            }
        });
    }

    public boolean fillClientList(String order) {
        lvwClientList.getItems().clear();

        if (order.equalsIgnoreCase(Constants.CLIENT_LIST_TIN)) {
            clientList = ctrl.getClientListTin();
            for (Client client1 : clientList) {
                lvwClientList.getItems().add(client1.getTinNumber());
            }
        } else if (order.equalsIgnoreCase(Constants.CLIENT_LIST_NAME)) {
            clientList = ctrl.getClientListName();
            for (Client client1 : clientList) {
                lvwClientList.getItems().add(client1.getName());
            }
        }

        if (lvwClientList.getItems().isEmpty() || lvwClientList.getItems() == null) {
            lvwClientList.getItems().add("The client list is empty! Try adding a client to the system.");
            return false;
        }
        return true;
    }

    public void selectedClient() {
        lvwClientList.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> lblClient.setText(clientList.get(t1.intValue()).getTinNumber()));
    }

    public void fillTestList() {
        lvwTestList.getItems().clear();
        client = ctrl.selectedClient(lblClient.getText());
        testList = ctrl.getValidatedTestList();

        for (Test test : testList) {
            lvwTestList.getItems().add(test.getTestNhsNumber());
        }

        if (lvwTestList.getItems().isEmpty() || lvwTestList.getItems() == null) {
            lvwTestList.getItems().add("The test list is empty! The selected client doesn't have any validated tests.");
            return;
        }
        selectedTest();
    }

    public void selectedTest() {
        lvwTestList.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> txtTestResults.setText(ctrl.toString(testList.get(t1.intValue()))));
    }
}
