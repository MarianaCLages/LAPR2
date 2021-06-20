package app.domain.stores;

import app.domain.model.*;
import app.domain.shared.Constants;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Class that represents an List of Tests in the system
 */
public class TestStore implements Serializable {
    private final List<Test> array;
    private List<String> testSortedListString = new ArrayList<>();
    private Test t;

    private Calendar cal;
    private LocalDate beginDate;
    private LocalDate todayDate = LocalDate.now();

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

    /**
     * Sorts the dates of the tests.
     *
     * @param clientTin the client's TIN number
     * @return the test list sorted by dates
     */
    public List<Test> sortDate(String clientTin) {
        Comparator<Test> comparator1 = (o1, o2) -> {
            LocalDateTime d1 = o1.getDate();
            Date date1 = Date.from(d1.atZone(ZoneId.systemDefault()).toInstant());
            LocalDateTime d2 = o2.getDate();
            Date date2 = Date.from(d2.atZone(ZoneId.systemDefault()).toInstant());

            if (date1.before(date2)) {
                return 1;
            } else if (date1.after(date2)) {
                return -1;
            } else {
                return 0;
            }

        };
        List<Test> testList2 = getTestByTin(clientTin);
        Collections.sort(testList2, comparator1);

        for (Test test : testList2) {
            testSortedListString.add(test.toString() + "\n");
        }
        return testList2;
    }

    /**
     * Gets the test sorted list.
     *
     * @return the test sorted list
     */
    public List<String> getTestSortedListString() {

        return testSortedListString;
    }

    /**
     * Transforms the test sorted list into a string.
     *
     * @param clientTin the client's TIN number
     * @return the test sorted list into a string
     */
    public String toStringSortedList(String clientTin) {
        for (Test test : sortDate(clientTin)) {
            System.out.println(test);
        }
        return null;
    }

    /**
     * Gets the test by the client's TIN number.
     *
     * @param clientTin the client's TIN number
     * @return the test
     */
    public List<Test> getTestByTin(String clientTin) {
        List<Test> tinList = new ArrayList<>();
        for (Test tinTests : this.array) {

            if (tinTests.getClientTin().equals(clientTin)) {
                tinList.add(tinTests);
            }
        }
        return tinList;
    }

    /**
     * Gets the test.
     *
     * @return the test
     */
    public Test getT() {
        return t;
    }

    /**
     * Gets the validated tests for a given client.
     *
     * @param client the client
     * @return the list of validated tests of a given client
     */
    public List<Test> getValidatedTestList(Client client) {
        List<Test> validatedTest = new ArrayList<>();

        for (Test test : array) {
            if (client.getTinNumber().equalsIgnoreCase(test.getClientTin()) && test.getState().equals(Constants.VALIDATED)) {
                validatedTest.add(test);
            }
        }
        return validatedTest;
    }

    /**
     * Gets the COVID-19 validated tests.
     *
     * @return the list of COVID-19 validated tests
     */
    public List<Test> getValidatedTestsListCovid() {

        List<Test> testList = new ArrayList<>();

        for (Test test : array) {
            if (test.getState().equals(Constants.VALIDATED) && test.getTestType().getTestID().equals(Constants.COV19)) {
                testList.add(test);
            }
        }
        return testList;
    }

    /**
     * Gets all the validated tests.
     *
     * @return the list of all the validated tests
     */
    public List<Test> getValidatedTestsListAll() {

        List<Test> testList = new ArrayList<>();

        for (Test test : array) {
            if (test.getState().equals(Constants.VALIDATED)) {
                testList.add(test);
            }
        }
        return testList;
    }

    /**
     * Gets the tests that are still waiting for results.
     *
     * @return the list of tests that are still waiting for results
     */
    public List<Test> getWaitingResultsTestsList() {

        List<Test> testList = new ArrayList<>();

        for (Test test : array) {
            if (test.getState().equals(Constants.CREATED) || test.getState().equals(Constants.SAMPLE_COLLECTED)) {
                testList.add(test);
            }
        }
        return testList;
    }

