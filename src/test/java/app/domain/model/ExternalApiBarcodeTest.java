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
}