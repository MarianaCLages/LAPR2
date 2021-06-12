package app.domain.stores;

import app.domain.model.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Class that represents an List of Tests in the system
 */
public class TestStore implements Serializable {
    private List<Test> array;
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
        int ID = this.array.size() + 1;
        StringBuilder testNumber = new StringBuilder(String.valueOf(ID));

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
        for (Test t : this.array) {
            if (t.getState().equals("DIAGNOSTIC_MADE")) {
                listToValidate.add(t);
            }
        }
        return listToValidate;
    }

    /**
     * @return the list of tests which state is "SAMPLE_ANALYSED"
     */
    public TestStore getListOfTestsAnalysed() {
        TestStore listToReport = new TestStore();
        for (Test t : this.array) {
            if (t.getState().equals("SAMPLE_ANALYSED")) {
                listToReport.addTest(t);
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
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
        return testList2;
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

        for (Test t : array) {
            if (client.getTinNumber().equalsIgnoreCase(t.getClientTin()) && t.getState().equals("VALIDATED")) {
                validatedTest.add(t);
            }
        }
        return validatedTest;
    }

    public List<Test> getValidatedTestsList() {

        // Company company = App.getInstance().getCompany();

        List<Test> testList = new ArrayList<>();

        for (Test t : array) {
            if (t.getState().equals("VALIDATED") && t.getTestType().getTestID().equals("COV19")) {
                testList.add(t);
            }
        }

        return testList;

    }

    public List<Test> getTestListArray() {
        return array;
    }

    @Override
    public String toString() {
        for (Test t: array) {
            System.out.println(t.toString());
        }
        return null;
    }
}
