package guru.springframework.rest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDto {

    private Long id;

    @ApiModelProperty(value = "First Name", required = true)
    private String firstName;
    @ApiModelProperty(value = "Last Name", required = true)
    private String lastName;
    private String customerUrl;

}
