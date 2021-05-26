package app.domain.mappers;

import app.domain.mappers.dto.TestTypeDTO;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.stores.ParameterCategoryStore;
import app.domain.stores.TestTypeStore;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestTypeListMapperTest {

    @Test
    public void validDto() {
        TestTypeStore store = new TestTypeStore();
        ParameterCategoryStore catstore = new ParameterCategoryStore();
        ParameterCategoryStore catstore1 = new ParameterCategoryStore();

        ParameterCategory cat = new ParameterCategory("QWERT", "name");
        catstore.add(cat);
        ParameterCategory cat1 = new ParameterCategory("QWRRT", "nan");
        catstore.add(cat1);

        ParameterCategory cat2 = new ParameterCategory("QWuRT", "namme");
        catstore1.add(cat2);
        ParameterCategory cat3 = new ParameterCategory("QWRRT", "nan");
        catstore1.add(cat3);

        TestType tt = new TestType("BL000", "Blood Test", "qwe", catstore);
        store.add(tt);
        TestType tt1 = new TestType("COV19", "Covid Test", "qwe", catstore1);
        store.add(tt1);

        TestTypeListMapper mapper = new TestTypeListMapper();

        List<TestTypeDTO> testTypeDTOS = mapper.toDTO(store);

        Assert.assertNotNull(testTypeDTOS);
    }

    @Test
    public void getTestIDTest() {
        TestTypeStore store = new TestTypeStore();
        ParameterCategoryStore catstore = new ParameterCategoryStore();
        ParameterCategoryStore catstore1 = new ParameterCategoryStore();

        ParameterCategory cat = new ParameterCategory("QWERT", "name");
        catstore.add(cat);
        ParameterCategory cat1 = new ParameterCategory("QWRRT", "nan");
        catstore.add(cat1);

        ParameterCategory cat2 = new ParameterCategory("QWuRT", "namme");
        catstore1.add(cat2);
        ParameterCategory cat3 = new ParameterCategory("QWRRT", "nan");
        catstore1.add(cat3);

        TestType tt = new TestType("BL000", "Blood Test", "qwe", catstore);
        store.add(tt);
        TestType tt1 = new TestType("COV19", "Covid Test", "qwe", catstore1);
        store.add(tt1);

        TestTypeListMapper mapper = new TestTypeListMapper();

        List<TestTypeDTO> testTypeDTOS = mapper.toDTO(store);

        String actual = testTypeDTOS.get(1).getTestID();
        String expected = "COV19";

        Assert.assertEquals(actual, expected);

    }

    @Test
    public void getDescriptionTest() {
        TestTypeStore store = new TestTypeStore();
        ParameterCategoryStore catstore = new ParameterCategoryStore();
        ParameterCategoryStore catstore1 = new ParameterCategoryStore();

        ParameterCategory cat = new ParameterCategory("QWERT", "name");
        catstore.add(cat);
        ParameterCategory cat1 = new ParameterCategory("QWRRT", "nan");
        catstore.add(cat1);

        ParameterCategory cat2 = new ParameterCategory("QWuRT", "namme");
        catstore1.add(cat2);
        ParameterCategory cat3 = new ParameterCategory("QWRRT", "nan");
        catstore1.add(cat3);

        TestType tt = new TestType("BL000", "Blood Test", "qwe", catstore);
        store.add(tt);
        TestType tt1 = new TestType("COV19", "Covid Test", "qwe", catstore1);
        store.add(tt1);

        TestTypeListMapper mapper = new TestTypeListMapper();

        List<TestTypeDTO> testTypeDTOS = mapper.toDTO(store);

        String actual = testTypeDTOS.get(1).getDescription();
        String expected = "Covid Test";

        Assert.assertEquals(actual, expected);

    }

}