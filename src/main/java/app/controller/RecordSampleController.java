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

/**
 * Controller of the User Story : Record the samples collected in the scope of a given test
 */
public class RecordSampleController {

    private Company company;
    private TestStore store;
    private TestStore tList;
    private SampleStore sampleList;
    private Test test;

    /**
     * Constructor of the class, receives an instance of the company class
     *
     * @param company instance of Company
     */
    public RecordSampleController(Company company) {
        this.company = company;
    }

    /**
     * Constructor of the class, gets an instance of the company class
     */
    public RecordSampleController() {
        this(App.getInstance().getCompany());
    }

    /**
     * This method creates a list to be filled with objects with characteristics from the objects meant to be presented
     *
     * @return a list filled with the data needed
     */
    public List<TestDTO> tList() {
        this.tList = company.getTestList();
        TestListMapper typeMapper = new TestListMapper();
        return typeMapper.toDTO(tList);
    }

    /**
     * This method returns the objects in the lists of Samples and Tests in the System
     */
    public void getLists() {
        this.sampleList = company.getSampleStore();
        this.tList = company.getTestList();
    }

    /**
     *
     * @return String that represents the Test instance
     */
    public String getTest() {
        return store.getTest();
    }

    /**
     * Creates a new Sample object by calling its constructor
     *
     * @param testID code of the test
     * @throws ClassNotFoundException stops the execution of the program if the class isn't found
     * @throws InstantiationException stops the execution of the program if the current method doesn't have access to the definitions of the specified requirements
     * @throws BarcodeException stops the execution of the program if the specified class object cannot be instantiated
     * @throws IllegalAccessException stops the execution of the program if the requirements are not met
     */
    public void createSample(String testID) throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        sampleList.createSample(testID);

    }

    /**
     * @return String that represents the Sample instance
     */
    public String getSample() {
        return sampleList.getSample();
    }

    /**
     * This method calls the method of the instance of SampleStore that saves the object Sample in the Sample List
     *
     * @return a boolean value that indicates the success of the operation
     * @throws IOException stops the execution of the program if the I/O operations fail or are interrupted
     * @throws OutputException stops the execution of the program if the Output operation fails or is interrupted
     */
    public boolean saveSample() throws IOException, OutputException {
        return sampleList.saveSample();
    }

    /**
     * This method searches through the test list the test associated with a specified code and changes the state of the object test
     *
     * @param testID code of the test
     */
    public void confirm(String testID) {

        this.test = tList.getTestByCode(testID);
        this.test.changeState("SAMPLE_COLLECTED");

    }
}
