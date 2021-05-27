package app.domain.model;


public class Sample {

    private String barcode;
    private String testID;


    public Sample(String TestID, String barcode){
        this.testID = testID;
        this.barcode = barcode;


    }

    public String getBarcode() {
        return barcode;
    }

    public String getTestID() {
        return testID;
    }


    @Override
    public String toString() {
        return "Sample: testID=" +testID +", barcode=" +barcode;
    }
}
