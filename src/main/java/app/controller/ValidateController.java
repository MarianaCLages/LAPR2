package app.controller;

import app.domain.mappers.TestListMapper;
import app.domain.mappers.TestStoreMapper;
import app.domain.mappers.dto.TestDTO;
import app.domain.mappers.dto.TestStoreDTO;
import app.domain.model.Company;
import app.domain.stores.TestStore;

import java.util.List;

public class ValidateController {
    private Company company;
    private TestStore tSt;
    private TestStoreDTO tListDTO;

    public ValidateController(){
        this(App.getInstance().getCompany());
    }

    public ValidateController(Company company) {
        this.company = company;
        this.tSt = company.testList();
    }

    public List<TestDTO> getListOfTests() {
        tSt.getListOfTestsToValidate();
        TestListMapper mapper = new TestListMapper();
        return mapper.toDTO(tSt);
    }


    public boolean addTest(String testID) {
        return tSt.addTest(tSt.getTestWithID(testID));

    }

  //  public boolean validateTestList(){ return tSt.validateTestList();}


}
