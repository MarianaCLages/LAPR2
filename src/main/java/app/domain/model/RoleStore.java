
package app.domain.model;

import javax.management.relation.RoleList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleStore {



    private  List <Role> array;
    private Role pc;

    private Role ClinicalChemistryTechnologist = new Role("0","Clinical Chemistry Technologist");
    private Role MedicalLabTechnician = new Role("1", "Medical Lab Technician");
    private Role LaboratoryCoordinator = new Role("2", "LaboratoryCoordinator");
    private Role Receptionist = new Role("3","Receptionist");
    private Role SpecialistDoctor = new Role("4","SpecialistDoctor");


    public RoleStore(){
        this.array = new ArrayList<>();

        array.add(ClinicalChemistryTechnologist);
        array.add(MedicalLabTechnician);
        array.add(LaboratoryCoordinator);
        array.add(LaboratoryCoordinator);
        array.add(Receptionist);
        array.add(SpecialistDoctor);
    }






    public Role get(int index ) {
        return array.get(index);
    }





    public List<Role> getRoleList(){

        return array;
    }









}



