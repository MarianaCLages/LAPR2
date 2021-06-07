package app.domain.mappers.dto;

import java.io.Serializable;

public class TestTypeDTO implements Serializable {
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

    @Override
    public String toString() {
        return "TestType :" + "testID=" + testID + ", description=" + description;
    }
}
