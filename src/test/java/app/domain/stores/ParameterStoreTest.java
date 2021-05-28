package app.domain.stores;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ParameterStoreTest {
    ParameterCategory cat = new ParameterCategory("AE554", "Hemogram");
    ParameterCategory cat1 = new ParameterCategory("AR554", "Hemogram");


    @Test
    public void validateValidParameter() {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc1 = new Parameter("A1234", "WBC", "description", cat);
        Parameter pc2 = new Parameter("A1234", "WBC", "description", cat);
        //Act
        store.add(pc1);
        //Assert
        Assert.assertFalse(store.validateParameter(pc1));
    }

    @Test
    public void validateParameterAlreadyExists() {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc1 = new Parameter("A1234", "WBC", "description", cat);
        //Act
        store.add(pc1);
        //Assert
        Assert.assertFalse(store.validateParameter(pc1));
    }

    @Test
    public void validateParameterIsNull() {
        //Arrange + act
        ParameterStore store = new ParameterStore();
        Parameter pc1 = null;
        //Assert
        Assert.assertFalse(store.validateParameter(pc1));
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
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIDOutOfLimits() {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc1 = new Parameter("A1234", "PLT", "description", cat);
        Parameter pc2 = new Parameter("A1235", "PLT", "description", cat);
        store.add(pc1);
        store.add(pc2);
        //act
        Parameter expected = store.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIDNegative() {
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
    public void createValidParameter() {
        ParameterStore store = new ParameterStore();

        Assert.assertNotNull(store.createParameter("A1234", "PLT", "description", cat));

    }

    @Test
    public void getPcValid() {
        ParameterStore store = new ParameterStore();
        Parameter pc1 = store.createParameter("A1234", "PLT", "description", cat);
        Parameter expected = pc1;
        Parameter actual = store.getPc();

        Assert.assertEquals(expected, actual);

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
        Parameter pc = store.createParameter("A1234", "PLT", "description", cat);

        //Assert
        Assert.assertTrue(store.saveParameter());

    }

    @Test
    public void saveParameterInvalid() {
        //Arrange
        ParameterStore store = new ParameterStore();
        Parameter pc = store.createParameter("A1234", "PLT", "description", cat);
        store.add(pc);
        Parameter pc1 = store.createParameter("A1232", "PLS", "description", cat);
        store.add(pc1);
        //Assert
        Assert.assertFalse(store.saveParameter());

    }

    @Test
    public void addValid() {
        ParameterStore store = new ParameterStore();
        Parameter pc = store.createParameter("A1234", "PLT", "description", cat);
        Assert.assertTrue(store.add(pc));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInvalid() {
        ParameterStore store = new ParameterStore();
        Parameter pc = store.createParameter(null, null, null, null);
    }

    @Test
    public void toStringTest() {
        ParameterStore store = new ParameterStore();
        Parameter pc1 = store.createParameter("A1234", "PLT", "description", cat);
        Parameter pc2 = store.createParameter("A1284", "PLT", "description", cat);

        store.add(pc1);
        store.add(pc2);

        String actual = store.toString();

        String expected = "[" + pc1.toString() + ", " + pc2.toString() + "]";

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getParameterList() {
        ParameterStore store = new ParameterStore();
        Parameter pc1 = store.createParameter("A1234", "PLT", "description", cat);
        Parameter pc2 = store.createParameter("A1284", "PLT", "description", cat);

        store.add(pc1);
        store.add(pc2);

        List<Parameter> parameterList = store.getParameterList("AE554");

        String actual = parameterList.toString();
        String expected = store.toString();
        Assert.assertEquals(actual, expected);

    }
    @Test
    public void getParameterListNull() {
        ParameterStore store = new ParameterStore();
        Parameter pc1 = store.createParameter("A1234", "PLT", "description", cat);
        Parameter pc2 = store.createParameter("A1284", "PLT", "description", cat);
        Parameter pc3 = store.createParameter("A1284", "PLT", "description", cat1);


        store.add(pc1);
        store.add(pc2);

        List<Parameter> parameterList = store.getParameterList("AE554");

        String actual = parameterList.toString();
        String expected = store.toString();
        Assert.assertEquals(actual, expected);

    }



    @Test
    public void getParameterTest(){
        ParameterStore store = new ParameterStore();
        Parameter pc1 = store.createParameter("A1234", "PLT", "description", cat);
        Parameter pc2 = store.createParameter("A1284", "PLT", "description", cat);

        store.add(pc1);
        store.add(pc2);

        Parameter p = store.getParameter("A1284");

        Assert.assertEquals(p,pc2);
    }

    @Test
    public void getParameterTestNull(){
        ParameterStore store = new ParameterStore();
        Parameter pc1 = store.createParameter("A1234", "PLT", "description", cat);
        Parameter pc2 = store.createParameter("A1284", "PLT", "description", cat);

        store.add(pc1);
        store.add(pc2);

        Parameter p = store.getParameter("A1288");

        Assert.assertNull(p);
    }

    @Test
    public void isNullTest(){
        ParameterStore store = new ParameterStore();
        Assert.assertTrue(store.isEmpty());
    }

    @Test
    public void getList(){
        ParameterStore store = new ParameterStore();
        Parameter pc1 = store.createParameter("A1234", "PLT", "description", cat);
        Parameter pc2 = store.createParameter("A1284", "PLT", "description", cat);

        store.add(pc1);
        store.add(pc2);

        Assert.assertNotNull(store.getList());
    }
    
    
}