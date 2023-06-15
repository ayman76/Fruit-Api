package guru.springframework.rest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryDto {

    private Long id;
    @ApiModelProperty(value = "Category Name", required =  true)
    private String name;

}
