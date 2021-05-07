package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.ParameterCategoryStore;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterCategoryControllerTest {
    Company company =App.getInstance().getCompany();


    @Test
    public void CreateControllerNoAttribute(){
        ParameterCategoryController controller = new ParameterCategoryController();
    }

    @Test
    public void CreateController(){
        ParameterCategoryController controller = new ParameterCategoryController(company);
    }


    @Test
    public void CreateParameterCategory(){
        ParameterCategoryController controller = new ParameterCategoryController(company);

        ParameterCategoryStore store = company.getParameterCategoryList();
        store.CreateParameterCategory("AF164","Hemogram");
    }

    @Test
    public void GetParameterCategory(){
        ParameterCategoryController controller = new ParameterCategoryController(company);

        controller.createParameterCategory("AF164","Hemogram");

        String expected = "Code = AF164 Name=Hemogram";
        String actual= controller.getpc();

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void saveParameterCategory(){
        ParameterCategoryController controller = new ParameterCategoryController(company);

        controller.createParameterCategory("AF164","Hemogram");

        Assert.assertTrue(controller.saveParameterCategory());
    }



}