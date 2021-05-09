package app.controller;

import app.domain.model.Company;
import app.domain.model.Role;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterEmployeeControllerTest {
    Company company = App.getInstance().getCompany();


    @Test
    public void RegisterEmployeeController(){
        RegisterEmployeeController ctrl = new RegisterEmployeeController();
        Assert.assertNotNull(ctrl);
    }

    @Test
    public void RegisterEmployeeControllerWithComp(){
        RegisterEmployeeController ctrl = new RegisterEmployeeController(company);
        Assert.assertNotNull(ctrl);
    }

    @Test
    public void CreateValidEmployee(){
        RegisterEmployeeController ctrl = new RegisterEmployeeController();
        ctrl.createEmployee("Bino","RuadeAli", "91234567811","something@isep.com","111111111111111111", "",1);
    }

    @Test
    public void geEm(){
        RegisterEmployeeController ctrl = new RegisterEmployeeController();
        ctrl.createEmployee("Bino","RuadeAli", "91234567811","something@isep.com","111111111111111111", "",1);
        Assert.assertNotNull(ctrl.getEm());
    }

    @Test
    public void saveEmployee(){
        RegisterEmployeeController ctrl = new RegisterEmployeeController();
        ctrl.createEmployee("Bino","RuadeAli", "91234567811","something@isep.com","111111111111111111", "",1);
        ctrl.saveEmployee();
    }


}