# US 18 - As_An_Administrator_I_Want_To_Send_The_Covid-19_Report_To_The_NHS_At_Any_Time

## 1. Requirements Engineering

### 1.1. User Story Description

*As an Administrator I want to send the Covid-19 report to the NHS at any time. I want to define the interval of dates
to fit the regression model, the number of historical points (number of days or number of weeks) that must be sent to
the NHS, the regression model to use and select the independent variables to use.*

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> “Considering that **Many Labs** has the exclusivity to perform **Covid 19 tests**, and that the contract with the **NHS** in England requires Many Labs to **summarize and report Covid 19 data**, the company needs to: **identify the number of Covid 19 tests performed, identify all positive results to Covid 19 tests, report the total number of Covid 19 cases per day, per week and per month of the year, and send the forecasts for these same time horizons the number of Covid 19 cases for the following day, next week and next month**.”

> "...should send them to the **NHS using their API**."

> “To make the **predictions** , the NHS contract defines that **a linear regression algorithm should be used** . The NHS required that both **simple linear and multiple linear regression algorithms should be evaluated to select the best model** . The accuracy of the **prediction models** should be **analysed and documented in the application user manual** (in the that must be delivered with the application The algorithm to be used by the application must be defined through a configuration file.”

**From the client clarifications:**

> **Question:** Which significance level should we use for the hypothesis tests?
>
> **Answer:** The application should allow the user to choose the significance level.

> **Question:** Regarding US18 and US19, it is only one report containing all the tests performed by Many Labs to be sent each time, correct? Or is it one report per laboratory, for example? Or other option?
>
> **Answer:** The report should contain all the tests made by Many Labs.

> **Question:** Should the report contain the data of all the tests with results (with or without report, with or without validation) or contain only the validated tests? (Or other option?)
>
> **Answer:** The NHS Covid report should include statistics computed only from validated tests.

> **Question:** From the project description it is known "send the forecasts for these same time horizons (the number of Covid-19 cases for the following day, next week and next month)." In the example report we have in moodle, there is a line that says "// Prediction values". Does this mean that after this line we should put our predictions or it refers to the following table?
> 
> **Answer:** Yes, the prediction values are the ones available in the table that we include in the example.

> **Question:** From Sprint D requirements we get "I want to define... the number of historical points (number of days or number of weeks) that must be sent to the NHS". Is the Administrator who must choose between days or weeks? If so, how should he make this choice? 
> 
> **Answer:** Yes. The Administrator should select between days and weeks using the user interface.

> **Question:** From Sprint D requirements we get "I want to define ... the regression model to use and select the independent variables to use"
**Q1:** If the admin selects the multilinear regression he/she can select more than one independent variable. Should he select from a checklist the ones that he/she want? If so, what are the supposed independent variables we need to include in the checklist?
From Sprint D requirements we also get "Administrator should be able to choose the independent variable to use with the simple linear regression model (either the number of tests realized or the mean age)."
**Q2**: When you say "mean age" are you referring to the average age of clients? 
>
> **Answer:**
**Q1** - to apply mlr you need two independent variables- daily number tests and mean age person tested daily. Also you need the same records per week.
**Q2**-It is already in **Q1**

>**Question:** Regarding the hypothesis tests: "The application should allow the user to choose the significance level."
**Q**: Is the same logic applied to the confidence intervals to be on the table?
>
> **Answer:** Yes the user could choose the significance level for hip t and confidence level for IC.


### 1.3. Acceptance Criteria

  * **AC1:** The system should allow the Administrator to select between a simple linear and multilinear regression model to fit the data. Moreover, the Administrator should be able to choose the independent variable to use with the simple linear regression model (either the number of tests realized or the mean age). The system should send the report using the NHS API (available in moodle).
  * **AC2:** The user could choose the significance level for hip t and confidence level for IC.
  * **AC3:** To apply mlr you need two independent variables - daily number tests and mean age person tested daily. Also you need the same records per week.
  * **AC4:** The Administrator should select between days and weeks using the user interface.
  * **AC5:** The NHS Covid report should include statistics computed only from validated tests.
  * **AC6:** The report should contain all the tests made by Many Labs.
  * **AC7:** The application should allow the user to choose the significance level.

### 1.4. Found out Dependencies

**From Sprint B:**

There is a dependency to:

* **"US07 Register a new employee"** since at least a Administrator must be registered in the system.

* **"US03 Register a new client"** since there should be "x" registered clients to apply the slr or mlr (1 of the 2 independent variables - mean age).

**From Sprint C:**

There is a dependency to:

* **"US15 Validate a test"** since to perform the slr or mlr (simple linear regression or multi linear regression) there should be validated tests (1 of the 2 independent variables - covid tests).

**From Sprint D :**

There is a dependency to : 

* **"US17 Import a CSV file"** since the system should have the tests from various days available to apply the desired models.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * The significance level;
  * Confidence level of IC;
  * Number of historical points;
  * The dates intervals to fit the model.
  
* Selected data:
  * The linear regression model (multi linear regression or the simple linear regression);
  * IF(Simple linear regression) : The independent variable (number of covid tests realized or mean age).

**Output Data:**

* NHS Report with all data;
* (In)Success of the operation.

### 1.6. System Sequence Diagram (SSD)

![US18_SSD](US18_SSD.svg)

### 1.7 Other Relevant Remarks

*Use this section to capture other relevant information that is related with this US such as (i) special requirements
; (ii) data and/or technology variations; (iii) how often this US is held.*

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US18_DM](US18_DM.svg)

