package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Data;
import app.domain.model.Test;
import app.domain.shared.exceptions.ChoiceBoxEmptyException;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SimpleLinearRegressionController implements Initializable {

    @FXML
    private ChoiceBox<String> myChoiceBoxSimple;

    private SceneController sceneController = SceneController.getInstance();

    private Company company = App.getInstance().getCompany();

    public void returnToGenerateNHSReport(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, "/FXML/NHSReportUI.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {"Covid-19 tests", "Mean age"};
        myChoiceBoxSimple.getItems().addAll(choices);
    }

    public void confirm(ActionEvent event) {

        try {

            if (myChoiceBoxSimple.getValue() == null) {
                throw new ChoiceBoxEmptyException();
            }

            String choice = myChoiceBoxSimple.getValue();

            if (myChoiceBoxSimple.getValue() == "Covid-19 tests") {

                 List<Test> validTests = company.getTestList().getValidatedTestsList();
                 System.out.println(company.getData().getHistoricalDays());

                 for (Test t : validTests){
                     System.out.println(t);
                 }

            } else if (myChoiceBoxSimple.getValue() == "Mean age") {

                List<Client> clientsWithTests = getClientsWithTests();
                int[] ages = getClientAge(clientsWithTests);
                //linearRegression(ages) quando isto estiver implementado

            }

        } catch (ChoiceBoxEmptyException err1) {
            errorAlert(err1.getMessage());
        } catch (RuntimeException err2) {
               errorAlert(err2.getMessage());
            // errorAlert("Please enter valid information (Don't leave blank containers!)");
        }


    }

    private void errorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText("Invalid format!");
        alert.setContentText(message);
        alert.show();
    }

    private List<Client> getClientsWithTests() {

        List<Client> clientList = company.getClientArrayList();
        List<Test> testList = company.getTestList().getTestListArray();

        List<Client> clientList1 = new ArrayList<>();

        for (Client c : clientList) {
            for (Test t : testList) {
                if (c.getTinNumber().equals(t.getClientTin())) {
                    if (!clientList1.contains(c))
                        clientList1.add(c);
                }
            }
        }

        return clientList1;

    }

    private int[] getClientAge(List<Client> clientList) {

        int[] clientsAges = new int[1000];
        int n = 0;

        for (Client c : clientList) {
            System.out.println(c.toString());
        }

        for (Client c : clientList) {

            LocalDate date = c.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            clientsAges[n] = Period.between(date, LocalDate.now()).getYears();
            n++;

        }

        for (int i = 0; i < clientsAges.length; i++) {
            if (clientsAges[i] != 0) System.out.println(clientsAges[i]);
        }


        return clientsAges;
    }

}