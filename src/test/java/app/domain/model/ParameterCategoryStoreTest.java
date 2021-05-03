package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class ParameterCategoryStoreTest {

    @Test
    public void validateParameterCategoryAlreadyExists() {
        //Arrange
        ParameterCategoryStore store = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        //Act
        store.add(pc1);
        //Assert
        Assert.assertFalse(store.ValidateParameterCategory(pc1));
    }

    @Test
    public void validateParameterCategoryIsNull() {
        //Arrange + act
        ParameterCategoryStore store = new ParameterCategoryStore();
        ParameterCategory pc1 = null;
        //Assert
        Assert.assertFalse(store.ValidateParameterCategory(pc1));
    }
    @Test
    public void validateParameterCategoryAlreadyExistsCode(){
        //Arrange + act
        ParameterCategoryStore store = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AF687","Hemogram");
        store.add(pc1);
        ParameterCategory pc2 = new ParameterCategory("AF687","Hemograma");

        Assert.assertFalse(store.ValidateParameterCategory(pc2));

    }



    @Test
    public void add() {
    }

    @Test
    public void getIDRight() {
        //Arrange
        ParameterCategoryStore store = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AF687","Hemogram");
        ParameterCategory pc2 = new ParameterCategory("AF887","Hetogram");
        store.add(pc1);
        store.add(pc2);
        //act
        ParameterCategory expected = store.get(1);
        ParameterCategory actual = pc2;
        Assert.assertEquals(expected,actual);
    }

    @Test (expected = IndexOutOfBoundsException.class )
    public void getIDOutOfLimits()  {
        //Arrange
        ParameterCategoryStore store = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AF687","Hemogram");
        ParameterCategory pc2 = new ParameterCategory("AF887","Hetogram");
        store.add(pc1);
        store.add(pc2);
        //act
        ParameterCategory expected = store.get(2);
    }

    @Test (expected = IndexOutOfBoundsException.class )
    public void getIDNegative()  {
        //Arrange
        ParameterCategoryStore store = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AF687","Hemogram");
        ParameterCategory pc2 = new ParameterCategory("AF887","Hetogram");
        store.add(pc1);
        store.add(pc2);
        //act
        ParameterCategory expected = store.get(-1);
    }

    @Test
    public void getByCodeRight() {
        //Arrange
        ParameterCategoryStore store = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AF687","Hemogram");
        ParameterCategory pc2 = new ParameterCategory("AF887","Hetogram");
        store.add(pc1);
        store.add(pc2);

        //act
        ParameterCategory expected = pc1;
        ParameterCategory actual = store.getByCode("AF687");

        //Assert
        Assert.assertEquals(expected,actual);
    }


    @Test
    public void getByCodeNotExists() {
        //Arrange
        ParameterCategoryStore store = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AF687","Hemogram");
        ParameterCategory pc2 = new ParameterCategory("AF887","Hetogram");
        store.add(pc1);
        store.add(pc2);

        //act
        ParameterCategory expected = null;
        ParameterCategory actual = store.getByCode("AH687");

        //Assert
        Assert.assertEquals(expected,actual);
    }

}