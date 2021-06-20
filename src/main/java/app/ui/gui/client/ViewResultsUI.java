
package app.ui.gui.client;

import app.controller.*;
import app.domain.shared.Constants;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import app.controller.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewResultsUI implements Initializable{

    private final SceneController sceneController = SceneController.getInstance();
    private final ViewResultsController vrc = new ViewResultsController();
    private final App app = sceneController.getApp();

    @FXML
    private ListView<String> ListView1;

    @FXML
    private Label label1;

    @FXML
    private Button button1;


    public void returnToMenu(ActionEvent event) {
        sceneController.switchMenu(event, Constants.CLIENT_UI);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label1.setText(null);



    }
    public void confirm() {
        button1.setDisable(true);
        vrc.sortedDateList();
        ListView1.getItems().addAll(vrc.getTestSortedList());
        selectedList();
        vrc.getTestSortedList().removeAll(vrc.getTestSortedList());

    }
    private void selectedList() {
        ListView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String test) {
                label1.setText(vrc.getDiagnosis(test));
            }
        });
    }

}

