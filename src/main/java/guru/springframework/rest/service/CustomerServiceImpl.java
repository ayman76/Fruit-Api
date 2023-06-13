package guru.springframework.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springframework.rest.api.v1.mapper.CustomerMapper;
import guru.springframework.rest.api.v1.model.CustomerDto;
import guru.springframework.rest.domain.Customer;
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
                    customerDto.setCustomerUrl("/api/v1/customers/" + customer.getId());
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
        CustomerDto customerDto = customerMapper.customerToCustomerDto(customerRepo.findById(id).get());
        customerDto.setCustomerUrl("/api/v1/customers/" + customerDto.getId());
        return customerDto;
    }

    @Override
    public CustomerDto createNewCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        Customer savedCustomer = customerRepo.save(customer);

        CustomerDto returnDto = customerMapper.customerToCustomerDto(savedCustomer);
        returnDto.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());

        return returnDto;
    }

    @Override
    public CustomerDto saveCustomerByDto(Long id, CustomerDto customerDto) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        customer.setId(id);
        return saveAndReturnDto(customer);
    }

    private CustomerDto saveAndReturnDto(Customer customer) {
        Customer savedCustomer = customerRepo.save(customer);
        CustomerDto returnCustomer = customerMapper.customerToCustomerDto(savedCustomer);
        returnCustomer.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());

        return returnCustomer;
    }

    @Override
    public CustomerDto patchCustomer(Long id, CustomerDto customerDto) {
        return customerRepo.findById(id).map(
                customer -> {
                    if (customerDto.getFirstName() != null) {
                        customer.setFirstName(customerDto.getFirstName());
                    }
                    if (customerDto.getLastName() != null) {
                        customer.setLastName(customerDto.getLastName());
                    }

                    CustomerDto returnCustomer = customerMapper.customerToCustomerDto(customerRepo.save(customer));
                    returnCustomer.setCustomerUrl("/api/v1/customers/" + returnCustomer.getId());

                    return returnCustomer;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Customer> foundedCustomer = customerRepo.findById(id);
        if (foundedCustomer.isPresent()) {
            customerRepo.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Customer Not Found");
        }

    }

}
