# US 006 - To create a Task

## 1. Requirements Engineering

### 1.1. User Story Description

As an organization employee, I want to create a new task in order to be further published.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**
> The Many Labs company wants to send to the NHS daily reports of Covid-19 data, including the number of observed values and estimated values. Reports should be generated automatically with historical data and must be sent every day at 6:00 am

> The contract with the NHS in England requires Many Labs to summarize and report Covid-19 data, the company needs to: identify the number of Covid-19 tests performed, identify all positive results to Covid-19 tests, report the total number of Covid-19 cases per day, per week and per month of the year, and send the forecasts for these same time horizons (the number of Covid-19 cases for the following day, next week and next month)

> Many Labs is also required to generate daily (automatic) reports with all the information demanded by the NHS and should send them to the NHS using their API.

> To make the predictions, the NHS contract defines that a linear regression algorithm should be used. The NHS required that both simple linear and multiple linear regression algorithms should be evaluated to select the best model. The accuracy of the prediction models should be analysed and documented in the application user manual (in the annexes) that must be delivered with the application. The algorithm to be used by the application must be defined through a configuration file.

**From the client clarifications:**

> **Question:** Regarding US18 and US19, it is only one report containing all the tests performed by Many Labs to be sent each time, correct? Or is it one report per laboratory, for example? Or other option?
>
> **Answer:** The report should contain all the tests made by Many Labs.

> **Question:** As the report is generated automatically, should the system generate a notication that the report was sent?
>
> **Answer:** The application should write the event into a log file.

> **Question:** In US19, in Sprint D Requirements, it says "The report should include day and week (observed and estimated) values..." and also "Reports...must be sent every day at 6:00 am". As the Report is to be automatically sent very early in the morning, do you wish the report to have the data concerning the day before and the last week starting at the day before?
>
> **Answer:** The format of the report should follow the report example that is available in moodle.

> **Question:** Should the report contain the data of all the tests with results (with or without report, with or without validation) or contain only the validated tests? (Or other option?)
>
> **Answer:** The NHS Covid report should include statistics computed only from validated tests.

> **Question:** Are we going to be able to use more math libraries in order to facilitate the calculus (for example, for confidence intervals) or is the rest of the calculus to be developed by each team?
>
> **Answer:** Each team should implement the classes and methods needed.

> **Question:** Which significance level should we use for the hypothesis tests?
>
> **Answer:** The application should allow the user to choose the significance level.

> **Question:** Should we assume the report scope is Many Labs or is it each laboratory?
>
> **Answer:** Many Labs Many has exclusivity for doing Covid-19 tests and should send nationwide reports to NHS. The scope is Many Labs.

> **Question:** Could you clarify how the historical points work? Acording to the NhsReportExample, it was chosen 15 points and the dates to fit the regression model but it seems that it was not chosen the day to start the prediction table
>
> **Answer:** In the header of the exampleNHSReport.txt file it says "If the administrator selects: The current day to be 31/05/2021...". Please relate this information with the table available in the exampleNHSReport.txt file. In US19, the current day is the day when the report is sent automatically to the NHS. The teams should not include sundays in their analysis or estimates. When the time resolution is a week, please consider only complete weeks.

### 1.3. Acceptance Criteria

* **AC1:** The report should include day and week (observed and estimated) values, the regression model used to estimate
  each value, R(SLR), R2 and R2 adjusted for SLR and MLR, hypothesis tests for regression coefficients significance
  model with Anova. Simple linear and multilinear regression models can be used to compute the estimates and
  corresponding confidence intervals. When the system is configured to use the simple linear regression model, the
  performance of each model should be used to select the best model (the one that uses the number of tests realized or
  the one that uses the mean age as independent variable). The best model will be used to make the estimated/expected
  values that will be send to NHS. The interval of dates to fit the regression model and the number of historical
  points (number of days and number of weeks) must be defined through a configuration file. The system should send the
  report using the NHS API (available in moodle).

* **AC2:** The report should contain all the tests made by Many Labs.

* **AC3:** The application should write the notification the event into a log file.

* **AC4:** The NHS Covid report should include statistics computed only from validated tests.

* **AC5:** The application should allow the user to choose the significance level.

### 1.4. Found out Dependencies

* There is a dependency to "US4", "US5", "US12","US14","US15" since the test needs to be on **last state** in order to
  be able to be mentioned in report.

### 1.5 Input and Output Data

**Input Data:**

*nothing*

**Output Data:**

* Report sent to the NHS

### 1.6. System Sequence Diagram (SSD)

**Alternative 1**

![US19_SSD](US19_SSD.svg)

### 1.7 Other Relevant Remarks

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US19_MD](US19_DM.svg)

### 2.2. Other Remarks

## 3. Design - User Story Realization

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for...                  | Answer          | Justification (with patterns)                                                                                               |
| :------------- | :---------------------                                       | :------------   | :----------------------------                                                                                               |
| Step 1         | ...schedule the sent of the report?                          | Company         | IE: Since the scope of the report is the company the Company class knows all the information required to create the report. |
|                | ...knowing all the tests?                                    | TestStore       | IE: TestStore stores all testes                                                                                             |
|                | ...knowing the positive COVID tests?                         | TestStore       | IE: TestStore stores all testes                                                                                             |
|                | ...knowing all valid COVID tests?                            | TestStore       | IE: TestStore stores all testes                                                                                             |
|                | ...knowing the the age of clients associated with the test ? | ClientStore     | IE: knows all clients                                                                                                       |
|                | ...creating the NHS report                                   | SendReportTask  | Pure Fabrication: there is no class on the domain to give this responsibility                                               |
|                | ...send the NHS report to NHS ?                              | NHSAPI          | API                                                                                                                         |
|                | ...create the regression model                               | RegressionModel | Pure Fabrication: there is no class on the domain to give this responsibility                                               |
|                |                                                              |                 |                                                                                                                             |
| Step 2         | ...write the success of the operation into a log file        | SendReportTask  | IE: has all the necessary data                                                                                              |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:
* NHSReport

