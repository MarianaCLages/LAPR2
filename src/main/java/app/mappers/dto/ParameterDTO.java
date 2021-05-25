package app.mappers.dto;

public class ParameterDTO {
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
}
