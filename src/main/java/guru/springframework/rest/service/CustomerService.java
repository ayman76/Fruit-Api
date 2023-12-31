package guru.springframework.rest.service;

import java.util.List;

import guru.springframework.rest.api.v1.model.CustomerDto;

public interface CustomerService {
    
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerByFirstName(String firstName);
    CustomerDto getCustomerByLastName(String lastName);
    CustomerDto getCustomerById(Long id);
    CustomerDto createNewCustomer(CustomerDto customerDto);
    CustomerDto saveCustomerByDto(Long id, CustomerDto customerDto);
    CustomerDto patchCustomer(Long id, CustomerDto customerDto);
    void deleteById(Long id);

}
