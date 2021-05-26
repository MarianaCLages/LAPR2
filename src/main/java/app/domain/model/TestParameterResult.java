package app.domain.model;

import app.domain.shared.Constants;

public class TestParameterResult {
    double result;
    String paramId;
    RefValue refValue;

    public TestParameterResult(double result, String paramId) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> oClass = Class.forName(Constants.RESULTS_REFERENCE_API);
        RefValueAdapter adap = (RefValueAdapter) oClass.newInstance();
        this.result = result;
        this.paramId = paramId;
        this.refValue = adap.getRefValue(paramId);
    }
}
