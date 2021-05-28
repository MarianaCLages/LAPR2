package app.domain.mappers;

import app.domain.mappers.dto.ParameterDTO;
import app.domain.mappers.dto.TestParameterDTO;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestParameter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestParameterListMapperTest {

    @Test
    public void validDto() {
        ParameterCategory cat = new ParameterCategory("QWERT", "name");
        List<TestParameter> parameters = new ArrayList<>();
        TestParameter pc = new TestParameter("HB000");
        TestParameter pc1 = new TestParameter("WBC00");

        parameters.add(pc);
        parameters.add(pc1);

        TestParameterListMapper mapper = new TestParameterListMapper();

        List<TestParameterDTO> parameterDTOList = mapper.toDTO(parameters);

        Assert.assertNotNull(parameterDTOList);

    }

    @Test
    public void getCode() {
        ParameterCategory cat = new ParameterCategory("QWERT", "name");
        List<TestParameter> parameters = new ArrayList<>();
        TestParameter pc = new TestParameter("HB000");
        TestParameter pc1 = new TestParameter("WBC00");

        parameters.add(pc);
        parameters.add(pc1);

        TestParameterListMapper mapper = new TestParameterListMapper();

        List<TestParameterDTO> parameterDTOList = mapper.toDTO(parameters);

        String actual = parameterDTOList.get(1).getPcode();
        String expected = "WBC00";

        Assert.assertEquals(actual,expected);

    }

    @Test
    public void toStringTest() {
        ParameterCategory cat = new ParameterCategory("QWERT", "name");
        List<TestParameter> parameters = new ArrayList<>();
        TestParameter pc = new TestParameter("HB000");
        TestParameter pc1 = new TestParameter("WBC00");

        parameters.add(pc);
        parameters.add(pc1);

        TestParameterListMapper mapper = new TestParameterListMapper();

        List<TestParameterDTO> parameterDTOList = mapper.toDTO(parameters);

        String actual = parameterDTOList.get(1).toString();
        String expected = "TestParameter: pcode= WBC00";

        Assert.assertEquals(actual,expected);

    }

}