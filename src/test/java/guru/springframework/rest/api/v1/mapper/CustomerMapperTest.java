package guru.springframework.rest.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guru.springframework.rest.api.v1.model.CustomerDto;
import guru.springframework.rest.domain.Customer;

public class CustomerMapperTest {

    CustomerMapper mapper = CustomerMapper.INSTATNCE;

    @Test
    void testCustomerToCustomerDto() {
        //given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Ayman");
        customer.setLastName("Mohamed");

        //when
        CustomerDto customerDto = mapper.customerToCustomerDto(customer);

        //then
        assertEquals(Long.valueOf(1L), customerDto.getId());
        assertEquals("Ayman", customerDto.getFirstName());
        assertEquals("Mohamed", customerDto.getLastName());

    }
}
