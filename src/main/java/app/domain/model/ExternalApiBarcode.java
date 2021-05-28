package app.domain.model;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ExternalApiBarcode implements BarcodeAdapter{

    Barcode barcode;
    BufferedImage barcodeimage;


    public ExternalApiBarcode(){
    }


    public String createBarcode(String ID) throws BarcodeException {
        this.barcode = BarcodeFactory.createUPCA(ID);
        return barcode.getData();
    }

    public BufferedImage createBarcodeImage(Barcode barcode) throws OutputException {
        this.barcodeimage = BarcodeImageHandler.getImage(barcode);
        return this.barcodeimage;
    }

    public String getBarcode(){
        return this.barcode.getData();
    }

    public void barcodeImage() throws IOException, OutputException {

        String fileName= "Barcode_"+ getBarcode();
        File outputFile= new File("Barcode\\"+ fileName+ ".jpg" );
        ImageIO.write(createBarcodeImage(this.barcode) ,"jpg", outputFile);

    }

}
