package app.domain.model;


public class Sample {

    private String barcode;
    private String TestID;


    public Sample(String TestID, String barcode){
        checkBarcodeRules(barcode);
        checkTestIDRules(TestID);
        this.TestID = TestID;
        this.barcode = barcode;

    }

    private void checkTestIDRules(String testID){
        if (testID == null) {
            throw new IllegalArgumentException("The Test ID must exist");
        }
    }

    private void checkBarcodeRules(String barcode) {
        if (barcode == null) {
            throw new IllegalArgumentException("The Test Code must exist");
        }
    }

    public String getBarcode() {
        return barcode;
    }

    public String getTestID() {
        return TestID;
    }


    @Override
    public String toString() {
        return "Sample: testID=" +TestID +", barcode=" +barcode;
    }
}