Other software classes (i.e. Pure Fabrication) identified:
* SendReportTask
* RegressionModel

## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US19_SD](US19_SD.svg)

**RunTank()**
![SD_runTask()](SD_runTask().svg)

## 3.3. Class Diagram (CD)


![US006_CD](US19_CD.svg)

# 4. Tests

**Test 1:** Check that it is not possible to create a mode of regression with arrays of different length.

    @Test(expected = IllegalArgumentException.class)
    public void differentLengthsTest() {
        double[][] matrix1 = {
                {120, 19},
                {200, 8},
                {150, 12},
                {180, 15},
                {240, 16},
                {250, 13}
        };
        double[] matrixb = {23.8, 24.2, 22.0, 26.2, 33.5, 35, 5};

        MultiLinearRegression s = new MultiLinearRegression(matrix1, matrixb);

    }

**Test 2:** Check if the task is successful created

    @Test
    public void taskCreated(){
        Assert.assertTrue(company.isCreatedTask());
    }

**Test 3:** Check if it is possible to obtain the list of validated CovidTests

    @Test
    public void getValidatedTestsListAll() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);
        TestStore store = new TestStore();
        app.domain.model.Test t = store.createTest("123456789187", "1234567890123456", testType, cat1, pa);

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate date1Client = LocalDate.now();
        Date date1 = Date.from(date1Client.atStartOfDay(defaultZoneId).toInstant());


        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date1, "email@gamil.com", "Zé");

        List<Client> clientList = new ArrayList<>();
        clientList.add(client);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -10);
        Date toDate = calendar.getTime();

        Client client2 = new Client("12345678911", "1234567890123457", "1234567892", "1234567891", toDate, "email@gamil.com", "Zé");
        clientList.add(client2);

        app.domain.model.Test teste = new app.domain.model.Test("1234s", "123456789012", "1234567890123456", testType, cat1, pa);
        store.saveTest();

        LocalDate beginDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        ParameterCategoryStore parameterCategoryStore = App.getInstance().getCompany().getParameterCategoryList();
        ParameterCategory pc10 = parameterCategoryStore.createParameterCategory("12345", "Hemogram");
        parameterCategoryStore.saveParameterCategory();
        ParameterCategory pc2 = parameterCategoryStore.createParameterCategory("12346", "Cholesterol");
        parameterCategoryStore.saveParameterCategory();
        ParameterCategory pc3 = parameterCategoryStore.createParameterCategory("12347", "Covid");
        parameterCategoryStore.saveParameterCategory();

        TestType covidTest = new TestType("COV19", "Covid", "Swab", parameterCategoryStore);

        List<ParameterCategory> testCategories = new ArrayList<>();
        testCategories.add(pc1);

        ParameterStore parameterStore = new ParameterStore();

        Parameter p4 = new Parameter(Constants.IG_GAN, "COVID", "000", pc3);
        parameterStore.add(p4);

        List<Parameter> testParameters1 = new ArrayList<>();
        testParameters1.add(p4);

        app.domain.model.Test t10 = new app.domain.model.Test("1234557890123456", "100000000100", "1234567890", covidTest, testCategories, testParameters1);
        t10.setCreatedDate(LocalDateTime.of(2021, Month.JUNE, 10, 11, 30));
        t10.addTestParameter();
        t10.changeState(Constants.SAMPLE_COLLECTED);
        t10.addTestResult(Constants.IG_GAN, 1.5);
        t10.changeState("VALIDATED");
        store.addTest(t10);

        Assert.assertNotNull(store.getValidatedTestsListAll());
    }


**Test 4:** Checks if it is possible to get the list of mean ages inside an interval
````
    @Test
    public void getClientAgeInsideTheInterval() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);
        TestStore store = new TestStore();
        app.domain.model.Test t = store.createTest("123456789187", "1234567890123456", testType, cat1, pa);

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate date1Client = LocalDate.now();
        Date date1 = Date.from(date1Client.atStartOfDay(defaultZoneId).toInstant());


        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date1, "email@gamil.com", "Zé");

        List<Client> clientList = new ArrayList<>();
        clientList.add(client);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -10);
        Date toDate = calendar.getTime();

        Client client2 = new Client("12345678911", "1234567890123457", "1234567892", "1234567891", toDate, "email@gamil.com", "Zé");
        clientList.add(client2);

        LocalDate beginDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        store.getClientAgeInsideTheInterval(clientList, 10, LocalDate.now());
    }

````
# 5. Construction (Implementation)

## Class SendReportTask
```
`package app.controller;

