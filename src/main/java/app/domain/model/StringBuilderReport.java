package app.domain.model;

import app.controller.App;
import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;
import app.domain.shared.Regression;
import app.domain.shared.exceptions.InvalidLengthException;
import app.domain.stores.TestStore;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class StringBuilderReport {

    private final Company company;
    private final TestStore testStore;
    private final LocalDate todayDateForCovidReport = LocalDate.now();
    private StringBuilder sb;
    private LinearRegression regressionSimple;
    private MultiLinearRegression regressionMulti;
    private double[] xLinear;
    private double[][] xMulti;
    private double[] yObs;
    private int historicalDays;
    private double significanceLevelAnova;
    private double significanceLevelCoefficient;
    private double significanceLevelEstimated;

    // Constants

    private static final String T_OBS = " t_obs = ";
    private static final String T_0 = "T0 = ";
    private static final String NO_REJECT_H0 = " No reject H0\n\n";
    private static final String REJECT_H0 = " Reject H0\n\n";
    private static final String SIGNIFICANCE_MODEL_ANOVA = "Significance model with Anova";
    private static final String HYP_TEST = "H0: b=0  H1:b<>0 \n";
    private static final String SPACE_1 = "\t\t\t\t\t";
    private static final String SPACE_2 = "\t\t\t\t\t\t";
    private static final String SPACE_3 = "\t\t\t\t\t\t\t\t";
    private static final String SPACE_4 = "\t\t\t";
    private static final String REGRESSION_MODEL = "The regression model is significant.";
    private static final String END_REPORT = "------------------------------------------------------------End of Report------------------------------------------------------------";

    /**
     * Constructor.
     * @param regression the regression
     */

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

    /**
     * Clears the string builder.
     */
    public void clear() {
        StringBuilder sbAux = new StringBuilder();
        this.sb = sbAux;
    }

    /**
     * Sets the confidence values.
     *
     * @param significanceLevelAnova       the ANOVA's significance level
     * @param significanceLevelCoefficient the significance level coefficient
     * @param significanceLevelEstimated   the estimated significance level
     */
    public void setConfidenceValues(double significanceLevelAnova, double significanceLevelCoefficient, double significanceLevelEstimated) {
        this.significanceLevelAnova = significanceLevelAnova;
        this.significanceLevelCoefficient = significanceLevelCoefficient;
        this.significanceLevelEstimated = significanceLevelEstimated;
    }

    /**
     * Sets the simple linear regression values.
     *
     * @param x              the x (array)
     * @param yObs           the observed y (array)
     * @param historicalDays the number of historical days
     */
    public void setvalues(double[] x, double[] yObs, int historicalDays) {
        this.xLinear = x;
        this.yObs = yObs;
        this.historicalDays = historicalDays;
    }

    /**
     * Sets the multi linear regression values.
     *
     * @param x              the x (matrix)
     * @param yObs           the observed y (array)
     * @param historicalDays the number of historical days
     */
    public void setvalues(double[][] x, double[] yObs, int historicalDays) {
        this.xMulti = x;
        this.yObs = yObs;
        this.historicalDays = historicalDays;
    }

    /**
     * Writes the multi linear regression report.
     *
     * @return the multi linear regression report
     */
    public StringBuilder stringConstructionMultiLinearRegression() throws InvalidLengthException {
        sb.append("\n\n").append("------------------------------------------------------------Beginning of Report------------------------------------------------------------").append("\n\n");

        sb.append("The regression model fitted using data from the interval\n")
                .append("^y = ").append(regressionMulti.toString())
                .append("\n\nOther statistics\n")
                .append("R = ").append(String.format("%.4f", regressionMulti.getR())).append("\n\n")
                .append("R^2 = " + String.format("%.4f", regressionMulti.getR2()) + "\n")
                .append("R^2 Adjusted = " + String.format("%.4f", regressionMulti.getR2Adjusted()) + "\n\n")
                .append("Hypothesis tests for regression coefficient\n\n ")
                .append("Hypothesis test for coefficient a\n H0: betta0=0   H1: betta0!=0 \n")
                .append(T_OBS)
                .append(String.format("%.4f", regressionMulti.getCriticValueStudent(significanceLevelCoefficient)) + "\n")
                .append(T_0 + String.format("%.4f", regressionMulti.getTestStatistics(0)) + "\n");

        if (regressionMulti.getCriticValueStudent(significanceLevelCoefficient) < regressionMulti.getTestStatistics(0)) {
            sb.append("Reject H0\n\n");
        } else {
            sb.append(NO_REJECT_H0);
        }

        sb.append("Hypothesis test for coefficient \n H0: betta1=0   H1: betta1!=0 \n")
                .append("t_obs = ")
                .append(String.format("%.4f", regressionMulti.getCriticValueStudent(significanceLevelCoefficient)) + "\n")
                .append(T_0 + String.format("%.4f", regressionMulti.getTestStatistics(1)) + "\n");

        if (regressionMulti.getCriticValueStudent(significanceLevelCoefficient) < regressionMulti.getTestStatistics(1)) {
            sb.append(REJECT_H0);
        } else {
            sb.append(NO_REJECT_H0);
        }

        sb.append("Hypothesis test for coefficient a\n H0: betta2=0   H1: betta2!=0 \n")
                .append(T_OBS)
                .append(String.format("%.4f", regressionMulti.getCriticValueStudent(significanceLevelCoefficient)) + "\n")
                .append(T_0 + String.format("%.4f", regressionMulti.getTestStatistics(2)) + "\n");

        if (regressionMulti.getCriticValueStudent(significanceLevelCoefficient) < regressionMulti.getTestStatistics(2)) {
            sb.append(REJECT_H0);
        } else {
            sb.append(NO_REJECT_H0);
        }
        sb.append("\n\n").append("------------------------------------------------------------Beginning of ANOVA------------------------------------------------------------").append("\n\n");

        sb.append("\n")
                .append(SIGNIFICANCE_MODEL_ANOVA)
                .append(HYP_TEST)
                .append("\n")
                .append("\t\t\t\t" + "df" + SPACE_1 + "SS" + "\t\t\t\t\t\t\t" + "MS" + SPACE_1 + "F" + "\n")
                .append("Regression" + "\t\t").append(this.regressionMulti.getK()).append(SPACE_1).append(String.format("%.4f", this.regressionMulti.getSQr())).append(SPACE_2).append(String.format("%.4f", this.regressionMulti.getMQr()) + SPACE_4 + this.regressionMulti.getF0() + "\n")
                .append("Residual" + "\t\t").append(this.regressionMulti.getN() - (this.regressionMulti.getK() + 1)).append(SPACE_1).append(String.format("%.4f", this.regressionMulti.getSQe())).append(SPACE_2).append(String.format("%.4f", this.regressionMulti.getMQe()) + "\n")
                .append("Total" + SPACE_4).append(this.regressionMulti.getN() - 1).append(SPACE_1).append(String.format("%.4f", this.regressionMulti.getSQt()) + "\n");

        sb.append("\n");
        if (this.regressionMulti.getF0() > this.regressionMulti.getCriticValueFisher(1 - significanceLevelAnova)) {
            sb.append(String.format("Decision: f0 > f %.4f = %.4f %n", 1 - significanceLevelAnova, this.regressionMulti.getCriticValueFisher(1 - significanceLevelAnova))).append("\n")
                    .append("Reject H0\n")
                    .append(REGRESSION_MODEL + "\n");
        } else {
            sb.append(String.format("Decision: f0 < f %.4f = %.4f %n", 1 - significanceLevelAnova, this.regressionMulti.getCriticValueFisher(1 - significanceLevelAnova))).append("\n")
                    .append("Don't reject H0\n")
                    .append(REGRESSION_MODEL + "\n");
        }

        sb.append("\n\n").append("--------------------------------------------------------------------End of ANOVA--------------------------------------------------------------------").append("\n\n");

        sb.append("\n\n").append("-------------------------------------------------------------------------------------Beginning of Prediction-------------------------------------------------------------------------------------").append("\n\n");

        sb.append("Date\t\t\t\t\t\t    " + "Number of OBSERVED positive cases\t\t\t\t\t\t" + "Number of ESTIMATED positive cases\t\t\t\t\t\t").append(100 - (significanceLevelEstimated * 100)).append("% intervals\n");

        for (int i = 0; i < this.yObs.length; i++) {

            int interW = this.historicalDays - i + 1;

            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DATE, -interW);
            Date toDate2 = cal2.getTime();

            LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if ((cal2.get(Calendar.DAY_OF_WEEK) != 1)) {

                this.sb.append(currentDay)
                        .append(SPACE_3)
                        .append(String.format("%.0f", this.yObs[i]))
                        .append("\t\t\t\t\t\t\t\t\t\t\t\t")
                        .append(String.format("%.2f", this.regressionMulti.getEstimate(this.xMulti[i])))
                        .append(SPACE_3 + "\t\t  ")
                        .append("]").append(String.format("%.2f", this.regressionMulti.lowerLimitAnswer(this.xMulti[i], significanceLevelEstimated)))
                        .append(",")
                        .append(String.format("%.2f", this.regressionMulti.upperLimitAnswer(this.xMulti[i], significanceLevelEstimated))).append("[")
                        .append("\n");

            }

        }

        sb.append("\n\n").append("-------------------------------------------------------------------------------------End of Prediction-------------------------------------------------------------------------------------").append("\n\n");

        return sb;
    }

    /**
     * Writes the simple linear regression report.
     *
     * @return the simple linear regression report
     */
    public StringBuilder stringConstructionLinearRegression() {
        sb.append("\n\n").append("------------------------------------------------------------Beginning of Report------------------------------------------------------------").append("\n\n");

        sb.append("The regression model fitted using data from the interval\n")
                .append("^y = ").append(regressionSimple.toString())
                .append("\n\nOther statistics\n")
                .append("R = ").append(String.format("%.4f", regressionSimple.getR())).append("\n\n")
                .append("R^2 = " + String.format("%.4f", regressionSimple.getR2()) + "\n")
                .append("Hypothesis tests for regression coefficient\n\n ")
                .append("Hypothesis test for coefficient a\n H0: a=0   H1: a!=0 \n")
                .append(T_OBS)
                .append(String.format("%.4f", regressionSimple.getCriticValueStudent(significanceLevelCoefficient)) + "\n")
                .append(T_0 + String.format("%.4f", regressionSimple.getTestStatistica()) + "\n");

        if (regressionSimple.getCriticValueStudent(significanceLevelCoefficient) < regressionSimple.getTestStatistica()) {
            sb.append(REJECT_H0);
        } else {
            sb.append(NO_REJECT_H0);
        }

        sb.append("Hypothesis test for coefficient \n H0: b1=0   H1: betta1!=0 \n")
                .append(T_OBS)
                .append(String.format("%.4f", regressionSimple.getCriticValueStudent(significanceLevelCoefficient)) + "\n")
                .append(T_0 + String.format("%.4f", regressionSimple.getTestStatisticb()) + "\n");

        if (regressionSimple.getCriticValueStudent(significanceLevelCoefficient) < regressionSimple.getTestStatisticb()) {
            sb.append(REJECT_H0);
        } else {
            sb.append(NO_REJECT_H0);
        }

        sb.append("\n\n").append("------------------------------------------------------------Beginning of ANOVA------------------------------------------------------------").append("\n\n");

        sb.append("\n")
                .append(SIGNIFICANCE_MODEL_ANOVA)
                .append(HYP_TEST)
                .append("\n")
                .append("\t\t\t\t" + "df" + SPACE_1 + "SS" + "\t\t\t\t\t\t\t" + "MS" + SPACE_2 + "F" + "\n")
                .append("Regression" + "\t\t").append(1).append(SPACE_1).append(String.format("%.4f", this.regressionSimple.getSr())).append(SPACE_2).append(String.format("%.4f", this.regressionSimple.getMsr()) + SPACE_4 + this.regressionSimple.getF0() + "\n")
                .append("Residual" + "\t\t").append(this.regressionSimple.getN() - 2).append(SPACE_1).append(String.format("%.4f", this.regressionSimple.getSe())).append(SPACE_2).append(String.format("%.4f", this.regressionSimple.getMse()) + "\n")
                .append("Total" + SPACE_4).append(this.regressionSimple.getN() - 1).append(SPACE_1).append(String.format("%.4f", this.regressionSimple.getSt()) + "\n");

        sb.append("\n");
        if (this.regressionSimple.getF0() > this.regressionSimple.getCriticValueFisher(1 - significanceLevelAnova)) {
            sb.append(String.format("Decision: f0 > f %.4f = %.4f %n", 1 - significanceLevelAnova, this.regressionSimple.getCriticValueFisher(1 - significanceLevelAnova))).append("\n")
                    .append("Reject H0\n")
                    .append(REGRESSION_MODEL + "\n");
        } else {
            sb.append(String.format("Decision: f0 < f %.4f = %.4f %n", 1 - significanceLevelAnova, this.regressionSimple.getCriticValueFisher(1 - significanceLevelAnova))).append("\n")
                    .append("Don't reject H0\n")
                    .append(REGRESSION_MODEL + "\n");
        }

        sb.append("\n\n").append("-----------------------------------------------------------------End of ANOVA-----------------------------------------------------------------").append("\n\n");

        sb.append("\n\n").append("-------------------------------------------------------------------------------------Beginning of Prediction-------------------------------------------------------------------------------------").append("\n\n");

        sb.append("Date\t\t\t\t\t\t    " + "Number of OBSERVED positive cases\t\t\t\t\t\t" + "Number of ESTIMATED positive cases\t\t\t\t\t\t").append(100 - (significanceLevelEstimated * 100)).append("% intervals\n");

        for (int i = 0; i < this.yObs.length; i++) {

            int interW = this.historicalDays - i + 1;

            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DATE, -interW);
            Date toDate2 = cal2.getTime();

            LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if ((cal2.get(Calendar.DAY_OF_WEEK) != 1)) {

                this.sb.append(currentDay)
                        .append(SPACE_3)
                        .append(String.format("%.0f", this.yObs[i]))
                        .append("\t\t\t\t\t\t\t\t\t\t\t\t");
                if (this.regressionSimple.predict(this.xLinear[i]) < 0) {
                    this.sb.append("0");
                } else {
                    this.sb.append(String.format("%.2f", this.regressionSimple.predict(this.xLinear[i])));
                }
                this.sb.append(SPACE_3 + "\t\t  ")
                        .append("]").append(String.format("%.2f", this.regressionSimple.lowerLimitAnswer(this.xLinear[i], significanceLevelEstimated)))
                        .append(",")
                        .append(String.format("%.2f", this.regressionSimple.upperLimitAnswer(this.xLinear[i], significanceLevelEstimated))).append("[")
                        .append("\n");


            }
        }

        sb.append("\n\n").append("-------------------------------------------------------------------------------------End of Prediction-------------------------------------------------------------------------------------").append("\n\n");

        return sb;
    }

    /**
     * Gets the current day inside an interval of a week.
     *
     * @return the current day inside an interval of a week
     */
    private LocalDate getCurrentDayInsideAWeekInterval() {

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -7);
        Date toDate2 = cal2.getTime();

        return toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    }

    /**
     * Gets the current day inside an interval of a month.
     *
     * @return the current day inside an interval of a month
     */
    private LocalDate getCurrentDayInsideAMonthInterval() {
        Calendar c = Calendar.getInstance();
        int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -monthMaxDays);
        Date toDate2 = cal2.getTime();

        return toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    }

    /**
     * Prints the COVID-19 tests per interval.
     *
     * @param selection the selection for the report generation time
     * @return the COVID-19 tests per interval
     */
    public StringBuilder printCovidTestsPerInterval(String selection) {

        if (selection.equals("Day")) {
            printTheCovidTestsIntoTheNHSReportDay();
        } else if (selection.equals("Week")) {
            printTheCovidTestsIntoTheNHSReportWeek();
        } else {
            printTheCovidTestsIntoTheNHSReportMonthly();
        }
        return sb;

    }

    /**
     * Prints the COVID-19 tests into the daily NHS report.
     *
     * @return the daily report with the printed COVID-19 tests
     */
    public StringBuilder printTheCovidTestsIntoTheNHSReportDay() {

        int dayTests = 0;

        sb.append("\n")
                .append("Today covid tests :")
                .append("\n\n")
                .append(todayDateForCovidReport)
                .append(" : ");

        for (Test t : this.testStore.getPositiveCovidTest()) {
            LocalDate tDate = t.getValidatedDate().toLocalDate();

            if (tDate.equals(todayDateForCovidReport)) {
                dayTests++;
            }

        }

        Calendar cal2 = Calendar.getInstance();
        if ((cal2.get(Calendar.DAY_OF_WEEK) != 1)) {

            sb.append(dayTests)
                    .append(" positive covid tests");
        }
        sb.append("\n\n").append(END_REPORT).append("\n\n");
        return sb;

    }

    /**
     * Prints the COVID-19 tests into the weekly NHS report.
     *
     * @return the weekly report with the printed COVID-19 tests
     */
    public StringBuilder printTheCovidTestsIntoTheNHSReportWeek() {

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
                LocalDate tDate = t.getValidatedDate().toLocalDate();
                if (tDate.equals(currentDay)) {
                    covidTestsIntoArray[i] += 1;
                }
            }

            if ((cal2.get(Calendar.DAY_OF_WEEK) != 1)) {

                sb.append(currentDay).append(" : ").append(covidTestsIntoArray[i]).append(" positive covid tests\n");

            }
        }
        sb.append("\n\n").append(END_REPORT).append("\n\n");
        return sb;
    }

    /**
     * Prints the COVID-19 tests into the monthly NHS report.
     *
     * @return the monthly report with the printed COVID-19 tests
     */
    public StringBuilder printTheCovidTestsIntoTheNHSReportMonthly() {

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
                LocalDate tDate = t.getValidatedDate().toLocalDate();
                if (tDate.equals(currentDay)) {
                    covidTestsIntoArray[i] += 1;
                }
            }

            if ((cal2.get(Calendar.DAY_OF_WEEK) != 1)) {

                sb.append(currentDay)
                        .append(" : ")
                        .append(covidTestsIntoArray[i])
                        .append(" positive covid tests\n");


            }
        }
        sb.append("\n\n").append(END_REPORT).append("\n\n");
        return sb;
    }

    /**
     * Gets the string builder.
     *
     * @return the string builder
     */
    public StringBuilder getSb() {
        return sb;
    }
}