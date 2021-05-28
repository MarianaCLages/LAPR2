package app.domain.model;


import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;

public interface BarcodeAdapter {

    String createBarcode(String ID) throws BarcodeException;

    void createBarcodeImage(Barcode barcode) throws OutputException;

    String getBarcode();

    void barcodeImage() throws IOException, OutputException;
}
