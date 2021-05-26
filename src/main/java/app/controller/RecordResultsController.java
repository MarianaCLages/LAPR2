package app.controller;

import app.domain.mappers.TestParameterListMapper;
import app.domain.mappers.dto.TestParameterDTO;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.model.TestParameter;
import app.domain.stores.TestStore;

import java.util.List;

public class RecordResultsController {
    private Company company;
    private TestStore tStore;
    private List<TestParameter> testParameters;
    private Test t;

    public RecordResultsController() {
        this(App.getInstance().getCompany());
    }

    public RecordResultsController(Company company) {
        this.company = company;
        this.tStore = company.testList();
    }

    public List<TestParameterDTO> getTestParameterList(String sampleId){
        this.t = tStore.getTest(sampleId);
        if (this.t == null){
            throw new IllegalStateException("Sample does not exists");
        }else {
            testParameters = t.getTestParam();
            TestParameterListMapper testParameterListMapper = new TestParameterListMapper();
            return testParameterListMapper.toDTO(testParameters);
        }
    }

    public boolean addTestResult(String parameterCode, double result) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
       if (t.addTestResult(parameterCode,result)){
           t.changeState("SAMPLE_ANALYSED");
           return true;
       }
       return false;
    }
}
