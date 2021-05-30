package app.domain.stores;

import app.domain.model.BarcodeAdapter;
import app.domain.model.Sample;
import app.domain.shared.Constants;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Class that represents a List of all the Test Samples in the system
 */
public class SampleStore {

    private Sample sample;
    private List<Sample> testSamples;
    private BarcodeAdapter em;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of Samples
     */
    public SampleStore() {
        this.testSamples = new ArrayList<>();
    }

    /**
     * This method creates a new Sample object by calling his constructor
     *
     * @param testID code of the test created
     * @return Sample object created
     * @throws ClassNotFoundException stops the execution of the program if the class isn't found
     * @throws IllegalAccessException stops the execution of the program if the current method doesn't have access to the definitions of the specified requirements
     * @throws InstantiationException stops the execution of the program if the specified class object cannot be instantiated
     * @throws BarcodeException stops the execution of the program if the requirements are not met
     */

    public boolean createSample(String testID) throws ClassNotFoundException, IllegalAccessException, InstantiationException, BarcodeException {
        Class<?> oClass = Class.forName(Constants.BARCODE_API);
        this.em = (BarcodeAdapter) oClass.newInstance();
        this.sample = new Sample(testID, em.createBarcode(createSampleID()));

        if (this.sample == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method is used to save the Sample object in the Array List already created, before adding the object the method validates it
     *
     * @return a boolean value that indicates the success of the operation
     * @throws IOException stops the execution of the program if the I/O operations fail or are interrupted
     * @throws OutputException stops the execution of the program if the Output operation fails or is interrupted
     */
    public boolean saveSample() throws IOException, OutputException {
        if (validateSample()) {
            testSamples.add(this.sample);
            System.out.println(this.sample.toString());
            em.barcodeImage();
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method checks if the Sample object received is not null, if it doesn't already exist in the ArrayList
     *
     * @return boolean value that is true if the object is not null and doesn't exist already in the ArrayList
     */
    public boolean validateSample() {
        return this.sample != null && !contains(this.sample) && !exists(this.sample);
    }

    /**
     * This method checks if the Sample object received already exists in the ArrayList
     *
     * @param sample
     * @return boolean value that is true if the object already exists in the ArrayList
     */
    private boolean exists(Sample sample) {
        for (Sample sample1 : this.testSamples) {
            if (sample.getBarcode().equals(sample1.getBarcode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks if the Sample object contains the List with the Test Samples
     *
     * @param sample
     * @return the List with the Sample object in it
     */
    private boolean contains(Sample sample) {
        return testSamples.contains(sample);
    }

    /**
     * This method sequentially creates the code of the sample made
     *
     * @return a string (a number) that represents the code of the sample
     */
    public String createSampleID() {
        int ID = testSamples.size() + 1;
        String SampleID = String.valueOf(ID);
        String empty;
        empty = "" + SampleID;
        while (empty.length() < 11) {
            empty = "0" + empty;
        }

        return empty;
    }

    /**
     * This method goes through all the objects in the TestSamples List and appends the String sampleID of the method getSample
     *
     * @param sampleID
     * @return with the Sample if the barcode showed is the same as the SampleID presented
     */
    public Sample getSample(String sampleID) {
        for (Sample s : testSamples) {
            if (s.getBarcode().equals(sampleID)) {
                return s;
            }
        }
        return null;
    }

    /**
     * @return the Sample object
     */
    public String getSample() {
        return this.sample.toString();
    }

}