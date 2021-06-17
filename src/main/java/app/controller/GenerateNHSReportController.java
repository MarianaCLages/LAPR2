package app.controller;

import app.domain.model.*;
import app.domain.shared.LinearRegression;
import app.domain.shared.exceptions.*;
import app.domain.stores.TestStore;
import com.sun.javafx.binding.StringFormatter;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GenerateNHSReportController {

    private Company company;
    private TestStore testStore;
    private StringBuilderReport stringBuilderReport;
    private Data data;

    private StringBuilder sb = new StringBuilder();

    public GenerateNHSReportController() {
        this(App.getInstance().getCompany());
    }

    public GenerateNHSReportController(Company company) {

        this.company = company;
        this.testStore = company.getTestList();
        this.stringBuilderReport = new StringBuilderReport();
        this.data = company.getData();

    }

    public void linearRegressionWithMeanAge() {

        List<Test> validTests = this.testStore.getListTestsInsideTheHistoricalDays(company.getTestList().getValidatedTestsListCovid());
        List<Client> clientsWithTests = this.testStore.getClientsWithTests(company.getClientArrayList());

        List<Test> validTestsInsideInterval = this.testStore.getListTestsInsideDateInterval(company.getTestList().getValidatedTestsListCovid(), company.getData().getIntervalStartDate(), company.getData().getIntervalEndDate());

        double[] ages = this.testStore.getClientAge(clientsWithTests, company.getData().getHistoricalDaysInt() + 1, company.getData().getHistoricalDaysInt());
        //  double[] covidTestsPerDayInsideTheHistoricalInterval = this.testStore.getCovidTestsPerDayIntoArray(validTestsInsideInterval, company.getData().getHistoricalDaysInt() + 1, company.getData().getHistoricalDaysInt());

        double[] agesInsideTheDateInterval = this.testStore.getClientAgeInsideTheInterval(clientsWithTests, company.getData().getDifferenceInDates() + 1, company.getData().getIntervalStartDate());
        double[] covidTestsPerDayInsideTheIntervalOfDates = this.testStore.getCovidTestsPerDayIntoArrayInsideInterval(validTests, company.getData().getDifferenceInDates() + 1, company.getData().getIntervalStartDate());

        LinearRegression linearRegression = new LinearRegression(agesInsideTheDateInterval, covidTestsPerDayInsideTheIntervalOfDates);

        StringBuilder sbAux = new StringBuilder();
        this.sb = sbAux;

        sb.append(linearRegression.toString());
        sb.append("\n");

        int i = 1;
        for (double xi : ages) {
            sb = this.stringBuilderReport.printPredictedValues(xi, sb, i, linearRegression);
            i++;
        }

        this.sb = this.stringBuilderReport.printCovidTestsPerInterval(sb);

    }

    public void linearRegressionWithCovidTests() {


        List<Test> validTests = this.testStore.getListTestsInsideTheHistoricalDays(company.getTestList().getValidatedTestsListCovid());
        List<Test> covidTests = this.testStore.getPositiveCovidTest(validTests);

        List<Test> validTestInsideInterval = this.testStore.getListTestsInsideDateInterval(company.getTestList().getValidatedTestsListCovid(), company.getData().getIntervalStartDate(), company.getData().getIntervalEndDate());
        List<Test> covidTestInsideInterval = this.testStore.getPositiveCovidTest(validTestInsideInterval);

        double[] positiveCovidTestsPerDayInsideTheHistoricalInterval = this.testStore.getCovidTestsPerDayIntoArray(covidTests, company.getData().getHistoricalDaysInt() + 1, company.getData().getHistoricalDaysInt());
        //  double[] covidTestsPerDayInsideTheHistoricalInterval = this.testStore.getCovidTestsPerDayIntoArray(validTests, company.getData().getHistoricalDaysInt() + 1, company.getData().getHistoricalDaysInt());

        double[] positiveCovidTestsPerDayInsideTheDateInterval = this.testStore.getCovidTestsPerDayIntoArrayInsideInterval(covidTestInsideInterval, company.getData().getDifferenceInDates() + 1, company.getData().getIntervalStartDate());
        double[] covidTestsPerDayInsideTheDateInterval = this.testStore.getCovidTestsPerDayIntoArrayInsideInterval(validTestInsideInterval, company.getData().getDifferenceInDates() + 1, company.getData().getIntervalStartDate());

        LinearRegression linearRegression = new LinearRegression(positiveCovidTestsPerDayInsideTheDateInterval, covidTestsPerDayInsideTheDateInterval);

        StringBuilder sbAux = new StringBuilder();
        this.sb = sbAux;

        sb.append("The regression model fitted using data from the interval\n")
                .append("^y=" + linearRegression.toString() + "\n\nOther statistics\n")
                .append("R^2 = " + String.format("%.4f", linearRegression.R2()) + "\n")
                .append("R =   " +   String.format("%.4f",linearRegression.RPARAMUDAR()) + "\n\n")
                .append("Date\t\t\t\t\t\t    " + "Number of OBSERVED positive cases\t\t\t\t\t\t" + "Number of ESTIMATED positive cases\t\t\t\t\t\t").append(this.company.getData().getConfidenceLevel()).append("% intervals\n");

        int i = 1;
        for (double xi : positiveCovidTestsPerDayInsideTheHistoricalInterval) {
            sb = this.stringBuilderReport.printPredictedValues(xi, sb, i, linearRegression);
            i++;

        }

        this.sb = this.stringBuilderReport.printCovidTestsPerInterval(sb);

    }

    public void setInformation(boolean dayReport, boolean weekReport, boolean monthlyReport, LocalDate start, LocalDate end, String historicalDays, String confidenceLevel) throws DateEmptyException, DateInvalidException, HistoricalDaysInvalidException, HistoricalDaysEmptyException, ConfidenceLevelICEmptyException, ConfidenceLevelInvalidException {

        Data data = getData();

        data.setIntervalDates(this.testStore.getIntervalDate(start, end));
        data.setHistoricalDays(historicalDays);
        data.setConfidenceLevelIC(100 - Integer.parseInt(confidenceLevel));

        setReport(dayReport, weekReport, monthlyReport, data);

        data.setDates(start, end);
        data.setDayReport(dayReport);
        data.setWeekReport(weekReport);
        data.setMonthlyReport(monthlyReport);

    }

    public void setReport(boolean dayReport, boolean weekReport, boolean monthlyReport, Data data) {

        if (dayReport && weekReport && monthlyReport) {

            data.setDayReport(true);
            data.setWeekReport(true);
            data.setMonthlyReport(true);

        } else if (weekReport && dayReport) {

            data.setDayReport(true);
            data.setWeekReport(true);

        } else if (dayReport && monthlyReport) {

            data.setMonthlyReport(true);
            data.setDayReport(true);

        } else if (weekReport && monthlyReport) {

            data.setMonthlyReport(true);
            data.setWeekReport(true);

        } else if (monthlyReport) {

            data.setMonthlyReport(true);

        } else if (dayReport) {

            data.setDayReport(true);

        } else if (weekReport) {

            data.setWeekReport(true);

        }

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