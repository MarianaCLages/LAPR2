package app.domain.mappers.dto;

import java.io.Serializable;

public class TestDTO implements Serializable {
    private String testNhsNumber;
    private String testCode;

    public TestDTO(String testNhsNumber, String testCode) {
        this.testNhsNumber = testNhsNumber;
        this.testCode = testCode;
    }

    public String getTestNhsNumber() {
        return testNhsNumber;
    }

    public String getTestCode() {
        return testCode;
    }

    @Override
    public String toString() {
        return "Test: " + "testNhsNumber= " + testNhsNumber + ", testCode= " + testCode;
    }
}
