package app.domain.mappers;

import app.domain.mappers.dto.CategoryListDTO;
import app.domain.model.ParameterCategory;
import app.domain.stores.ParameterCategoryStore;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CategoryListMapperTest {
    @Test
    public void validDto() {
        ParameterCategoryStore pStore = new ParameterCategoryStore();
        ParameterCategory pc = new ParameterCategory("12345", "name");
        ParameterCategory pc1 = new ParameterCategory("12345", "name");
        pStore.add(pc);
        pStore.add(pc1);
        CategoryListMapper mapper = new CategoryListMapper();
        List<CategoryListDTO> categoryListDTO = mapper.toDTO(pStore);

        Assert.assertNotNull(categoryListDTO);

    }

    @Test
    public void getCode() {
        ParameterCategoryStore pStore = new ParameterCategoryStore();
        ParameterCategory pc = new ParameterCategory("12345", "name");
        ParameterCategory pc1 = new ParameterCategory("12345", "name");
        pStore.add(pc);
        pStore.add(pc1);
        CategoryListMapper mapper = new CategoryListMapper();
        List<CategoryListDTO> categoryListDTO = mapper.toDTO(pStore);

        String actual = categoryListDTO.get(1).getCode();
        String expected = "12345";

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getName() {
        ParameterCategoryStore pStore = new ParameterCategoryStore();
        ParameterCategory pc = new ParameterCategory("12345", "name");
        ParameterCategory pc1 = new ParameterCategory("12345", "name");
        pStore.add(pc);
        pStore.add(pc1);
        CategoryListMapper mapper = new CategoryListMapper();
        List<CategoryListDTO> categoryListDTO = mapper.toDTO(pStore);

        String actual = categoryListDTO.get(1).getName();
        String expected = "name";

        Assert.assertEquals(actual, expected);
    }

}