package app.domain.model;

import app.domain.shared.Constants;
import auth.AuthFacade;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class SpecialistDoctor extends Employee{

    private String DoctorIndexNumber;
    private Role role;
    private String name;
    private String address;
    private String phonenumber;
    private String email;
    private String SOC;
    private String EmployeeID;


    public SpecialistDoctor(String EmployeeID,String name, String address, String phonenumber, String email, String SOC, String DoctorIndexNumber, Role role) {
        super(EmployeeID ,name, address, phonenumber, email, SOC, role);

        checkDoctorIndexNumberRules(DoctorIndexNumber);

        this.DoctorIndexNumber = DoctorIndexNumber;


    }


    private void checkDoctorIndexNumberRules(String DoctorIndexNumber) {
        if (StringUtils.isBlank(DoctorIndexNumber))
            throw new IllegalArgumentException("Doctor Index Number cannot be blank.");

        DoctorIndexNumber = DoctorIndexNumber.toLowerCase();
        char[] charArray = DoctorIndexNumber.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= '0' && c <= '9')) {
                throw new IllegalArgumentException("Doctor Index Number only accepts numbers");
            }
        }
    }

    @Override
    public String toString() {
        return "Employee:" +
                "ID=" + this.EmployeeID +
                ", name=" + this.name +
                ", address=" + this.address +
                ", phonenumber=" + this.phonenumber +
                ", email=" + this.email +
                ", SOC=" + this.SOC +
                ", DoctorIndexNumber="+ this.DoctorIndexNumber +
                ", Role="+ this.role ;


    }

    private String getPassword() {
        int lenght = 10;

        return RandomStringUtils.randomAlphanumeric(lenght);
    }

    public boolean addUserWithRole(Company company) {
        boolean success = false;
        String password = getPassword();
        AuthFacade authFacade = company.getAuthFacade();
        success = authFacade.addUserWithRole(this.name, this.email, getPassword(), Constants.ROLE_SPECIALISTDOCTOR);
        if (success){
            Email mail = new Email(this.email,getPassword());

        }
        return success;
    }
}
