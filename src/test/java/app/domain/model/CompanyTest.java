package app.domain.model;

import app.domain.shared.Constants;
import app.domain.stores.ClientStore;
import app.domain.stores.ParameterCategoryStore;
import app.domain.stores.TestTypeStore;
import auth.AuthFacade;
import org.junit.Assert;
import org.junit.Test;

public class CompanyTest {

    @Test(expected = IllegalArgumentException.class)
    public void GetDesignationBlank() {
        Company company = new Company("");
    }

    @Test
    public void getAuthFacadeTest() {
        Company company = new Company(Constants.PARAMS_COMPANY_DESIGNATION);
        AuthFacade auth = company.getAuthFacade();
    }


    @Test
    public void getDesignationTest() {
        Company company = new Company(Constants.PARAMS_COMPANY_DESIGNATION);
        String actual = company.getDesignation();
        String expected = Constants.PARAMS_COMPANY_DESIGNATION;

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getParameterCategoryList() {
        Company company = new Company(Constants.PARAMS_COMPANY_DESIGNATION);
        ParameterCategoryStore store = company.getParameterCategoryList();
    }


    @Test
    public void getTestTypeList() {
        Company company = new Company(Constants.PARAMS_COMPANY_DESIGNATION);
        TestTypeStore store = company.getTestTypeList();
    }

    @Test
    public void getClientList() {
        Company company = new Company(Constants.PARAMS_COMPANY_DESIGNATION);
        ClientStore store = company.getClientList();
    }
}