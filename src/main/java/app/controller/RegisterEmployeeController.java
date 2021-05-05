package app.controller;

import app.domain.model.*;

public class RegisterEmployeeController{


    private Company company;
    private Employee em;
    private EmployeeStore store;

    public RegisterEmployeeController() {

        this(App.getInstance().getCompany());
    }



    public RegisterEmployeeController(Company company) {
        this.company = company;
        this.em = null;
    }


    public void createEmployee(String name, String address, String phonenumber, String email, String SOC, String DoctorIndexNumber, Role Role) {
        store = company.getEmployeeList();
        store.CreateEmployee(name, address, phonenumber, email, SOC, DoctorIndexNumber, Role);
    }

    public boolean saveEmployee() {
        return this.store.saveEmployee();
    }
}

