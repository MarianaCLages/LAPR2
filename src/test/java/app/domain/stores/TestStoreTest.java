package app.domain.stores;

import app.domain.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
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

        Assert.assertEquals(t.toString(), "Test: testCode=000000000000001, testNhsNumber=123456789187, clientCc=1234567890123456, TestTypeID=BL000, state=CREATED");

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
        app.domain.model.Test t1 = store.createTest("123456789187", "1234567890123456", testType, cat1, pa);
        app.domain.model.Test t2 = store.createTest("123456789185", "1234567890123456", testType, cat1, pa);
        app.domain.model.Test t3 = store.createTest("123456782187", "1234567890123455", testType, cat1, pa);

        store.addTest(t3);
        store.addTest(t2);
        store.addTest(t1);

        List<app.domain.model.Test> list = store.sortDate("1234567890123456");



        List<app.domain.model.Test> expected = new ArrayList<>();
        expected.add(t2);
        expected.add(t1);
        Assert.assertEquals(list,expected);
    }

    @Test
    public void getValidatedTestList() throws ParseException {

        List<app.domain.model.Test> validatedTest = new ArrayList<>();

        ParameterCategory[] categories = new ParameterCategory[1];
        String dateStr = "19-02-2021";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(dateStr);

        Client client = new Client("12345698905", "1234567891183456", "1234766891", "1234567881", date, 'M', "manuel@alberto.com", "Manuel");
        ParameterCategory cat = new ParameterCategory("codee", "name");
        categories[0] = cat;

        ParameterCategoryStore pcStore = new ParameterCategoryStore();
        ParameterCategory pc1 = pcStore.createParameterCategory("12345", "Hemogram");
        pcStore.saveParameterCategory();
        List<ParameterCategory> testCategories = new ArrayList<>();
        testCategories.add(pc1);
        TestType covidTest = new TestType("COV19", "Covid", "Swab", pcStore);

        ParameterStore pStore = new ParameterStore();
        Parameter p4 = new Parameter("IgGAN", "COVID", "000", pc1);
        pStore.add(p4);
        List<Parameter> testParameters1 = new ArrayList<>();
        testParameters1.add(p4);

        TestStore testStore = new TestStore();
        app.domain.model.Test test = new app.domain.model.Test("1234557890123456", "100000000100", "1234567890", covidTest, testCategories, testParameters1);
        testStore.saveTest();

        validatedTest.add(test);

        testStore.getValidatedTestList(client);
    }
}