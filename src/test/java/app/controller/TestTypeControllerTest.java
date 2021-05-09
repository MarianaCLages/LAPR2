package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.ParameterCategoryStore;
import org.junit.Assert;
import org.junit.Test;

public class TestTypeControllerTest {

    Company company = App.getInstance().getCompany();


    @Test
    public void CreateControllerNoAttribute() {
        TestTypeController controller = new TestTypeController();
    }

    @Test
    public void CreateControllerWithAttribute() {
        TestTypeController controller = new TestTypeController(company);
    }

    @Test
    public void CreateTestType() {
        TestTypeController controller = new TestTypeController(company);
        ParameterCategory cat1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory cat2 = new ParameterCategory("AE555", "Hemogram");
        ParameterCategoryStore store = new ParameterCategoryStore();
        store.add(cat1);
        store.add(cat2);
        controller.createTestType("283h3", "descrição", "metodo 1", store);

    }

    @Test
    public void SaveTestType() {
        TestTypeController controller = new TestTypeController(company);
        ParameterCategory cat1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory cat2 = new ParameterCategory("AE555", "Hemogram");
        ParameterCategoryStore store = new ParameterCategoryStore();
        store.add(cat1);
        store.add(cat2);
        controller.createTestType("283h3", "descrição", "metodo 1", store);

        Assert.assertTrue(controller.saveTestType());

    }

    @Test
    public void getParameterList() {
        TestTypeController controller = new TestTypeController(company);
        ParameterCategory cat1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory cat2 = new ParameterCategory("AE555", "Hemogram");
        ParameterCategoryStore store = new ParameterCategoryStore();
        store.add(cat1);
        store.add(cat2);
        controller.createTestType("283h3", "descrição", "metodo 1", store);

       controller.getTestT();

    }

    @Test
    public void ParameterCategoryStore() {
        TestTypeController controller = new TestTypeController(company);
        ParameterCategory cat1 = new ParameterCategory("AE554", "Hemogram");
        ParameterCategory cat2 = new ParameterCategory("AE555", "Hemogram");
        ParameterCategoryStore store = new ParameterCategoryStore();
        store.add(cat1);
        store.add(cat2);
        controller.createTestType("283h3", "descrição", "metodo 1", store);
        controller.getParameterCategoryList();
    }

}