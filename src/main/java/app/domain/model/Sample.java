package app.domain.model;


import net.sourceforge.barbecue.Barcode;

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
}
