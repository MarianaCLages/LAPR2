package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClinicalAnalysisLabStoreTest {

    @Test
    public void RegisterLabCorrect() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestTypeStore store = new TestTypeStore();
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","63578976543","12345678901", store);
        //Assert
        Assert.assertNotNull(lab);
    }
/*
    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabNameTooShort() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("labo","porto", "2gs45","63578976543","1627483987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabAddressTooLong() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","rio, porto, portugal, europa, terra", "2gs45","63578976543","1627483987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberNotElevenDigits() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","63345789767906543","1627483987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberNotOnlyDigits() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","67043a87!61","1627483987");
    }
*/
}