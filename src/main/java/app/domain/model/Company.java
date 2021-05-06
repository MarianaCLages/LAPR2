package app.domain.model;

import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import javax.management.relation.Role;
import javax.management.relation.RoleList;
import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;

    private ParameterCategoryStore parameterCategoryList;
    private ParameterStore parameterList;
    private TestTypeStore testTypeList;
    private ClinicalAnalysisLabStore clinicalAnalysisLabList;
    private RoleStore roleList;
    private EmployeeStore employeeList;
    private ClientStore clientList;

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    /**
     * creates a new empty list of Parameter Categories objects
     * @return empty list of Parameter Categories objects
     */

    public ParameterCategoryStore getParameterCategoryList() {
        return this.parameterCategoryList = new ParameterCategoryStore();
    }

    public EmployeeStore getEmployeeList(){
        return this.employeeList = new EmployeeStore();
    }

    public ParameterStore getParameterList() {
        return this.parameterList = new ParameterStore();
    }

    public TestTypeStore getTestTypeList() {return this.testTypeList= new TestTypeStore();}

    public RoleStore getRoleList() {
        return this.roleList = new RoleStore();
    }

    public ClinicalAnalysisLabStore getClinicalAnalysisLabList() {return this.clinicalAnalysisLabList = new ClinicalAnalysisLabStore();}

    public ClientStore getClientList(){
        return this.clientList = new ClientStore();
    }
}

