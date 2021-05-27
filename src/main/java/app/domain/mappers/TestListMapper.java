package app.domain.mappers;

import app.domain.model.Test;
import app.domain.stores.TestStore;
import app.domain.mappers.dto.TestDTO;

import java.util.ArrayList;
import java.util.List;

public class TestListMapper {

    private TestDTO toDTO(Test t) {
        return new TestDTO(t.getTestCode(), t.getTestNhsNumber());
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
