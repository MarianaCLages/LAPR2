package app.controller;

import app.domain.mappers.TestListMapper;
import app.domain.mappers.dto.TestDTO;
import app.domain.model.Company;
import app.domain.model.TestType;
import app.domain.model.Test;
import app.domain.stores.TestStore;


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

    public void createSampleBarcode(net.sourceforge.barbecue.Barcode barcode){
        sample.createSample(barcode);
    }


}
