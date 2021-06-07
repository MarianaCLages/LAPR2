package app.domain.model;

import java.io.Serializable;

/**
 * Class that represents a parameter associated with a test
 */
public class TestParameter implements Serializable {
    private final String pCode;
    private TestParameterResult testParameterResult;

    /**
     * @param pCode Parameter Code
     */
    public TestParameter(String pCode) {
        this.pCode = pCode;
    }

    /**
     * returns the code of the parameter
     *
     * @return the code of the parameter
     */
    public String getpCode() {
        return pCode;
    }

    /**
     * Associates a result to the test Parameter by instantiation a new TestParameterResult object
     *
     * @param result   double that represents numerical the result of the analysis of the test parameters
     * @param refValue reference values of the results of the parameter
     */
    public void addResult(double result, RefValue refValue) {
        this.testParameterResult = new TestParameterResult(result, this.pCode, refValue);
    }

    /**
     * @return a string that represents the test results
     */
    public String getTestParameterResult() {
        return this.testParameterResult.toString();
    }

    /**
     * Method that represents the object in a string
     *
     * @return a string that represents the object
     */
    @Override
    public String toString() {
        return "TestParameter{" +
                "pCode='" + pCode + '\'' +
                ", testParameterResult=" + testParameterResult +
                '}';
    }
}
