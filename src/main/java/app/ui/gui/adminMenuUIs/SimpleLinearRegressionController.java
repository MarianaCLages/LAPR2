package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.domain.model.*;
import app.domain.shared.LinearRegression;
import app.domain.shared.exceptions.ChoiceBoxEmptyException;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

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

    @FXML
    private TextArea myTextAreaSimple;

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

                linearRegressionWithCovidTests();

            } else if (myChoiceBoxSimple.getValue().equals("Mean age")) {

                List<Client> clientsWithTests = getClientsWithTests();
                int[] ages = getClientAge(clientsWithTests);
                //linearRegression(ages) quando isto estiver implementado

            }

        } catch (ChoiceBoxEmptyException err2) {
            errorAlert(err2.getMessage());
        } catch (RuntimeException err1) {
            //errorAlert("Please enter valid information (Don't leave blank containers!)");
            errorAlert(err1.getMessage());
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
        List<Test> validTestList = getListTestsInsideTheHistoricalDays(company.getTestList().getTestListArray());

        List<Client> clientList1 = new ArrayList<>();

        for (Client c : clientList) {
            for (Test t : validTestList) {
                if (c.getTinNumber().equals(t.getClientTin())) {
                    if (!clientList1.contains(c))
                        clientList1.add(c);
                }
            }
        }

        return clientList1;

    }

    private List<Test> getClientsWithTests2() {

        List<Client> clientList = company.getClientArrayList();
        List<Test> validTestList = getListTestsInsideTheHistoricalDays(company.getTestList().getTestListArray());

        List<Test> testList = new ArrayList<>();

        for (Client c : clientList) {
            for (Test t : validTestList) {
                if (c.getTinNumber().equals(t.getClientTin())) {
                    if (!testList.contains(t))
                        testList.add(t);
                }
            }
        }

        return testList;

    }

    private int[] getClientAge(List<Client> clientList) {

        int[] clientsAges = new int[company.getData().getHistoricalDaysInt() + 1];
        int n = 0;
        int x = 0;
        int sum = 0;
        int age = 0;

        for (Client c : clientList) {
            System.out.println(c.toString());
        }

        LocalDate todayDate = LocalDate.now(); //Date atual (dia de hoje)

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -company.getData().getHistoricalDaysInt());
        Date toDate = cal.getTime();

        LocalDate beginDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        int interval = Period.between(beginDate, todayDate).getDays();

        for (int i = 0; i < interval; i++) {

            int interV = company.getData().getHistoricalDaysInt() - i;

            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DATE, -interV);
            Date toDate2 = cal2.getTime();

            LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)


            for (Test t1 : getClientsWithTests2()) {

                LocalDate testDate = t1.getDate().toLocalDate();

                if (testDate.equals(currentDay)) {

                    Client c1 = null;

                    for (Client c : clientList) {
                        if (t1.getClientTin().equals(c.getTinNumber())) {
                            c1 = c;
                        }
                    }

                    LocalDate date = c1.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    age = Period.between(date, LocalDate.now()).getYears();
                    sum += age;
                    x++;

                }

            }

            if (x != 0) clientsAges[n] = sum / x;
            n++;
            x = 0;
            sum = 0;

        }


        for (int i : clientsAges) {
            System.out.println(i);
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

        return covidTestList;

    }

    private List<Test> getListTestsInsideTheHistoricalDays(List<Test> list) {

        LocalDate todayDate = LocalDate.now(); //Date atual (dia de hoje)

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -company.getData().getHistoricalDaysInt());
        Date toDate = cal.getTime();

        LocalDate beginDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        List<Test> validCovidTests = new ArrayList<>();

        for (Test t : list) {
            LocalDate testDate = t.getDate().toLocalDate();

            if (Period.between(beginDate, testDate).getDays() >= 0 && Period.between(testDate, todayDate).getDays() >= 0) {
                validCovidTests.add(t);
            }
        }

        return validCovidTests;

    }

    private double[] getCovidTestsPerDayIntoArray(List<Test> testList) {

        LocalDate todayDate = LocalDate.now(); //Date atual (dia de hoje)

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -company.getData().getHistoricalDaysInt());
        Date toDate = cal.getTime();

        LocalDate beginDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        int interval = Period.between(beginDate, todayDate).getDays();

        double[] positiveCovidTestsPerDay = new double[interval + 1];

        for (int i = 0; i < interval; i++) {

            int interV = company.getData().getHistoricalDaysInt() - i;

            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DATE, -interV);
            Date toDate2 = cal2.getTime();

            LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

            for (Test t : testList) {
                LocalDate testDate = t.getDate().toLocalDate();
                if (testDate.equals(currentDay)) {
                    positiveCovidTestsPerDay[i] += 1;
                }
            }

            for (double x : positiveCovidTestsPerDay) {
                System.out.println(x);
            }

        }

        return positiveCovidTestsPerDay;

    }

    private void linearRegressionWithCovidTests() {

        List<Test> validTests = getListTestsInsideTheHistoricalDays(company.getTestList().getValidatedTestsList());
        List<Test> covidTests = getPositiveCovidTest(validTests);

        double[] positiveCovidTestsPerDayInsideTheHistoricalInterval = getCovidTestsPerDayIntoArray(covidTests);
        double[] covidTestsPerDayInsideTheHistoricalInterval = getCovidTestsPerDayIntoArray(validTests);

        LinearRegression linearRegression = new LinearRegression(positiveCovidTestsPerDayInsideTheHistoricalInterval, covidTestsPerDayInsideTheHistoricalInterval);

        StringBuilder sb = new StringBuilder();

        sb.append(linearRegression.toString());
        sb.append("\n");

        int i = 1;
        for (double xi : positiveCovidTestsPerDayInsideTheHistoricalInterval) {
            sb.append("x");
            sb.append(i);
            sb.append(": ");
            sb.append(linearRegression.predict(xi));
            sb.append("\n");
            i++;
        }

        myTextAreaSimple.setText(sb.toString());

    }

}