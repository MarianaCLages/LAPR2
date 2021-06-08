package app.domain.model;

import app.domain.stores.SampleStore;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.Module;
import net.sourceforge.barbecue.output.Output;
import net.sourceforge.barbecue.output.OutputException;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

public class ExternalApiBarcodeTest {

    @Test
    public void createBarcodeTest() throws BarcodeException {
        //Arrange + Act
        Sample rep = new Sample("123456789012", "12345678901");
        ExternalApiBarcode barcode = new ExternalApiBarcode();
        String actual = barcode.createBarcode("12345678901");

        String expected = "12345678901";
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void createBarcodeImageTest() throws OutputException, IOException {
        SampleStore store = new SampleStore();
        Sample rep = new Sample("123456789012", "12345678901");

        store.saveSample();
        BarcodeAdapter em = new BarcodeAdapter() {
            @Override
            public String createBarcode(String ID) throws BarcodeException {
                return null;
            }

            @Override
            public void createBarcodeImage(Barcode barcode) throws OutputException {

            }

            @Override
            public String getBarcode() {
                return null;
            }

            @Override
            public void barcodeImage() throws IOException, OutputException {

            }
        };
        em.barcodeImage();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createBarcodeImageErrorTest() throws OutputException, IOException {
        SampleStore store = new SampleStore();
        Sample rep = new Sample("123456789012", null);

        store.saveSample();
        BarcodeAdapter em = new BarcodeAdapter() {
            @Override
            public String createBarcode(String ID) throws BarcodeException {
                return null;
            }

            @Override
            public void createBarcodeImage(Barcode barcode) throws OutputException {

            }

            @Override
            public String getBarcode() {
                return null;
            }

            @Override
            public void barcodeImage() throws IOException, OutputException {

            }
        };
        em.barcodeImage();
    }
    @Test
    public void getBarcodeTest() throws IOException, OutputException {
        //Arrange + Act
        SampleStore store = new SampleStore();
        Sample rep = new Sample("123456789012", "12345678901");

        store.saveSample();
        BarcodeAdapter em = new BarcodeAdapter() {
            @Override
            public String createBarcode(String ID) throws BarcodeException {
                return null;
            }

            @Override
            public void createBarcodeImage(Barcode barcode) throws OutputException {

            }

            @Override
            public String getBarcode() {
                return null;
            }

            @Override
            public void barcodeImage() throws IOException, OutputException {

            }
        };
        em.getBarcode();


    }

    @Test
    public void barcodeImageTest() throws IOException, OutputException {
        SampleStore store = new SampleStore();
        Sample rep = new Sample("123456789012", "12345678901");

        store.saveSample();
        BarcodeAdapter em = new BarcodeAdapter() {
            @Override
            public String createBarcode(String ID) throws BarcodeException {
                return null;
            }

            @Override
            public void createBarcodeImage(Barcode barcode) throws OutputException {

            }

            @Override
            public String getBarcode() {
                return null;
            }

            @Override
            public void barcodeImage() throws IOException, OutputException {

            }
        };
        em.barcodeImage();
    }

}