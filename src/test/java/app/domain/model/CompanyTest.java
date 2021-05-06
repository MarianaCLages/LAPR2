package app.domain.model;

import app.domain.shared.Constants;
import auth.AuthFacade;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyTest {

    @Test(expected = IllegalArgumentException.class)
    public void GetDesignationBlank(){
        Company company = new Company("");
    }

    @Test
    public void getAuthFacadeTest(){
        Company company = new Company(Constants.PARAMS_COMPANY_DESIGNATION);
        AuthFacade auth = company.getAuthFacade();

    }
}