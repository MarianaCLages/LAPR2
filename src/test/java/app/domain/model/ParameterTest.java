package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterTest {
    ParameterCategory cat = new ParameterCategory("12345678", "SUP");

    @Test(expected = IllegalArgumentException.class)
    public void CreateCodeParameterOver5Test() {
        //Arrange + Act
        Parameter parameter = new Parameter("A12345","SUP", "another one", cat );
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateCodeParameterUnder5Test() {
        //Arrange + Act
        Parameter parameter = new Parameter("A123","SUP", "another one", cat );
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNameParameterTestOver8char() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234", "ulittlebitch", "another one", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidDescriptionParameterTestOver20char() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234", "SUP", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalideNameCodeTest() {
        //Arrange + Act
        Parameter parameter = new Parameter("A123456789", "ulittlebitch", "another one", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalideNameDescriptionTest() {
        //Arrange + Act
        Parameter parameter = new Parameter("A12345", "SUP", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalideCodeDescriptionTest() {
        //Arrange + Act
        Parameter parameter = new Parameter("A1234", "ulittlebitch", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", cat);
    }

}