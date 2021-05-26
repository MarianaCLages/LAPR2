package app.domain.model;


public class Sample {

    private net.sourceforge.barbecue.Barcode barcode;


    public Sample(net.sourceforge.barbecue.Barcode barcode){
        this.barcode = barcode;
    }


    public net.sourceforge.barbecue.Barcode getBarcode() {
        return barcode;
    }
}
