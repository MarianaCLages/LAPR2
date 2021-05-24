package app.domain.stores;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.stores.*;
import org.junit.Assert;
import org.junit.Test;

public class TestStoreTest {


    @Test
    public void createValidTest() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("AF123", "description", "sei lá", cat);
        TestStore store = new TestStore();

        Assert.assertTrue(store.createTest("123456789187", "1234567890123456", testType, cat, pa));

    }

    @Test
    public void addValidTest() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("AF123", "description", "sei lá", cat);
        TestStore store = new TestStore();

        store.createTest("123456789187", "1234567890123456", testType, cat, pa);

        Assert.assertTrue(store.saveTest());

    }

    @Test
    public void addInvalidTest() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("AF123", "description", "sei lá", cat);
        TestStore store = new TestStore();

        store.createTest("123456789187", "1234567890123456", testType, cat, pa);
        store.saveTest();

        store.createTest("123456789187", "1234567890123456", testType, cat, pa);

        Assert.assertFalse(store.saveTest());

    }

    @Test
    public void getTestTest() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("AF123", "description", "sei lá", cat);
        TestStore store = new TestStore();

        store.createTest("123456789187", "1234567890123456", testType, cat, pa);
        store.saveTest();

        String expected = "Test: testCode=000000000000001, testNhsNumber=123456789187, clientCc=1234567890123456, testType="+testType.toString()+", catList="+cat.toString()+", paList="+pa.toString();
        String actual = store.getTest();

        Assert.assertEquals(expected,actual);
    }

}