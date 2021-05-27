package app.domain.model;

import app.domain.shared.Constants;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;

import java.util.ArrayList;
import java.util.List;

public class SampleStore {


    private Sample sample;
    private List<Sample> testSamples;

    public SampleStore() {
        this.testSamples = new ArrayList<>();
    }


    public boolean createSample(String testID) throws ClassNotFoundException, IllegalAccessException, InstantiationException, BarcodeException {
        Class<?> oClass = Class.forName(Constants.BARCODE_API);
        BarcodeAdapter em = (BarcodeAdapter) oClass.newInstance();
        this.sample = new Sample(testID,em.createBarcode(CreateSampleID()));


        if (validateSample(this.sample)){
            testSamples.add(this.sample);

            return true;

        }else{
            return false;
        }
    }
    public boolean saveSample() {
        if (validateSample(this.sample)) {
            testSamples.add(this.sample);
            System.out.println(this.sample.toString());
            return true;
        } else {
            return false;
        }
    }


    public boolean validateSample(Sample sample) {
        return this.sample != null && !contains(this.sample) && !exists(this.sample);
    }

    private boolean exists(Sample sample) {
        for (Sample sample1 : this.testSamples) {
            if (sample.getBarcode().equals(sample1.getBarcode())) {
                return true;
            }
        }
        return false;
    }

    private boolean contains(Sample sample) {
        return testSamples.contains(sample);
    }

    public String CreateSampleID(){
        int ID = testSamples.size() + 1;
        String SampleID = String.valueOf(ID);
        String empty;
        empty = ""+SampleID;
        while(empty.length() < 11){
            empty = "0" + empty;
        }

        return empty;
    }

    public String getSample(){
        return this.sample.toString();
    }

}
