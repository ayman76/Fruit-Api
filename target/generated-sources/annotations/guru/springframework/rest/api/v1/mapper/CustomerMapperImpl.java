package guru.springframework.rest.api.v1.mapper;

import guru.springframework.rest.api.v1.model.CustomerDto;
import guru.springframework.rest.domain.Customer;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;


@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T15:35:42+0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230413-0857, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setFirstName( customer.getFirstName() );
        customerDto.setId( customer.getId() );
        customerDto.setLastName( customer.getLastName() );

        return customerDto;
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setFirstName( customerDto.getFirstName() );
        customer.setId( customerDto.getId() );
        customer.setLastName( customerDto.getLastName() );

        return customer;
    }
}
