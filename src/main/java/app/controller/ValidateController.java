package app.controller;

import app.domain.mappers.TestStoreMapper;
import app.domain.mappers.TestTypeListMapper;
import app.domain.mappers.dto.TestStoreDTO;
import app.domain.mappers.dto.TestTypeDTO;
import app.domain.model.Company;
import app.domain.stores.TestStore;

import java.util.List;

public class ValidateController {
 /*   private Company company;
    private TestStore tSt;

    public ValidateController(){
        this(App.getInstance().getCompany());
    }

    public ValidateController(Company company) {
        this.company = company;
        this.tSt = company.testList();
    }



    public List<TestStoreDTO> getListOfTests() {
        this.tSt = company.testList();
        TestStoreMapper tSMapper = new TestStoreMapper();
        return tSMapper.toDTO(tSt);
    }


    public List<TestTypeDTO> getTestTypeList() {
        this.ttList = company.testTypeList();
        TestTypeListMapper typeMapper = new TestTypeListMapper();
        return typeMapper.toDTO(ttList);
    }

    public boolean addTest(String testID) {return tSt.add(tSt.getTestWithID(testID));}

    public boolean validateTestList(){ return tSt.validateTestList();}
*/

}