import app.domain.model.Client;
import app.domain.model.StringBuilderReport;
import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;
import app.domain.shared.exceptions.InvalidLengthException;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;
import app.ui.gui.Alerts;
import com.nhs.report.Report2NHS;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SendReportTask extends TimerTask implements Serializable {
private LocalDate finishDate;
private int historicalDays;
private LocalDate beginningDate;
private double confidenceLevelAnova;
private double confidenceLevelVariables;
private double confidenceLevelEstimated;
private String regression;
private String scope;
StringBuilderReport report;
private boolean runed= false;

    public SendReportTask() {
        //Send report constructor
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        runed = true;
        LinearRegression linearRegressionChosen = null;
        TestStore testStore = App.getInstance().getCompany().getTestList();
        ClientStore clientStore = App.getInstance().getCompany().getClientList();
        getProps();

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -historicalDays);
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        testStore.setDates(historicalDays);
        double[] positiveCovidTestsPerDayInsideTheDateInterval = testStore.getPositiveCovidTestsPerDayIntoArrayInsideInterval(Period.between(this.beginningDate, this.finishDate).getDays() + 1, this.beginningDate);
        double[] positiveCovidTestsPerDayInsideTheHistoricalInterval = testStore.getCovidTestsPerDayIntoArray(historicalDays);

        double[] covidTestsPerDayInsideTheDateInterval = testStore.getCovidTestsPerDayIntoArrayInsideInterval(Period.between(this.beginningDate, this.finishDate).getDays() + 1, this.beginningDate);
        double[] covidTestsPerDayInsideHistoricalDays = testStore.getCovidTestsPerDayIntoArrayInsideInterval(historicalDays + 1, currentDay);

        List<Client> clientsWithTests = testStore.getClientsWithTests(clientStore.getClientList());
        double[] ages = testStore.getClientAge(clientsWithTests, this.historicalDays);
        double[] agesInsideTheDateInterval = testStore.getClientAgeInsideTheInterval(clientsWithTests, Period.between(this.beginningDate, this.finishDate).getDays() + 1, this.beginningDate);

        if (regression.equals("Linear")) {

            LinearRegression linearRegressionNumberTest = new LinearRegression(covidTestsPerDayInsideTheDateInterval, positiveCovidTestsPerDayInsideTheDateInterval);

            LinearRegression linearRegressionMeanAge = new LinearRegression(agesInsideTheDateInterval, positiveCovidTestsPerDayInsideTheDateInterval);

            if (linearRegressionNumberTest.getR2() > linearRegressionMeanAge.getR2()) {
                linearRegressionChosen = linearRegressionNumberTest;
                this.report = new StringBuilderReport(linearRegressionChosen);
                report.setvalues(covidTestsPerDayInsideHistoricalDays, positiveCovidTestsPerDayInsideTheHistoricalInterval, historicalDays);
                this.report.setConfidenceValues(confidenceLevelAnova, confidenceLevelVariables, confidenceLevelEstimated);
                this.report.stringConstructionLinearRegression();

            } else {
                linearRegressionChosen = linearRegressionMeanAge;
                this.report = new StringBuilderReport(linearRegressionChosen);
                report.setvalues(ages, positiveCovidTestsPerDayInsideTheHistoricalInterval, historicalDays);
                this.report.setConfidenceValues(confidenceLevelAnova, confidenceLevelVariables, confidenceLevelEstimated);

                this.report.stringConstructionLinearRegression();
                this.report.printCovidTestsPerInterval(this.scope);

            }
            Report2NHS.writeUsingFileWriter(this.report.getSb().toString());
            log();


        } else if (regression.equals("Multilinear")) {


            double[][] multiarray = new double[covidTestsPerDayInsideTheDateInterval.length][2];
            for (int i = 0; i < multiarray.length; i++) {
                multiarray[i][0] = covidTestsPerDayInsideTheDateInterval[i];
                multiarray[i][1] = agesInsideTheDateInterval[i];

            }

            double[][] multiarray2 = new double[covidTestsPerDayInsideHistoricalDays.length][2];
            for (int j = 0; j < multiarray2.length; j++) {
                multiarray2[j][0] = covidTestsPerDayInsideHistoricalDays[j];
                multiarray2[j][1] = ages[j];
            }

            MultiLinearRegression s = new MultiLinearRegression(multiarray, positiveCovidTestsPerDayInsideTheDateInterval);
            this.report = new StringBuilderReport(s);
            this.report.setConfidenceValues(confidenceLevelAnova, confidenceLevelVariables, confidenceLevelEstimated);
            this.report.setvalues(multiarray2, positiveCovidTestsPerDayInsideTheHistoricalInterval, historicalDays);

            try {
                this.report.stringConstructionMultiLinearRegression();
            } catch (InvalidLengthException e) {
                Alerts.errorAlert(e.getMessage());
            }
            Report2NHS.writeUsingFileWriter(report.getSb().toString());
            log();
        }
    }

    /**
     * Gets the properties.
     */
    private void getProps() {
        Properties prop = App.getProperties();
        this.beginningDate = LocalDate.parse(prop.getProperty("fit.date.beginning"));
        this.finishDate = LocalDate.parse(prop.getProperty("fit.date.ending"));
        this.historicalDays = Integer.parseInt((prop.getProperty("historical.days")));
        this.confidenceLevelAnova = Double.parseDouble(prop.getProperty("significance.level.anova"));
        this.confidenceLevelVariables = Double.parseDouble(prop.getProperty("significance.level.coefficient"));
        this.confidenceLevelEstimated = Double.parseDouble(prop.getProperty("significance.level.estimated"));
        this.regression = prop.getProperty("type.regression");
        this.scope = prop.getProperty("report.scope");

    }

    /**
     * Logs a message to the NHS.
     */
    private void log() {
        Logger logger = Logger.getLogger("Logger");
        FileHandler fh;

        try {

            fh = new FileHandler("Logs//log.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter;
            formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.setUseParentHandlers(false);
            logger.info("Report sent to NHS\n");

        } catch (SecurityException | IOException e) {
            Alerts.errorAlert(e.getMessage());
        }
    }

    public boolean isRuned() {
        return runed;
    }
}
````

## Class Company

````java
package app.controller;

