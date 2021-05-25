package app.mappers;

import app.domain.model.Parameter;
import app.domain.model.Test;
import app.domain.model.TestType;
import app.domain.stores.TestStore;
import app.mappers.dto.ParameterDTO;
import app.mappers.dto.TestDTO;
import app.mappers.dto.TestTypeDTO;

import java.util.ArrayList;
import java.util.List;

public class TestListMapper {

    private TestDTO toDTO(Test t) {
        return new TestDTO(t.getTestNhsNumber(), t.getTestCode());
    }

    public List<TestDTO> toDTO(TestStore tList) {
        List<Test> testList = tList.getList();
        List<TestDTO> testDTO = new ArrayList<>();
        for (Test test : testList) {
            testDTO.add(this.toDTO(test));
        }
        return testDTO;
    }
}
