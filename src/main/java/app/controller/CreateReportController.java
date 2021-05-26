package app.controller;


import app.domain.mappers.TestTypeListMapper;
import app.domain.mappers.dto.TestTypeDTO;
import app.domain.model.Company;
import app.domain.model.TestType;
import app.domain.stores.TestStore;
import app.domain.stores.TestTypeStore;


import java.util.List;

public class CreateReportController {

    private Company company;
    private TestStore store;
    private TestTypeStore ttList;
    private TestType testType;

    public CreateReportController(Company company) {
        this.company = company;
        store = company.getTestList();
    }



    public List<TestTypeDTO> getTestTypeList() {
        this.ttList = company.testTypeList();
        TestTypeListMapper typeMapper = new TestTypeListMapper();
        return typeMapper.toDTO(ttList);
    }



}
