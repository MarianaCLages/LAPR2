package app.controller;

import app.domain.model.*;

import java.util.List;

public class RegisterEmployeeController{


    private Company company;
    private Employee em;
    private EmployeeStore store;
    private List roleList;

    public RegisterEmployeeController() {

        this(App.getInstance().getCompany());
    }



    public RegisterEmployeeController(Company company) {
        this.company = company;
        this.em = null;
    }


    public void createEmployee(String name, String address, String phonenumber, String email, String SOC, String DoctorIndexNumber, int Role) {
        store = company.getEmployeeList();
        store.CreateEmployee(name, address, phonenumber, email, SOC, DoctorIndexNumber, Role);
    }

    public List getRoleList() {
        return this.roleList = company.roleList();
    }

    public Employee getEm(){
        return store.getEm();
    }

    public boolean saveEmployee() {
        boolean saved = false;

        saved = store.saveEmployee();
        store.addUserWithRole(this.company);

        return saved;
    }
}