    /**
     * Gets the amount of tests that are still waiting for results in a given time.
     *
     * @param timeBeg the beginning time
     * @param timeEnd the ending time
     * @return the amount of tests that are still waiting for results in a given time
     */
    public int getWaitingResultsTestsListTime(LocalDateTime timeBeg, LocalDateTime timeEnd) {

        int count = 0;

        for (Test test : array) {
            if (timeBeg.isBefore(test.getCreatedDate()) && timeEnd.isAfter(test.getCreatedDate())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the amount of tests that are still waiting for the diagnosis in a given time.
     *
     * @param timeBeg the beginning time
     * @param timeEnd the ending time
     * @return the amount of tests that are still waiting for the diagnosis in a given time
     */
    public int getWaitingDiagnosisTestsListTime(LocalDateTime timeBeg, LocalDateTime timeEnd) {

        int count = 0;

        for (Test test : array) {
            if (timeBeg.isBefore(test.getSampleCreatedDate()) && timeEnd.isAfter(test.getSampleCreatedDate())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the amount of tests that are validated in a given time.
     *
     * @param timeBeg the beginning time
     * @param timeEnd the ending time
     * @return the amount of tests that are validated in a given time
     */
    public int getValidatedTestsListTime(LocalDateTime timeBeg, LocalDateTime timeEnd) {

        int count = 0;

        for (Test test : array) {
            if (timeBeg.isBefore(test.getValidatedDate()) && timeEnd.isAfter(test.getValidatedDate())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the test list.
     *
     * @return the test list
     */
    public List<Test> getTestListArray() {
        return array;
    }

    /**
     * Returns a string representation of the test list.
     *
     * @return a string representation of the test list
     */
    @Override
    public String toString() {
        for (Test test : array) {
            System.out.println(test.toString());
        }
        return "";
    }

    /**
     * Gets the tests that are still waiting for results.
     *
     * @return the list of the tests that are still waiting for results
     */
    public List<Test> getWaitingResult() {
        List<Test> testList = new ArrayList<>();
        for (Test t : array) {
            if (t.getState().equals(Constants.SAMPLE_COLLECTED) || t.getState().equals(Constants.CREATED)) {
                testList.add(t);
            }

        }
        return testList;
    }

    /**
     * Gets the tests that are still waiting for the diagnosis.
     *
     * @return the list of the tests that are still waiting for the diagnosis
     */
    public List<Test> getWaitingDiagnosis() {
        List<Test> testList = new ArrayList<>();
        for (Test t : array) {
            if (t.getState().equals(Constants.SAMPLE_COLLECTED) || t.getState().equals("CREATED") || t.getState().equals("SAMPLE_ANALYSED")) {
                testList.add(t);
            }

        }
        return testList;
    }

    /**
     * Gets the positive COVID-19 tests.
     *
     * @return the list of the positive COVID-19 tests
     */
    public List<Test> getPositiveCovidTest() {

        List<Test> covidTestList = new ArrayList<>();

        for (Test t : getValidatedTestsListCovid()) {
            for (TestParameter t1 : t.getTestParam()) {
                if (t1 != null) {
                    if (t1.getpCode().equals(Constants.IG_GAN) && t1.getTestParameterResult().getResult() > Constants.VALID_COVID_PARAMETER_VALUE) {
                        covidTestList.add(t);
                    }
                }
            }
        }
        return covidTestList;
    }

    /**
     * Gets the number of days between two given dates.
     *
     * @param start the start date
     * @param end   the end date
     * @return the number of days between two given dates
     */
    public int getIntervalDate(LocalDate start, LocalDate end) {
        return Period.between(start, end).getDays();
    }

    /**
     * Sets the dates.
     *
     * @param historicalDaysInt the number of historical days
     */
    public void setDates(int historicalDaysInt) {

        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -historicalDaysInt);
        Date toDate = cal.getTime();

        beginDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)
    }

    /**
     * Gets all the clients with registered tests.
     *
     * @param clientList the list of clients
     * @return the list of clients with registered tests
     */
    public List<Client> getClientsWithTests(List<Client> clientList) {

        List<Test> validTestList = getListTestsInsideTheHistoricalDays();

        List<Client> clientList1 = new ArrayList<>();

        for (Client c : clientList) {
            for (Test t : validTestList) {
                if (c.getTinNumber().equals(t.getClientTin())) {
                    if (!clientList1.contains(c)) {
                        clientList1.add(c);
                    }
                }
            }
        }
        return clientList1;
    }

    /**
     * Gets all the tests that have an associated client.
     *
     * @param clientList the list of clients
     * @return the list of tests that have an associated client
     */
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

    /**
     * Gets the client's ages.
     *
     * @param clientList        the list of clients
     * @param historicalDaysInt the number of historical days
     * @return the client's ages
     */
    public double[] getClientAge(List<Client> clientList, int historicalDaysInt) {

        double[] clientsAges = new double[historicalDaysInt + 1];

        int n = 0;
        int x = 0;
        int sum = 0;
        int age = 0;

        for (int i = 0; i < historicalDaysInt + 1; i++) {

            LocalDate currentDay = getCurrentDay(i, historicalDaysInt);

            for (Test t1 : getClientsWithTestsListWithTests(clientList)) {

                LocalDate testDate = t1.getValidatedDate().toLocalDate();

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

    /**
     * Gets the client's age inside a time interval.
     *
     * @param clientList the list of clients
     * @param space      the array's length
     * @param startDate  the start date
     * @return the client's age inside a time interval
     */
    public double[] getClientAgeInsideTheInterval(List<Client> clientList, int space, LocalDate startDate) {

        double[] clientsAges = new double[space];

        int n = 0;
        int x = 0;
        int sum = 0;
        int age = 0;

        for (int i = 0; i < space; i++) {

            LocalDate currentDay = getCurrentDayInsideInterval(i, startDate);

            for (Test t1 : getClientsWithTestsListWithTests(clientList)) {

                LocalDate testDate = t1.getValidatedDate().toLocalDate();

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

    /**
     * Gets the current day.
     *
     * @param i                 the i
     * @param historicalDaysInt the number of historical days
     * @return the current day
     */
    public LocalDate getCurrentDay(int i, int historicalDaysInt) {

        int interV = historicalDaysInt - i;

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -interV);
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        return currentDay;
    }

    /**
     * Gets the current day inside a date interval.
     *
     * @param i                 the i
     * @param startDateInterval the start date of the interval
     * @return the current day inside a date interval
     */
    public LocalDate getCurrentDayInsideInterval(int i, LocalDate startDateInterval) {

        int startDayInterval = (int) ChronoUnit.DAYS.between(startDateInterval, todayDate);

        int interW = startDayInterval - i;

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -interW);
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)
        return currentDay;
    }

    /**
     * Gets the tests inside a date interval.
     *
     * @param startDateInterval the start date of the interval
     * @param endDateInterval   the end date of the interval
     * @return the tests inside a date interval
     */
    public Object[] getTestsInsideDateInterval(LocalDate startDateInterval, LocalDate endDateInterval) {

        List<Test> tests = new ArrayList<>();

        for (Test t : array) {
            LocalDate testDate = t.getDate().toLocalDate();

            if (Period.between(startDateInterval, testDate).getDays() >= 0 && Period.between(testDate, endDateInterval).getDays() >= 0) {
                tests.add(t);
            }
        }
        return tests.toArray();
    }

    /**
     * Gets the validated tests inside a date interval.
     *
     * @param startDateInterval the start date of the interval
     * @param endDateInterval   the end date of the interval
     * @return the validated tests inside a date interval
     */
    public List<Test> getListTestsInsideDateInterval(LocalDate startDateInterval, LocalDate endDateInterval) {

        List<Test> validTests = new ArrayList<>();

        for (Test t : getValidatedTestsListCovid()) {
            LocalDate testDate = t.getValidatedDate().toLocalDate();

            if (Period.between(startDateInterval, testDate).getDays() >= 0 && Period.between(testDate, endDateInterval).getDays() >= 0) {
                validTests.add(t);
            }
        }
        return validTests;
    }

    /**
     * Gets the COVID-19 tests per day inside a date interval.
     *
     * @param space             the array's length
     * @param startDateInterval the start date of the interval
     * @return the COVID-19 tests per day inside a date interval
     */
    public double[] getCovidTestsPerDayIntoArrayInsideInterval(int space, LocalDate startDateInterval) {

        double[] positiveCovidTestsPerDay = new double[space];

        for (int i = 0; i < space; i++) {

            LocalDate currentDay = getCurrentDayInsideInterval(i, startDateInterval);

            for (Test t : getListTestsInsideTheHistoricalDays()) {
                LocalDate testDate = t.getValidatedDate().toLocalDate();
                if (testDate.equals(currentDay)) {
                    positiveCovidTestsPerDay[i] += 1;
                }
            }

        }
        return positiveCovidTestsPerDay;
    }

    /**
     * Gets the positive COVID-19 tests per day inside a date interval.
     *
     * @param space             the array's length
     * @param startDateInterval the start date of the interval
     * @return the positive COVID-19 tests per day inside a date interval
     */
    public double[] getPositiveCovidTestsPerDayIntoArrayInsideInterval(int space, LocalDate startDateInterval) {

        double[] positiveCovidTestsPerDay = new double[space];

        for (int i = 0; i < space; i++) {

            LocalDate currentDay = getCurrentDayInsideInterval(i, startDateInterval);

            for (Test t : getPositiveCovidTest()) {
                LocalDate testDate = t.getValidatedDate().toLocalDate();
                if (testDate.equals(currentDay)) {
                    positiveCovidTestsPerDay[i] += 1;
                }
            }
        }
        return positiveCovidTestsPerDay;
    }

    /**
     * Gets the positive COVID-19 tests per day.
     *
     * @param historicalDaysInt the number of historical days
     * @return the positive COVID-19 tests per day
     */
    public double[] getCovidTestsPerDayIntoArray(int historicalDaysInt) {

        double[] positiveCovidTestsPerDay = new double[historicalDaysInt + 1];

        for (int i = 0; i < historicalDaysInt + 1; i++) {

            LocalDate currentDay = getCurrentDay(i, historicalDaysInt);

            for (Test t : getPositiveCovidTest()) {
                LocalDate testDate = t.getValidatedDate().toLocalDate();
                if (testDate.equals(currentDay)) {
                    positiveCovidTestsPerDay[i] += 1;
                }
            }

        }
        return positiveCovidTestsPerDay;
    }

    /**
     * Gets the test list inside the historical days.
     *
     * @return the test list inside the historical days
     */
    public List<Test> getListTestsInsideTheHistoricalDays() {

        List<Test> validCovidTests = getValidatedTestsListCovid();
        List<Test> covidTestsInterval = new ArrayList<>();

        for (Test test : validCovidTests) {
            LocalDate testDate = test.getValidatedDate().toLocalDate();

            if (Period.between(beginDate, testDate).getDays() >= 0 && Period.between(testDate, todayDate).getDays() >= 0) {
                covidTestsInterval.add(test);
            }
        }
        return covidTestsInterval;
    }

    /**
     * Gets the amount of tests in a interval.
     *
     * @param inter the interval
     * @return the amount of tests in a interval
     */
    public int getAllTestsInAInterval(int inter) {

        int sum = 0;

        for (int i = 0; i < inter; i++) {

            inter -= 1;

            Calendar calendar = getDayForTests(inter);
            Date toDate2 = calendar.getTime();
            LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if ((calendar.get(Calendar.DAY_OF_WEEK) != 1)) {
                for (Test t : getTestListArray()) {
            //        if (t.getAnalysedDate().toLocalDate() != null || t.getAnalysedDate() != null || t.getDiagnosticDate().toLocalDate() != null || t.getSampleCreatedDate().toLocalDate() != null || t.getAnalysedDate().toLocalDate() != null) {
                        if (t.getDate().toLocalDate().equals(currentDay)) {
                            sum += 1;
                        }
                   // }
                }
            }
        }
        return sum;
    }

    /**
     * Gets the date of the tests.
     *
     * @param inter the interval
     * @return the date of the tests
     */
    public Calendar getDayForTests(int inter) {

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -inter);

        return cal2;
    }

    /**
     * Number of tests available in the array (test store)
     *
     * @return the number of tests available
     */

    public int numberOfTests() {
        int sum = 0;
        for (int i = 0; i < array.size(); i++) {
            sum += 1;
        }
        return sum;
    }


}