package guru.springframework.rest.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guru.springframework.rest.api.v1.model.CategoryDto;
import guru.springframework.rest.domain.Category;

public class CategoryMapperTest {

    /**
     *
     */
    private static final String NAME = "Ayman";
    private static final long ID = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void testCategoryToCategoryDTO() {
        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        //when 
        CategoryDto categoryDto = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(Long.valueOf(ID), categoryDto.getId());
        assertEquals(NAME, categoryDto.getName());
    }
}
