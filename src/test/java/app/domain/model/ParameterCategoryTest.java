package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

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
    public void CreateNameTooBigCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AFO4I", "Hemogramatuaaaaaa");
    }

    @Test
    public void CreateName10CharCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AFO4I", "Hemogramat");
    }


    @Test
    public void CreateNameLessThan10CharCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AFO4I", "Hemogra");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateCodeTooSmallCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AFO4", "Hemograma");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateCodeTooBigCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AFO4IP", "Hemograma");
    }

    @Test
    public void CreateCodeWith5Characters() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AFO4I", "Hemograma");
    }


    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNameCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("AFO4I", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNameAndCodeCategoryTest() {
        //Arrange + Act
        ParameterCategory cat = new ParameterCategory("", "");
    }


    @Test
    public void getCodeTest() {
        ParameterCategory cat = new ParameterCategory("AF856", "Hemograma");
        String expected = "AF856";
        String actual = cat.getCode();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNameTest() {
        ParameterCategory cat = new ParameterCategory("AF856", "Hemograma");
        String expected = "Hemograma";
        String actual = cat.getName();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void toStringTest() {
        ParameterCategory cat = new ParameterCategory("AF856", "Hemograma");
        String expected = "Code = AF856 Name=Hemograma";
        String actual = cat.toString();

        Assert.assertEquals(expected, actual);

    }


}