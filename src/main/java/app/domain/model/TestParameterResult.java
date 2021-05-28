package app.domain.model;

public class TestParameterResult {
    double result;
    String paramId;
    RefValue refValue;

    public TestParameterResult(double result, String paramId, RefValue refValue) {

        this.result = result;
        this.paramId = paramId;
        this.refValue = refValue;
    }

    @Override
    public String toString() {
        return "Parameter: " + paramId + " ->" + " Result: " + result + " " + refValue.getMetric() + " . Reference: Max: " + refValue.getMaxValue() + "; Min: " + refValue.getMinValue();
    }
}
