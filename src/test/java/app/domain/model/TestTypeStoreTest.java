package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class TestTypeStoreTest {

    @Test
    public void validateValidTestType() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        //Arrange
        TestTypeStore store = new TestTypeStore();
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Act
        store.add(t);
        //Assert
        Assert.assertTrue(store.ValidateTestType(t));
    }
    @Test
    public void validateTestTypeIsNull() {
        //Arrange + act
        TestTypeStore store = new TestTypeStore();
        TestType t = null;
        //Assert
        Assert.assertFalse(store.ValidateTestType(t));
    }

    @Test
    public void validateTestTypeAlreadyExistsID(){
        //Arrange + act
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AF687","Hemogram");
        cat.add(pc1);
        //Arrange
        TestTypeStore store = new TestTypeStore();
        TestType t1 = new TestType("283h3", "descrição", "metodo 1", cat);
        TestType t2 = new TestType("283h3", "descrição", "metodo 1", cat);
        //Act
        store.add(t1);
        store.add(t2);
        //Assert
        Assert.assertTrue(store.ValidateTestType(t2));
    }









}