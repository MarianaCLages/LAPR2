package app.domain.model;

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
                "ID=" + EmployeeID +
                ", name=" + name +
                ", address=" + address +
                ", phonenumber=" + phonenumber +
                ", email=" + email +
                ", SOC=" + SOC +
                ", DoctorIndexNumber="+ DoctorIndexNumber +
                ", Role="+ role ;


    }
}
