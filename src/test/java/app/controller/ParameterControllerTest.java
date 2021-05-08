package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.ParameterCategoryStore;
import app.domain.model.ParameterStore;
import org.junit.Assert;
import org.junit.Test;

public class ParameterControllerTest {

    Company company = App.getInstance().getCompany();
    ParameterCategory cat = new ParameterCategory("AE554", "Hemogram");



    @Test
    public void CreateControllerNoAttribute() {
        ParameterController controller = new ParameterController();
    }

    @Test
    public void CreateControllerWithAttribute() {
        ParameterController controller = new ParameterController();
    }

    @Test
    public void CreateParameter(){
        ParameterController controller = new ParameterController(company);

        ParameterStore store = company.getParameterList();
        store.CreateParameter("AF165","ysgdf","description",cat);
    }

    @Test
    public void GetParameter(){
        ParameterController controller = new ParameterController(company);

        controller.createParameter("A1234","WBC","description",cat);

        String expected = "Parameter: code=A1234, name=WBC, description=description, category="+ cat.toString();
        String actual= controller.getpc();

        Assert.assertEquals(actual,expected);
    }


    @Test
    public void saveParameterCategory(){
        ParameterController controller = new ParameterController(company);

        controller.createParameter("A1234","WBC","description",cat);

        Assert.assertTrue(controller.saveParameter());
    }


    @Test
    public void getParameterCategoryList() {

    }

}