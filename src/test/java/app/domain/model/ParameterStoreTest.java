package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterStoreTest {
    ParameterCategory cat = new ParameterCategory("AE554", "Hemogram");

    @Test
    public void validateValidParameter() {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc1 = new Parameter("A1234", "WBC", "description", cat);
        Parameter pc2 = new Parameter("A1234", "WBC", "description", cat);
        //Act
        store.add(pc1);
        //Assert
        Assert.assertFalse(store.ValidateParameter(pc1));
    }
    @Test
    public void validateParameterAlreadyExists() {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc1 = new Parameter("A1234", "WBC", "description", cat);
         //Act
        store.add(pc1);
        //Assert
        Assert.assertFalse(store.ValidateParameter(pc1));
    }
    @Test
    public void validateParameterIsNull() {
        //Arrange + act
        ParameterStore store = new ParameterStore();
        Parameter pc1 = null;
        //Assert
        Assert.assertFalse(store.ValidateParameter(pc1));
    }
    @Test
    public void getIDRight() {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc1 = new Parameter("A1234", "WBC", "description", cat);
        Parameter pc2 = new Parameter("A1235", "PLT", "description", cat);
        store.add(pc1);
        store.add(pc2);
        //act
        Parameter expected = store.get(1);
        Parameter actual = pc2;
        Assert.assertEquals(expected,actual);
    }
    @Test (expected = IndexOutOfBoundsException.class )
    public void getIDOutOfLimits()  {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc1 = new Parameter("A1234", "PLT", "description", cat);
        Parameter pc2 = new Parameter("A1235", "PLT", "description", cat);
        store.add(pc1);
        store.add(pc2);
        //act
        Parameter expected = store.get(2);
    }
    @Test (expected = IndexOutOfBoundsException.class )
    public void getIDNegative()  {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc1 = new Parameter("A1234", "PLT", "description", cat);
        Parameter pc2 = new Parameter("A1235", "PLT", "description", cat);
        store.add(pc1);
        store.add(pc2);
        //act
        Parameter expected = store.get(-1);

    }
    @Test
    public void getByCodeRight() {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc1 = new Parameter("A1234", "PLT", "description", cat);
        Parameter pc2 = new Parameter("A1235", "PLT", "description", cat);
        store.add(pc1);
        store.add(pc2);

        //act
        Parameter expected = pc1;
        Parameter actual = store.getByCode("A1234");

        //Assert
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void getByCodeNotExists() {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc1 = new Parameter("A1234", "PLT", "description", cat);
        Parameter pc2 = new Parameter("A1235", "PLT", "description", cat);
        store.add(pc1);
        store.add(pc2);

        //act
        Parameter expected = null;
        Parameter actual = store.getByCode("A1236");

        //Assert
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void createValidParameter() {
        ParameterStore store = new ParameterStore();

        Assert.assertNotNull(store.CreateParameter("A1234", "PLT", "description", cat));

    }
    @Test
    public void getPcValid() {
        ParameterStore store = new ParameterStore();
        Parameter pc1 = store.CreateParameter("A1234", "PLT", "description", cat);
        Parameter expected = pc1;
        Parameter actual= store.getPc();

        Assert.assertEquals(expected,actual);

    }
    @Test
    public void containsTrue() {
        //Assert
        ParameterStore store = new ParameterStore();
        Parameter pc1 = new Parameter("A1234", "PLT", "description", cat);

        //Act
        store.add(pc1);

        //Assert
        Assert.assertTrue(store.contains(pc1));
    }
    @Test
    public void containsFalse() {
        //Assert
        ParameterStore store = new ParameterStore();
        Parameter pc1 = new Parameter("A1234", "PLT", "description", cat);
        Parameter pc2 = new Parameter("A1234", "PLT", "description", cat);
        //Act
        store.add(pc1);

        //Assert
        Assert.assertFalse(store.contains(pc2));

    }
    @Test
    public void saveParameterValid() {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc  = store.CreateParameter("A1234", "PLT", "description", cat);

        //Assert
        Assert.assertTrue(store.saveParameter());

    }
    @Test
    public void saveParameterCategoryInvalid() {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc  = store.CreateParameter("A1234", "PLT", "description", cat);
        store.add(pc);
        Parameter pc1  = store.CreateParameter("A1234", "PLT", "description", cat);
        store.add(pc1);
        //Assert
        Assert.assertFalse(store.saveParameter());

    }
    @Test
    public void addValid() {
        ParameterStore store = new ParameterStore();
        Parameter pc  = store.CreateParameter("A1234", "PLT", "description", cat);
        Assert.assertTrue(store.add(pc));
    }
    @Test (expected = IllegalArgumentException.class)
    public void addInvalid() {
        ParameterStore store = new ParameterStore();
        Parameter pc  = store.CreateParameter(null, null, null, null);
    }





















}