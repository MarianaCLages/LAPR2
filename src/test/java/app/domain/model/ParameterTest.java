package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterTest {
    ParameterCategory cat = new ParameterCategory("12345", "Hemogram");


    @Test
    public void CreateValidCodeParameterTestEquals5char() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234", "WBC", "description", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateCodeParameterOver5Test() {
        //Arrange + Act
        Parameter parameter = new Parameter("A12345","WBC", "description", cat );
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateCodeParameterNothingTest() {
        //Arrange + Act
        Parameter parameter = new Parameter("","WBC", "description", cat );
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateCodeParameterUnder5Test() {
        //Arrange + Act
        Parameter parameter = new Parameter("A123","WBC", "description", cat );
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNameParameterTestOver8char() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234", "White Blood Cell", "description", cat);
    }
    @Test
    public void CreateInvalidNameParameterTestEquals8char() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234", "Platelet", "description", cat);
    }

    @Test
    public void CreateValidNameParameterTestUnder8char() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234", "WBC", "description", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNameParameterTestWithNum() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234", "WBC1", "description", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateNameParameterNothingTest() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234","", "description", cat );
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidDescriptionParameterTestOver20char() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234", "WBC", "description:aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", cat);
    }

    @Test
    public void CreateValidDescriptionParameterTestUnder20char() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234", "WBC", "description", cat);
    }
    @Test
    public void CreateValidDescriptionParameterTest20char() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234", "WBC", "aaaaaaaaaaaaaaaaaaaa", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateDescriptionParameterNothingTest() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234","WBC", "", cat );
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalideNameCodeTest() {
        //Arrange + Act
        Parameter parameter = new Parameter("A123456789", "White Blood Cell", "description", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalideNameDescriptionTest() {
        //Arrange + Act
        Parameter parameter = new Parameter("A12345", "WBC", "description:aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalideCodeDescriptionTest() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234", "White Blood Cell", "description:aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", cat);
    }

    @Test
    public void toStringTest(){
        Parameter parameter = new Parameter("A1234","WBC","description",cat);
        String expected = "Parameter: code=A1234, name=WBC, description=description, category="+ cat.toString();
        String actual = parameter.toString();
        Assert.assertEquals(expected,actual);
    }
}
