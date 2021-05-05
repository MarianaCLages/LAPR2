package app.domain.model;

import org.junit.Assert;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TestTypeTest {
    //   String testID; 5 chars
    //   String description; <21
    //   String collectingMethod; <16
    //   ParameterCategory category;

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
    public void CreateTestTypeInvalidID() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("wwl1ww", "descrição", "metodo 1", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateTestTypeBlankID(){
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("", "descrição", "metodo 1", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateTestTypeInvalidDescription() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("2ed45", "descrição descrição descrição", "metodo 1", cat);
    }
    @Test(expected = IllegalArgumentException.class)
    public void CreateTestTypeBlankDescription() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("2ed45", "", "metodo 1", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateTestTypeInvalidCollectingMethod() {

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("283h3", "descrição", "metodo 1 metodo 2 metodo 3", cat);
    }
    @Test(expected = IllegalArgumentException.class)
    public void CreateTestTypeBlankCollectingMethod() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory pc2 = new ParameterCategory("A8554", "Hemograma");
        cat.add(pc1);
        cat.add(pc2);
        TestType t = new TestType("283h3", "descrição", "", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateTestTypeEmptyCategories() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
    }


}