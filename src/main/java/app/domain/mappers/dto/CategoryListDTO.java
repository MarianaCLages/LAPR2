package app.domain.mappers.dto;

public class CategoryListDTO {
    private String code;
    private String name;

    public CategoryListDTO(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CategoryList: " + "code= " + code + ", name= " + name;
    }
}
