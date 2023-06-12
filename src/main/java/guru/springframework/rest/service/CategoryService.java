package guru.springframework.rest.service;

import java.util.List;

import guru.springframework.rest.api.v1.model.CategoryDto;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryByName(String name);
}
