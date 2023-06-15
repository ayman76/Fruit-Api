package guru.springframework.rest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDto {

    private Long id;
    private String name;
    private String vendorUrl;

}