### 2.2. Other Remarks

*Use this section to capture some additional notes/remarks that must be taken into consideration into the design
activity. In some case, it might be useful to add other analysis artifacts (e.g. activity or state diagrams).*

## 3. Design - User Story Realization

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| **Step/Msg 1:** asks to record the results of a given test | ... interacting with the actor? | TestResultUI | **Pure
Fabrication:** there is no reason to assign this responsibility to any existing class in the Domain Model |
|                                                        | ... coordinating the US? | TestResultController | Controller |
| **Step/Msg 2:** request sample barcode number | n/a | | |
| **Step/Msg 3:** types the sample barcode number | ... knowing TestStore? | Company | **
IE:** TestStore is initialized in Company |
|                                             | ... knowing all the existent test? | TestStore | **
IE:** knows its own tests |
|                                             | ... transferring the data typed in the UI to the domain? | TestParameterDTO | **
DTO:** When there is so much data to transfer, it is better to opt by using a DTO in order to reduce coupling between UI and domain |
| **Step/Msg 4:** shows one parameter at a time and requests each value/result | n/a | | |
| **Step/Msg 5:** types the value/result | ... knowing and getting the reference values? | ExternalModule | **Protected
Variation:** It is necessary to know which adapter to use in order to get the reference values for the correct API |
|                                    | ... getting the ExternalModule? | TestType | **
IE:** knows what API to get depending on the type of test |
|                                    | ... creating the TestParameterResult object? | TestParameter | **
Creator:** TestParameterResult is an attribute of TestParameter |
|                                    | ... validating and saving the typed data? | ReferenceValue | **
IE:** knows its own data |
|                                    | ... changing the test state? | Test | **
IE:** After the tests being recorded, Test must change its state |
| **Step/Msg 6:** informs operation success | ... informing operation success? | RecordTestResultUI | **
IE:** responsible for user interaction |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Company
* TestParameter
* TestParameterResult

Other software classes (i.e. Pure Fabrication) identified:

* RecordTestResultUI
* RecordTestResultController
* ExternalModule
* RefValue
* RefValueAdapter (1 for each API)
* TestParameterDTO
* TestParameterMapper

## 3.2. Sequence Diagram (SD)

![US18_SD](US18_SD.svg)

## 3.3. Class Diagram (CD)

*In this section, it is suggested to present an UML static view representing the main domain related software classes
that are involved in fulfilling the requirement as well as and their relations, attributes and methods.*

![US18_CD](US18_CD.svg)

# 4. Tests

