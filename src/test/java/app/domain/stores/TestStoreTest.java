package app.domain.stores;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
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
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);
        TestStore store = new TestStore();
        app.domain.model.Test t = store.createTest("123456789187", "1234567890123456", testType, cat1, pa);

        Assert.assertEquals(t.toString(),"Test: testCode=000000000000001, testNhsNumber=123456789187, clientCc=1234567890123456, TestTypeID=BL000");

    }

    @Test
    public void addValidTest() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);
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
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);
        TestStore store = new TestStore();

        store.createTest("123456789187", "1234567890123456", testType, cat1, pa);
        store.saveTest();

        store.createTest("123456789187", "1234567890123456", testType, cat1, pa);

        Assert.assertFalse(store.saveTest());

    }

    @Test
    public void addAlreadyExistentTest() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);
        TestStore store = new TestStore();

        store.createTest("123456789187", "1234567890123456", testType, cat1, pa);
        store.saveTest();
        store.createTest("123456789187", "1234567898123456", testType, cat1, pa);

        Assert.assertFalse(store.saveTest());
    }

    @Test
    public void getTestByCode() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);
        TestStore store = new TestStore();

        store.createTest("123456789187", "1234567890123456", testType, cat1, pa);
        store.saveTest();
        String expected = store.getTest();

        app.domain.model.Test actualTest = store.getTestByCode("123456789187");
        String actual = actualTest.toString();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getTestByCodeInvalid() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);
        TestStore store = new TestStore();

        store.createTest("123456789187", "1234567890123456", testType, cat1, pa);
        store.saveTest();

        app.domain.model.Test actualTest = store.getTestByCode("123456779187");

        Assert.assertNull(actualTest);
    }

    @Test
    public void sortDateTest() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);
        TestStore store = new TestStore();
        app.domain.model.Test t1 = store.createTest( "123456789187", "1234567890123456", testType, cat1, pa);
        app.domain.model.Test t2 = store.createTest( "123456789185", "1234567890123456", testType, cat1, pa);
        app.domain.model.Test t3 = store.createTest( "123456782187", "1234567890123455", testType, cat1, pa);

        store.addTest(t3);
        store.addTest(t2);
        store.addTest(t1);

        List<app.domain.model.Test> list = store.sortDate("1234567890123456");

        store.toStringSortedList("1234567890123456");
    }
}