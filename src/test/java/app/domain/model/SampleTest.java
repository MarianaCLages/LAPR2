package app.domain.model;

import app.domain.stores.ParameterCategoryStore;
import org.junit.Assert;
import org.junit.Test;

public class SampleTest {

    @Test
    public void Sample() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterCategoryList cat1 = new ParameterCategoryList();
        cat1.add(pc1);
        ParameterList pa = new ParameterList();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test t = new app.domain.model.Test("123456789012","123456789012","1234567890123456",testType,cat1,pa);
        Sample s = new Sample(t.getTestCode(), "12345678901");
    }

    @Test(expected = IllegalArgumentException.class)
    public void SampleNull() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterCategoryList cat1 = new ParameterCategoryList();
        cat1.add(pc1);
        ParameterList pa = new ParameterList();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test t = new app.domain.model.Test(null,"123456789012","1234567890123456",testType,cat1,pa);
        Sample s = new Sample(t.getTestCode(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestCodeNull() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterCategoryList cat1 = new ParameterCategoryList();
        cat1.add(pc1);
        ParameterList pa = new ParameterList();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test t = new app.domain.model.Test(null,"123456789012","1234567890123456",testType,cat1,pa);
        Sample s = new Sample(t.getTestCode(), "12345678901");
    }

    @Test(expected = IllegalArgumentException.class)
    public void BarcodeNull() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterCategoryList cat1 = new ParameterCategoryList();
        cat1.add(pc1);
        ParameterList pa = new ParameterList();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test t = new app.domain.model.Test("123456789012","123456789012","1234567890123456",testType,cat1,pa);
        Sample s = new Sample(t.getTestCode(), null);
    }
}
