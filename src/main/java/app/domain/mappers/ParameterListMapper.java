package app.domain.mappers;

import app.domain.mappers.dto.ParameterDTO;
import app.domain.model.Parameter;
import app.domain.stores.ParameterStore;

import java.util.ArrayList;
import java.util.List;

public class ParameterListMapper {

    public ParameterDTO toDTO(Parameter pa) {
        return new ParameterDTO(pa.getCode(), pa.getName());
    }

    public List<ParameterDTO> toDTO(List<Parameter> paList) {
        List<ParameterDTO> parametersDTO = new ArrayList<>();
        for (Parameter pa : paList) {
            parametersDTO.add(this.toDTO(pa));
        }
        return parametersDTO;
    }
}
