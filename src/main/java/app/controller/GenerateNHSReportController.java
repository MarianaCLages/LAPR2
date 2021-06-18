package app.controller;

import app.domain.model.*;
import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;
import app.domain.shared.exceptions.*;
import app.domain.stores.TestStore;
import app.ui.gui.Alerts;

import java.time.LocalDate;
import java.time.Period;
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

    private List<Client> clientsWithTests;

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

        clientsWithTests = this.testStore.getClientsWithTests(company.getClientArrayList());

        this.agesInsideTheHistoricalDays = this.testStore.getClientAge(clientsWithTests, this.company.getData().getHistoricalDaysInt());
        this.agesInsideTheDateInterval = this.testStore.getClientAgeInsideTheInterval(clientsWithTests, this.company.getData().getDifferenceInDates() + 1, this.company.getData().getIntervalStartDate());

        this.covidTestsPerDayInsideTheHistoricalDays = this.testStore.getCovidTestsPerDayIntoArray(this.company.getData().getHistoricalDaysInt());
        this.covidTestsPerDayInsideTheIntervalOfDates = this.testStore.getCovidTestsPerDayIntoArrayInsideInterval(this.company.getData().getDifferenceInDates() + 1, this.company.getData().getIntervalStartDate());

        this.positiveCovidTestsPerDayInsideTheHistoricalInterval = this.testStore.getCovidTestsPerDayIntoArray(this.company.getData().getHistoricalDaysInt());
        this.positiveCovidTestsPerDayInsideTheDateInterval = this.testStore.getPositiveCovidTestsPerDayIntoArrayInsideInterval(this.company.getData().getDifferenceInDates() + 1, this.company.getData().getIntervalStartDate());

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

        double[][] multiarray = new double[covidTestsPerDayInsideTheIntervalOfDates.length][2];
        for (int i = 0; i < multiarray.length; i++) {
            multiarray[i][0] = covidTestsPerDayInsideTheIntervalOfDates[i];
            multiarray[i][1] = agesInsideTheDateInterval[i];

        }

        double[][] multiarrayObs = new double[covidTestsPerDayInsideTheHistoricalDays.length][2];
        for (int i = 0; i < multiarrayObs.length; i++) {
            multiarrayObs[i][0] = covidTestsPerDayInsideTheHistoricalDays[i];
            multiarrayObs[i][1] = agesInsideTheHistoricalDays[i];
        }

        multiRegressionPrintValues(multiarray, positiveCovidTestsPerDayInsideTheDateInterval, positiveCovidTestsPerDayInsideTheHistoricalInterval, multiarrayObs);

    }

    private void multiRegressionPrintValues(double[][] x, double[] y, double[] yObs, double[][] xObs) {

        MultiLinearRegression s = new MultiLinearRegression(x, y);

        this.stringBuilderReport = new StringBuilderReport(s);
        this.stringBuilderReport.setvalues(xObs, yObs, company.getData().getHistoricalDaysInt());
        this.stringBuilderReport.setConfidenceValues(company.getData().getConfidenceLevelAnova(), company.getData().getConfidenceLevelVariables(), company.getData().getConfidenceLevelEstimated());

        StringBuilder sbAux = new StringBuilder();
        this.sb = sbAux;

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

        StringBuilder sbAux = new StringBuilder();
        this.sb = sbAux;

        this.sb = stringBuilderReport.stringConstructionLinearRegression();
        this.sb = this.stringBuilderReport.printCovidTestsPerInterval(company.getData().getSelection());

    }

    public void setInformation(LocalDate start, LocalDate end, String historicalDays, String ICAnova, String selection, String ICVariables, String ICEstimated) throws DateEmptyException, DateInvalidException, HistoricalDaysInvalidException, HistoricalDaysEmptyException, ConfidenceLevelICEmptyException, ConfidenceLevelInvalidException {

        Data data = getData();

        data.setIntervalDates(this.testStore.getIntervalDate(start, end));
        data.setHistoricalDays(historicalDays);

        data.setConfidenceLevelAnova(100 - Integer.parseInt(ICAnova));
        data.setConfidenceLevelEstimated(100 - Integer.parseInt(ICEstimated));
        data.setConfidenceLevelVariables(100 - Integer.parseInt(ICVariables));

        data.setSelection(selection);

        data.setDates(start, end);

    }


    public LocalDate getStartDate(String Text) {

        int n = Integer.parseInt(Text);

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

}