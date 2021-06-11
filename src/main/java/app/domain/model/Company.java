package app.domain.model;

import app.domain.shared.Constants;
import app.domain.shared.Serialize;
import app.domain.stores.*;
import auth.AuthFacade;
import auth.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company implements Serializable {

    private String designation;
    private AuthFacade authFacade;

    private ParameterCategoryStore parameterCategoryList;
    private ClinicalAnalysisLabStore clinicalAnalysisLabList;
    private EmployeeStore employeeList;
    private ClientStore clientList;
    private TestStore testList;
    private SampleStore sampleStore;
    private ParameterStore parameterList;
    private TestTypeStore testTypeList;
    private RoleStore roleList;
    private UserSession user;

    /**
     * Constructor of the Company Class, instances a new object of AuthFacade and new empty stores
     *
     * @param designation Designation of Company
     */
    public Company(String designation, String hour, String min, String sec) {
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
        scheduleReport(Integer.parseInt(hour), Integer.parseInt(min), Integer.parseInt(sec));

    }

    public Company() {
        Company temp = (Company) readCompany();

        this.designation = Objects.requireNonNull(temp).getDesignation();
        this.authFacade = temp.getAuthFacade();
        this.parameterList = temp.getParameterList();
        this.roleList = temp.getRoleList();
        this.testTypeList = temp.getTestTypeList();
        this.parameterCategoryList = temp.getParameterCategoryList();
        this.employeeList = temp.getEmployeeList();
        this.clinicalAnalysisLabList = temp.getClinicalAnalysisLabList();
        this.clientList = temp.getClientList();
        this.testList = temp.getTestList();
        this.sampleStore = temp.getSampleStore();

    }

    private void scheduleReport(int hour, int min, int sec) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, hour);
        today.set(Calendar.MINUTE, min);
        today.set(Calendar.SECOND, sec);

        Timer timer = new Timer();
        timer.schedule(new SendReportTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

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
        return this.parameterList;
    }

    /**
     * @return the list of Types of Tests in the System
     */
    public TestTypeStore getTestTypeList() {
        return this.testTypeList;
    }

    /**
     * @return the list of Roles in the System
     */
    public RoleStore getRoleList() {
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

    public List<Client> getClientArrayList(){
        return getClientList().returnClientList();
    }

    /**
     * @return the list of Tests in the System
     */
    public TestStore getTestList() {
        return this.testList;
    }

    /**
     * @return the list of Samples in the System
     */
    public SampleStore getSampleStore() {
        return sampleStore;
    }


    public String getUserID() {
        return user.getUserId().toString();
    }


    public boolean saveCompany() {
        return Serialize.writeObject(Constants.COMPANY_SER, this);
    }

    private Object readCompany() {

        try {
            return Serialize.readFile(Constants.COMPANY_SER);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}