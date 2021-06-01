package app.domain.model;

import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private final String testCode;
    private final String testNhsNumber;
    private final String clientTin;
    private final TestType testType;
    private final List<ParameterCategory> catList;
    private final List<Parameter> paList;
    private final LocalDate createdDate;
    private State state;
    private List<TestParameter> testParam;
    private LocalDate sampleCreatedDate;
    private LocalDate analysedData;
    private LocalDate diagnosticDate;
    private LocalDate validatedDate;
    private Report rep;

    /**
     * Constructor of the Test object, it call methods on order to validate the NhsNumber, the list of categories and the list of parameters
     *
     * @param testCode      unique code generated automatically
     * @param testNhsNumber unique code that identifies the test
     * @param clientTin     unique code that identifies the client associated with the test
     * @param testType      type of this test
     * @param catList       list of parameters categories that are measured in this test
     * @param paList        list of parameters that are measured in this test
     */
    public Test(String testCode, String testNhsNumber, String clientTin, TestType testType, List<ParameterCategory> catList, List<Parameter> paList) {

        checkTestNhsNumberRules(testNhsNumber);
        checkTestCodeRules(testCode);
        checkCatList(catList);
        checkPaList(paList);
        this.testCode = testCode;
        this.testNhsNumber = testNhsNumber;
        this.clientTin = clientTin;
        this.testType = testType;
        this.catList = catList;
        this.paList = paList;
        this.createdDate = LocalDate.now();

    }

    /**
     * This method checks if the test code meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param testCode unique code generated automatically
     */
    private void checkTestCodeRules(String testCode) {
        if (testCode == null) {
            throw new IllegalArgumentException("The Test Code must exist");
        }
    }

    /**
     * This method checks if the list of parameters meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param paList list of parameters that are measured in this test
     */
    private void checkPaList(List<Parameter> paList) {
        if (paList.isEmpty()) {
            throw new IllegalArgumentException("Parameter List must not be empty");
        }

    }

    /**
     * This method checks if the list of parameters categories meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param catList list of parameters categories that are measured in this test
     */
    private void checkCatList(List<ParameterCategory> catList) {
        if (catList.isEmpty()) {
            throw new IllegalArgumentException("Category List must not be empty");
        }
    }

    /**
     * This method checks if the list of parameters categories meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param testNhsNumber unique code that identifies the test
     */
    private void checkTestNhsNumberRules(String testNhsNumber) {
        if (testNhsNumber == null) {
            throw new IllegalArgumentException("The NHS Number must exist");
        }

        if (!StringUtils.isAlphanumeric(testNhsNumber)) {
            throw new IllegalArgumentException("The NHS Number must have just alphanumeric characters");
        }

        if (testNhsNumber.length() > Constants.TEST_NHS_CODE) {
            throw new IllegalArgumentException("The NHS Number must have a maximum of 12 characters");
        }

    }

    /**
     * Creates a new TestParameter object for each Parameter in the Parameter list received in the constructor and saves it in a new TestParameter List
     */
    public void addTestParameter() {
        this.testParam = new ArrayList<>();
        for (Parameter p : this.paList) {
            String code = p.getCode();
            TestParameter tp = new TestParameter(code);

            this.testParam.add(tp);

        }
        changeState(State.CREATED);
    }

    /**
     * Changes the state of the object test by changing the variable state with a value from the enum "State"
     *
     * @param s a value of the enum "State"
     */
    private void changeState(State s) {
        this.state = s;
    }

    /**
     * Changes the state of the object test by changing the variable state with a value from the enum "State
     * @param s a value of the enum "State"
     */
    public void changeState(String s) {
        switch (s) {
            case "CREATED":
                changeState(State.CREATED);
                break;
            case "SAMPLE_COLLECTED":
                changeState(State.SAMPLE_COLLECTED);
                this.sampleCreatedDate = LocalDate.now();
                break;
            case "SAMPLE_ANALYSED":
                changeState(State.SAMPLE_ANALYSED);
                this.analysedData = LocalDate.now();
                break;
            case "DIAGNOSTIC_MADE":
                changeState(State.DIAGNOSTIC_MADE);
                this.diagnosticDate = LocalDate.now();
                break;
            case "VALIDATED":
                changeState(State.VALIDATED);
                this.validatedDate = LocalDate.now();
                break;
            default:
                break;
        }
    }

    /**
     * @return a string with all dates of the test
     */
    public String getDates() {

        return "Created at:" +
                this.createdDate.toString() + "\n" +
                "Samples collected at:" +
                this.sampleCreatedDate.toString() + "\n" +
                "Analysed at:" +
                this.analysedData.toString() + "\n" +
                "Diagnosed at:" +
                this.diagnosticDate.toString() + "\n";
    }


    /**
     * @return Test unique NHS number
     */
    public String getTestNhsNumber() {
        return testNhsNumber;
    }

    /**
     * @return unique code generated automatically
     */
    public String getTestCode() {
        return testCode;
    }

    /**
     * Method that represents the object in a string
     *
     * @return a string that represents the object
     */
    @Override
    public String toString() {
        return "Test: testCode=" + testCode + ", testNhsNumber=" + testNhsNumber + ", clientCc=" + clientTin + ", testType=" + testType + ", catList=" + catList + ", paList=" + paList;
    }

    /**
     * @return a string with all the results associated with the test
     */
    public String getResults() {
        StringBuilder listString = new StringBuilder();

        for (TestParameter s : this.testParam) {
            listString.append(s.getTestParameterResult()).append("\n");
        }
        return String.valueOf(listString);

    }

    /**
     * @param parameterCode the code of the parameter that will be associated with a result
     * @param result double that represents numerical the result of the analysis of the test parameters
     * @return boolean represents the success of the operation
     */
    public boolean addTestResult(String parameterCode, double result) {

        if (!state.equals(State.SAMPLE_COLLECTED)) {
            return false;
        }

        TestParameter pa = null;
        for (TestParameter p : testParam) {
            if (p.getpCode().equals(parameterCode)) {
                pa = p;
            }
        }
        if (pa == null) {
            return false;
        }

        String externalModule = testType.getExternalModule();
        try {
            Class<?> oClass = Class.forName(externalModule);
            RefValueAdapter em = (RefValueAdapter) oClass.newInstance();
            pa.addResult(result, em.getRefValue(pa.getpCode()));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * @return this list of Test Parameters associated with the test
     */
    public List<TestParameter> getTestParam() {
        return testParam;
    }

    /**
     * @param diagnosis string that represents the diagnosis made by the Specialist Doctor
     * @return boolean represents the success of the operation
     */
    public boolean createReport(String diagnosis) {
        this.rep = new Report(this.testCode, diagnosis);

        return this.rep != null;
    }

    /**
     * changes the state of the test to "DIAGNOSTIC_MADE"
     */
    public void saveReport() {
        changeState(State.DIAGNOSTIC_MADE);
    }

    /**
     * @return a string with the current state of the test
     */
    public String getState() {
        return state.toString();
    }


    /**
     * This enum represents all the states that the Test object can assume
     */
    enum State {
        CREATED,
        SAMPLE_COLLECTED,
        SAMPLE_ANALYSED,
        DIAGNOSTIC_MADE,
        VALIDATED;


    }
    public String getClientTin(){
        return clientTin;
    }
    public LocalDate getDate(){
        return this.createdDate;
    }


}
