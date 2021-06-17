package app.controller;

//import app.domain.model.*;
//import app.domain.shared.LinearRegression;
//import app.domain.stores.TestStore;
//
//import java.time.LocalDate;
//import java.time.Period;
//import java.time.ZoneId;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//public class GenerateNHSReportController {
//
//    private Company company;
//    private TestStore testStore;
//    private StringBuilderReport stringBuilderReport;
//    private StringBuilder sb = stringBuilderReport.getSb();
//    private Data data;
//
//    private Calendar cal;
//    private LocalDate beginDate;
//    private LocalDate todayDate = LocalDate.now();
//
//    public GenerateNHSReportController() {
//        this(App.getInstance().getCompany());
//    }
//
//    public GenerateNHSReportController(Company company) {
//
//        this.company = company;
//        this.testStore = company.getTestList();
//        this.stringBuilderReport = new StringBuilderReport();
//        this.data = company.getData();
//
//    }
////
////    public void linearRegressionWithMeanAge() {
////
////        List<Test> validTests = this.testStore.getListTestsInsideTheHistoricalDays(company.getTestList().getValidatedTestsList());
////        List<Client> clientsWithTests = this.testStore.getClientsWithTests();
////
////        List<Test> validTestsInsideInterval = this.testStore.getListTestsInsideDateInterval(company.getTestList().getValidatedTestsList());
////
////        double[] ages = this.testStore.getClientAge(clientsWithTests, company.getData().getHistoricalDaysInt() + 1);
////        double[] covidTestsPerDayInsideTheHistoricalInterval = this.testStore.getCovidTestsPerDayIntoArray(validTestsInsideInterval, company.getData().getHistoricalDaysInt() + 1);
////
////        double[] agesInsideTheDateInterval = this.testStore.getClientAgeInsideTheInterval(clientsWithTests, company.getData().getDifferenceInDates() + 1);
////        double[] covidTestsPerDayInsideTheIntervalOfDates = this.testStore.getCovidTestsPerDayIntoArrayInsideInterval(validTests, company.getData().getDifferenceInDates() + 1);
////
////        LinearRegression linearRegression = new LinearRegression(agesInsideTheDateInterval, covidTestsPerDayInsideTheIntervalOfDates);
////
////        StringBuilder sb = new StringBuilder();
////        sb.append(linearRegression.toString());
////        sb.append("\n");
////
////        int i = 1;
////        for (double xi : ages) {
////            sb = this.stringBuilderReport.printPredictedValues(xi, sb, i, linearRegression);
////            i++;
////        }
////
////        this.stringBuilderReport.printCovidTestsPerInterval(sb);
////
////    }
////
////    public void linearRegressionWithCovidTests() {
////
////
////        List<Test> validTests = this.testStore.getListTestsInsideTheHistoricalDays(company.getTestList().getValidatedTestsList());
////        List<Test> covidTests = this.testStore.getPositiveCovidTest(validTests);
////
////        List<Test> validTestInsideInterval = this.testStore.getListTestsInsideDateInterval(company.getTestList().getValidatedTestsList());
////        List<Test> covidTestInsideInterval = this.testStore.getPositiveCovidTest(validTestInsideInterval);
////
////        double[] positiveCovidTestsPerDayInsideTheHistoricalInterval = this.testStore.getCovidTestsPerDayIntoArray(covidTests, company.getData().getHistoricalDaysInt() + 1);
////        double[] covidTestsPerDayInsideTheHistoricalInterval = this.testStore.getCovidTestsPerDayIntoArray(validTests, company.getData().getHistoricalDaysInt() + 1);
////
////        double[] positiveCovidTestsPerDayInsideTheDateInterval = this.testStore.getCovidTestsPerDayIntoArrayInsideInterval(covidTestInsideInterval, company.getData().getDifferenceInDates() + 1);
////        double[] covidTestsPerDayInsideTheDateInterval = this.testStore.getCovidTestsPerDayIntoArrayInsideInterval(validTestInsideInterval, company.getData().getDifferenceInDates() + 1);
////
////        LinearRegression linearRegression = new LinearRegression(positiveCovidTestsPerDayInsideTheDateInterval, covidTestsPerDayInsideTheDateInterval);
////
////        StringBuilder sb = new StringBuilder();
////        sb.append(linearRegression.toString());
////        sb.append("\n");
////
////        int i = 1;
////        for (double xi : positiveCovidTestsPerDayInsideTheHistoricalInterval) {
////            sb = this.stringBuilderReport.printPredictedValues(xi, sb, i, linearRegression);
////            i++;
////
////        }
////
////        this.stringBuilderReport.printCovidTestsPerInterval(sb);
////
////    }
////
////    public StringBuilder getSb() {
////        return sb;
////    }
////
////    public int getIntervalDate(LocalDate start, LocalDate end) {
////        int n;
////        return n = Period.between(start, end).getDays();
////    }
////
////    public LocalDate getStartDate(String Text) {
////
////        int n = Integer.parseInt(Text);
////
////        Calendar cal = Calendar.getInstance();
////        cal.add(Calendar.DATE, -n);
////        Date toDate = cal.getTime();
////
////        LocalDate startDate;
////
////        return startDate = toDate.toInstant()
////                .atZone(ZoneId.systemDefault())
////                .toLocalDate();
////
////    }
////
////    public void setDates() {
////
////        cal = Calendar.getInstance();
////        cal.add(Calendar.DATE, -company.getData().getHistoricalDaysInt());
////        Date toDate = cal.getTime();
////
////        beginDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)
////
////    }
////
////    public Calendar getCal() {
////        return cal;
////    }
////
////    public LocalDate getBeginDate() {
////        return beginDate;
////    }
////
////    public LocalDate getTodayDate() {
////        return todayDate;
////    }
////
////    public LocalDate getTodayDateForCovidReport() {
////        return todayDate;
////    }
////
////    public Data getData() {
////        return data;
////    }
//}
