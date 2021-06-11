package app.domain.model;

import app.domain.shared.Constants;
import app.domain.stores.ParameterCategoryStore;
import auth.AuthFacade;
import org.junit.Assert;
import org.junit.Test;

public class CompanyTest {
    Company company = new Company(Constants.PARAMS_COMPANY_DESIGNATION,"6","0","0");


    @Test(expected = IllegalArgumentException.class)
    public void GetDesignationBlank() {
        Company company = new Company("","6","0","0");
    }

    @Test
    public void getAuthFacadeTest() {
        AuthFacade auth = company.getAuthFacade();
        Assert.assertNotNull(auth);
    }


    @Test
    public void getDesignationTest() {
        String actual = company.getDesignation();
        String expected = Constants.PARAMS_COMPANY_DESIGNATION;

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getParameterCategoryList() {
        Assert.assertNotNull(company.getParameterCategoryList());
    }


    @Test
    public void getTestTypeList() {
        Assert.assertNotNull(company.getTestTypeList());
    }

    @Test
    public void getClientList() {
        Assert.assertNotNull(company.getClientList());
    }

    @Test
    public void getEmployeeStore(){
        Assert.assertNotNull(company.getEmployeeList());
    }

    @Test
    public void getParameterStore(){
        Assert.assertNotNull(company.getParameterList());
    }

    @Test
    public void getRoleList(){
        Assert.assertNotNull(company.getRoleList());
    }

    @Test
    public void getClinicalAnalysisLabList(){
        Assert.assertNotNull(company.getClinicalAnalysisLabList());
    }

    @Test
    public void getTestList(){
        Assert.assertNotNull(company.getTestList());
    }

    @Test
    public void getSampleStore(){
        Assert.assertNotNull(company.getSampleStore());
    }

}