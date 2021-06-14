package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.LinearRegression;
import app.domain.shared.exceptions.ChoiceBoxEmptyException;
import app.ui.gui.Alerts;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
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

    private Company company = sceneController.getCompany();

    private LocalDate todayDate;
    private Calendar cal;
    private LocalDate beginDate;
    private LocalDate todayDateForCovidReport = LocalDate.now();

    public void returnToGenerateNHSReport(ActionEvent event) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(event, Constants.NHS_REPORT_UI);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {Constants.COVID_TESTS, Constants.MEAN_AGE};
        myChoiceBoxSimple.getItems().addAll(choices);
        setDates();
    }

    private void setDates() {

        todayDate = LocalDate.now(); //Date atual (dia de hoje)

        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -company.getData().getHistoricalDaysInt());
        Date toDate = cal.getTime();

        beginDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

    }

    public void confirm(ActionEvent event) {

        try {

            if (myChoiceBoxSimple.getValue() == null) {
                throw new ChoiceBoxEmptyException();
            }

            String choice = myChoiceBoxSimple.getValue();

            if (myChoiceBoxSimple.getValue().equals(Constants.COVID_TESTS)) {

                linearRegressionWithCovidTests();

            } else if (myChoiceBoxSimple.getValue().equals(Constants.MEAN_AGE)) {

                linearRegressionWithMeanAge();

            }

        } catch (ChoiceBoxEmptyException err2) {
            Alerts.errorAlert(err2.getMessage());
        } catch (RuntimeException err1) {
            Alerts.errorAlert(err1.getMessage());
            //  Alerts.errorAlert("Please enter valid information (Don't leave blank containers!)");
        }

    }

    private void linearRegressionWithCovidTests() {

        List<Test> validTests = getListTestsInsideTheHistoricalDays(company.getTestList().getValidatedTestsList());
        List<Test> covidTests = getPositiveCovidTest(validTests);

        List<Test> validTestInsideInterval = getListTestsInsideDateInterval(company.getTestList().getValidatedTestsList());
        List<Test> covidTestInsideInterval = getPositiveCovidTest(validTestInsideInterval);

        double[] positiveCovidTestsPerDayInsideTheHistoricalInterval = getCovidTestsPerDayIntoArray(covidTests, company.getData().getHistoricalDaysInt() + 1);
        double[] covidTestsPerDayInsideTheHistoricalInterval = getCovidTestsPerDayIntoArray(validTests, company.getData().getHistoricalDaysInt() + 1);

        double[] positiveCovidTestsPerDayInsideTheDateInterval = getCovidTestsPerDayIntoArrayInsideInterval(covidTestInsideInterval, company.getData().getDifferenceInDates() + 1);
        double[] covidTestsPerDayInsideTheDateInterval = getCovidTestsPerDayIntoArrayInsideInterval(validTestInsideInterval, company.getData().getDifferenceInDates() + 1);

        LinearRegression linearRegression = new LinearRegression(positiveCovidTestsPerDayInsideTheDateInterval, covidTestsPerDayInsideTheDateInterval);

        StringBuilder sb = new StringBuilder();
        sb.append(linearRegression.toString());
        sb.append("\n");

        int i = 1;
        for (double xi : positiveCovidTestsPerDayInsideTheHistoricalInterval) {
            sb = printPredictedValues(xi, sb, i, linearRegression);
            i++;

        }

        printCovidTestsPerInterval(sb);

        myTextAreaSimple.setText(sb.toString());

    }

    private void linearRegressionWithMeanAge() {

        List<Test> validTests = getListTestsInsideTheHistoricalDays(company.getTestList().getValidatedTestsList());
        List<Client> clientsWithTests = getClientsWithTests();

        List<Test> validTestsInsideInterval = getListTestsInsideDateInterval(company.getTestList().getValidatedTestsList());

        double[] ages = getClientAge(clientsWithTests, company.getData().getHistoricalDaysInt() + 1);
        double[] covidTestsPerDayInsideTheHistoricalInterval = getCovidTestsPerDayIntoArray(validTestsInsideInterval, company.getData().getHistoricalDaysInt() + 1);

        double[] agesInsideTheDateInterval = getClientAgeInsideTheInterval(clientsWithTests, company.getData().getDifferenceInDates() + 1);
        double[] covidTestsPerDayInsideTheIntervalOfDates = getCovidTestsPerDayIntoArrayInsideInterval(validTests, company.getData().getDifferenceInDates() + 1);

        LinearRegression linearRegression = new LinearRegression(agesInsideTheDateInterval, covidTestsPerDayInsideTheIntervalOfDates);

        StringBuilder sb = new StringBuilder();
        sb.append(linearRegression.toString());
        sb.append("\n");

        int i = 1;
        for (double xi : ages) {
            sb = printPredictedValues(xi, sb, i, linearRegression);
            i++;
        }

        printCovidTestsPerInterval(sb);

        myTextAreaSimple.setText(sb.toString());

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

    private List<Test> getClientsWithTestsListWithTests() {

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

    private double[] getClientAge(List<Client> clientList, int space) {

        double[] clientsAges = new double[space]; // O mais 1 é pq é preciso registar o dia de "HJ"

        int n = 0;
        int x = 0;
        int sum = 0;
        int age = 0;

        for (int i = 0; i < space; i++) {

            LocalDate currentDay = getCurrentDay(i);

            for (Test t1 : getClientsWithTestsListWithTests()) {

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

        return clientsAges;

    }

    private double[] getClientAgeInsideTheInterval(List<Client> clientList, int space) {

        double[] clientsAges = new double[space]; // O mais 1 é pq é preciso registar o dia de "HJ"

        int n = 0;
        int x = 0;
        int sum = 0;
        int age = 0;

        for (int i = 0; i < space; i++) {

            LocalDate currentDay = getCurrentDayInsideInterval(i);

            for (Test t1 : getClientsWithTestsListWithTests()) {

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

        return clientsAges;

    }

    private List<Test> getPositiveCovidTest(List<Test> covidList) {

        List<Test> covidTestList = new ArrayList<>();

        for (Test t : covidList) {
            for (TestParameter t1 : t.getTestParam()) {
                if (t1 != null) {
                    if (t1.getpCode().equals(Constants.VALID_COVID_PARAMETER) && t1.getTestParameterResult().getResult() > Constants.VALID_COVID_PARAMETER_VALUE) {
                        covidTestList.add(t);
                    }
                }
            }
        }

        return covidTestList;

    }

    private double[] getCovidTestsPerDayIntoArray(List<Test> testList, int space) {

        double[] positiveCovidTestsPerDay = new double[space];

        for (int i = 0; i < space; i++) {

            LocalDate currentDay = getCurrentDay(i);

            for (Test t : testList) {
                LocalDate testDate = t.getDate().toLocalDate();
                if (testDate.equals(currentDay)) {
                    positiveCovidTestsPerDay[i] += 1;
                }
            }

        }

        return positiveCovidTestsPerDay;

    }

    private List<Test> getListTestsInsideTheHistoricalDays(List<Test> list) {

        List<Test> validCovidTests = new ArrayList<>();

        for (Test t : list) {
            LocalDate testDate = t.getDate().toLocalDate();

            if (Period.between(beginDate, testDate).getDays() >= 0 && Period.between(testDate, todayDate).getDays() >= 0) {
                validCovidTests.add(t);
            }
        }

        return validCovidTests;

    }

    private LocalDate getCurrentDay(int i) {

        int interV = company.getData().getHistoricalDaysInt() - i;

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -interV);
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        return currentDay;

    }

    private LocalDate getCurrentDayInsideInterval(int i) {

        int startDayInterval = Period.between(company.getData().getIntervalStartDate(), todayDate).getDays();

        int interW = startDayInterval - i;

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -interW);
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        return currentDay;

    }

    private List<Test> getListTestsInsideDateInterval(List<Test> list) {

        List<Test> validTests = new ArrayList<>();

        for (Test t : list) {
            LocalDate testDate = t.getDate().toLocalDate();

            if (Period.between(company.getData().getIntervalStartDate(), testDate).getDays() >= 0 && Period.between(testDate, company.getData().getIntervalEndDate()).getDays() >= 0) {
                validTests.add(t);
            }
        }

        return validTests;

    }

    private double[] getCovidTestsPerDayIntoArrayInsideInterval(List<Test> testList, int space) {

        double[] positiveCovidTestsPerDay = new double[space];

        for (int i = 0; i < space; i++) {

            LocalDate currentDay = getCurrentDayInsideInterval(i);

            for (Test t : testList) {
                LocalDate testDate = t.getDate().toLocalDate();
                if (testDate.equals(currentDay)) {
                    positiveCovidTestsPerDay[i] += 1;
                }
            }

        }

        return positiveCovidTestsPerDay;

    }

    private StringBuilder printPredictedValues(double xi, StringBuilder sb, int i, LinearRegression linearRegression) {

        int startDayIntervalForStringBuilder = company.getData().getHistoricalDaysInt();

        int interW = startDayIntervalForStringBuilder - i + 1;

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -interW);
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        sb.append(currentDay);
        sb.append(": ");
        sb.append(linearRegression.predict(xi));
        sb.append("\n");
        return sb;

    }

    private LocalDate getCurrentDayInsideAWeekInterval() {

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -7);
        Date toDate2 = cal2.getTime();

        LocalDate weekIntervalDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return weekIntervalDay;

    }

    private void printCovidTestsPerInterval(StringBuilder sb) {

        if (company.getData().getDayReportValue() && company.getData().getWeekReportValue()) {
            getCovidTestsIntoTheNHSReport(true, true, sb);
        } else if (company.getData().getWeekReportValue()) {
            getCovidTestsIntoTheNHSReport(false, true, sb);
        } else if (company.getData().getDayReportValue()) {
            getCovidTestsIntoTheNHSReport(true, false, sb);
        }

    }

    private StringBuilder getCovidTestsIntoTheNHSReport(boolean day, boolean week, StringBuilder sb) {

        if (!week) {
            printTheCovidTestsIntoTheNHSReportDay(sb);
        } else if (!day) {
            printTheCovidTestsIntoTheNHSReportWeek(sb);
        } else if (day && week) {
            printTheCovidTestsIntoTheNHSReportDay(sb);
            printTheCovidTestsIntoTheNHSReportWeek(sb);
        }

        return sb;
    }

    private StringBuilder printTheCovidTestsIntoTheNHSReportDay(StringBuilder sb) {
        int dayTests = 0;
        sb.append("\n");
        sb.append("Today covid tests :");
        sb.append("\n\n");
        sb.append(todayDateForCovidReport);
        sb.append(" : ");
        for (Test t : getPositiveCovidTest(company.getTestList().getValidatedTestsList())) {
            LocalDate tDate = t.getDate().toLocalDate();

            if (tDate.equals(todayDateForCovidReport)) {
                dayTests++;
            }

        }

        sb.append(dayTests);
        sb.append(" positive covid tests");
        return sb;
    }

    private StringBuilder printTheCovidTestsIntoTheNHSReportWeek(StringBuilder sb) {
        int interval = Period.between(getCurrentDayInsideAWeekInterval(), todayDateForCovidReport).getDays();
        int[] covidTestsIntoArray = new int[interval + 1];

        sb.append("\n");
        sb.append("Week report:");
        sb.append("\n");

        for (int i = 0; i < interval; i++) {

            int interW = 7 - i - 1;

            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DATE, -interW);
            Date toDate2 = cal2.getTime();

            LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

            for (Test t : getPositiveCovidTest(company.getTestList().getValidatedTestsList())) {
                LocalDate tDate = t.getDate().toLocalDate();
                if (tDate.equals(currentDay)) {
                    covidTestsIntoArray[i] += 1;
                }
            }

            sb.append(currentDay);
            sb.append(" : ");
            sb.append(covidTestsIntoArray[i]);
            sb.append(" positive covid tests");
            sb.append("\n");

        }
        return sb;
    }
}