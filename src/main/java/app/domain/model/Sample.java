package app.domain.model;


import net.sourceforge.barbecue.Barcode;

public class Sample {

    private String collectingMethod;
    private Barcode barcode;


    public Sample(Barcode barcode){
        this.barcode = barcode;
    }


    public Object getBarcode() {
        return barcode;
    }
}
