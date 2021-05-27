package app.controller;

import app.domain.mappers.TestListMapper;
import app.domain.mappers.dto.TestDTO;
import app.domain.model.Company;
import app.domain.model.SampleStore;
import app.domain.model.TestType;
import app.domain.model.Test;
import app.domain.stores.TestStore;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;


import java.util.List;

public class RecordSampleController {

    private Company company;
    private TestStore store;
    private TestStore tList;
    private SampleStore sampleList;


    public RecordSampleController(Company company) {
        this.company = company;
    }

    public RecordSampleController() {
        this(App.getInstance().getCompany());

    }


    public List<TestDTO> tList() {
        this.tList = company.testList();
        TestListMapper typeMapper = new TestListMapper();
        return typeMapper.toDTO(tList);
    }
    public void getLists(){
        this.sampleList = new SampleStore();
        this.tList = company.testList();
    }
    public String getTest(){
        return store.getTest();
    }
    public void createSample(String testID) throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        sampleList.createSample(testID);

    }

    public String getSample(){
        return sampleList.getSample();
    }
    public boolean saveSample(){
        return sampleList.saveSample();
    }
}
