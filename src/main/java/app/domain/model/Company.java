package app.domain.model;

import app.domain.stores.*;
import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private final String designation;
    private final AuthFacade authFacade;

    private final ParameterCategoryStore parameterCategoryList;
    private ParameterStore parameterList;
    private TestTypeStore testTypeList;
    private final ClinicalAnalysisLabStore clinicalAnalysisLabList;
    private RoleStore roleList;
    private final EmployeeStore employeeList;
    private final ClientStore clientList;
    private final TestStore testList;
    private final SampleStore sampleStore;

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

        this.parameterCategoryList = new ParameterCategoryStore();
        this.employeeList = new EmployeeStore();
        this.clinicalAnalysisLabList = new ClinicalAnalysisLabStore();
        this.clientList = new ClientStore();
        this.testList = new TestStore();
        this.sampleStore = new SampleStore();

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
        return this.employeeList;

    }

    /**
     * creates a new empty list of Parameter objects
     *
     * @return empty list of Parameter objects
     */
    public ParameterStore getParameterList() {
        this.parameterList = new ParameterStore();
        return this.parameterList;
    }


    /**
     * creates a list with all the Roles objects in the system
     *
     * @return list of Roles objects
     */
    public List<Role> roleList() {
        return roleList.getRoleList();
    }


    /**
     * creates a new empty list of TestType objects
     *
     * @return empty list of TestType objects
     */
    public TestTypeStore getTestTypeList() {
        this.testTypeList = new TestTypeStore();
        return this.testTypeList;
    }

    /**
     * creates a  list with all the TestType objects in the system
     *
     * @return list of TestType objects
     */
    public TestTypeStore testTypeList() {
        return this.testTypeList;
    }

    /**
     * creates a list of Role objects
     *
     * @return list of Role objects
     */
    public RoleStore getRoleList() {
        this.roleList = new RoleStore();
        return this.roleList;
    }

    /**
     * creates a new empty list of ClinicalAnalysisLab objects
     *
     * @return empty list of ClinicalAnalysisLab objects
     */
    public ClinicalAnalysisLabStore getClinicalAnalysisLabList() {
        return this.clinicalAnalysisLabList;
    }

    /**
     * creates a new empty list of Client objects
     *
     * @return empty list of Client objects
     */
    public ClientStore getClientList() {
        return this.clientList;
    }

    public ClientStore clientList(){
        return this.clientList;
    }


    /**
     * creates a new empty list of Test objects
     * @return empty list of Test objects
     */
    public TestStore getTestList() {
        return this.testList;
    }
    public TestStore testList(){
        return this.testList;
    }

    public ParameterStore parameterList() {
        return this.parameterList;
    }

    public SampleStore getSampleStore() {
        return sampleStore;
    }
}