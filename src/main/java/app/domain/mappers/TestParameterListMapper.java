package app.domain.mappers;

import app.domain.mappers.dto.TestParameterDTO;
import app.domain.model.TestParameter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestParameterListMapper implements Serializable {

    private TestParameterDTO toDTO(TestParameter t) {
        return new TestParameterDTO(t.getpCode());
    }

    public List<TestParameterDTO> toDTO(List<TestParameter> testParameters) {
        List<TestParameterDTO> testDTO = new ArrayList<>();
        for (TestParameter test : testParameters) {
            testDTO.add(this.toDTO(test));
        }
        return testDTO;
    }

}
