package app.domain.model;

import app.controller.App;
import app.domain.shared.LinearRegression;
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

    public StringBuilderReport() {
        this.company = App.getInstance().getCompany();
        this.testStore = company.getTestList();
        this.sb = new StringBuilder();
    }

    public StringBuilder printPredictedValues(double xi, StringBuilder sb, int i, LinearRegression linearRegression) {

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

    public void printCovidTestsPerInterval(StringBuilder sb) {

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
        sb.append("\n");
        sb.append("Today covid tests :");
        sb.append("\n\n");
        sb.append(todayDateForCovidReport);
        sb.append(" : ");
        for (Test t : this.testStore.getPositiveCovidTest(company.getTestList().getValidatedTestsList())) {
            LocalDate tDate = t.getDate().toLocalDate();

            if (tDate.equals(todayDateForCovidReport)) {
                dayTests++;
            }

        }

        sb.append(dayTests);
        sb.append(" positive covid tests");
        return sb;
    }

    public StringBuilder printTheCovidTestsIntoTheNHSReportWeek(StringBuilder sb) {

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

            for (Test t : this.testStore.getPositiveCovidTest(company.getTestList().getValidatedTestsList())) {
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

    public StringBuilder printTheCovidTestsIntoTheNHSReportMonthly(StringBuilder sb) {

        int interval = Period.between(getCurrentDayInsideAMonthInterval(), todayDateForCovidReport).getDays();
        int[] covidTestsIntoArray = new int[interval + 1];

        Calendar c = Calendar.getInstance();
        int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        sb.append("\n");
        sb.append("Month report:");
        sb.append("\n");

        for (int i = 0; i < interval; i++) {

            int interM = monthMaxDays - i - 1;

            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DATE, -interM);
            Date toDate2 = cal2.getTime();

            LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

            for (Test t : this.testStore.getPositiveCovidTest(company.getTestList().getValidatedTestsList())) {
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

    public StringBuilder getSb() {
        return sb;
    }


}
