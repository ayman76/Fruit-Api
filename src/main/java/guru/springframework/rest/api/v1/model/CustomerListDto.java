package guru.springframework.rest.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerListDto {
    List<CustomerDto> customers;
    
}