### TestParameterResult Tests

**Test 1:** Get the test parameter result's parameterID.

    public void getParamID() {

        TestParameterResult tpr1 = new TestParameterResult("IgGAN", 15);

        String expected = "IgGAN";
        String actual = tpr1.getParamID();

        Assert.assertEquals(expected, actual);

    }

**Test 2:** Get the test parameter result's result.

    public void getResult() {

        TestParameterResult tpr1 = new TestParameterResult("IgGAN", 15.5);

        Double expected = 15.5;
        Double actual = tpr1.getResult();

        Assert.assertEquals(expected, actual);

    }

**Test 3:** Get the test parameter result's reference value.

    public void getRefValue() {

        TestParameterResult tpr1 = new TestParameterResult("IgGAN", 1.4);

        RefValue expected = new RefValue("mm/hr", 1.0, 10.0);

        tpr1.setRefValue(expected);
        RefValue actual = tpr1.getRefValue();

        Assert.assertEquals(expected, actual);

    }

**Test 4:** Set the test parameter result's reference value.

    public void setRefValue() {

        TestParameterResult tpr1 = new TestParameterResult("IgGAN", 15.5);
        RefValue rv1 = new RefValue("aaa", 10.0, 20.0);

        tpr1.setRefValue(rv1);
        RefValue actual = tpr1.getRefValue();

        Assert.assertEquals(rv1, actual);

    }

### RefValue Tests

**Test 5:** Get the metric.

    public void getMetric() {

        RefValue rv1 = new RefValue("aaa", 10.0, 20.0);

        String expected = "aaa";
        String actual = rv1.getMetric();

        Assert.assertEquals(expected, actual);

    }

**Test 6:** Get the minimum reference value.

    public void getRefValueMin() {

        RefValue rv1 = new RefValue("aaa", 10.0, 20.0);

        Double expected = 10.0;
        Double actual = rv1.getRefValueMin();

        Assert.assertEquals(expected, actual);

    }

**Test 7:** Get the maximum reference value.

    public void getRefValueMax() {

        RefValue rv1 = new RefValue("aaa", 10.0, 20.0);

        Double expected = 20.0;
        Double actual = rv1.getRefValueMax();

        Assert.assertEquals(expected, actual);

    }

**Test 8:** Get corresponding test using the sampleID.

    private boolean getCorrespondingTest(String sampleID) {
    String testID;

        List<Sample> samples = sampleStore.getSampleList();

        testID = "";

        for (Sample sa : samples) {
            if (sa.getBarcode().equals(sampleID)) {
                testID = sa.getTr().getTestID();
            }
        }

        List<Test> tests = testStore.getTestList();

        for (Test test1 : tests) {
            if (test1.getTestID().equals(testID)) {
                this.test = test1;
                return test1.compareTestState("SAMPLE_COLLECTED");
            }
        }
        return false;
    }

*It is also recommended organizing this content by subsections.*

# 5. Construction (Implementation)

### Class RecordTestResultUI

    public class RecordTestResultUI implements Runnable {

        RecordTestResultController ctrl;

        public RecordTestResultUI() {
            ctrl = new RecordTestResultController();
        }

        @Override
        public void run() {

            boolean result = true;
            boolean repeat = false;
            double value;

            String sampleID = Utils.readLineFromConsole("Please enter the sample barcode number of the test:");

            List<TestParameter> parameters = ctrl.getParameters(sampleID);

            if (this.ctrl.getSampleListDto() == null || this.ctrl.getSampleListDto().isEmpty()) {
                System.out.println("The list is of samples is empty!");
            } else {
                do {
                    for (TestParameter param : parameters) {
                        System.out.println();
                        System.out.print("Parameters: " + param.getParam().getName());

                        value = Utils.readDoubleFromConsole("Please insert the result/value:");

                        try {
                            result = ctrl.addTestParameterResult(param.getParam().getCode(), value);
                            result = true;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            result = false;
                        }

                        if (result) {
                            System.out.println("Test parameter result saved with success!");
                        } else {
                            System.out.println("Incorrect input of data (an error has occurred).");
                            repeat = Utils.confirm("Try again? (s/n)");
                        }
                    }
                } while (repeat);
            }

            if (result) {
                ctrl.setState();
                System.out.println("Success! All test parameters results have been recorded.");
            } else {
                System.out.println("Something went wrong... please, try again.");
            }
        }
    }

