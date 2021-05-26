package app.domain.mappers;

import app.domain.mappers.dto.ParameterDTO;
import app.domain.model.Parameter;

import java.util.ArrayList;
import java.util.List;

public class ParameterListMapper {

    private ParameterDTO toDTO(Parameter pa) {
        return new ParameterDTO(pa.getName(),pa.getCode());
    }

    public List<ParameterDTO> toDTO(List<Parameter> paList) {
        List<ParameterDTO> parametersDTO = new ArrayList<>();
        for (Parameter pa : paList) {
            parametersDTO.add(this.toDTO(pa));
        }
        return parametersDTO;
    }
}
