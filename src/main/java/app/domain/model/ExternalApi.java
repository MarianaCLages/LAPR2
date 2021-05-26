package app.domain.model;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import java.awt.image.BufferedImage;


public class ExternalApi implements BarcodeAdapter{

    Barcode barcode;
    private static int barcodegrown = 1;


    public ExternalApi(){

    }




    public String createBarcode() throws BarcodeException {
        barcodegrown++;
        Barcode barcode = BarcodeFactory.createUPCA(String.format("%011d",barcodegrown));
        return barcode.getData();
    }

    public BufferedImage createBarcodeImage(Barcode barcode) throws OutputException {
        return BarcodeImageHandler.getImage(barcode);
    }

    public String getBarcode(){
        return barcode.getData();
    }
}
