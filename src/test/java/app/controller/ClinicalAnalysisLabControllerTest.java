/* package app.controller;

import app.domain.model.*;
import org.junit.Assert;
import org.junit.Test;

public class ClinicalAnalysisLabControllerTest {
    private Company company;
    private ClinicalAnalysisLab cal;
    private ParameterCategoryStore cat = new ParameterCategoryStore();
    private ClinicalAnalysisLabStore store;
    private ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
    private TestType t1 = new TestType("283h3", "descrição", "metodo 1", cat);

    @Test
    public void ClinicalAnalysisLabController() {
        ClinicalAnalysisLabController controller = new ClinicalAnalysisLabController();
    }

    @Test
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
}
*/