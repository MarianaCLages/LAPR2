package app.mappers.dto;

public class TestDTO {
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
}
