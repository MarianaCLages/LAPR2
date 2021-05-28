package app.controller;

import app.domain.mappers.TestListMapper;
import app.domain.mappers.dto.TestDTO;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.stores.SampleStore;
import app.domain.stores.TestStore;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;
import java.util.List;

public class RecordSampleController {

    private Company company;
    private TestStore store;
    private TestStore tList;
    private SampleStore sampleList;
    private Test test;


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

    public void getLists() {
        this.sampleList = company.getSampleStore();
        this.tList = company.testList();
    }

    public String getTest() {
        return store.getTest();
    }

    public void createSample(String testID) throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        sampleList.createSample(testID);

    }

    public String getSample() {
        return sampleList.getSample();
    }

    public boolean saveSample() throws IOException, OutputException {
        return sampleList.saveSample();
    }

    public void confirm(String testID) {

        this.test = tList.getTestByCode(testID);
        this.test.changeState("SAMPLE_COLLECTED");

    }
}
