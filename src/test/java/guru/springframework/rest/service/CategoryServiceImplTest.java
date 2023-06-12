package guru.springframework.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.rest.api.v1.mapper.CategoryMapper;
import guru.springframework.rest.api.v1.model.CategoryDto;
import guru.springframework.rest.domain.Category;
import guru.springframework.rest.repository.CategoryRepo;

public class CategoryServiceImplTest {

    private static final String NAME = "Fruits";
    private static final long ID = 1L;

    @Mock
    CategoryRepo categoryRepo;

    CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepo);
    }

    @Test
    void testGetAllCategories() {
        // given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepo.findAll()).thenReturn(categories);

        // when
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();

        // then
        assertEquals(3, categoryDtos.size());
    }

    @Test
    void testGetCategoryByName() {
        //given 
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepo.findByName(anyString())).thenReturn(category);

        //when
        CategoryDto categoryDto = categoryService.getCategoryByName(NAME);

        assertEquals(Long.valueOf(ID), categoryDto.getId());
        assertEquals(NAME, categoryDto.getName());

    }
}
