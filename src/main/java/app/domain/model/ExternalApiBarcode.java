package app.domain.model;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import java.io.File;
import java.io.IOException;


public class ExternalApiBarcode implements BarcodeAdapter {

    Barcode barcode;

    public ExternalApiBarcode() {
        // constructor
    }


    public String createBarcode(String ID) throws BarcodeException {
        this.barcode = BarcodeFactory.createUPCA(ID);
        return barcode.getData();
    }

    public void createBarcodeImage(Barcode barcode) throws OutputException {
        String fileName = "Barcode_" + getBarcode();
        File outputFile = new File("Barcode\\" + fileName + ".jpg");

        try {
            BarcodeImageHandler.saveJPEG(barcode, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBarcode() {
        return this.barcode.getData();
    }

    public void barcodeImage() throws OutputException {
        createBarcodeImage(this.barcode);


    }

}
