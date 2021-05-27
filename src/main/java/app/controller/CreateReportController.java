package app.controller;


import app.domain.mappers.TestListMapper;
import app.domain.mappers.dto.TestDTO;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.stores.TestStore;


import java.util.List;

public class CreateReportController {

    private Company company;
    private TestStore tList;
    private Test t;




    public CreateReportController(Company company) {
        this.company = company;
    }

    public CreateReportController() {
        this(App.getInstance().getCompany());
    }



    public List<TestDTO> tList() {
        this.tList = company.testList();
        TestListMapper typeMapper = new TestListMapper();
        return typeMapper.toDTO(tList);
    }

    public String getResults(String ) {
        this.t = tList.getTestByCode();
        return t.getResults();
    }

    public void createReport(String diagnosis){
        t.createReport(diagnosis);
    }

    public void saveReport(){
        t.saveReport();
    }

    public void getTestCode(){
        t.getTestCode();
    }



}
