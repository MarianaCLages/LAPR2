package app.domain.stores;

import app.domain.model.*;
import app.domain.shared.Constants;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

/**
 * Class that represents an List of Tests in the system
 */
public class TestStore implements Serializable {
    private final List<Test> array;
    private List<String> testSortedListString = new ArrayList<>();
    private Test t;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of Test
     */
    public TestStore() {
        this.array = new ArrayList<>();
    }

    /**
     * This method creates a new Test object by calling his constructor
     *
     * @param testNhsNumber unique code that identifies the test
     * @param clientTin     unique code that identifies the client associated with the test
     * @param testType      type of this test
     * @param catList       list of parameters categories that are measured in this test
     * @param paList        list of parameters that are measured in this test
     * @return boolean value representing the test validity within the system requirements
     */
    public Test createTest(String testNhsNumber, String clientTin, TestType testType, List<ParameterCategory> catList, List<Parameter> paList) {

        this.t = new Test(getTestId(), testNhsNumber, clientTin, testType, catList, paList);
        this.t.addTestParameter();

        return t;
    }

    /**
     * method creates the unique id of a test, the unique id is a number with 15 digits and increases conforming the number of tests in the system, for example if there are 5 tests in the system the id created will be "000000000000006"
     *
     * @return string that represents the id of the test
     */
    public String getTestId() {
        int id = this.array.size() + 1;
        StringBuilder testNumber = new StringBuilder(String.valueOf(id));

        while (testNumber.length() < 15) {
            testNumber.insert(0, "0");
        }

        return testNumber.toString();
    }

    /**
     * checks if the test is valid, in order to be valid the test must not be null, the object cannot already be stored in the list and the object cannot be equal to an object already existent in the list
     *
     * @return boolean value representing the test validity
     */
    public boolean validateTest() {
        return this.t != null && !contains(this.t) && !exists(this.t);
    }

