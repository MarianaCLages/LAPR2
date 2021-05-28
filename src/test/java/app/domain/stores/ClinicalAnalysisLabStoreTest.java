package app.domain.stores;

import app.domain.model.ClinicalAnalysisLab;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.stores.ClinicalAnalysisLabStore;
import app.domain.stores.ParameterCategoryStore;
import app.domain.stores.TestTypeStore;
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
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLabStore lab = new ClinicalAnalysisLabStore();
        Assert.assertNotNull(lab.CreateClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", store));
    }

    @Test
    public void CreateClinicalAnalysisLabWrong() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLabStore lab = new ClinicalAnalysisLabStore();
        Assert.assertNotNull(lab.CreateClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", store));
    }

    @Test
    public void ValidateClinicalAnalysisLab(){
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLabStore cal = new ClinicalAnalysisLabStore();
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", store);
        cal.add(lab);
        Assert.assertFalse(cal.ValidateClinicalAnalysisLab(lab));
    }


    @Test
    public void ValidateClinicalAnalysisLabWrong(){
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLabStore cal = new ClinicalAnalysisLabStore();
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("lab2", "porto", "2gs45","6357896543", "12345678901", store);
        cal.add(lab);
        Assert.assertFalse(cal.ValidateClinicalAnalysisLab(lab));
    }

    @Test
    public void contains(){
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLabStore cal = new ClinicalAnalysisLabStore();
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", store);
        cal.add(lab);
        Assert.assertTrue(cal.contains(lab));
    }

    @Test
    public void saveClinicalAnalysisLab(){
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLabStore cal = new ClinicalAnalysisLabStore();
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", store);
        Assert.assertFalse(cal.saveClinicalAnalysisLab());
    }

    @Test
    public void saveClinicalAnalysisLabWrong(){
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLabStore cal = new ClinicalAnalysisLabStore();
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", store);
        Assert.assertFalse(cal.saveClinicalAnalysisLab());
    }


    @Test
    public void add(){
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLabStore cal = new ClinicalAnalysisLabStore();
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45","6357896543", "12345678901", store);
        Assert.assertTrue(cal.add(lab));
    }

}