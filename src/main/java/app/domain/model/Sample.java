package app.domain.model;

import app.domain.stores.TestParameterStore;

public class Sample {

    private String collectingMethod;
    private String barcode;


    public Sample(String collectingMethod, String barcode){
        this.collectingMethod = collectingMethod;
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }
}
