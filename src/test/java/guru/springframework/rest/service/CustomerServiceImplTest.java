package guru.springframework.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.rest.api.v1.mapper.CustomerMapper;
import guru.springframework.rest.api.v1.model.CustomerDto;
import guru.springframework.rest.domain.Customer;
import guru.springframework.rest.repository.CustomerRepo;

public class CustomerServiceImplTest {

    private static final long ID = 1L;

    private static final String LASTNAME = "Mohamed";

    private static final String FIRSTNAME = "Ayman";

    @Mock
    CustomerRepo customerRepo;

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepo, CustomerMapper.INSTATNCE);
    }

    @Test
    void testGetAllCustomers() {
        // given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepo.findAll()).thenReturn(customers);

        // when
        List<CustomerDto> customerDtos = customerService.getAllCustomers();

        // then
        assertEquals(3, customerDtos.size());
    }

    @Test
    void testGetCustomerByFirstName() {
        // given
        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);
        customer.setId(ID);

        when(customerRepo.findByFirstName(anyString())).thenReturn(customer);

        // when
        CustomerDto customerDto = customerService.getCustomerByFirstName(FIRSTNAME);

        // then
        assertEquals(Long.valueOf(ID), customerDto.getId());
        assertEquals(FIRSTNAME, customerDto.getFirstName());
        assertEquals(LASTNAME, customerDto.getLastName());

    }

    @Test
    void testGetCustomerByLastName() {
        // given
        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);
        customer.setId(ID);

        when(customerRepo.findByLastName(anyString())).thenReturn(customer);

        // when
        CustomerDto customerDto = customerService.getCustomerByLastName(LASTNAME);

        // then
        assertEquals(Long.valueOf(ID), customerDto.getId());
        assertEquals(FIRSTNAME, customerDto.getFirstName());
        assertEquals(LASTNAME, customerDto.getLastName());
    }

    @Test
    void testGetCustomerById() {
        // given
        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);
        customer.setId(ID);

        when(customerRepo.findById(anyLong())).thenReturn(Optional.of(customer));

        // when
        CustomerDto customerDto = customerService.getCustomerById(ID);

        // then
        assertEquals(Long.valueOf(ID), customerDto.getId());
        assertEquals(FIRSTNAME, customerDto.getFirstName());
        assertEquals(LASTNAME, customerDto.getLastName());
    }
}
