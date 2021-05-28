package app.controller;

import app.domain.mappers.TestStoreMapper;
import app.domain.mappers.dto.TestStoreDTO;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.stores.TestStore;

import java.util.List;

public class ValidateController {
    private Company company;
    private TestStore store;
    private List<Test> tSt;
    private TestStoreDTO tListDTO;

    public ValidateController(){
        this(App.getInstance().getCompany());
    }

    public ValidateController(Company company) {
        this.company = company;
        this.store = company.testList();
    }

    public List<TestStoreDTO> getListOfTests() {
        TestStoreMapper mapper = new TestStoreMapper();
        return mapper.toDTO(store);
    }


    public boolean addTest(String testID) {
        return tSt.add(store.getTestByCode(testID));
    }

    //public boolean validateTestList(){}


}
