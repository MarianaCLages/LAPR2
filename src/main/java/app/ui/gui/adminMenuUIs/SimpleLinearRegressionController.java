package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.domain.model.*;
import app.domain.shared.exceptions.ChoiceBoxEmptyException;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

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
                List<Test> covidTests = getPositiveCovidTest(validTests);

                System.out.println("Valid Tests        ---------------------------------");
                    for (Test t : validTests) {
                    System.out.println(t);
                }
                System.out.println("Covid Tests positive ---------------------------------");
                for (Test t : covidTests) {
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
        int x = 0;
        int sum = 0;
        int age = 0;

        getListOfCovidTestInsideTheHistoricalDays();

        for (Client c : clientList) {
            System.out.println(c.toString());
        }

        for (Client c : clientList) {
            for (Test t1 : company.getTestList().getTestListArray()) {
                // if (t.getDate()>date3) {
                LocalDate date = c.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                age = Period.between(date, LocalDate.now()).getYears();
                sum += age;
                x++;

                //}
            }

            clientsAges[n] = sum / x;
            n++;
            x = 0;
            sum = 0;

        }

        for (int i = 0; i < clientsAges.length; i++) {
            if (clientsAges[i] != 0) System.out.println(clientsAges[i]);
        }


        return clientsAges;
    }


    private List<Test> getPositiveCovidTest(List<Test> covidList) {

        List<Test> covidTestList = new ArrayList<>();

        for (Test t : covidList) {
            for (TestParameter t1 : t.getTestParam()) {
                if (t1 != null) {
                    if (t1.getpCode().equals("IgGAN") && t1.getTestParameterResult().getResult() > 1.4) {
                        covidTestList.add(t);
                    }
                }
            }
        }

        System.out.println("---------------------------------");

        for (Test t : covidTestList) {
            System.out.println(t.toString());
        }

        return covidTestList;

    }

    private void getListOfCovidTestInsideTheHistoricalDays(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date2 = new Date();
        String todate = dateFormat.format(date2);
        LocalDate date3 = LocalDate.now();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -company.getData().getDate());
        Date todate1 = cal.getTime();

        LocalDate todate2 = todate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String fromdate = dateFormat.format(todate1);

        List<Test> validCovidTests = new ArrayList<>();

        for (Test t : company.getTestList().getValidatedTestsList()) {
            LocalDate localDate = t.getDate().toLocalDate();
            if (Period.between(todate2, localDate).getDays() < 0 && Period.between(localDate, date3).getDays() > 0) {
                System.out.println(localDate);
                validCovidTests.add(t);
            }
        }
    }

}