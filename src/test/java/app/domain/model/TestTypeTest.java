package app.domain.model;

import org.junit.Assert;
import org.junit.Test;


public class TestTypeTest {

    private ParameterCategoryStore cat;

    @Test
    public void CreateValidTestType() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        //Arrange + Act
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Assert
        Assert.assertNotNull(t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidTestTypeID() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("wwl11ww", "descrição", "metodo 1", cat);
    }

    @Test
    public void CreateTestTypeID5Chars() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("wl11w", "descrição", "metodo 1", cat);
    }


    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidTestTypeBlankID(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("", "descrição", "metodo 1", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidTestTypeDescriptionOver15Chars() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("2ed45", "descrição descrição descrição", "metodo 1", cat);
    }

    @Test
    public void CreateTestTypeDescription15Chars() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("2ed45", "descrição descr", "metodo 1", cat);
    }

    @Test
    public void CreateInvalidTestTypeDescriptionUnder15Chars() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("2ed45", "descrição dqwr", "metodo 1", cat);
    }


    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidTestTypeBlankDescription() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("2ed45", "", "metodo 1", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidTestTypeCollectingMethodOver20Chars() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("283h3", "descrição", "metodo 1 metodo 2 metodo 3", cat);
    }

    @Test
    public void CreateTestTypeCollectingMethodUnder20Chars() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
    }

    @Test
    public void CreateTestTypeCollectingMethod20Chars() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("283h3", "descrição", "metodo 1 metodo 2 me", cat);
    }


    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidTestTypeBlankCollectingMethod() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("283h3", "descrição", "", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidTestTypeEmptyCategories() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
    }


    @Test
    public void getTestIDTest() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);

        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);

        String expected = "283h3";
        String actual = t.getTestID();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCatStoreTest() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);

        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);

        ParameterCategoryStore expected = cat;
        ParameterCategoryStore actual = t.getCatStore();

        Assert.assertEquals(expected, actual);
    }





}