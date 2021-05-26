package app.domain.model;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import java.awt.image.BufferedImage;


public class ExternalApiBarcode implements BarcodeAdapter{

    Barcode barcode;


    public ExternalApiBarcode(){

    }




    public String createBarcode() throws BarcodeException {
        Barcode barcode = BarcodeFactory.createUPCA(String.format("%011d"));
        return barcode.getData();
    }

    public BufferedImage createBarcodeImage(Barcode barcode) throws OutputException {
        return BarcodeImageHandler.getImage(barcode);
    }

    public String getBarcode(){
        return barcode.getData();
    }
}