import app.domain.model.Client;
import app.domain.model.StringBuilderReport;
import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;
import app.domain.shared.exceptions.InvalidLengthException;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;
import app.ui.gui.Alerts;
import com.nhs.report.Report2NHS;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SendReportTask extends TimerTask implements Serializable {
    private LocalDate finishDate;
    private int historicalDays;
    private LocalDate beginningDate;
    private double confidenceLevelAnova;
    private double confidenceLevelVariables;
    private double confidenceLevelEstimated;
    private String regression;
    private String scope;
    StringBuilderReport report;
    private boolean runed= false;

    public SendReportTask() {
        //Send report constructor
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        runed = true;
        LinearRegression linearRegressionChosen = null;
        TestStore testStore = App.getInstance().getCompany().getTestList();
        ClientStore clientStore = App.getInstance().getCompany().getClientList();
        getProps();

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -historicalDays);
        Date toDate2 = cal2.getTime();

        LocalDate currentDay = toDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date de começo do intervalo (dia de hj - historical days)

        testStore.setDates(historicalDays);
        double[] positiveCovidTestsPerDayInsideTheDateInterval = testStore.getPositiveCovidTestsPerDayIntoArrayInsideInterval(Period.between(this.beginningDate, this.finishDate).getDays() + 1, this.beginningDate);
        double[] positiveCovidTestsPerDayInsideTheHistoricalInterval = testStore.getCovidTestsPerDayIntoArray(historicalDays);

        double[] covidTestsPerDayInsideTheDateInterval = testStore.getCovidTestsPerDayIntoArrayInsideInterval(Period.between(this.beginningDate, this.finishDate).getDays() + 1, this.beginningDate);
        double[] covidTestsPerDayInsideHistoricalDays = testStore.getCovidTestsPerDayIntoArrayInsideInterval(historicalDays + 1, currentDay);

        List<Client> clientsWithTests = testStore.getClientsWithTests(clientStore.getClientList());
        double[] ages = testStore.getClientAge(clientsWithTests, this.historicalDays);
        double[] agesInsideTheDateInterval = testStore.getClientAgeInsideTheInterval(clientsWithTests, Period.between(this.beginningDate, this.finishDate).getDays() + 1, this.beginningDate);

        if (regression.equals("Linear")) {

            LinearRegression linearRegressionNumberTest = new LinearRegression(covidTestsPerDayInsideTheDateInterval, positiveCovidTestsPerDayInsideTheDateInterval);

            LinearRegression linearRegressionMeanAge = new LinearRegression(agesInsideTheDateInterval, positiveCovidTestsPerDayInsideTheDateInterval);

            if (linearRegressionNumberTest.getR2() > linearRegressionMeanAge.getR2()) {
                linearRegressionChosen = linearRegressionNumberTest;
                this.report = new StringBuilderReport(linearRegressionChosen);
                report.setvalues(covidTestsPerDayInsideHistoricalDays, positiveCovidTestsPerDayInsideTheHistoricalInterval, historicalDays);
                this.report.setConfidenceValues(confidenceLevelAnova, confidenceLevelVariables, confidenceLevelEstimated);
                this.report.stringConstructionLinearRegression();

            } else {
                linearRegressionChosen = linearRegressionMeanAge;
                this.report = new StringBuilderReport(linearRegressionChosen);
                report.setvalues(ages, positiveCovidTestsPerDayInsideTheHistoricalInterval, historicalDays);
                this.report.setConfidenceValues(confidenceLevelAnova, confidenceLevelVariables, confidenceLevelEstimated);

                this.report.stringConstructionLinearRegression();
                this.report.printCovidTestsPerInterval(this.scope);

            }
            Report2NHS.writeUsingFileWriter(this.report.getSb().toString());
            log();


        } else if (regression.equals("Multilinear")) {


            double[][] multiarray = new double[covidTestsPerDayInsideTheDateInterval.length][2];
            for (int i = 0; i < multiarray.length; i++) {
                multiarray[i][0] = covidTestsPerDayInsideTheDateInterval[i];
                multiarray[i][1] = agesInsideTheDateInterval[i];

            }

            double[][] multiarray2 = new double[covidTestsPerDayInsideHistoricalDays.length][2];
            for (int j = 0; j < multiarray2.length; j++) {
                multiarray2[j][0] = covidTestsPerDayInsideHistoricalDays[j];
                multiarray2[j][1] = ages[j];
            }

            MultiLinearRegression s = new MultiLinearRegression(multiarray, positiveCovidTestsPerDayInsideTheDateInterval);
            this.report = new StringBuilderReport(s);
            this.report.setConfidenceValues(confidenceLevelAnova, confidenceLevelVariables, confidenceLevelEstimated);
            this.report.setvalues(multiarray2, positiveCovidTestsPerDayInsideTheHistoricalInterval, historicalDays);

            try {
                this.report.stringConstructionMultiLinearRegression();
            } catch (InvalidLengthException e) {
                Alerts.errorAlert(e.getMessage());
            }
            Report2NHS.writeUsingFileWriter(report.getSb().toString());
            log();
        }
    }

    /**
     * Gets the properties.
     */
    private void getProps() {
        Properties prop = App.getProperties();
        this.beginningDate = LocalDate.parse(prop.getProperty("fit.date.beginning"));
        this.finishDate = LocalDate.parse(prop.getProperty("fit.date.ending"));
        this.historicalDays = Integer.parseInt((prop.getProperty("historical.days")));
        this.confidenceLevelAnova = Double.parseDouble(prop.getProperty("significance.level.anova"));
        this.confidenceLevelVariables = Double.parseDouble(prop.getProperty("significance.level.coefficient"));
        this.confidenceLevelEstimated = Double.parseDouble(prop.getProperty("significance.level.estimated"));
        this.regression = prop.getProperty("type.regression");
        this.scope = prop.getProperty("report.scope");

    }

    /**
     * Logs a message to the NHS.
     */
    private void log() {
        Logger logger = Logger.getLogger("Logger");
        FileHandler fh;

        try {

            fh = new FileHandler("Logs//log.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter;
            formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.setUseParentHandlers(false);
            logger.info("Report sent to NHS\n");

        } catch (SecurityException | IOException e) {
            Alerts.errorAlert(e.getMessage());
        }
    }

    public boolean isRuned() {
        return runed;
    }
}
````

