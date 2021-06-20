package app.controller;

import app.domain.model.Client;
import app.domain.model.StringBuilderReport;
import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;
import app.domain.shared.exceptions.InvalidLengthException;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;
import app.ui.gui.Alerts;
import com.nhs.report.Report2NHS;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SendReportTask extends TimerTask implements Serializable {
    private LocalDate finishDate;
    private int historicalDays;
    private LocalDate beginningDate;
    private double confidenceLevelAnova;
    private double confidenceLevelVariables;
    private double confidenceLevelEstimated;
    private String regression;
    private String scope;
    StringBuilderReport report;
    private boolean runed= false;

    public SendReportTask() {
        //Send report constructor
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        runed = true;
        LinearRegression linearRegressionChosen = null;
        TestStore testStore = App.getInstance().getCompany().getTestList();
        ClientStore clientStore = App.getInstance().getCompany().getClientList();
        getProps();

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -historicalDays);
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        testStore.setDates(historicalDays);
        double[] positiveCovidTestsPerDayInsideTheDateInterval = testStore.getPositiveCovidTestsPerDayIntoArrayInsideInterval(Period.between(this.beginningDate, this.finishDate).getDays() + 1, this.beginningDate);
        double[] positiveCovidTestsPerDayInsideTheHistoricalInterval = testStore.getCovidTestsPerDayIntoArray(historicalDays);

        double[] covidTestsPerDayInsideTheDateInterval = testStore.getCovidTestsPerDayIntoArrayInsideInterval(Period.between(this.beginningDate, this.finishDate).getDays() + 1, this.beginningDate);
        double[] covidTestsPerDayInsideHistoricalDays = testStore.getCovidTestsPerDayIntoArrayInsideInterval(historicalDays + 1, currentDay);

        List<Client> clientsWithTests = testStore.getClientsWithTests(clientStore.getClientList());
        double[] ages = testStore.getClientAge(clientsWithTests, this.historicalDays);
        double[] agesInsideTheDateInterval = testStore.getClientAgeInsideTheInterval(clientsWithTests, Period.between(this.beginningDate, this.finishDate).getDays() + 1, this.beginningDate);

        if (regression.equals("Linear")) {

            LinearRegression linearRegressionNumberTest = new LinearRegression(covidTestsPerDayInsideTheDateInterval, positiveCovidTestsPerDayInsideTheDateInterval);

            LinearRegression linearRegressionMeanAge = new LinearRegression(agesInsideTheDateInterval, positiveCovidTestsPerDayInsideTheDateInterval);

            if (linearRegressionNumberTest.getR2() > linearRegressionMeanAge.getR2()) {
                linearRegressionChosen = linearRegressionNumberTest;
                this.report = new StringBuilderReport(linearRegressionChosen);
                report.setvalues(covidTestsPerDayInsideHistoricalDays, positiveCovidTestsPerDayInsideTheHistoricalInterval, historicalDays);
                this.report.setConfidenceValues(confidenceLevelAnova, confidenceLevelVariables, confidenceLevelEstimated);
                this.report.stringConstructionLinearRegression();

            } else {
                linearRegressionChosen = linearRegressionMeanAge;
                this.report = new StringBuilderReport(linearRegressionChosen);
                report.setvalues(ages, positiveCovidTestsPerDayInsideTheHistoricalInterval, historicalDays);
                this.report.setConfidenceValues(confidenceLevelAnova, confidenceLevelVariables, confidenceLevelEstimated);

                this.report.stringConstructionLinearRegression();
                this.report.printCovidTestsPerInterval(this.scope);

            }
            Report2NHS.writeUsingFileWriter(this.report.getSb().toString());
            log();


        } else if (regression.equals("Multilinear")) {


            double[][] multiarray = new double[covidTestsPerDayInsideTheDateInterval.length][2];
            for (int i = 0; i < multiarray.length; i++) {
                multiarray[i][0] = covidTestsPerDayInsideTheDateInterval[i];
                multiarray[i][1] = agesInsideTheDateInterval[i];

            }

            double[][] multiarray2 = new double[covidTestsPerDayInsideHistoricalDays.length][2];
            for (int j = 0; j < multiarray2.length; j++) {
                multiarray2[j][0] = covidTestsPerDayInsideHistoricalDays[j];
                multiarray2[j][1] = ages[j];
            }

            MultiLinearRegression s = new MultiLinearRegression(multiarray, positiveCovidTestsPerDayInsideTheDateInterval);
            this.report = new StringBuilderReport(s);
            this.report.setConfidenceValues(confidenceLevelAnova, confidenceLevelVariables, confidenceLevelEstimated);
            this.report.setvalues(multiarray2, positiveCovidTestsPerDayInsideTheHistoricalInterval, historicalDays);

            try {
                this.report.stringConstructionMultiLinearRegression();
            } catch (InvalidLengthException e) {
                Alerts.errorAlert(e.getMessage());
            }
            Report2NHS.writeUsingFileWriter(report.getSb().toString());
            log();
        }
    }

    /**
     * Gets the properties.
     */
    private void getProps() {
        Properties prop = App.getProperties();
        this.beginningDate = LocalDate.parse(prop.getProperty("fit.date.beginning"));
        this.finishDate = LocalDate.parse(prop.getProperty("fit.date.ending"));
        this.historicalDays = Integer.parseInt((prop.getProperty("historical.days")));
        this.confidenceLevelAnova = Double.parseDouble(prop.getProperty("significance.level.anova"));
        this.confidenceLevelVariables = Double.parseDouble(prop.getProperty("significance.level.coefficient"));
        this.confidenceLevelEstimated = Double.parseDouble(prop.getProperty("significance.level.estimated"));
        this.regression = prop.getProperty("type.regression");
        this.scope = prop.getProperty("report.scope");

    }

    /**
     * Logs a message to the NHS.
     */
    private void log() {
        Logger logger = Logger.getLogger("Logger");
        FileHandler fh;

        try {

            fh = new FileHandler("Logs//log.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter;
            formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.setUseParentHandlers(false);
            logger.info("Report sent to NHS\n");

        } catch (SecurityException | IOException e) {
            Alerts.errorAlert(e.getMessage());
        }
    }

    public boolean isRuned() {
        return runed;
    }
}