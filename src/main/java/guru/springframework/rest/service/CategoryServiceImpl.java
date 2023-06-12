package guru.springframework.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springframework.rest.api.v1.mapper.CategoryMapper;
import guru.springframework.rest.api.v1.model.CategoryDto;
import guru.springframework.rest.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepo categoryRepo) {
        this.categoryMapper = categoryMapper;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepo.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepo.findByName(name));
    }

}