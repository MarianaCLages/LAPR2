package app.domain.model;

/**
 * Class that represents a Sample
 */
public class Sample {

    private final String barcode;
    private final String testID;

    /**
     * Constructor of the Sample, it calls methods in order to validate the parameters
     *
     * @param testID id of the test
     * @param barcode unique number which is an attribute of the sample
     */
    public Sample(String testID, String barcode) {
        checkBarcodeRules(barcode);
        checkTestIDRules(testID);
        this.testID = testID;
        this.barcode = barcode;

    }

    /**
     * This method checks if the Test ID provided meets the requirements, if not it throws a exception making the execution stop
     *
     * @param testID string with the id of the test
     */
    private void checkTestIDRules(String testID) {
        if (testID == null) {
            throw new IllegalArgumentException("The Test ID must exist");
        }
    }

    /**
     * This method checks if the Barcode provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param barcode unique number which is an attribute of the sample
     */
    private void checkBarcodeRules(String barcode) {
        if (barcode == null) {
            throw new IllegalArgumentException("The Barcode must exist");
        }
    }

    /**
     *
     * @return a string with the Barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @return a string with the Test ID
     */
    public String getTestID() {
        return testID;
    }

    /**
     * @return a string with the following format: "Sample testID=" testID", barcode=" barcode
     */
    @Override
    public String toString() {
        return "Sample: testID=" + testID + ", barcode=" + barcode;
    }
}
