package app.controller;

import app.domain.model.*;
import org.junit.Assert;
import org.junit.Test;

public class ClinicalAnalysisLabControllerTest {
    Company company = App.getInstance().getCompany();


    @Test
    public void ClinicalAnalysisLabController() {
        ClinicalAnalysisLabController controller = new ClinicalAnalysisLabController();
        Assert.assertNotNull(controller);
    }

    @Test
    public void ClinicalAnalysisLabControllerComp() {
        ClinicalAnalysisLabController ctrl = new ClinicalAnalysisLabController(company);
        Assert.assertNotNull(ctrl);

    }

    @Test
    public void createValidClinicalAnalysisLaboratory() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ClinicalAnalysisLabStore store = new ClinicalAnalysisLabStore();
        TestTypeStore ts = new TestTypeStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t1 = new TestType("283h3", "descrição", "metodo 1", cat);
        ts.add(t1);

        ClinicalAnalysisLabController ctrl = new ClinicalAnalysisLabController();
        ctrl.createClinicalAnalysisLab("aqwertyujhgfcsg", "3425 Stone Street", "LAB01", "1234567890", "12345678901", t1);
    }


    @Test
    public void saveClininalAnalysisLab() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ClinicalAnalysisLabStore store = new ClinicalAnalysisLabStore();
        TestTypeStore ts = new TestTypeStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t1 = new TestType("283h3", "descrição", "metodo 1", cat);
        ts.add(t1);

        ClinicalAnalysisLabController ctrl = new ClinicalAnalysisLabController();
        ctrl.createClinicalAnalysisLab("aqwertyujhgfcsg", "3425 Stone Street", "LAB01", "1234567890", "12345678901", t1);

        Assert.assertTrue(ctrl.saveClinicalAnalysisLab());

    }


    @Test
    public void getCal() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ClinicalAnalysisLabStore store = new ClinicalAnalysisLabStore();
        TestTypeStore ts = new TestTypeStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t1 = new TestType("283h3", "descrição", "metodo 1", cat);
        ts.add(t1);

        ClinicalAnalysisLabController ctrl = new ClinicalAnalysisLabController();
        ctrl.createClinicalAnalysisLab("aqwertyujhgfcsg", "3425 Stone Street", "LAB01", "1234567890", "12345678901", t1);


        Assert.assertNotNull(ctrl.getcal());
    }


    @Test
    public void getTypeTestList() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ClinicalAnalysisLabStore store = new ClinicalAnalysisLabStore();
        TestTypeStore ts = new TestTypeStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t1 = new TestType("283h3", "descrição", "metodo 1", cat);
        ts.add(t1);

        ClinicalAnalysisLabController ctrl = new ClinicalAnalysisLabController();
        ctrl.getTypetestList();

    }





 /*   @Test
    public void createClinicalAnalysisLab() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ClinicalAnalysisLabController controller = new ClinicalAnalysisLabController();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        Assert.assertEquals(true, controller.createClinicalAnalysisLab("laboratorio dois", "porto", "2gs45", "6357896543", "12345678901", t));

    }

    @Test
    public void saveTestType() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ClinicalAnalysisLabStore store;
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t1 = new TestType("283h3", "descrição", "metodo 1", cat);
        ClinicalAnalysisLabController controller = new ClinicalAnalysisLabController();
        //controller.createClinicalAnalysisLab("laboratorio dois","porto", "2gs45","6357896543","12345678901", t1);
        Assert.assertTrue(controller.saveTestType());
    }

  */
}
