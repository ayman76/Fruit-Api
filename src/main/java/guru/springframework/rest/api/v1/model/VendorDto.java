package guru.springframework.rest.api.v1.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDto {

    private Long id;
    @ApiModelProperty(value = "Vendor Name", required = true)
    private String name;
    private String vendorUrl;
    private List<ProductDto> products;

}
