package guru.springframework.rest.api.v1.mapper;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

import guru.springframework.rest.api.v1.model.CategoryDto;
import guru.springframework.rest.domain.Category;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2023-06-13T02:08:01+0300", comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230413-0857, environment: Java 17.0.7 (Eclipse Adoptium)")
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto categoryToCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());

        return categoryDto;
    }
}