### Class RecordTestResultController

    public class RecordTestResultController {

        private Company company;
        private SampleStore sampleStore;
        private TestStore testStore;
        private Test test;

        private SampleMapper sampleMapper;

        public RecordTestResultController(){
            this(App.getInstance().getCompany());
            this.sampleStore = company.getSampleStore();
            this.testStore = company.getTestStore();
        }

        public RecordTestResultController(Company company) {
            this.company=company;
        }

        private boolean getCorrespondingTest(String sampleID) {
            String testID;

            List<Sample> samples = sampleStore.getSampleList();

            testID = "";

            for (Sample sa : samples) {
                if (sa.getBarcode().equals(sampleID)) {
                    testID = sa.getTr().getTestID();
                }
            }

            List<Test> tests = testStore.getTestList();

            for (Test test1 : tests) {
                if (test1.getTestID().equals(testID)) {
                    this.test = test1;
                    return test1.compareTestState("SAMPLE_COLLECTED");
                }
            }
            return false;
        }

        public List<TestParameter> getParameters(String sampleID) {
            getCorrespondingTest(sampleID);

            if(test == null) {
                return null;
            }
            return test.getTpList();
        }

        public boolean addTestParameterResult(String parameterCode, double result) {

            try {
                test.addTestParameterResult(parameterCode, result);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        public void setState() {
            test.setState("SAMPLE_ANALYSED");
        }

        public List<Sample> getSampleList(){return company.getSampleList(); }

        public List<SampleDTO> getSampleListDto(){

            this.sampleMapper = new SampleMapper();

            return sampleMapper.toDTO(getSampleList());
        }
    }

### Class TestParameter

    public class TestParameter {

        private String testID;
        private Parameter param;
        private TestParameterResult tpr;

        public TestParameter(String testID, Parameter param) {
            this.testID = testID;
            this.param = param;
            this.tpr = null;
        }

        public String getTestID() {
            return testID;
        }

        public Parameter getParam() {
            return param;
        }

        public TestParameterResult getTpr() {
            return tpr;
        }

        public void setTpr(TestParameterResult tpr) {
            this.tpr = tpr;
        }
    }

### Class TestParameterResult

    public class TestParameterResult {

        private String paramID;
        private double result;
        private RefValue refValue;

        public TestParameterResult(String paramID, double result) {
            this.paramID = paramID;
            this.result = result;
        }

        public String getParamID() {
            return paramID;
        }

        public double getResult() {
            return result;
        }

        public RefValue getRefValue() {
            return refValue;
        }

        public void setRefValue(RefValue refValue) {
            this.refValue = refValue;
        }
    }

### Class RefValue

    public class RefValue {

        private String metric;
        private double refValueMin;
        private double refValueMax;

        public RefValue(String metric, double refValueMin, double refValueMax) {
            this.metric = metric;
            this.refValueMin = refValueMin;
            this.refValueMax = refValueMax;
        }

        public String getMetric() {
            return metric;
        }

        public double getRefValueMin() {
            return refValueMin;
        }

        public double getRefValueMax() {
            return refValueMax;
        }
    }

### Interface RefValueAdapter

    public interface RefValueAdapter {

        RefValue getRefValue(String param);

        double getRefValueMin(String param);

        double getRefValueMax(String param);

        String getMetric(String param);
    }

### Class RefValueAdapter1

    public class RefValueAdapter1 implements RefValueAdapter {
    CovidReferenceValues1API api;

        public RefValueAdapter1() {
            this.api = new CovidReferenceValues1API();
        }

        public RefValue getRefValue(String param) {
            return new RefValue(getMetric(param), getRefValueMin(param), getRefValueMax(param));
        }

        public double getRefValueMin(String paramID) {
            return api.getMinReferenceValue(paramID, Constants.ACCESS_KEY);
        }

        public double getRefValueMax(String paramID) {
            return api.getMaxReferenceValue(paramID, Constants.ACCESS_KEY);
        }

        public String getMetric(String paramID) {
            return api.usedMetric(paramID, Constants.ACCESS_KEY);
        }
    }

### Class RefValueAdapter2

    public class RefValueAdapter2 implements RefValueAdapter {
    ExternalModule2API api;

        public RefValueAdapter2() {
            this.api = new ExternalModule2API();
        }

        public RefValue getRefValue(String param) {
            return new RefValue(getMetric(param), getRefValueMin(param), getRefValueMax(param));
        }

        public double getRefValueMin(String paramID) {
            EMRefValue refValue = api.getReferenceFor(paramID);
            return refValue.getMinValue();
        }

        public double getRefValueMax(String paramID) {
            EMRefValue refValue = api.getReferenceFor(paramID);
            return refValue.getMaxValue();
        }

        public String getMetric(String paramID) {
            return api.getMetricsFor(paramID);
        }
    }

### Class RefValueAdapter3

    public class RefValueAdapter3 implements RefValueAdapter{
    ExternalModule3API api;

        public RefValueAdapter3() {
            this.api = new ExternalModule3API();
        }

        public RefValue getRefValue(String param) {
            return new RefValue(getMetric(param), getRefValueMin(param), getRefValueMax(param));
        }

        public double getRefValueMin(String paramID) {
            return api.getMinReferenceValue(paramID, Constants.ACCESS_KEY);
        }

        public double getRefValueMax(String paramID) {
            return api.getMaxReferenceValue(paramID, Constants.ACCESS_KEY);
        }

        public String getMetric(String paramID) {
            return api.usedMetric(paramID, Constants.ACCESS_KEY);
        }
    }

# 6. Integration and Demo

### Integration in the Company class

    TestStore testStore = new TestStore();

    public TestStore getTestStore() {
        return testStore;
    }

### Integration in the Test class

    public void createTestParameter(String testID, List<Parameter> parameters) {
        TestParameter tp;
        for (Parameter param : parameters) {
            tp = new TestParameter(testID, param);
            this.tpList.add(tp);
        }
    }

    public List<TestParameter> getTpList() {
        return tpList;
    }

    public boolean addTestParameterResult(String parameterCode, double result) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        TestParameter tp1 = null;

        for (TestParameter tp : tpList) {
            if (tp.getParam().getCode().equals(parameterCode)) {
                tp1 = tp;
            }
        }

        if (tp1 == null) {
            return false;
        }

        String em = tt.getExternalModule();
        Class<?> daclass = Class.forName(em);
        RefValueAdapter adp = (RefValueAdapter) daclass.newInstance();

        String paramCode = tp1.getParam().getCode();

        TestParameterResult tpr = new TestParameterResult(parameterCode, result);
        tpr.setRefValue(adp.getRefValue(paramCode));

        tp1.setTpr(tpr);
        return true;
    }

    public boolean compareTestState(String state) {

        if (state.equals("SAMPLE_ANALYSED") || state.equals("DIAGNOSTIC_MADE") || state.equals("VALIDATED")) {
            return false;
        }
        return true;
    }

### Integration in the TestType class

    public String getExternalModule() {
        return externalModule;
    }

    public String setExternalModule(String testCode) {

        if(testCode.equals("BL000")) {
            return Constants.EM_REFERENCE_API;
        }

        if(testCode.equals("COV19")) {
            return Constants.COVID_REFERENCE_API;
        }
        return null;
    }

### Constants class

    public static final int ACCESS_KEY = 12345;
    public static final String COVID_REFERENCE_API = "app.domain.model.RefValueAdapter1";
    public static final String EM_REFERENCE_API = "app.domain.model.RefValueAdapter2";
    public static final String BC_REFERENCE_API = "app.domain.model.BarcodeAdapter1";

# 7. Observations