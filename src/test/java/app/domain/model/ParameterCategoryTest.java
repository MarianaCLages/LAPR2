package app.domain.model;

import app.domain.model.ParameterCategory;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterCategoryTest {

    @Test
    public void CreateValidCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AF856", "Hemograma");
        //Assert
        Assert.assertNotNull(cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateCodeTooSmallCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AF5", "Hemograma");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateCodeTooBigCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AFFFS858f", "Hemograma");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNameCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AF5", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNameAndCodeCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("", "");
    }

}