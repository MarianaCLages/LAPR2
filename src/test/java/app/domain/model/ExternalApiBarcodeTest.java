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
    public void createBarcodeImageTest() throws BarcodeException, OutputException, IOException {
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
        ExternalApiBarcode e = new ExternalApiBarcode();
        Barcode b = new Barcode("12345678901") {
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