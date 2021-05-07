package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class ClinicalAnalysisLabStoreTest {
    private ParameterCategoryStore cat = new ParameterCategoryStore();
    private TestTypeStore store = new TestTypeStore();

    @Test
    public void RegisterLabCorrect() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45", "6357896543", "12345678901", t);
        //Assert
        Assert.assertNotNull(lab);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabNameTooShort() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("labo","porto", "2gs45","63578976543","1627483987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabAddressTooLong() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","rio, porto, portugal, europa, terra", "2gs45","63578976543","1627483987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberNotElevenDigits() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","63345789767906543","1627483987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberNotOnlyDigits() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","67043a87!61","1627483987", t);
    }
}