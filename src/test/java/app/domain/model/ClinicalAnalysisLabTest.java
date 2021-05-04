package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClinicalAnalysisLabTest {

    @Test
    public void RegisterLabCorrect() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","63578976543","1627483987");
        //Assert
        Assert.assertNotNull(lab);
    }
    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabNameToShort() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("labo","porto", "2gs45","63578976543","1627483987");
    }
    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabAddressToLong() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("labo","rio, porto, portugal, europa, terra", "2gs45","63578976543","1627483987");
    }
}