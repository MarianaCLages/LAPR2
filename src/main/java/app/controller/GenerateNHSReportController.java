package app.controller;

import app.domain.model.*;
import app.domain.shared.LinearRegression;
import app.domain.shared.exceptions.*;
import app.domain.stores.TestStore;

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

    private Calendar cal;
    private LocalDate beginDate;
    private LocalDate todayDate = LocalDate.now();

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
            sb = this.stringBuilderReport.printPredictedValues(xi, sb, i, linearRegression);
            i++;
        }

        this.stringBuilderReport.printCovidTestsPerInterval(sb);

    }

    public void linearRegressionWithCovidTests() {


        List<Test> validTests = getListTestsInsideTheHistoricalDays(company.getTestList().getValidatedTestsList());
        List<Test> covidTests = this.testStore.getPositiveCovidTest(validTests);

        List<Test> validTestInsideInterval = getListTestsInsideDateInterval(company.getTestList().getValidatedTestsList());
        List<Test> covidTestInsideInterval = this.testStore.getPositiveCovidTest(validTestInsideInterval);

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
            sb = this.stringBuilderReport.printPredictedValues(xi, sb, i, linearRegression);
            i++;

        }

        this.stringBuilderReport.printCovidTestsPerInterval(sb);

    }

    public int getIntervalDate(LocalDate start, LocalDate end) {
        int n;
        return n = Period.between(start, end).getDays();
    }

    public LocalDate getStartDate(String Text) {

        int n = Integer.parseInt(Text);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -n);
        Date toDate = cal.getTime();

        LocalDate startDate;

        return startDate = toDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

    }

    public void setDates() {

        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -company.getData().getHistoricalDaysInt());
        Date toDate = cal.getTime();

        beginDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

    }

    public List<Client> getClientsWithTests() {

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

    public List<Test> getClientsWithTestsListWithTests() {

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

    public double[] getClientAge(List<Client> clientList, int space) {

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

    public double[] getClientAgeInsideTheInterval(List<Client> clientList, int space) {

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

    public LocalDate getCurrentDay(int i) {

        int interV = company.getData().getHistoricalDaysInt() - i;

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -interV);
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        return currentDay;

    }

    public LocalDate getCurrentDayInsideInterval(int i) {

        int startDayInterval = Period.between(company.getData().getIntervalStartDate(), todayDate).getDays();

        int interW = startDayInterval - i;

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -interW);
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        return currentDay;

    }

    public List<Test> getListTestsInsideDateInterval(List<Test> list) {

        List<Test> validTests = new ArrayList<>();

        for (Test t : list) {
            LocalDate testDate = t.getDate().toLocalDate();

            if (Period.between(company.getData().getIntervalStartDate(), testDate).getDays() >= 0 && Period.between(testDate, company.getData().getIntervalEndDate()).getDays() >= 0) {
                validTests.add(t);
            }
        }

        return validTests;

    }

    public double[] getCovidTestsPerDayIntoArrayInsideInterval(List<Test> testList, int space) {

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

    public double[] getCovidTestsPerDayIntoArray(List<Test> testList, int space) {

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

    public List<Test> getListTestsInsideTheHistoricalDays(List<Test> list) {

        List<Test> validCovidTests = new ArrayList<>();

        for (Test t : list) {
            LocalDate testDate = t.getDate().toLocalDate();

            if (Period.between(beginDate, testDate).getDays() >= 0 && Period.between(testDate, todayDate).getDays() >= 0) {
                validCovidTests.add(t);
            }
        }

        return validCovidTests;

    }

    public void setInformation(boolean dayReport, boolean weekReport, boolean monthlyReport, LocalDate start, LocalDate end, String historicalDays, String confidenceLevel) throws DateEmptyException, DateInvalidException, HistoricalDaysInvalidException, HistoricalDaysEmptyException, ConfidenceLevelICEmptyException, ConfidenceLevelInvalidException {

        Data data = getData();

        data.setIntervalDates(getIntervalDate(start, end));
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


    public Calendar getCal() {
        return cal;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public LocalDate getTodayDate() {
        return todayDate;
    }
    
    public Data getData() {
        return data;
    }

    public StringBuilder getSb() {
        return this.stringBuilderReport.getSb();
    }

}
