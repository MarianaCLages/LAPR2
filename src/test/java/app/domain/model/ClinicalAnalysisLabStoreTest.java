package app.domain.model;

import app.controller.ClinicalAnalysisLabController;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ClinicalAnalysisLabStoreTest {
    private ParameterCategoryStore cat = new ParameterCategoryStore();
    private TestTypeStore store = new TestTypeStore();
    ClinicalAnalysisLab cal;
    List<ClinicalAnalysisLab> array;

    @Test
    public void CreateClinicalAnalysisLab() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLabStore lab = new ClinicalAnalysisLabStore();
        Assert.assertEquals(true, lab.CreateClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", t));
    }

    @Test
    public void CreateClinicalAnalysisLabWrong() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLabStore lab = new ClinicalAnalysisLabStore();
        Assert.assertEquals(false, !lab.CreateClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", t));
    }

   @Test
    public void ValidateClinicalAnalysisLab(){
       ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
       cat.add(pc1);
       TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
       //Arrange + Act
       ClinicalAnalysisLabStore cal = new ClinicalAnalysisLabStore();
       ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", t);
       cal.add(lab);
       Assert.assertEquals(true, cal.ValidateClinicalAnalysisLab(lab));
   }

    @Test
    public void ValidateClinicalAnalysisLabWrong(){
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLabStore cal = new ClinicalAnalysisLabStore();
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", t);
        cal.add(lab);
        Assert.assertEquals(false, !cal.ValidateClinicalAnalysisLab(lab));
    }

    @Test
    public void contains(){
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLabStore cal = new ClinicalAnalysisLabStore();
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", t);
        cal.add(lab);
        Assert.assertEquals(true, cal.contains(lab));
    }

    @Test
    public void saveClinicalAnalysisLab(){
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLabStore cal = new ClinicalAnalysisLabStore();
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", t);
        Assert.assertEquals(true, cal.saveClinicalAnalysisLab(lab));
    }

    @Test
    public void saveClinicalAnalysisLabWrong(){
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLabStore cal = new ClinicalAnalysisLabStore();
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", t);
        Assert.assertEquals(false, !cal.saveClinicalAnalysisLab(lab));
    }


    @Test
    public void add(){
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLabStore cal = new ClinicalAnalysisLabStore();
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", t);
        Assert.assertEquals(true, cal.add(lab));
    }

}