## Class LinearRegression
##Class LinearRegression
````java
package app.domain.shared;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

/**
 * The code LinearRegression class performs a simple linear regression
 * on an set of n data points (y_i, x_i).
 * That is, it fits a straight line y = alpha + beta * x,
 * (where y is the response variable, x is the predictor variable,
 * alpha is the y-intercept, and beta is the slope)
 * that minimizes the sum of squared residuals of the linear regression model.
 * It also computes associated statistics, including the coefficient of
 * determination R^2 and the standard deviation of the
 * estimates for the slope and y-intercept.
 */
public class LinearRegression implements Regression {
    private final double intercept;
    private final double slope;


    private final double r2;
    private final double xbar;
    private final double ybar;


    private final int n;
    private final double st;
    private final double mse;
    private final double msr;
    private final double f0;
    private double sxx;
    private double syy;
    private double sxy;
    private double sr;
    private double se;

    /**
     * Performs a linear regression on the data points (y[i], x[i]).
     *
     * @param x the values of the predictor variable
     * @param y the corresponding values of the response variable
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public LinearRegression(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        this.n = x.length;

        // first pass
        double sumx = 0.0;
        double sumy = 0.0;
        double sumx2 = 0.0;
        for (int i = 0; i < n; i++) {
            sumx += x[i];
            sumx2 += x[i] * x[i];
            sumy += y[i];
        }
        this.xbar = sumx / n;
        this.ybar = sumy / n;

        // second pass: compute summary statistics
        this.sxx = 0.0;
        this.syy = 0.0;
        this.sxy = 0.0;
        for (int i = 0; i < n; i++) {
            this.sxx += (x[i] - this.xbar) * (x[i] - this.xbar);
            this.syy += (y[i] - this.ybar) * (y[i] - this.ybar);
            this.sxy += (x[i] - this.xbar) * (y[i] - this.ybar);
        }
        this.slope = sxy / sxx;
        this.intercept = this.ybar - this.slope * this.xbar;

        // more statistical analysis
        this.se = 0.0;      // residual sum of squares
        this.sr = 0.0;      // regression sum of squares
        for (int i = 0; i < n; i++) {
            double fit = this.slope * x[i] + this.intercept;
            this.se += (fit - y[i]) * (fit - y[i]);
            sr += (fit - this.ybar) * (fit - this.ybar);
        }
        this.st = this.se + this.sr;

        int degreesOfFreedom = n - 2;
        this.r2 = sr / syy;
        this.mse = se / degreesOfFreedom;
        this.msr = this.sr;

        this.f0 = msr / mse;

    }

    /**
     * Returns the y-intercept alpha of the best of the best-fit line y = alpha + beta * x.
     *
     * @return the y-intercept alpha of the best-fit line y = alpha + beta * x
     */
    public double intercept() {
        return intercept;
    }

    /**
     * Returns the slope beta of the best of the best-fit line y = alpha + beta * x.
     *
     * @return the slope beta of the best-fit line y = alpha + beta * x
     */
    public double slope() {
        return slope;
    }

    /**
     * Returns the coefficient of determination R^2.
     *
     * @return the coefficient of determination R^2,
     * which is a real number between 0 and 1
     */
    public double R2() {
        return r2;
    }

    /**
     * Gets the r by making the square root of R^2.
     *
     * @return the R
     */
    public double R() {
        return Math.sqrt(r2);
    }

    /**
     * Returns the expected response y given the value of the predictor
     * variable x.
     *
     * @param x the value of the predictor variable
     * @return the expected response y given the value of the predictor
     * variable x
     */
    public double predict(double x) {
        return slope * x + intercept;
    }

    /**
     * Gets the critic value (t-Student).
     *
     * @param alpha the alpha
     * @return the critic value (t-Student)
     */
    public double getCriticValueStudent(double alpha) {
        TDistribution td = new TDistribution(this.n);

        return td.inverseCumulativeProbability(1 - alpha);
    }

    /**
     * Gets the critic value (f-Snedecor).
     *
     * @param alphaf the alpha
     * @return the critic value (f-Snedecor)
     */
    public double getCriticValueFisher(double alphaf) {
        FDistribution fDistribution = new FDistribution(1, this.n - 2);
        return fDistribution.inverseCumulativeProbability(1 - alphaf);
    }

    /**
     * Calculates the lower limit answer.
     *
     * @param x     the x
     * @param alpha the alpha
     * @return the lower limit answer
     */
    public double lowerLimitAnswer(double x, double alpha) {
        double y = predict(x);
        double criticalValue = getCriticValueStudent(alpha);
        double s = Math.sqrt(this.se / (this.n - 2));
        return y - criticalValue * s * Math.sqrt(1 + 1 / n + (Math.pow((x - this.xbar), 2) / this.sxx));
    }

    /**
     * Calculates the upper limit answer.
     *
     * @param x     the x
     * @param alpha the alpha
     * @return the upper limit answer
     */
    public double upperLimitAnswer(double x, double alpha) {
        double y = predict(x);
        double s = Math.sqrt(this.se / (this.n - 2));
        double criticalValue = getCriticValueStudent(alpha);
        return y + criticalValue * s * Math.sqrt(1 + 1 / n + (Math.pow((x - this.xbar), 2) / this.sxx));
    }

