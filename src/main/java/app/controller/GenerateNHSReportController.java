package app.controller;

import app.domain.model.*;
import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;
import app.domain.shared.exceptions.*;
import app.domain.stores.TestStore;
import app.ui.gui.Alerts;
import com.nhs.report.Report2NHS;

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

    /**
     * Creates an empty NHS Report controller.
     */
    public GenerateNHSReportController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor.
     *
     * @param company the company that administrates the system
     */
    public GenerateNHSReportController(Company company) {

        this.company = company;
        this.testStore = company.getTestList();
        this.data = company.getData();

    }

    /**
     * Sets all the data.
     */
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

    }


    /**
     * Prints the linear regression values for mean age as independent variable.
     */
    public void linearRegressionWithMeanAge() {
        setData();

        linearRegressionPrintValues(agesInsideTheDateInterval, positiveCovidTestsPerDayInsideTheDateInterval, positiveCovidTestsPerDayInsideTheHistoricalInterval, covidTestsPerDayInsideTheHistoricalDays);
    }

    /**
     * Prints the linear regression values for Covid-19 tests as independent variable.
     */
    public void linearRegressionWithCovidTests() {
        setData();

        linearRegressionPrintValues(covidTestsPerDayInsideTheIntervalOfDates, positiveCovidTestsPerDayInsideTheDateInterval, positiveCovidTestsPerDayInsideTheHistoricalInterval, covidTestsPerDayInsideTheHistoricalDays);
    }

    /**
     * Does the multi linear regression.
     */
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

    /**
     * Prints the multi regression values.
     *
     * @param x    the x
     * @param y    the y
     * @param yObs the observed y
     * @param xObs the observed x
     */

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
        Report2NHS.writeUsingFileWriter(stringBuilderReport.getSb().toString());

    }

    /**
     * Prints the linear regression values.
     *
     * @param x    the x array
     * @param y    the y array
     * @param yObs the observed y (array)
     * @param xObs the observed x (array)
     */
    private void linearRegressionPrintValues(double[] x, double[] y, double[] yObs, double[] xObs) {

        LinearRegression linearRegression = new LinearRegression(x, y);

        this.stringBuilderReport = new StringBuilderReport(linearRegression);
        this.stringBuilderReport.setvalues(xObs, yObs, company.getData().getHistoricalDaysInt());
        this.stringBuilderReport.setConfidenceValues(company.getData().getConfidenceLevelAnova(), company.getData().getConfidenceLevelVariables(), company.getData().getConfidenceLevelEstimated());

        this.stringBuilderReport.clear();

        this.sb = stringBuilderReport.stringConstructionLinearRegression();
        this.sb = this.stringBuilderReport.printCovidTestsPerInterval(company.getData().getSelection());
        Report2NHS.writeUsingFileWriter(stringBuilderReport.getSb().toString());

    }

    /**
     * Sets the information.
     *
     * @param start          the beggining date
     * @param end            the ending date
     * @param historicalDays the number of historical days
     * @param icAnova        the ANOVA's alpha (confidence level for the ANOVA table)
     * @param selection      the selection for the report generation time
     * @param icVariables    the confidence level values
     * @param icEstimated    the confidence level estimated
     * @throws DateInvalidException
     * @throws HistoricalDaysInvalidException
     * @throws HistoricalDaysEmptyException
     * @throws ConfidenceLevelInvalidException
     */
    public void setInformation(LocalDate start, LocalDate end, String historicalDays, String icAnova, String selection, String icVariables, String icEstimated) throws DateInvalidException, HistoricalDaysInvalidException, HistoricalDaysEmptyException, ConfidenceLevelInvalidException {

        data.setIntervalDates(this.testStore.getIntervalDate(start, end));
        data.setHistoricalDays(historicalDays);

        data.setConfidenceLevelAnova((double) 100 - Integer.parseInt(icAnova));
        data.setConfidenceLevelEstimated((double) 100 - Integer.parseInt(icEstimated));
        data.setConfidenceLevelVariables((double) 100 - Integer.parseInt(icVariables));

        data.setSelection(selection);

        data.setDates(start, end);

    }

    /**
     * Sets the dates.
     * @param historicalDaysInt the number of historical days
     */
    public void setDates(int historicalDaysInt) {
        this.testStore.setDates(historicalDaysInt);
    }

    /**
     * Gets the data.
     * @return the data
     */
    public Data getData() {
        return data;
    }

    /**
     * Gets the string builder.
     * @return the string builder
     */
    public StringBuilder getSb() {
        return this.sb;
    }

    /**
     * Gets the today's date.
     * @return the today's date
     */
    public LocalDate getTodayDate() {
        return LocalDate.now();
    }

    /**
     * Gets the company.
     * @return the company
     */
    public Company getCompany() {
        return company;
    }
}