package app.domain;

import app.domain.model.*;
import app.domain.stores.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestStoreTest {


    @Test
    public void createValidTest() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);
        TestStore store = new TestStore();

        Assert.assertTrue(store.createTest("123456789187", "1234567890123456", testType, cat1, pa));

    }

    @Test
    public void addValidTest() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);
        TestStore store = new TestStore();

        store.createTest("123456789187", "1234567890123456", testType, cat1, pa);

        Assert.assertTrue(store.saveTest());

    }

    @Test
    public void addInvalidTest() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);
        TestStore store = new TestStore();

        store.createTest("123456789187", "1234567890123456", testType, cat1, pa);
        store.saveTest();

        store.createTest("123456789187", "1234567890123456", testType, cat1, pa);

        Assert.assertFalse(store.saveTest());

    }

}