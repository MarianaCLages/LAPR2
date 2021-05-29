package app.domain.model;


public class Sample {

    private final String barcode;
    private final String testID;


    public Sample(String testID, String barcode) {
        checkBarcodeRules(barcode);
        checkTestIDRules(testID);
        this.testID = testID;
        this.barcode = barcode;

    }

    private void checkTestIDRules(String testID) {
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
        return testID;
    }


    @Override
    public String toString() {
        return "Sample: testID=" + testID + ", barcode=" + barcode;
    }
}
