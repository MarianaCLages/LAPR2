package app.domain.model;

import app.domain.stores.*;
import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private final String designation;
    private final AuthFacade authFacade;

    private final ParameterCategoryStore parameterCategoryList;
    private final ClinicalAnalysisLabStore clinicalAnalysisLabList;
    private final EmployeeStore employeeList;
    private final ClientStore clientList;
    private final TestStore testList;
    private final SampleStore sampleStore;
    private ParameterStore parameterList;
    private TestTypeStore testTypeList;
    private RoleStore roleList;

    /**
     * Constructor of the Company Class, instances a new object of AuthFacade and new empty stores
     *
     * @param designation Designation of Company
     */
    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.parameterList = new ParameterStore();
        this.roleList = new RoleStore();
        this.testTypeList = new TestTypeStore();
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
     * @return the list of Parameters Categories in the System
     */

    public ParameterCategoryStore getParameterCategoryList() {
        return this.parameterCategoryList;
    }

    /**
     * @return the list of Employees in the System
     */

    public EmployeeStore getEmployeeList() {
        return this.employeeList;

    }

    /**
     * @return the list of Parameters in the System
     */
    public ParameterStore getParameterList() {
        this.parameterList = new ParameterStore();
        return this.parameterList;
    }

    /**
     * @return the list of Types of Tests in the System
     */
    public TestTypeStore getTestTypeList() {
        this.testTypeList = new TestTypeStore();
        return this.testTypeList;
    }

    /**
     * @return the list of Roles in the System
     */
    public RoleStore getRoleList() {
        this.roleList = new RoleStore();
        return this.roleList;
    }

    /**
     * @return the list of Clinical Analysis Labs in the System
     */
    public ClinicalAnalysisLabStore getClinicalAnalysisLabList() {
        return this.clinicalAnalysisLabList;
    }

    /**
     * @return the list of Clients in the System
     */
    public ClientStore getClientList() {
        return this.clientList;
    }

    /**
     * @return the list of Tests in the System
     */
    public TestStore getTestList() {
        return this.testList;
    }

    /**
     *
     * @return the list of Samples in the System
     */
    public SampleStore getSampleStore() {
        return sampleStore;
    }
}