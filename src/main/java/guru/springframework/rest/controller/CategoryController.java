package guru.springframework.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.rest.api.v1.model.CategoryDto;
import guru.springframework.rest.api.v1.model.CategoryListDto;
import guru.springframework.rest.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "This is Category Controller")
@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "This will get list of categories", notes = "These are some notes about the API")
    @GetMapping
    public ResponseEntity<CategoryListDto> getAllCategories() {
        return new ResponseEntity<CategoryListDto>(new CategoryListDto(categoryService.getAllCategories()),
                HttpStatus.OK);
    }

    @ApiOperation(value = "This will get category object with given name", notes = "These are some notes about the API")
    @GetMapping("{name}")
    public ResponseEntity<CategoryDto> getCategoryByName(@PathVariable String name) {
        return new ResponseEntity<CategoryDto>(categoryService.getCategoryByName(name), HttpStatus.OK);
    }

}
