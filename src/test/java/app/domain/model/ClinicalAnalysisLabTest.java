package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class ClinicalAnalysisLabTest {
    private ParameterCategoryStore cat = new ParameterCategoryStore();
    private TestTypeStore store = new TestTypeStore();


    @Test
    public void RegisterLabCorrect() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","6357896543","12345678901", t);
        //Assert
        Assert.assertNotNull(lab);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabNameTooShort() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("labo","porto", "2gs45","6357976543","16297483987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabAddressTooLong() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","rio, porto, portugal, europa, terra", "2gs45","6357976543","16275483987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberNotElevenDigits() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","6334906543","16274980843573987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberNotOnlyDigits() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","6334906543","16!748a987t", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterTinNot10Digits() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","63349369906543","16274835987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterTinNotOnlyDigits() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","63!906g543","16274835987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabIdMoreThan5Chars() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs406k5t1","6334906543","16274835987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabIdLessThan5Chars() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "12h","6334906543","16274835987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabNameBlank() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("","porto", "2gs45","6334906543","16274835987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterAddressBlank() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","", "2gs45","6334906543","16274835987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabIdBlank() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "","6334906543","16274835987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterTinBlank() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","","16274835987", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberBlank() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","6334906543","", t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestTypeBlank(){
        ParameterCategory pc1 = new ParameterCategory(null, null);
        cat.add(pc1);
        TestType t = new TestType(null, null, null, cat);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois","porto", "2gs45","6357896543","12345678901", t);
    }

    @Test
    public void ClinicalAnalysisLab() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab(t);
    }
}