    /**
     * Calculates the upper limit for the param a.
     *
     * @param alpha the alpha
     * @return the upper limit for the param a
     */
    public double upperLimitParama(double alpha) {
        double s = Math.sqrt(this.se / (this.n - 2));
        double criticalValue = getCriticValueStudent(alpha);
        return this.intercept + criticalValue * s * Math.sqrt(1 / n + (Math.pow(this.xbar, 2) / this.sxx));
    }

    /**
     * Calculates the lower limit for the param a.
     *
     * @param alpha the alpha
     * @return the lower limit for the param a
     */
    public double lowerLimitParama(double alpha) {
        double s = Math.sqrt(this.se / (this.n - 2));
        double criticalValue = getCriticValueStudent(alpha);
        return this.intercept - criticalValue * s * Math.sqrt(1 / n + (Math.pow(this.xbar, 2) / this.sxx));
    }

    /**
     * Calculates the upper limit for the param b.
     *
     * @param alpha the alpha
     * @return the upper limit for the param b
     */
    public double upperLimitParamb(double alpha) {
        double s = Math.sqrt(this.se / (this.n - 2));
        double criticalValue = getCriticValueStudent(alpha);
        return this.slope + criticalValue * s * Math.sqrt(1 / this.sxx);
    }

    /**
     * Calculates the lower limit for the param b.
     *
     * @param alpha the alpha
     * @return the lower limit for the param b
     */
    public double lowerLimitParamb(double alpha) {
        double s = Math.sqrt(this.se / (this.n - 2));
        double criticalValue = getCriticValueStudent(alpha);
        return this.slope - criticalValue * s * Math.sqrt(1 / this.sxx);
    }

    /**
     * Gets the test statistics for the param b
     *
     * @return the test statistics for the param b
     */
    public double getTestStatisticb() {
        return (this.slope) / (Math.sqrt(this.se / (this.n - 2)) / Math.sqrt(this.sxx));
    }

    /**
     * Gets the test statistics for the param a
     *
     * @return the test statistics for the param a
     */
    public double getTestStatistica() {
        return (this.intercept) / (Math.sqrt(this.se / (this.n - 2)) / Math.sqrt((1 / n) + Math.pow(this.xbar, 2) / this.sxx));
    }

    /**
     * Gets the R^2.
     *
     * @return the R^2
     */
    public double getR2() {
        return r2;
    }

    /**
     * Gets the ST.
     *
     * @return the ST
     */
    public double getSt() {
        return st;
    }

    /**
     * Gets the MSE.
     *
     * @return the MSE
     */
    public double getMse() {
        return mse;
    }

    /**
     * Gets the MSR.
     *
     * @return the MSR
     */
    public double getMsr() {
        return msr;
    }

    /**
     * Gets the f0.
     *
     * @return the f0
     */
    public double getF0() {
        return f0;
    }

    /**
     * Gets the SR.
     *
     * @return the SR
     */
    public double getSr() {
        return sr;
    }

    /**
     * Gets the SE.
     *
     * @return the SE
     */
    public double getSe() {
        return se;
    }

    /**
     * Gets the R by making the square root of R^2.
     *
     * @return the R
     */
    public double getR() {
        return Math.sqrt(this.r2);
    }

    /**
     * Gets the n.
     *
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * Returns a string representation of the simple linear regression model.
     *
     * @return a string representation of the simple linear regression model,
     * including the best-fit line and the coefficient of determination
     * R^2
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("%.4f n + %.4f", slope(), intercept()));

        return s.toString();
    }
}

````

#Class MultiLinearRegression
````java
package app.domain.shared;

import app.domain.shared.exceptions.InvalidLengthException;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

public class MultiLinearRegression implements Regression {
    private double[][] C;
    private double F0;
    private double intercept;
    private double slope1;
    private double slope2;
    private double r2;
    private double r2Ajusted;
    private double[] betta;
    private double SQt;
    private double SQr;
    private double SQe;
    private double MQr;
    private double MQe;
    private double[][] x;
    private double[] y;
    private int n;
    private int k;

    /**
     * Does the multi linear regression.
     *
     * @param x the x (matrix)
     * @param y the y (array)
     */
    public MultiLinearRegression(double[][] x, double[] y) {

        if (x.length != y.length) {
            throw new IllegalArgumentException("The array's length are not equal!");
        }

        double[][] m1 = new double[x.length][x[0].length + 1];
        for (int i = 0; i < m1.length; i++) {
            m1[i][0] = 1;
            for (int j = 1; j < m1[i].length; j++) {
                m1[i][j] = x[i][j - 1];
            }
        }

        this.k = 2;
        this.n = y.length;
        this.x = m1;
        this.y = y;

        double[][] xt = transpose(this.x);

        double[][] xtx = matrixProduct(xt, this.x);

        double[][] xtxinv = inverse(xtx);

        double[][] xtxinvxt = matrixProduct(xtxinv, transpose(this.x));

        this.betta = new double[xtxinvxt.length];

        for (int j = 0; j < xtxinvxt.length; j++) {
            for (int m = 0; m < xtxinvxt[0].length; m++) {

                betta[j] += xtxinvxt[j][m] * y[m];

            }
        }

        this.C = inverse(matrixProduct(transpose(this.x), this.x));

        this.intercept = this.betta[0];
        this.slope1 = this.betta[1];
        this.slope2 = this.betta[2];


        this.SQt = calculateSQT(y);
        this.SQr = calculateSQR(y, betta, this.x);
        this.SQe = calculateSQE(this.SQt, this.SQr);

        this.r2 = SQr / SQt;


        this.r2Ajusted = calculateR2Adjusted(this.r2, n, k);

        this.MQr = SQr / this.k;


        this.MQe = this.SQe / (this.n - (this.k + 1));

        this.F0 = this.MQr / this.MQe;

    }

