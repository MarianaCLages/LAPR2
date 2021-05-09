package app.domain.model;

import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private final String designation;
    private final AuthFacade authFacade;

    private ParameterCategoryStore parameterCategoryList;
    private ParameterStore parameterList;
    private TestTypeStore testTypeList;
    private ClinicalAnalysisLabStore clinicalAnalysisLabList;
    private RoleStore roleList;
    private EmployeeStore employeeList;
    private ClientStore clientList;

    /**
     * Constructor of the Company Class, instances a new object of AuthFacade
     *
     * @param designation Designation of Company
     */
    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
    }

    /**
     * @return designation of the Company
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @return Object of AuthFacade instantiated by the Company Controller
     */
    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    /**
     * creates a new empty list of Parameter Categories objects
     *
     * @return empty list of Parameter Categories objects
     */

    public ParameterCategoryStore getParameterCategoryList() {
        this.parameterCategoryList = new ParameterCategoryStore();
        return this.parameterCategoryList;
    }

    /**
     * creates a list with all the Parameter objects in the system
     *
     * @return list of Parameter objects
     */
    public ParameterCategoryStore parameterCategoryList() {
        return this.parameterCategoryList;
    }

    /**
     * creates a new empty list of Employee objects
     *
     * @return empty list of Employee objects
     */

    public EmployeeStore getEmployeeList() {
        return this.employeeList = new EmployeeStore();
    }

    /**
     * creates a new empty list of Parameter objects
     *
     * @return empty list of Parameter objects
     */
    public ParameterStore getParameterList() {
        return this.parameterList = new ParameterStore();
    }


    /**
     * creates a list with all the Roles objects in the system
     *
     * @return list of Roles objects
     */
    public List roleList() {
        return roleList.getRoleList();
    }


    /**
     * creates a new empty list of TestType objects
     *
     * @return empty list of TestType objects
     */
    public TestTypeStore getTestTypeList() {
        return this.testTypeList = new TestTypeStore();
    }

    /**
     * creates a  list with all the TestType objects in the system
     *
     * @return list of TestType objects
     */
    public TestTypeStore TestTypeList() {
        return this.testTypeList;
    }

    /**
     * creates a list of Role objects
     *
     * @return list of Role objects
     */
    public RoleStore getRoleList() {
        return this.roleList = new RoleStore();
    }

    /**
     * creates a new empty list of ClinicalAnalysisLab objects
     *
     * @return empty list of ClinicalAnalysisLab objects
     */
    public ClinicalAnalysisLabStore getClinicalAnalysisLabList() {
        this.clinicalAnalysisLabList = new ClinicalAnalysisLabStore();
        return this.clinicalAnalysisLabList;
    }

    /**
     * creates a new empty list of Client objects
     *
     * @return empty list of Client objects
     */
    public ClientStore getClientList() {
        this.clientList = new ClientStore();
        return this.clientList;
    }
}