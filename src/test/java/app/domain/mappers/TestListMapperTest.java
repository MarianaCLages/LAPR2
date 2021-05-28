package app.domain.mappers;

import app.domain.mappers.dto.TestDTO;

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

public class TestListMapperTest {

    @Test
    public void validDto() {
        TestStore store = new TestStore();

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test t = new app.domain.model.Test("123456789012","123456789012","1234567890123456",testType,cat1,pa);
        store.addTest(t);
        TestListMapper mapper = new TestListMapper();

        List<TestDTO> testDTOS = mapper.toDTO(store);

        Assert.assertNotNull(testDTOS);
    }

    @Test
    public void getTestCodeTest() {
        TestStore store = new TestStore();

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test t = new app.domain.model.Test("123456789012","123456789012","1234567890123456",testType,cat1,pa);
        store.addTest(t);
        TestListMapper mapper = new TestListMapper();

        List<TestDTO> testDTOS = mapper.toDTO(store);


        String actual = testDTOS.get(0).getTestCode();
        String expected = "123456789012";

        Assert.assertEquals(actual, "123456789012");
    }

    @Test
    public void getTestNhsNumberTest() {
        TestStore store = new TestStore();

        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000","Nome","description",pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000","description","sei lá",cat);

        app.domain.model.Test t = new app.domain.model.Test("123456789012","123456789012","1234567890123456",testType,cat1,pa);
        store.addTest(t);
        TestListMapper mapper = new TestListMapper();

        List<TestDTO> testDTOS = mapper.toDTO(store);


        String actual = testDTOS.get(0).getTestNhsNumber();
        String expected = "123456789012";

        Assert.assertEquals(actual, "123456789012");
    }
}