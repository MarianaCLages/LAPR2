package app.domain.mappers;

import app.domain.mappers.dto.TestStoreDTO;
import app.domain.model.Test;
import app.domain.stores.TestStore;

import java.util.ArrayList;
import java.util.List;

public class TestStoreMapper {

    private TestStoreDTO toDTO(Test t) {return new TestStoreDTO(t);}

    public List<TestStoreDTO> toDTO(TestStore store) {
        List<Test> testList = store.getListOfTestsToValidate();
        List<TestStoreDTO> tListDto = new ArrayList<>();
        for (Test test : testList) {
            tListDto.add(this.toDTO(test));
        }
        return tListDto;
    }
}
