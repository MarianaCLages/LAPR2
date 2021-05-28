package app.domain.model;

import app.domain.stores.ParameterCategoryStore;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTest {

    @Test
    public void createValidTest(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("1234s","123456789012","1234567890123456",testType,cat1,pa);
        Assert.assertNotNull(test);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTestParameterEmpty(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        TestType testType = new TestType("BL000","description","sei lá",cat);


        app.domain.model.Test test = new app.domain.model.Test("1234s","123456789012","1234567890123456",testType,cat1,pa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTestCategoryEmpty(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("1234s","123456789012","1234567890123456",testType,cat1,pa);
    }


    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTestNhsNumberNull(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001",null,"1234567890123456",testType,cat1,pa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTestNhsNumberNotAllowedCharacters(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","12345678901?","1234567890123456",testType,cat1,pa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTestNhsNumberSize(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","1234567890187","1234567890123456",testType,cat1,pa);
    }

    @Test
    public void addTestParameterTest(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","123456789187","1234567890123456",testType,cat1,pa);

        test.addTestParameter();
    }

    @Test
    public void getTestNhsNumberTest(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","123456789187","1234567890123456",testType,cat1,pa);

        String expected = "123456789187";
        String actual = test.getTestNhsNumber();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void toStringTest(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","123456789187","1234567890123456",testType,cat1,pa);

        String expected = "Test: testCode=000000000000001, testNhsNumber=123456789187, clientCc=1234567890123456, testType="+testType.toString()+", catList="+cat1.toString()+", paList="+pa.toString();
        String actual = test.toString();

        Assert.assertEquals(expected,actual);

    }


    @Test
    public void addTestResultTestValid() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","123456789187","1234567890123456",testType,cat1,pa);
        test.addTestParameter();
        test.changeState("SAMPLE_COLLECTED");

        Assert.assertTrue(test.addTestResult("AH000",0.254));
    }

    @Test
    public void addTestResultNotAnalysedSample() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","123456789187","1234567890123456",testType,cat1,pa);
        test.addTestParameter();

        Assert.assertFalse(test.addTestResult("AH000",0.254));
    }

    @Test
    public void addTestResultNullTestParameters() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("000000000000001","123456789187","1234567890123456",testType,cat1,pa);

        test.addTestParameter();
        test.changeState("SAMPLE_COLLECTED");
        boolean value = test.addTestResult("AH001",0.254);
        Assert.assertFalse(value);
    }

    @Test
    public void createReportTest(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("1234s","123456789012","1234567890123456",testType,cat1,pa);
        boolean rep = test.createReport("a");
        Assert.assertTrue(rep);
    }

    @Test
    public void saveReportTest(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("1234s","123456789012","1234567890123456",testType,cat1,pa);

        test.saveReport();
        String actual = test.getState();
        String expected = "DIAGNOSTIC_MADE";
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void getIDTest(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("1234s","123456789012","1234567890123456",testType,cat1,pa);

        String actual = test.getID();
        String expected = "1234s";
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void getStateTest(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test test = new app.domain.model.Test("1234s","123456789012","1234567890123456",testType,cat1,pa);

        test.saveReport();

        Assert.assertEquals(test.getState(),"DIAGNOSTIC_MADE");
    }

}