package app.domain.stores;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.stores.ParameterCategoryStore;
import app.domain.stores.TestTypeStore;
import org.junit.Assert;
import org.junit.Test;

public class TestTypeStoreTest {
    private ParameterCategoryStore cat = new ParameterCategoryStore();
    private TestTypeStore store = new TestTypeStore();

    @Test
    public void validateTestTypeAlreadyExists() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        //Arrange
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        //Act
        store.add(t);
        //Assert
        Assert.assertFalse(store.ValidateTestType(t));
    }


    @Test
    public void validateTestTypeIsNull() {
        //Arrange + act
        TestType t = null;
        //Assert
        Assert.assertFalse(store.ValidateTestType(t));
    }

    @Test
    public void isEmptyTest(){ Assert.assertTrue(store.isEmpty());}



    @Test
    public void alreadyUsedID(){
        //Arrange + act
        ParameterCategory pc1 = new ParameterCategory("AF687","Hemogram");
        cat.add(pc1);
        //Arrange
        TestType t1 = new TestType("COV19", "descrição", "metodo 1", cat);
        TestType t2 = new TestType("COV19", "descrição hc", "metodo 7", cat);
        //Act
        store.add(t1);
        store.add(t2);
        //Assert
        Assert.assertFalse(store.uniqueID(t2));
    }

    @Test
    public void getByIDTest() {
        ParameterCategory pc1 = new ParameterCategory("AF687","Hemogram");
        cat.add(pc1);

        TestType t1 = new TestType("COV19", "descrição", "metodo 1", cat);
        TestType t2 = new TestType("COV19", "descrição", "metodo 3", cat);

        store.add(t1);
        store.add(t2);

        TestType expected = t1;
        TestType actual = store.getByID("COV19");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getByIDTestNull() {
        ParameterCategory pc1 = new ParameterCategory("AF687","Hemogram");
        cat.add(pc1);

        TestType t1 = new TestType("COV19", "descrição", "metodo 1", cat);
        TestType t2 = new TestType("COV19", "descrição", "metodo 3", cat);

        store.add(t1);
        store.add(t2);

        TestType expected = null;
        TestType actual = store.getByID("COt19");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void saveTestTypeInvalid() {
        //Arrange
        ParameterCategory pc1 = new ParameterCategory("AF687","Hemogram");
        cat.add(pc1);
        TestType t1 = new TestType("COV19", "descrição", "metodo 1", cat);
        store.add(t1);
        TestType t2 = new TestType("COV19", "descrição", "metodo 1", cat);
        store.add(t2);
        //Assert
        Assert.assertFalse(store.saveTestType());
    }

    @Test
    public void createValidTestType() {
        TestTypeStore store = new TestTypeStore();
        ParameterCategory pc1 = new ParameterCategory("AF687","Hemogram");
        cat.add(pc1);
        Assert.assertNotNull(store.CreateTestType("COV19","descrição", "metodo 1", cat));

    }





}