package app.controller;

import app.domain.mappers.TestListMapper;
import app.domain.mappers.TestStoreMapper;
import app.domain.mappers.dto.TestDTO;
import app.domain.mappers.dto.TestStoreDTO;
import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.model.TestParameter;
import app.domain.stores.TestStore;

import java.util.List;

public class ViewResultsController {

    private Company company;
    private Client client;
    private TestStore store;
    private Test test;
    private TestStore tList;

    public ViewResultsController() {
        this(App.getInstance().getCompany());
    }

    public ViewResultsController(Company company) {
        this.company = company;
    }

    public String  CompareEmail(){
        company.getClientEmail(this.client).equals(company.getUserID());
            return company.getClientTin();
    }

    public List<TestStoreDTO> getListOfTests() {
        TestStoreMapper mapper = new TestStoreMapper();
        return mapper.toDTO(store);
    }


}
