package app.controller;

import app.domain.model.*;
import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;
import app.domain.shared.exceptions.*;
import app.domain.stores.TestStore;
import app.ui.gui.Alerts;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class GenerateNHSReportController {

    private Company company;
    private TestStore testStore;
    private StringBuilderReport stringBuilderReport;
    private Data data;

    private double[] agesInsideTheHistoricalDays;
    private double[] agesInsideTheDateInterval;
    private double[] covidTestsPerDayInsideTheHistoricalDays;
    private double[] covidTestsPerDayInsideTheIntervalOfDates;
    private double[] positiveCovidTestsPerDayInsideTheHistoricalInterval;
    private double[] positiveCovidTestsPerDayInsideTheDateInterval;

    private StringBuilder sb = new StringBuilder();

    public GenerateNHSReportController() {
        this(App.getInstance().getCompany());
    }

    public GenerateNHSReportController(Company company) {

        this.company = company;
        this.testStore = company.getTestList();
        this.data = company.getData();

    }

    private void setData() {

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -company.getData().getHistoricalDaysInt());
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)


        List<Client> clientsWithTests = this.testStore.getClientsWithTests(company.getClientArrayList());

        this.agesInsideTheHistoricalDays = this.testStore.getClientAge(clientsWithTests, this.company.getData().getHistoricalDaysInt());
        this.agesInsideTheDateInterval = this.testStore.getClientAgeInsideTheInterval(clientsWithTests, this.company.getData().getDifferenceInDates() + 1, this.company.getData().getIntervalStartDate());

        this.covidTestsPerDayInsideTheHistoricalDays = this.testStore.getCovidTestsPerDayIntoArrayInsideInterval(company.getData().getHistoricalDaysInt() + 1, currentDay);
        this.covidTestsPerDayInsideTheIntervalOfDates = this.testStore.getCovidTestsPerDayIntoArrayInsideInterval(this.company.getData().getDifferenceInDates() + 1, this.company.getData().getIntervalStartDate());

        this.positiveCovidTestsPerDayInsideTheHistoricalInterval = this.testStore.getCovidTestsPerDayIntoArray(this.company.getData().getHistoricalDaysInt());
        this.positiveCovidTestsPerDayInsideTheDateInterval = this.testStore.getPositiveCovidTestsPerDayIntoArrayInsideInterval(this.company.getData().getDifferenceInDates() + 1, this.company.getData().getIntervalStartDate());


        System.out.println("Testes positivos nos historical days");
        int sum = 0;
        for (double xi : positiveCovidTestsPerDayInsideTheHistoricalInterval) {
            sum += xi;
        }
        System.out.println(sum);

        System.out.println("Testes positivos no intervalo de datas");
