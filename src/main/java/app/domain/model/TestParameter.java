package app.domain.model;

public class TestParameter {
    private final String pCode;
    private TestParameterResult testParameterResult;

    /**
     * @param pCode Parameter Code
     */
    public TestParameter(String pCode) {
        this.pCode = pCode;
    }

    public String getpCode() {
        return pCode;
    }

    public void addResult(double result, RefValue refValue) {
        this.testParameterResult = new TestParameterResult(result, this.pCode, refValue);
    }


    public String getTestParameterResult() {
        return this.testParameterResult.toString();
    }

    @Override
    public String toString() {
        return "TestParameter{" +
                "pCode='" + pCode + '\'' +
                ", testParameterResult=" + testParameterResult +
                '}';
    }
}
