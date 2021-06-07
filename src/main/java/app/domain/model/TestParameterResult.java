package app.domain.model;

import java.io.Serializable;

/**
 * Class that represents the result of a parameter associated with a test
 */
public class TestParameterResult implements Serializable {
    double result;
    String paramId;
    RefValue refValue;

    /**
     *
     * @param result double that represents numerical the result of the analysis of the test parameters
     * @param paramId unique Id that represents the parameter that will receive the result
     * @param refValue reference values of the results of the parameter
     */
    public TestParameterResult(double result, String paramId, RefValue refValue) {

        this.result = result;
        this.paramId = paramId;
        this.refValue = refValue;
    }

    /**
     * Method that represents the object in a string
     * @return a string that represents the object
     */
    @Override
    public String toString() {
        return "Parameter: " + paramId + " ->" + " Result: " + result + " " + refValue.getMetric() + " . Reference: Max: " + refValue.getMaxValue() + "; Min: " + refValue.getMinValue();
    }
}
