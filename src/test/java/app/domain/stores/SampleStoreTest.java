package app.domain.stores;


import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.Sample;
import app.domain.model.TestType;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SampleStoreTest {

    @Test
    public void createSampleTest() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        //Arrange + act
        SampleStore store = new SampleStore();

        //Assert
        Assert.assertTrue(store.createSample("BL000"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void createSampleErrorTest() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        //Arrange + act
        SampleStore store = new SampleStore();

        //Assert
        Assert.assertTrue(store.createSample(null));
    }

    @Test
    public void saveSampleTest() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException, IOException, OutputException {
        //Arrange + act
        SampleStore store = new SampleStore();
        store.createSample("BL000");

        //Assert
        Assert.assertTrue(store.saveSample());
    }

    @Test
    public void validateSampleTest() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        //Arrange + act
        SampleStore store = new SampleStore();
        store.createSample("BL000");

        //Assert
        Assert.assertTrue(store.validateSample());
    }

    @Test
    public void createSampleIDTest() {
        //Arrange + act

        SampleStore store = new SampleStore();
        String s = "00000000001";
        String a = store.createSampleID();
        //Assert
        Assert.assertEquals(s,a);
    }

    @Test
    public void getSampleTest() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException {
        //Arrange + act

        SampleStore store = new SampleStore();
        String a = "Sample: testID=BL000, barcode=00000000001";
        store.createSample("BL000");
        String b = store.getSample();

        Assert.assertEquals(a,store.getSample());

    }

    @Test
    public void getSampleTestID() throws ClassNotFoundException, InstantiationException, BarcodeException, IllegalAccessException, IOException, OutputException {
        //Arrange + act
        SampleStore store = new SampleStore();
        store.createSample("BL000");
        Assert.assertTrue(store.saveSample());
        Sample a = store.getSample("00000000001");
        Sample b = new Sample("BL000","00000000001");
        //Assert
        Assert.assertEquals(a.toString(),b.toString());

    }






}
