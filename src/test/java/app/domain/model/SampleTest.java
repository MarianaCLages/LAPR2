package app.domain.model;

import app.domain.stores.ParameterCategoryStore;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SampleTest {

    @Test
    public void Sample() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);

        app.domain.model.Test t = new app.domain.model.Test("123456789012", "123456789012", "1234567890123456", testType, cat1, pa);
        Sample s = new Sample(t.getTestCode(), "12345678901");
    }

    @Test(expected = IllegalArgumentException.class)
    public void SampleNull() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);

        app.domain.model.Test t = new app.domain.model.Test(null, "123456789012", "1234567890123456", testType, cat1, pa);
        Sample s = new Sample(t.getTestCode(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void SampleNullTest() {
        Sample s = new Sample(null,"12345678901");
    }
    @Test
    public void SampleNameTest() {
        Sample s = new Sample("BL000","12345678901");
    }

    @Test
    public void getBarcodeTest() {
        Sample s = new Sample("BL000","12345678901");
        String a = s.getBarcode();
        String b = "12345678901";
        Assert.assertEquals(a,b);
    }
    @Test
    public void getTestIDTest() {
        Sample s = new Sample("BL000","12345678901");
        String a = s.getTestID();
        String b = "BL000";
        Assert.assertEquals(a,b);
    }
    @Test
    public void toStringTest() {
        Sample s = new Sample("BL000","12345678901");
        String a = s.toString();
        String b = "Sample: testID=BL000, barcode=12345678901";
        Assert.assertEquals(a,b);
    }




    @Test(expected = IllegalArgumentException.class)
    public void TestCodeNull() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);

        app.domain.model.Test t = new app.domain.model.Test(null, "123456789012", "1234567890123456", testType, cat1, pa);
        Sample s = new Sample(t.getTestCode(), "12345678901");
    }

    @Test(expected = IllegalArgumentException.class)
    public void BarcodeNull() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);

        app.domain.model.Test t = new app.domain.model.Test("123456789012", "123456789012", "1234567890123456", testType, cat1, pa);
        Sample s = new Sample(t.getTestCode(), null);
    }
}
