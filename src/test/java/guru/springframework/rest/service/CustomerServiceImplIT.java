package guru.springframework.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import guru.springframework.rest.api.v1.mapper.CustomerMapper;
import guru.springframework.rest.api.v1.model.CustomerDto;
import guru.springframework.rest.bootstrap.Bootstrap;
import guru.springframework.rest.domain.Customer;
import guru.springframework.rest.repository.CategoryRepo;
import guru.springframework.rest.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
public class CustomerServiceImplIT {

    CustomerService customerService;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @BeforeEach
    void setUp() throws Exception {
        log.error("Loading Customer Data");
        log.error("" + customerRepo.findAll().size());

        // setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepo, customerRepo);
        bootstrap.run();

        customerService = new CustomerServiceImpl(customerRepo, CustomerMapper.INSTATNCE);
    }

    private Long getCustomerIdValue() {
        List<Customer> customers = customerRepo.findAll();

        log.error("Customers Found" + customers.size());

        // return first Id
        return customers.get(0).getId();
    }

    @Test
    void testPatchCustomerUpdateFirstName() {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepo.getReferenceById(id);
        assertNotNull(originalCustomer);

        // saved Original firstName
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(updatedName);

        customerService.patchCustomer(id, customerDto);

        Customer updatedCustomer = customerRepo.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getFirstName());
        assertNotEquals(originalFirstName, updatedCustomer.getFirstName());
        assertEquals(originalLastName, updatedCustomer.getLastName());
    }

    @Test
    void testPatchCustomerUpdateLastNameName() {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepo.getReferenceById(id);
        assertNotNull(originalCustomer);

        // saved Original firstName
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDto customerDto = new CustomerDto();
        customerDto.setLastName(updatedName);

        customerService.patchCustomer(id, customerDto);

        Customer updatedCustomer = customerRepo.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getLastName());
        assertNotEquals(originalLastName, updatedCustomer.getLastName());
        assertEquals(originalFirstName, updatedCustomer.getFirstName());
    }

}