    /**
     * checks if the test exists in the list by comparing the nhs number of each test
     *
     * @param t test object to be compared
     * @return boolean value that represents the existence of the test
     */
    private boolean exists(Test t) {
        for (Test t1 : this.array) {
            if (t.getTestNhsNumber().equals(t1.getTestNhsNumber())) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if the test object already exits in the list
     *
     * @param t test object to be tested
     * @return boolean value that represents the existence of the object
     */
    private boolean contains(Test t) {
        return array.contains(t);
    }

    /**
     * this methods adds the test object to the list
     *
     * @return boolean value that represents the success of the operation
     */
    public boolean saveTest() {
        if (validateTest()) {
            array.add(this.t);
            return true;
        }
        return false;
    }

    /**
     * @return the list of all the tests in the system
     */
    public List<Test> getList() {
        return this.array;
    }

    /**
     * @return a string that represents the test
     */
    public String getTest() {
        return this.t.toString();
    }

    /**
     * @param testId unique nhs code that represents the test
     * @return the test that is associated with nhs code if there is not a test with this code returns null
     */
    public Test getTestByCode(String testId) {
        for (Test t1 : this.array) {
            if (t1.getTestNhsNumber().equals(testId)) {
                return t1;
            }
        }
        return null;
    }

    /**
     * @return the list of tests which state is "DIAGNOSTIC_MADE"
     */
    public List<Test> getListOfTestsToValidate() {
        List<Test> listToValidate = new ArrayList<>();
        for (Test test : this.array) {
            if (test.getState().equals("DIAGNOSTIC_MADE")) {
                listToValidate.add(test);
            }
        }
        return listToValidate;
    }

    /**
     * @return the list of tests which state is "SAMPLE_ANALYSED"
     */
    public TestStore getListOfTestsAnalysed() {
        TestStore listToReport = new TestStore();
        for (Test test : this.array) {
            if (test.getState().equals("SAMPLE_ANALYSED")) {
                listToReport.addTest(test);
            }
        }
        return listToReport;
    }

    /**
     * adds the test to the list without validation
     *
     * @param t test instance that is going to be saved to the list
     * @return boolean value that represents the success of the operation
     */
    public boolean addTest(Test t) {
        return array.add(t);
    }


    public List<Test> sortDate(String clientTin) {
        Comparator<Test> comparator1 = new Comparator<Test>() {
            @Override
            public int compare(Test o1, Test o2) {
                LocalDateTime d1 = o1.getDate();
                Date date1 = java.util.Date.from(d1.atZone(ZoneId.systemDefault()).toInstant());
                LocalDateTime d2 = o2.getDate();
                Date date2 = java.util.Date.from(d2.atZone(ZoneId.systemDefault()).toInstant());

                if (date1.before(date2)) {
                    return 1;
                } else if (date1.after(date2)) {
                    return -1;
                } else {
                    return 0;
                }

            }

        };
        List<Test> testList2 = getTestByTin(clientTin);
        Collections.sort(testList2, comparator1);

        for (Test test : testList2) {
            testSortedListString.add(test.toString()+"\n");
        }

        return testList2;
    }

    public List<String> getTestSortedListString() {
        return testSortedListString;
    }

    public String toStringSortedList(String clientTin) {
        for (Test test : sortDate(clientTin)) {
            System.out.println(test);
        }
        return null;
    }

    public List<Test> getTestByTin(String clientTin) {
        List<Test> tinList = new ArrayList<>();
        for (Test tinTests : this.array) {

            if (tinTests.getClientTin().equals(clientTin)) {
                tinList.add(tinTests);
            }
        }
        return tinList;
    }

    public Test getT() {
        return t;
    }

    public List<Test> getValidatedTestList(Client client) {
        List<Test> validatedTest = new ArrayList<>();

        for (Test test : array) {
            if (client.getTinNumber().equalsIgnoreCase(test.getClientTin()) && test.getState().equals("VALIDATED")) {
                validatedTest.add(test);
            }
        }
        return validatedTest;
    }

    public List<Test> getValidatedTestsListCovid() {

        List<Test> testList = new ArrayList<>();

        for (Test test : array) {
            if (test.getState().equals("VALIDATED") && test.getTestType().getTestID().equals("COV19")) {
                testList.add(test);
            }
        }

        return testList;

    }

    public List<Test> getValidatedTestsListAll() {

        List<Test> testList = new ArrayList<>();

        for (Test test : array) {
            if (test.getState().equals("VALIDATED")) {
                testList.add(test);
            }
        }

        return testList;

    }


    public List<Test> getWaitingResultsTestsList() {

        List<Test> testList = new ArrayList<>();

        for (Test test : array) {
            if (test.getState().equals("CREATED") || test.getState().equals("SAMPLE_COLLECTED")) {
                testList.add(test);
            }
        }

        return testList;

    }

    public int getWaitingResultsTestsListTime(LocalDateTime timeBeg, LocalDateTime timeEnd) {

        int count = 0;

        for (Test test : array) {
            if (timeBeg.isBefore(test.getCreatedDate()) && timeEnd.isAfter(test.getCreatedDate())) {
                count++;
            }
        }

        return count;
    }

    public int getWaitingDiagnosisTestsListTime(LocalDateTime timeBeg, LocalDateTime timeEnd) {

        int count = 0;

        for (Test test : array) {
            if (timeBeg.isBefore(test.getSampleCreatedDate()) && timeEnd.isAfter(test.getSampleCreatedDate())) {
                count++;
            }
        }

        return count;
    }

    public int getValidatedTestsListTime(LocalDateTime timeBeg, LocalDateTime timeEnd) {

        int count = 0;

        for (Test test : array) {
            if (timeBeg.isBefore(test.getValidatedDate()) && timeEnd.isAfter(test.getValidatedDate())) {
                count++;
            }
        }

        return count;
    }

    public List<Test> getTestListArray() {
        return array;
    }

    @Override
    public String toString() {
        for (Test test : array) {
            System.out.println(test.toString());
        }
        return null;
    }

    public List<Test> getPositiveCovidTest() {

        List<Test> covidTestList = new ArrayList<>();

        for (Test t : getValidatedTestsListCovid()) {
            for (TestParameter t1 : t.getTestParam()) {
                if (t1 != null) {
                    if (t1.getpCode().equals(Constants.VALID_COVID_PARAMETER) && t1.getTestParameterResult().getResult() > Constants.VALID_COVID_PARAMETER_VALUE) {
                        covidTestList.add(t);
                    }
                }
            }
        }

        return covidTestList;

    }

    private Calendar cal;
    private LocalDate beginDate;
    private LocalDate todayDate = LocalDate.now();

    public int getIntervalDate(LocalDate start, LocalDate end) {
        return Period.between(start, end).getDays();
    }

    public void setDates(int historicalDaysInt) {

        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -historicalDaysInt);
        Date toDate = cal.getTime();

        beginDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

    }

    public List<Client> getClientsWithTests(List<Client> clientList) {

        List<Test> validTestList = getListTestsInsideTheHistoricalDays();

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

    public List<Test> getClientsWithTestsListWithTests(List<Client> clientList) {

        List<Test> validTestList = getListTestsInsideTheHistoricalDays();

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

    public double[] getClientAge(List<Client> clientList, int historicalDaysInt) {

        double[] clientsAges = new double[historicalDaysInt + 1]; // O mais 1 é pq é preciso registar o dia de "HJ"

        int n = 0;
        int x = 0;
        int sum = 0;
        int age = 0;

        for (int i = 0; i < historicalDaysInt + 1; i++) {

            LocalDate currentDay = getCurrentDay(i, historicalDaysInt);

            for (Test t1 : getClientsWithTestsListWithTests(clientList)) {

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

    public double[] getClientAgeInsideTheInterval(List<Client> clientList, int space,LocalDate startDate) {

        double[] clientsAges = new double[space]; // O mais 1 é pq é preciso registar o dia de "HJ"

        int n = 0;
        int x = 0;
        int sum = 0;
        int age = 0;

        for (int i = 0; i < space; i++) {

            LocalDate currentDay = getCurrentDayInsideInterval(i,startDate);

            for (Test t1 : getClientsWithTestsListWithTests(clientList)) {

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

    public LocalDate getCurrentDay(int i, int historicalDaysInt) {

        int interV = historicalDaysInt - i;

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -interV);
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        return currentDay;

    }

    public LocalDate getCurrentDayInsideInterval(int i, LocalDate startDateInterval) {

        int startDayInterval = Period.between(startDateInterval, todayDate).getDays();

        int interW = startDayInterval - i;

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -interW);
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        return currentDay;

    }

    public List<Test> getListTestsInsideDateInterval(LocalDate startDateInterval, LocalDate endDateInterval) {

        List<Test> validTests = new ArrayList<>();

        for (Test t : getValidatedTestsListCovid()) {
            LocalDate testDate = t.getDate().toLocalDate();

            if (Period.between(startDateInterval, testDate).getDays() >= 0 && Period.between(testDate, endDateInterval).getDays() >= 0) {
                validTests.add(t);
            }
        }

        return validTests;

    }

    public double[] getCovidTestsPerDayIntoArrayInsideInterval(int space, LocalDate startDateInterval) {

        double[] positiveCovidTestsPerDay = new double[space];

        for (int i = 0; i < space; i++) {

            LocalDate currentDay = getCurrentDayInsideInterval(i, startDateInterval);

            for (Test t : getListTestsInsideTheHistoricalDays()) {
                LocalDate testDate = t.getDate().toLocalDate();
                if (testDate.equals(currentDay)) {
                    positiveCovidTestsPerDay[i] += 1;
                }
            }

        }

        return positiveCovidTestsPerDay;

    }
    public double[] getPositiveCovidTestsPerDayIntoArrayInsideInterval(int space, LocalDate startDateInterval) {

        double[] positiveCovidTestsPerDay = new double[space];

        for (int i = 0; i < space; i++) {

            LocalDate currentDay = getCurrentDayInsideInterval(i, startDateInterval);

            for (Test t : getPositiveCovidTest()){
                LocalDate testDate = t.getDate().toLocalDate();
                if (testDate.equals(currentDay)) {
                    positiveCovidTestsPerDay[i] += 1;
                }
            }

        }

        return positiveCovidTestsPerDay;

    }

    public double[] getCovidTestsPerDayIntoArray(int historicalDaysInt) {

        double[] positiveCovidTestsPerDay = new double[historicalDaysInt + 1];

        for (int i = 0; i < historicalDaysInt + 1; i++) {

            LocalDate currentDay = getCurrentDay(i, historicalDaysInt);

            for (Test t : getPositiveCovidTest()) {
                LocalDate testDate = t.getDate().toLocalDate();
                if (testDate.equals(currentDay)) {
                    positiveCovidTestsPerDay[i] += 1;
                }
            }

        }

        return positiveCovidTestsPerDay;

    }

    public List<Test> getListTestsInsideTheHistoricalDays() {

        List<Test> validCovidTests = getValidatedTestsListCovid();
        List<Test> covidTestsInterval = new ArrayList<>();

        for (Test t : validCovidTests) {
            LocalDate testDate = t.getDate().toLocalDate();

            if (Period.between(beginDate, testDate).getDays() >= 0 && Period.between(testDate, todayDate).getDays() >= 0) {
                covidTestsInterval.add(t);
            }
        }

        return covidTestsInterval;

    }




}