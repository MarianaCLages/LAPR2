package app.domain.model;

import app.domain.stores.ParameterCategoryStore;
import app.domain.stores.ParameterStore;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestTest {

    @Test
    public void createValidTest(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("1234s","123456789012","1234567890123456",testType,cat,pa);
        Assert.assertNotNull(test);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTestParameterEmpty(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        TestType testType = new TestType("AF123","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("1234s","123456789012","1234567890123456",testType,cat,pa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTestCategoryEmpty(){
        ParameterCategoryStore cat1 = new ParameterCategoryStore();
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("AF123","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("1234s","123456789012","1234567890123456",testType,cat1,pa);
    }


    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTestNhsNumberNull(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("AF123","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001",null,"1234567890123456",testType,cat,pa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTestNhsNumberNotAllowedCharacters(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("AF123","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","12345678901?","1234567890123456",testType,cat,pa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTestNhsNumberSize(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("AF123","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","1234567890187","1234567890123456",testType,cat,pa);
    }

    @Test
    public void addTestParameterTest(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","123456789187","1234567890123456",testType,cat,pa);

        test.addTestParameter();
    }

    @Test
    public void getTestNhsNumberTest(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","123456789187","1234567890123456",testType,cat,pa);

        String expected = "123456789187";
        String actual = test.getTestNhsNumber();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void toStringTest(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        ParameterStore pa = new ParameterStore();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","123456789187","1234567890123456",testType,cat,pa);

        String expected = "Test: testCode=000000000000001, testNhsNumber=123456789187, clientCc=1234567890123456, testType="+testType.toString()+", catList="+cat.toString()+", paList="+pa.toString();
        String actual = test.toString();

        Assert.assertEquals(expected,actual);

    }


}