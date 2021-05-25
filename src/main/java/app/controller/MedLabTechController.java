package app.controller;

import app.domain.model.Company;
import app.domain.model.TestType;
import app.domain.model.Test;
import app.domain.stores.TestStore;
import app.domain.stores.TestTypeStore;
import app.mappers.TestListMapper;
import app.mappers.TestTypeListMapper;
import app.mappers.dto.TestDTO;
import app.mappers.dto.TestTypeDTO;

import java.util.List;

public class MedLabTechController {

    private Company company;
    private TestStore store;
    private TestStore tList;
    private TestType testType;
    private Test sample;


    public List<TestDTO> tList() {
        this.tList = company.testList();
        TestListMapper typeMapper = new TestListMapper();
        return typeMapper.toDTO(tList);
    }

    public String getTest(){
        return store.getTest();
    }
/*
    public void createSampleBarcode(){
        sample.createSample(testType.getCollectingMethod(),);

    }

 */
}
