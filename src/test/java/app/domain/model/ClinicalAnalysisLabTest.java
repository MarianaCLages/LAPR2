package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClinicalAnalysisLabTest {

    @Test
    public void RegisterLabCorrect() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","6357896543","12345678901");
        //Assert
        Assert.assertNotNull(lab);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabNameTooShort() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("labo","porto", "2gs45","6357976543","16297483987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabAddressTooLong() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","rio, porto, portugal, europa, terra", "2gs45","6357976543","16275483987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberNotElevenDigits() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","6334906543","16274980843573987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberNotOnlyDigits() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","6334906543","16!748a987t");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterTinNot10Digits() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","63349369906543","16274835987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterTinNotOnlyDigits() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","63!906g543","16274835987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabIdMoreThan5Chars() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs406k5t1","6334906543","16274835987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabIdLessThan5Chars() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "12h","6334906543","16274835987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabNameBlank() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("","porto", "2gs45","6334906543","16274835987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterAddressBlank() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","", "2gs45","6334906543","16274835987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabIdBlank() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "","6334906543","16274835987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterTinBlank() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","","16274835987");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberBlank() {
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","6334906543","");
    }
}