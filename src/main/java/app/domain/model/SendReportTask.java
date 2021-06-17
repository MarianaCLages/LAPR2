package app.domain.model;


import app.controller.App;
import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

public class SendReportTask extends TimerTask implements Serializable {
    private LocalDate finishDate;
    private int historicalDays;
    private LocalDate beginningDate;
    private double confidenceLevelAnova;
    private double confidenceLevelVariables;
    private double confidenceLevelEstimated;
    private String independentVariable;
    private String regression;
    private String variable;

    public SendReportTask() {
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        TestStore testStore = App.getInstance().getCompany().getTestList();
        ClientStore clientStore = App.getInstance().getCompany().getClientList();
        getProps();
        testStore.setDates(historicalDays);
        double[] positiveCovidTestsPerDayInsideTheDateInterval = testStore.getPositiveCovidTestsPerDayIntoArrayInsideInterval(Period.between(this.beginningDate, this.finishDate).getDays() + 1, this.beginningDate);
        double[] positiveCovidTestsPerDayInsideTheHistoricalInterval = testStore.getCovidTestsPerDayIntoArray(historicalDays);
        double[] covidTestsPerDayInsideTheDateInterval = testStore.getCovidTestsPerDayIntoArrayInsideInterval(Period.between(this.beginningDate, this.finishDate).getDays() + 1, this.beginningDate);

        List<Client> clientsWithTests = testStore.getClientsWithTests(clientStore.returnClientList());
        double[] ages = testStore.getClientAge(clientsWithTests, this.historicalDays);
        double[] agesInsideTheDateInterval = testStore.getClientAgeInsideTheInterval(clientsWithTests, Period.between(this.beginningDate, this.finishDate).getDays() + 1, this.beginningDate);

        if (regression.equals("Linear")) {

            System.out.println(Arrays.toString(covidTestsPerDayInsideTheDateInterval));
            System.out.println(Arrays.toString(positiveCovidTestsPerDayInsideTheDateInterval));

            LinearRegression linearRegressionNumberTest = new LinearRegression(covidTestsPerDayInsideTheDateInterval, positiveCovidTestsPerDayInsideTheDateInterval);

            System.out.println(linearRegressionNumberTest.toString());
            System.out.println("a");
            LinearRegression linearRegressionMeanAge = new LinearRegression(agesInsideTheDateInterval, positiveCovidTestsPerDayInsideTheDateInterval);
            System.out.println(linearRegressionNumberTest.toString());
            System.out.println("aa");


        } else if (regression.equals("Multilinear")) {


            double[][] multiarray = new double[covidTestsPerDayInsideTheDateInterval.length][2];
            for (int i = 0; i < multiarray.length; i++) {
                multiarray[i][0] = covidTestsPerDayInsideTheDateInterval[i];
                multiarray[i][1] = agesInsideTheDateInterval[i];

            }

            MultiLinearRegression s = new MultiLinearRegression(multiarray, positiveCovidTestsPerDayInsideTheDateInterval);

        }


    }

    private void getProps() {
        Properties prop = App.getProperties();
        this.beginningDate = LocalDate.parse(prop.getProperty("fit.date.beginning"));
        this.finishDate = LocalDate.parse(prop.getProperty("fit.date.ending"));
        this.historicalDays = Integer.parseInt((prop.getProperty("historical.days")));
        this.confidenceLevelAnova = Double.parseDouble(prop.getProperty("significance.level.anova"));
        this.confidenceLevelVariables = Double.parseDouble(prop.getProperty("significance.level.coefficient"));
        this.confidenceLevelEstimated = Double.parseDouble(prop.getProperty("significance.level.estimated"));
        this.regression = prop.getProperty("type.regression");
        this.variable = prop.getProperty("independent.variable");

    }

}



