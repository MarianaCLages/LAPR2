package app.domain.mappers.dto;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryListDTOTest {

    @Test
    public void toStringTest() {
        CategoryListDTO s = new CategoryListDTO("AF856","Hemogram");
        String a = s.toString();
        String b = "CategoryList: code= AF856, name= Hemogram";
        Assert.assertEquals(a,b);
    }

}