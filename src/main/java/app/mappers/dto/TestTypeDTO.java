package app.mappers.dto;

public class TestTypeDTO {
    private String testID;
    private String description;

    public TestTypeDTO(String testID, String description) {
        this.testID = testID;
        this.description = description;
    }

    public String getTestID() {
        return testID;
    }

    public String getDescription() {
        return description;
    }
}
