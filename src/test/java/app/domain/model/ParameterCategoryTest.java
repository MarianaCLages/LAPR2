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
    public void CreateNullCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory(null, null);

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
    public void CreateCodeWith4Characters() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AFO4", "Hemograma");
    }

    @Test
    public void CreateCodeWith5Characters() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AFO4I", "Hemograma");
    }

    @Test
    public void CreateCodeWith8Characters() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AFO4I8R4", "Hemograma");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateCodeWith9Characters() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AFO4I8R46", "Hemograma");
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

    @Test(expected = IllegalArgumentException.class)
    public void setInvalidCodeTooSmall() {
        ParameterCategory cat = new ParameterCategory("AF856", "Hemograma");
        cat.setCode("F");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setInvalidCodeTooBig() {
        ParameterCategory cat = new ParameterCategory("AF856", "Hemograma");
        cat.setCode("AFFFS858afs");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setInvalidCodeBlank() {
        ParameterCategory cat = new ParameterCategory("AF856", "Hemograma");
        cat.setCode("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setInvalidNameBlank() {
        ParameterCategory cat = new ParameterCategory("AF856", "Hemograma");
        cat.setName("");
    }

    @Test
    public void getCode() {
        ParameterCategory cat = new ParameterCategory("AF856", "Hemograma");
        String expected = "AF856";
        String actual = cat.getCode();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getName() {
        ParameterCategory cat = new ParameterCategory("AF856", "Hemograma");
        String expected = "Hemograma";
        String actual = cat.getName();

        Assert.assertEquals(expected,actual);
    }
}