package app.domain.stores;

import app.domain.model.Role;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RoleStoreTest {

    @Test
    public void RoleStoreTest() {
        RoleStore roleList = new RoleStore();
        Assert.assertNotNull(roleList);
    }

    @Test
    public void getRoleIndexRightTest() {
        RoleStore roleList = new RoleStore();
        String actual = String.valueOf(roleList.get(1));
        String expected = String.valueOf(new Role("1", "Medical Lab Technician"));

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void getRoleListRight() {
        RoleStore roleList = new RoleStore();

        List<Role> actual = roleList.getRoleList();

        List<Role> array = new ArrayList<>();


        Role ClinicalChemistryTechnologist = new Role("0", "Clinical Chemistry Technologist");
        Role MedicalLabTechnician = new Role("1", "Medical Lab Technician");
        Role LaboratoryCoordinator = new Role("2", "LaboratoryCoordinator");
        Role Receptionist = new Role("3", "Receptionist");
        Role SpecialistDoctor = new Role("4", "SpecialistDoctor");


        array.add(ClinicalChemistryTechnologist);
        array.add(MedicalLabTechnician);
        array.add(LaboratoryCoordinator);
        array.add(Receptionist);
        array.add(SpecialistDoctor);
    }

}