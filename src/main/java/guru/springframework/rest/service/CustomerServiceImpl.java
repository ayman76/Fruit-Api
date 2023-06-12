package guru.springframework.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springframework.rest.api.v1.mapper.CustomerMapper;
import guru.springframework.rest.api.v1.model.CustomerDto;
import guru.springframework.rest.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepo customerRepo, CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepo.findAll()
                .stream()
                .map(customer -> {
                    CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
                    customerDto.setCustomerUrl("/api/v1/customer/" + customer.getId());
                    return customerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerByFirstName(String firstName) {
        return customerMapper.customerToCustomerDto(customerRepo.findByFirstName(firstName));
    }

    @Override
    public CustomerDto getCustomerByLastName(String lastName) {
        return customerMapper.customerToCustomerDto(customerRepo.findByLastName(lastName));
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return customerMapper.customerToCustomerDto(customerRepo.findById(id).get());
    }

}