<<<<<<< HEAD
        int sum2=0;
        for(double xi : positiveCovidTestsPerDayInsideTheDateInterval){
            sum2+=xi;
=======
        int sum2 = 0;
        for (double xi : positiveCovidTestsPerDayInsideTheHistoricalInterval) {
            sum2 += xi;
>>>>>>> b48258a1bd8be8924c927e04440a9bee0ac2e8ec
        }
        System.out.println(sum2);

        System.out.println("Testes nos historical days");
        int sum3 = 0;
        for (double xi : covidTestsPerDayInsideTheHistoricalDays) {
            sum3 += xi;
        }
        System.out.println(sum3);

        System.out.println("Testes nos historical days");
        int sum4 = 0;
        for (double xi : covidTestsPerDayInsideTheIntervalOfDates) {
            sum4 += xi;
        }
        System.out.println(sum4);

    }

    public void linearRegressionWithMeanAge() {
        setData();

        linearRegressionPrintValues(agesInsideTheDateInterval, positiveCovidTestsPerDayInsideTheDateInterval, positiveCovidTestsPerDayInsideTheHistoricalInterval, covidTestsPerDayInsideTheHistoricalDays);
    }

    public void linearRegressionWithCovidTests() {
        setData();

        linearRegressionPrintValues(covidTestsPerDayInsideTheIntervalOfDates, positiveCovidTestsPerDayInsideTheDateInterval, positiveCovidTestsPerDayInsideTheHistoricalInterval, covidTestsPerDayInsideTheHistoricalDays);
    }

    public void multiRegression() {
        setData();

        double[][] multiArray = new double[covidTestsPerDayInsideTheIntervalOfDates.length][2];
        for (int i = 0; i < multiArray.length; i++) {
            multiArray[i][0] = covidTestsPerDayInsideTheIntervalOfDates[i];
            multiArray[i][1] = agesInsideTheDateInterval[i];

        }

        double[][] multiArrayObs = new double[covidTestsPerDayInsideTheHistoricalDays.length][2];
        for (int i = 0; i < multiArrayObs.length; i++) {
            multiArrayObs[i][0] = covidTestsPerDayInsideTheHistoricalDays[i];
            multiArrayObs[i][1] = agesInsideTheHistoricalDays[i];
        }

        multiRegressionPrintValues(multiArray, positiveCovidTestsPerDayInsideTheDateInterval, positiveCovidTestsPerDayInsideTheHistoricalInterval, multiArrayObs);

    }

    private void multiRegressionPrintValues(double[][] x, double[] y, double[] yObs, double[][] xObs) {

        MultiLinearRegression s = new MultiLinearRegression(x, y);

        this.stringBuilderReport = new StringBuilderReport(s);
        this.stringBuilderReport.setvalues(xObs, yObs, company.getData().getHistoricalDaysInt());
        this.stringBuilderReport.setConfidenceValues(company.getData().getConfidenceLevelAnova(), company.getData().getConfidenceLevelVariables(), company.getData().getConfidenceLevelEstimated());

        this.stringBuilderReport.clear();

        try {
            this.sb = this.stringBuilderReport.stringConstructionMultiLinearRegression();

        } catch (InvalidLengthException e) {
            Alerts.errorAlert(e.getMessage());
        }

        this.sb = this.stringBuilderReport.printCovidTestsPerInterval(company.getData().getSelection());

    }

    private void linearRegressionPrintValues(double[] x, double[] y, double[] yObs, double[] xObs) {

        LinearRegression linearRegression = new LinearRegression(x, y);

        this.stringBuilderReport = new StringBuilderReport(linearRegression);
        this.stringBuilderReport.setvalues(xObs, yObs, company.getData().getHistoricalDaysInt());
        this.stringBuilderReport.setConfidenceValues(company.getData().getConfidenceLevelAnova(), company.getData().getConfidenceLevelVariables(), company.getData().getConfidenceLevelEstimated());

        this.stringBuilderReport.clear();

        this.sb = stringBuilderReport.stringConstructionLinearRegression();
        this.sb = this.stringBuilderReport.printCovidTestsPerInterval(company.getData().getSelection());

    }

    public void setInformation(LocalDate start, LocalDate end, String historicalDays, String icAnova, String selection, String icVariables, String icEstimated) throws DateEmptyException, DateInvalidException, HistoricalDaysInvalidException, HistoricalDaysEmptyException, ConfidenceLevelInvalidException {

        data.setIntervalDates(this.testStore.getIntervalDate(start, end));
        data.setHistoricalDays(historicalDays);

        data.setConfidenceLevelAnova((double) 100 - Integer.parseInt(icAnova));
        data.setConfidenceLevelEstimated((double) 100 - Integer.parseInt(icEstimated));
        data.setConfidenceLevelVariables((double) 100 - Integer.parseInt(icVariables));

        data.setSelection(selection);

        data.setDates(start, end);

    }


    public LocalDate getStartDate(String text) {

        int n = Integer.parseInt(text);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -n);
        Date toDate = cal.getTime();

        return toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    }

    public void setDates(int historicalDaysInt) {
        this.testStore.setDates(historicalDaysInt);
    }

    public Data getData() {
        return data;
    }

    public StringBuilder getSb() {
        return this.sb;
    }

    public LocalDate getTodayDate() {
        return LocalDate.now();
    }

    public Company getCompany() {
        return company;
    }
}