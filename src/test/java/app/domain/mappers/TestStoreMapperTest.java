package app.domain.mappers;

import app.domain.mappers.dto.TestDTO;
import app.domain.mappers.dto.TestStoreDTO;
import app.domain.mappers.dto.TestTypeDTO;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.stores.ParameterCategoryStore;
import app.domain.stores.TestStore;
import app.domain.stores.TestTypeStore;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestStoreMapperTest {


    @Test
    public void testMapper() {
        try {
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

            Parameter pa = new Parameter("aaaaa", "aaaa", "aaaa", cat1);

            TestType tt = new TestType("BL000", "Blood Test", "qwe", catstore);
            store.add(tt);
            TestType tt1 = new TestType("COV19", "Covid Test", "qwe", catstore1);
            store.add(tt1);

            List<ParameterCategory> paList = new ArrayList<>();
            paList.add(cat2);
            List<Parameter> pList = new ArrayList<>();
            pList.add(pa);

            app.domain.model.Test teste = new app.domain.model.Test("1234s", "123456789012", "1234567890123456", tt1, paList, pList);
            teste.changeState("Validated");

            TestStore testStore = new TestStore();
            testStore.addTest(teste);


            TestStoreMapper mapper = new TestStoreMapper();

            List<TestStoreDTO> testDTOS = mapper.toDTO(testStore);

            TestStoreDTO testStoreDTO = new TestStoreDTO(teste);
            testStoreDTO.toString();

            Assert.assertNotNull(testDTOS);
        } catch (Exception e) {

        }

    }
}