    /**
     * Calculates the product of a matrix and a vector.
     *
     * @param matrix the matrix
     * @param vector the vector
     * @return the matrix resulting from the multiplication
     */
    private static double[] matrixVectorProduct(double[][] matrix, double[] vector) {


        double[] product = new double[matrix.length];
        int i = 0;

        for (int j = 0; j < matrix.length; j++) {
            for (int k = 0; k < matrix.length; k++) {
                product[i] += matrix[j][k] * vector[k];
            }

            i++;

        }
        return product;
    }

    /**
     * Calculates the product of two matrices.
     *
     * @param matrixA the matrix A
     * @param matrixB the matrix B
     * @return the matrix resulting from the multiplication
     */
    private static double[][] matrixProduct(double[][] matrixA, double[][] matrixB) {

        double[][] product = new double[matrixA.length][matrixB[0].length];

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    product[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return product;
    }

    /**
     * Calculates the transposed matrix.
     *
     * @param matrix the matrix
     * @return the transposed matrix
     */
    private static double[][] transpose(double[][] matrix) {
        double[][] transpose = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                transpose[i][j] = matrix[j][i];
            }
        }
        return transpose;
    }

    /**
     * Calculates the determinant of a matrix.
     *
     * @param matrix the matrix
     * @return the matrix's determinant
     */
    //dar aqui os creditos
    //uses the laplace theorem to calculate the determinant
    private static double determinant(double[][] matrix) {
        if (matrix.length != matrix[0].length)
            throw new IllegalStateException("The matrix should be a square matrix!");

        if (matrix.length == 2)
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int i = 0; i < matrix[0].length; i++)
            det += Math.pow(-1, i) * matrix[0][i]
                    * determinant(minor(matrix, 0, i));
        return det;
    }

    /**
     * Calculates the inverse matrix.
     *
     * @param matrix the matrix
     * @return the inverse matrix of a given matrix
     */
    //calculates the inverse matrix using the complement matrix
    private static double[][] inverse(double[][] matrix) {
        double[][] inverse = new double[matrix.length][matrix.length];

        // minors and cofactors
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                inverse[i][j] = Math.pow(-1, i + j) * determinant(minor(matrix, i, j));

        // adjugate and determinant
        double det = 1.0 / determinant(matrix);
        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j <= i; j++) {
                double temp = inverse[i][j];
                inverse[i][j] = inverse[j][i] * det;
                inverse[j][i] = temp * det;
            }
        }

        return inverse;
    }

    private static double[][] minor(double[][] matrix, int row, int column) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != row && j < matrix[i].length; j++)
                if (j != column)
                    minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
        return minor;
    }

    /**
     * Gets the interception.
     *
     * @return the interception
     */
    public double getIntercept() {
        return intercept;
    }

    /**
     * Gets the slope 1.
     *
     * @return the slope 1
     */
    public double getSlope1() {
        return slope1;
    }

    /**
     * Gets the slope 2.
     *
     * @return the slope 2
     */
    public double getSlope2() {
        return slope2;
    }

    /**
     * Gets the F0.
     *
     * @return the F0
     */
    public double getF0() {
        return F0;
    }

    /**
     * Gets the critic value (t-Student).
     *
     * @param alpha the alpha
     * @return the critic value (t-Student)
     */
    public double getCriticValueStudent(double alpha) {
        TDistribution td = new TDistribution(this.n - this.k - 1);

        return td.inverseCumulativeProbability(1 - alpha);
    }

    /**
     * Gets the critic value (f-Snedecor).
     *
     * @param alphaf the alpha
     * @return the critic value (f-Snedecor)
     */
    public double getCriticValueFisher(double alphaf) {
        FDistribution fDistribution = new FDistribution(this.k, this.n - (this.k + 1));
        return fDistribution.inverseCumulativeProbability(1 - alphaf);
    }

    /**
     * Calculates the lower limit coefficient.
     *
     * @param index the index
     * @param alpha the alpha
     * @return the lower limit coefficient
     */
    public double lowerLimitCoefficient(int index, double alpha) {

        double critTD = getCriticValueStudent(alpha);

        double variance = this.MQe;

        return this.betta[index] - critTD * Math.sqrt(variance * this.C[index][index]);

    }

    /**
     * Calculates the upper limit coefficient.
     *
     * @param index the index
     * @param alpha the alpha
     * @return the upper limit coefficient
     */
    public double upperLimitCoefficient(int index, double alpha) {

        double critTD = getCriticValueStudent(alpha);

        double variance = this.MQe;

        return this.betta[index] + critTD * Math.sqrt(variance * this.C[index][index]);

    }

    /**
     * Calculates the SQr.
     *
     * @param y     the y (array)
     * @param betta the betta
     * @param x     the x (matrix)
     * @return the SQr
     */
    private double calculateSQR(double[] y, double[] betta, double[][] x) {
        double[][] bettat = new double[betta.length][1];
        for (int i = 0; i < betta.length; i++) {
            bettat[i][0] = betta[i];
        }
        x = transpose(x);


        double[] btxt = new double[x[0].length];
        for (int i = 0; i < x[0].length; i++) {
            for (int j = 0; j < bettat.length; j++) {
                btxt[i] += x[j][i] * bettat[j][0];
            }
        }

        double btxty = 0;
        for (int i = 0; i < y.length; i++) {
            btxty += y[i] * btxt[i];
        }

        return btxty - y.length * Math.pow(calculateym(y), 2);

    }

    /**
     * Calculates the ym.
     *
     * @param y the y (array)
     * @return the ym
     */
    private double calculateym(double[] y) {
        double ym = 0;

        for (int i = 0; i < y.length; i++) {
            ym += y[i];
        }
        return ym / y.length;

    }

    /**
     * Calculates the SQt.
     *
     * @param y the y (array)
     * @return the SQt
     */
    private double calculateSQT(double[] y) {
        int n = y.length;
        double yty = 0;
        for (int i = 0; i < y.length; i++) {
            yty += y[i] * y[i];
        }

        return yty - n * Math.pow(calculateym(y), 2);
    }

    /**
     * Calculates the SQe.
     *
     * @param SQT the SQt
     * @param SQR the SQr
     * @return the SQe
     */
    private double calculateSQE(double SQT, double SQR) {
        return SQT - SQR;
    }

    /**
     * Calculates the adjusted r2.
     *
     * @param r2 the r2
     * @param n  the n
     * @param k  the k
     * @return the adjusted r2
     */
    private double calculateR2Adjusted(double r2, double n, double k) {

        return (1 - (((n - 1) / (n - (k + 1))) * (1 - r2)));
    }

    /**
     * Gets the estimated y.
     *
     * @param x the x (array)
     * @return the estimated y
     * @throws InvalidLengthException
     */
    public double getEstimate(double[] x) throws InvalidLengthException {
        if (x.length != this.betta.length - 1) {
            throw new InvalidLengthException();
        }

        double yEstimated = 0;

        yEstimated += yEstimated + this.betta[0];
        for (int i = 0; i < x.length; i++) {
            yEstimated += this.betta[i + 1] * x[i];
        }

        return yEstimated;
    }

    /**
     * Calculates the lower limit answer.
     *
     * @param x0    the x0 (array)
     * @param alpha the alpha
     * @return the lower limit answer
     * @throws InvalidLengthException
     */
    public double lowerLimitAnswer(double[] x0, double alpha) throws InvalidLengthException {
        if (x0.length != this.betta.length - 1) {
            throw new InvalidLengthException();
        }

        double[] x1 = new double[x0.length + 1];

        x1[0] = 1;

        for (int i = 1; i < x1.length; i++) {
            x1[i] = x0[i - 1];
        }

        double[][] x1t = new double[x1.length][1];

        for (int i = 0; i < x1.length; i++) {
            x1t[i][0] = x1[i];
        }

        double[] cx0 = matrixVectorProduct(this.C, x1);

        double xtcx = 0;

        for (int i = 0; i < cx0.length; i++) {
            xtcx += cx0[i] * x1t[i][0];
        }


        double critTD = getCriticValueStudent(alpha);
        double variance = this.MQe;

        return getEstimate(x0) - critTD * Math.sqrt(variance * (1 + xtcx));

    }

    /**
     * Calculates the upper limit answer.
     *
     * @param x0    the x0 (array)
     * @param alpha the alpha
     * @return the upper limit answer
     * @throws InvalidLengthException
     */
    public double upperLimitAnswer(double[] x0, double alpha) throws InvalidLengthException {
        if (x0.length != this.betta.length - 1) {
            throw new InvalidLengthException();
        }

        double[] x1 = new double[x0.length + 1];

        x1[0] = 1;

        for (int i = 1; i < x1.length; i++) {
            x1[i] = x0[i - 1];
        }

        double[][] x1t = new double[x1.length][1];

        for (int i = 0; i < x1.length; i++) {
            x1t[i][0] = x1[i];
        }

        double[] cx0 = matrixVectorProduct(this.C, x1);

        double xtcx = 0;

        for (int i = 0; i < cx0.length; i++) {
            xtcx += cx0[i] * x1t[i][0];
        }

        double critTD = getCriticValueStudent(alpha);
        double variance = this.MQe;

        return getEstimate(x0) + critTD * Math.sqrt(variance * (1 + xtcx));

    }

    /**
     * Gets the test statistics.
     *
     * @param index the index
     * @return the test statistics
     */
    public double getTestStatistics(int index) {
        return this.betta[index] / Math.sqrt(this.MQe * this.C[index][index]);
    }

    /**
     * Gets the r2.
     *
     * @return the r2
     */
    public double getR2() {
        return r2;
    }

    /**
     * Gets the r by making the square root of r2.
     *
     * @return the r
     */
    @Override
    public double getR() {
        return Math.sqrt(this.r2);
    }

    /**
     * Gets the adjusted r2.
     *
     * @return the adjusted r2
     */
    public double getR2Adjusted() {
        return r2Ajusted;
    }

    /**
     * Gets the SQt.
     *
     * @return the SQt
     */
    public double getSQt() {
        return SQt;
    }

    /**
     * Gets the SQr.
     *
     * @return the SQr
     */
    public double getSQr() {
        return SQr;
    }

    /**
     * Gets the SQe.
     *
     * @return the SQe
     */
    public double getSQe() {
        return SQe;
    }

    /**
     * Gets the MQr.
     *
     * @return the MQr
     */
    public double getMQr() {
        return MQr;
    }

    /**
     * Gets the MQe.
     *
     * @return the MQe
     */
    public double getMQe() {
        return MQe;
    }

    /**
     * Gets the n.
     *
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * Gets the k.
     *
     * @return the k
     */
    public int getK() {
        return k;
    }

    /**
     * Returns the textual description of the multi linear regression in the format: slope1, slope2 and interception.
     *
     * @return the multi linear regression results/details
     */
    @Override
    public String toString() {
        return slope1 + "x1 + " + slope2 + "x2 + " + intercept;
    }
}

````


# 6. Integration and Demo

# 7. Observations
