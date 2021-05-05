package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClinicalAnalysisLabTest {

    @Test
    public void RegisterLabCorrect() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","63578976543","12345678901");
        //Assert
        Assert.assertNotNull(lab);
    }

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

}