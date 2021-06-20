package app.domain.model;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExternalApiBarcode implements BarcodeAdapter, Serializable {

    Barcode barcode;
    private static final Logger LOGGER = Logger.getLogger( ExternalApiBarcode.class.getName() );
    public ExternalApiBarcode() {
        // constructor
    }

    public String createBarcode(String ID) throws BarcodeException {
        this.barcode = BarcodeFactory.createUPCA(ID);
        return barcode.getData();
    }

    public void createBarcodeImage(Barcode barcode) throws OutputException {
        barcode.setPreferredBarHeight(120);
        String fileName = "Barcode_" + getBarcode();
        File file1 = new File("./Barcode/");
        file1.mkdir();

        File outputFile = new File("./Barcode/" + fileName + ".jpg");

        try {
            BarcodeImageHandler.saveJPEG(barcode, outputFile);
        } catch (IOException e) {
            LOGGER.log( Level.SEVERE, e.toString(), e );
        }
    }

    public String getBarcode() {
        return this.barcode.getData();
    }

    public void barcodeImage() throws OutputException {
        createBarcodeImage(this.barcode);
    }

}
