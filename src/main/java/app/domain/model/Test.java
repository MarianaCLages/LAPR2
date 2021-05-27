package app.domain.model;

import app.domain.shared.Constants;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private State state;
    private String testCode;
    private String testNhsNumber;
    private String clientTin;
    private TestType testType;
    private ParameterCategoryList catList;
    private ParameterList paList;
    private List<TestParameter> testParam;
    private LocalDate createdDate;
    private LocalDate sampleCreatedDate;
    private LocalDate analysedData;
    private LocalDate diagnosticDate;
    private LocalDate validatedDate;
    private List<Sample> testSamples;
    private Sample sample;
    private Report rep;

    /**
     * Constructor of the Test object, it call methods on order to validate the NhsNumber, the list of categories and the list of parameters
     *
     * @param testCode      unique code generated automatically
     * @param testNhsNumber unique code that identifies the test
     * @param clientTin      unique code that identifies the client associated with the test
     * @param testType      type of this test
     * @param catList       list of parameters categories that are measured in this test
     * @param paList        list of parameters that are measured in this test
     */
    public Test(String testCode, String testNhsNumber, String clientTin, TestType testType, ParameterCategoryList catList, ParameterList paList) {

        checkTestNhsNumberRules(testNhsNumber);
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
     * This method checks if the list of parameters meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param paList list of parameters that are measured in this test
     */
    private void checkPaList(ParameterList paList) {
        if (paList.isEmpty() || paList == null) {
            throw new IllegalArgumentException("Parameter List must not be empty");
        }

    }

    /**
     * This method checks if the list of parameters categories meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param catList list of parameters categories that are measured in this test
     */
    private void checkCatList(ParameterCategoryList catList) {
        if (catList.isEmpty() || catList == null) {
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
        for (Parameter p : this.paList.getList()) {
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

    public String getDates(){
        StringBuilder listString = new StringBuilder();
        listString.append("Created at:"); listString.append(this.createdDate.toString()).append("\n");
        listString.append("Samples collected at:"); listString.append(this.sampleCreatedDate.toString()).append("\n");
        listString.append("Analysed at:"); listString.append(this.analysedData.toString()).append("\n");
        listString.append("Diagnosed at:"); listString.append(this.diagnosticDate.toString()).append("\n");

        return String.valueOf(listString);
    }


    /**
     * @return Test unique NHS number
     */
    public String getTestNhsNumber() {
        return testNhsNumber;
    }

    public String getTestCode() { return testCode; }

    @Override
    public String toString() {
        return "Test: testCode=" + testCode + ", testNhsNumber=" + testNhsNumber + ", clientCc=" + clientTin + ", testType=" + testType + ", catList=" + catList + ", paList=" + paList;
    }
    public String getResults(){
        StringBuilder listString = new StringBuilder();

        for (TestParameter s : this.testParam) {
            listString.append(s.getTestParameterResult()).append("\n");
        }
        return String.valueOf(listString);

    }


    public boolean addTestResult(String parameterCode, double result) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

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
        Class<?> oClass = Class.forName(externalModule);
        RefValueAdapter em = (RefValueAdapter) oClass.newInstance();

        pa.addResult(result, em.getRefValue(pa.getpCode()));

        return true;
    }



    public List<TestParameter> getTestParam() {
        return testParam;
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
    public boolean createReport(String diagnosis){
        this.rep = new Report(this.testCode,diagnosis);

        if (this.rep == null){
            return false;
        }else {
            return true;
        }
    }

    public void saveReport(){
        changeState(State.DIAGNOSTIC_MADE);
    }








    /*
    public String getID(){return testCode;}
*/
}
