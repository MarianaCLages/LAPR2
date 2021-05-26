package app.domain.mappers;

import app.domain.mappers.dto.ParameterDTO;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.stores.ParameterStore;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParameterListMapperTest {

    @Test
    public void validDto(){
        ParameterCategory cat = new ParameterCategory("QWERT","name");
        List<Parameter> parameters = new ArrayList<>();
        Parameter pc = new Parameter("HB000","HB","Haemoglobin",cat);
        Parameter pc1 = new Parameter("WBC00","WBC","White Cell Count",cat);

        parameters.add(pc);
        parameters.add(pc1);

        ParameterListMapper mapper = new ParameterListMapper();

        List<ParameterDTO> parameterDTOList = mapper.toDTO(parameters);

        Assert.assertNotNull(parameterDTOList);

    }

    @Test
    public void getCode(){
        ParameterCategory cat = new ParameterCategory("QWERT","name");
        List<Parameter> parameters = new ArrayList<>();
        Parameter pc = new Parameter("HB000","HB","Haemoglobin",cat);
        Parameter pc1 = new Parameter("WBC00","WBC","White Cell Count",cat);

        parameters.add(pc);
        parameters.add(pc1);

        ParameterListMapper mapper = new ParameterListMapper();

        List<ParameterDTO> parameterDTOList = mapper.toDTO(parameters);

        String actual = parameterDTOList.get(1).getCode();
        String expected = "WBC00";

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void getName(){
        ParameterCategory cat = new ParameterCategory("QWERT","name");
        List<Parameter> parameters = new ArrayList<>();
        Parameter pc = new Parameter("HB000","HB","Haemoglobin",cat);
        Parameter pc1 = new Parameter("WBC00","WBC","White Cell Count",cat);

        parameters.add(pc);
        parameters.add(pc1);

        ParameterListMapper mapper = new ParameterListMapper();

        List<ParameterDTO> parameterDTOList = mapper.toDTO(parameters);

        String actual = parameterDTOList.get(1).getName();
        String expected = "WBC";

        Assert.assertEquals(actual,expected);
    }

}