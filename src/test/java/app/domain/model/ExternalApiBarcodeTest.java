package app.domain.model;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.Module;
import net.sourceforge.barbecue.output.Output;
import net.sourceforge.barbecue.output.OutputException;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class ExternalApiBarcodeTest {

    @Test
    public void createBarcodeTest() throws BarcodeException {
        //Arrange + Act
        Sample rep = new Sample("123456789012","12345678901");
        ExternalApiBarcode barcode = new ExternalApiBarcode();
        String actual = barcode.createBarcode("12345678901");

        String expected = "12345678901";
        Assert.assertEquals(expected,actual);

    }


    @Test
    public void getBarcode() throws BarcodeException {
        //Arrange + Act
        Sample rep = new Sample("123456789012","12345678901");
        ExternalApiBarcode barcode = new ExternalApiBarcode();
        Barcode something = new Barcode("28-05-2021") {
            @Override
            protected Module[] encodeData() {
                return new Module[0];
            }

            @Override
            protected Module calculateChecksum() {
                return null;
            }

            @Override
            protected Module getPreAmble() {
                return null;
            }

            @Override
            protected Module getPostAmble() {
                return null;
            }

            @Override
            protected Dimension draw(Output output, int i, int i1, int i2, int i3) throws OutputException {
                return null;
            }
        };
        String actual = something.getData();

        String expected = "28-05-2021";
        Assert.assertEquals(expected,actual);

    }







}