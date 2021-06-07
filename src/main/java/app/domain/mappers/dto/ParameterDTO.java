package app.domain.mappers.dto;

import java.io.Serializable;

public class ParameterDTO implements Serializable {
    private String name;
    private String code;


    public ParameterDTO(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Parameter: " + "name=" + name + ", code=" + code;
    }
}
