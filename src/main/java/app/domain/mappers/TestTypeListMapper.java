package app.domain.mappers;

import app.domain.mappers.dto.TestTypeDTO;
import app.domain.model.TestType;
import app.domain.stores.TestTypeStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestTypeListMapper implements Serializable {

    private TestTypeDTO toDTO(TestType testType) {
        return new TestTypeDTO(testType.getTestID(), testType.getDescription());
    }

    /**
     * creates a list of data transfer objects
     *
     * @param testTypes
     * @return
     */
    public List<TestTypeDTO> toDTO(TestTypeStore testTypes) {
        List<TestType> typesList = testTypes.getList();

        List<TestTypeDTO> testTypesDTO = new ArrayList<>();
        for (TestType type : typesList) {
            testTypesDTO.add(this.toDTO(type));
        }
        return testTypesDTO;
    }

}
