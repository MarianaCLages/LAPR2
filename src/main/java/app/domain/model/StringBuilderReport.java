package app.domain.model;

import app.controller.App;
import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;
import app.domain.shared.Regression;
import app.domain.stores.TestStore;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class StringBuilderReport {

    private Company company;
    private TestStore testStore;
    private LocalDate todayDateForCovidReport = LocalDate.now();
    private StringBuilder sb;
    private LinearRegression regressionSimple;
    private MultiLinearRegression regressionMulti;
    private double[] xLinear;
    private double[][] xMulti;
    private double[] yObs;
    private int historicalDays;

    public StringBuilderReport(Regression regression) {
        if (regression instanceof LinearRegression) {
            this.regressionSimple = (LinearRegression) regression;
        } else if (regression instanceof MultiLinearRegression) {
            this.regressionMulti = (MultiLinearRegression) regression;
        } else {
            throw new IllegalArgumentException();
        }

        this.company = App.getInstance().getCompany();
        this.testStore = company.getTestList();
        this.sb = new StringBuilder();
    }

    public void clear() {
        StringBuilder sbAux = new StringBuilder();
        this.sb = sbAux;
    }

    public void setvalues(double[] x, double[] yObs, int historicalDays) {
        this.xLinear = x;
        this.yObs = yObs;
        this.historicalDays = historicalDays;
    }

    public void setvalues(double[][] x, double[] yObs, int historicalDays) {
        this.xMulti = x;
        this.yObs = yObs;
        this.historicalDays = this.historicalDays;
    }

    public StringBuilder stringConstructionLinearRegression(double alpha) {
        sb.append("The regression model fitted using data from the interval\n")
                .append("^y = " + regressionSimple.toString() + "\n\nOther statistics\n")
                .append("R^2 = " + String.format("%.4f", regressionSimple.R2()) + "\n")
                .append("R     = " + String.format("%.4f", regressionSimple.R()) + "\n\n")
                .append("Hypothesis tests for regression coefficient\n\n Hypothesis test for coefficient a\n H0: a=0   H1: a!=0 \n")
                .append(" t_obs = ")
                .append(String.format("%.4f", regressionSimple.getCriticValueStudent(alpha)) + "\n");

        if (regressionSimple.getCriticValueStudent(alpha) > regressionSimple.getTestStatistica()) {
            sb.append("Reject H0\n\n");
        } else {
            sb.append(" No reject H0\n\n");
        }

        sb.append("Hypothesis test for coefficient b\n H0 : b=0 H1: b!=0\n")
                .append(" t_obs = ")
                .append(String.format("%.4f", regressionSimple.getCriticValueStudent(alpha)) + "\n");

        if (regressionSimple.getCriticValueStudent(alpha) > regressionSimple.getTestStatisticb()) {
            sb.append(" Reject H0\n\n");
        } else {
            sb.append(" No reject H0\n\n");
        }
        sb.append("Date\t\t\t\t\t\t    " + "Number of OBSERVED positive cases\t\t\t\t\t\t" + "Number of ESTIMATED positive cases\t\t\t\t\t\t").append(this.company.getData().getConfidenceLevel()).append("% intervals\n");

        return sb;
    }


    public StringBuilder printPredictedValues() {
        for (int i = 0; i < this.yObs.length; i++) {


            int interW = this.historicalDays - i + 1;


            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DATE, -interW);
            Date toDate2 = cal2.getTime();

            LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (!(cal2.get(Calendar.DAY_OF_WEEK) == 1)) {

                this.sb.append(currentDay)
                        .append("\t\t\t\t\t\t\t\t")
                        .append(String.format("%.0f", this.yObs[i]))
                        .append("\t\t\t\t\t\t\t\t\t\t\t\t")
                        .append(String.format("%.2f", regressionSimple.predict(this.xLinear[i])))
                        .append("\t\t\t\t\t\t\t\t")
 //por os intervalos    .append(String.format("%.2f", regressionSimple.predict(this.xLinear[i])))
                        .append("\n");

            }
        }
        return this.sb;

    }

    public LocalDate getCurrentDayInsideAWeekInterval() {

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -7);
        Date toDate2 = cal2.getTime();

        LocalDate weekIntervalDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return weekIntervalDay;

    }

    public LocalDate getCurrentDayInsideAMonthInterval() {
        Calendar c = Calendar.getInstance();
        int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -monthMaxDays);
        Date toDate2 = cal2.getTime();

        LocalDate weekIntervalDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return weekIntervalDay;


    }

    public StringBuilder printCovidTestsPerInterval(StringBuilder sb) {

        if (company.getData().getMontlhyReportValue() && company.getData().getDayReportValue() && company.getData().getWeekReportValue()) {
            getCovidTestsIntoTheNHSReport(true, true, true, sb);
        } else if (company.getData().getDayReportValue() && company.getData().getWeekReportValue()) {
            getCovidTestsIntoTheNHSReport(true, true, false, sb);
        } else if (company.getData().getWeekReportValue() && company.getData().getMontlhyReportValue()) {
            getCovidTestsIntoTheNHSReport(false, true, true, sb);
        } else if (company.getData().getMontlhyReportValue() && company.getData().getDayReportValue()) {
            getCovidTestsIntoTheNHSReport(true, false, true, sb);
        } else if (company.getData().getWeekReportValue()) {
            getCovidTestsIntoTheNHSReport(false, true, false, sb);
        } else if (company.getData().getDayReportValue()) {
            getCovidTestsIntoTheNHSReport(true, false, false, sb);
        } else if (company.getData().getMontlhyReportValue()) {
            getCovidTestsIntoTheNHSReport(false, false, true, sb);
        }

        return sb;

    }

    public StringBuilder getCovidTestsIntoTheNHSReport(boolean day, boolean week, boolean monthly, StringBuilder sb) {

        if (!week && !monthly) {
            printTheCovidTestsIntoTheNHSReportDay(sb);
        } else if (!day && !monthly) {
            printTheCovidTestsIntoTheNHSReportWeek(sb);
        } else if (day && week) {
            printTheCovidTestsIntoTheNHSReportDay(sb);
            printTheCovidTestsIntoTheNHSReportWeek(sb);
        } else if (day && week && monthly) {
            printTheCovidTestsIntoTheNHSReportDay(sb);
            printTheCovidTestsIntoTheNHSReportWeek(sb);
            printTheCovidTestsIntoTheNHSReportMonthly(sb);
        } else if (!day && !week) {
            printTheCovidTestsIntoTheNHSReportMonthly(sb);
        } else if (day && monthly) {
            printTheCovidTestsIntoTheNHSReportDay(sb);
            printTheCovidTestsIntoTheNHSReportMonthly(sb);
        } else if (week && monthly) {
            printTheCovidTestsIntoTheNHSReportWeek(sb);
            printTheCovidTestsIntoTheNHSReportMonthly(sb);
        }

        return sb;
    }

    public StringBuilder printTheCovidTestsIntoTheNHSReportDay(StringBuilder sb) {

        int dayTests = 0;

        sb.append("\n")
                .append("Today covid tests :")
                .append("\n\n")
                .append(todayDateForCovidReport)
                .append(" : ");

        for (Test t : this.testStore.getPositiveCovidTest()) {
            LocalDate tDate = t.getDate().toLocalDate();

            if (tDate.equals(todayDateForCovidReport)) {
                dayTests++;
            }

        }

        Calendar cal2 = Calendar.getInstance();
        if (!(cal2.get(Calendar.DAY_OF_WEEK) == 1)) {

            sb.append(dayTests)
                    .append(" positive covid tests");
        }

        return sb;

    }

    public StringBuilder printTheCovidTestsIntoTheNHSReportWeek(StringBuilder sb) {

        int interval = Period.between(getCurrentDayInsideAWeekInterval(), todayDateForCovidReport).getDays();
        int[] covidTestsIntoArray = new int[interval + 1];

        sb.append("\n")
                .append("Week report:")
                .append("\n");

        for (int i = 0; i < interval; i++) {

            int interW = 7 - i - 1;

            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DATE, -interW);
            Date toDate2 = cal2.getTime();

            LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

            for (Test t : this.testStore.getPositiveCovidTest()) {
                LocalDate tDate = t.getDate().toLocalDate();
                if (tDate.equals(currentDay)) {
                    covidTestsIntoArray[i] += 1;
                }
            }

            if (!(cal2.get(Calendar.DAY_OF_WEEK) == 1)) {

                sb.append(currentDay).append(" : ").append(covidTestsIntoArray[i]).append(" positive covid tests\n");

            }
        }

        return sb;

    }

    public StringBuilder printTheCovidTestsIntoTheNHSReportMonthly(StringBuilder sb) {

        int interval = Period.between(getCurrentDayInsideAMonthInterval(), todayDateForCovidReport).getDays();
        int[] covidTestsIntoArray = new int[interval + 1];

        Calendar c = Calendar.getInstance();
        int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        sb.append("\n")
                .append("Month report:")
                .append("\n");

        for (int i = 0; i < interval; i++) {

            int interM = monthMaxDays - i - 1;

            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DATE, -interM);
            Date toDate2 = cal2.getTime();

            LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

            for (Test t : this.testStore.getPositiveCovidTest()) {
                LocalDate tDate = t.getDate().toLocalDate();
                if (tDate.equals(currentDay)) {
                    covidTestsIntoArray[i] += 1;
                }
            }

            if (!(cal2.get(Calendar.DAY_OF_WEEK) == 1)) {

                sb.append(currentDay)
                        .append(" : ")
                        .append(covidTestsIntoArray[i])
                        .append(" positive covid tests\n");


            }
        }
        return sb;
    }

    public StringBuilder getSb() {
        return sb;
    }

    private void buildReport() {

    }

}