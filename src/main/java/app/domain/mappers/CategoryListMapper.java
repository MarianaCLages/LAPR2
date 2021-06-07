package app.domain.mappers;

import app.domain.mappers.dto.CategoryListDTO;
import app.domain.model.ParameterCategory;
import app.domain.stores.ParameterCategoryStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryListMapper implements Serializable {
    private CategoryListDTO toDTO(ParameterCategory cat) {
        return new CategoryListDTO(cat.getCode(), cat.getName());
    }

    public List<CategoryListDTO> toDTO(ParameterCategoryStore catStore) {
        List<ParameterCategory> catList = catStore.getList();
        List<CategoryListDTO> categoriesDTO = new ArrayList<>();
        for (ParameterCategory cat : catList) {
            categoriesDTO.add(this.toDTO(cat));
        }
        return categoriesDTO;
    }

}
