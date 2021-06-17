package app.ui.gui.clientUIs;

import app.controller.App;
import app.controller.ViewResultsController;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewResultsUI implements Initializable {

        ViewResultsController VRc = new ViewResultsController();

        @FXML
        private ListView<String> ListView1;

        @FXML
        private TextArea TextArea1;

    private SceneController sceneController = SceneController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextArea1.setEditable(false);
        VRc.sortedDateList();
        ListView1.getItems().addAll(VRc.getTestSortedList());
        selectedList();

    }

    private void selectedList() {
        ListView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String test) {
                TextArea1.setText(VRc.getResult(test));
            }
        });
    }
    public void returnToMenu(ActionEvent event) {
        App app = sceneController.getApp();
        sceneController.switchMenu(event, Constants.CLIENT_UI);
    }
}
