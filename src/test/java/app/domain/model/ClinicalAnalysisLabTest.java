package app.domain.model;

import app.domain.stores.ParameterCategoryStore;
import app.domain.stores.TestTypeStore;
import org.junit.Assert;
import org.junit.Test;

public class ClinicalAnalysisLabTest {
    private ParameterCategoryStore cat = new ParameterCategoryStore();
    private TestTypeStore store = new TestTypeStore();

    @Test(expected = IllegalArgumentException.class)
    public void ClinicalNull() {
        ClinicalAnalysisLab cli = new ClinicalAnalysisLab(null, null, null, null, null, null);
    }

    @Test
    public void RegisterLabCorrect() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45", "6357896543", "12345678901", store);
        //Assert
        Assert.assertNotNull(lab);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabNameTooLong() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio laboratorio dois", "porto", "2gs45", "6357976543", "16297483987", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabAddressTooLong() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "rio, porto, portugal, europa, terra", "2gs45", "6357976543", "16275483987", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberNotElevenDigits() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45", "6334906543", "16274980843573987", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberNotOnlyDigits() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45", "6334906543", "16!748a987t", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterTinNot10Digits() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45", "63349369906543", "16274835987", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterTinNotOnlyDigits() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45", "63!906g543", "16274835987", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabIdMoreThan5Chars() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs406k5t1", "6334906543", "16274835987", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabIdLessThan5Chars() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "12h", "6334906543", "16274835987", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabNameBlank() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("", "porto", "2gs45", "6334906543", "16274835987", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterAddressBlank() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "", "2gs45", "6334906543", "16274835987", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterLabIdBlank() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "", "6334906543", "16274835987", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterTinBlank() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45", "", "16274835987", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterPhoneNumberBlank() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("283h3", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45", "6334906543", "", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestTypeBlank() {
        ParameterCategory pc1 = new ParameterCategory(null, null);
        cat.add(pc1);
        TestType t = new TestType(null, null, null, cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        //Arrange + Act
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratorio dois", "porto", "2gs45", "6357896543", "12345678901", store);
    }

    @Test
    public void ClinicalAnalysisLab() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab(store);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ClinicalAnalysisLabNameNull() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab(null, "porto", "2gs45", "6357896543", "12345678901", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ClinicalAnalysisLabNameMax() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("qwertyuiopasdfghjkler", "porto", "2gs45", "6357896543", "12345678901", store);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ClinicalAnalysisLabAddressBlank() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratório três", "", "2gs45", "6357896543", "12345678901", store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ClinicalAnalysisLabAddressMax() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratório três", "qwertyuiopasdfghjklerifhadygopy", "2gs45", "6357896543", "12345678901", store);
    }

    @Test
    public void toStringTest() {
        ParameterCategory pc1 = new ParameterCategory("AE554", "Hemogram");
        cat.add(pc1);
        TestType t = new TestType("BL000", "descrição", "metodo 1", cat);
        TestTypeStore store = new TestTypeStore();
        store.add(t);
        ClinicalAnalysisLab lab = new ClinicalAnalysisLab("laboratório três", "porto", "2gs45", "6357896543", "12345678901", store);

        String expected = "ClinicalAnalysisLab: name=laboratório três, address=porto, id=2gs45, tin=6357896543, phonenumber=12345678901, typetest=" + store.toString();

        String actual = lab.toString();

        Assert.assertEquals(actual, expected);
    }


}