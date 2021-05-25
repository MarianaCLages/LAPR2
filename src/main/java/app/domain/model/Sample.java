package app.domain.model;

import app.domain.stores.TestParameterStore;
import net.sourceforge.barbecue.Barcode;

public class Sample {

    private String collectingMethod;
    private net.sourceforge.barbecue.Barcode barcode;


    public Sample(net.sourceforge.barbecue.Barcode barcode){
        this.barcode = barcode;
    }


    public Object getBarcode() {
        return barcode;
    }
}
