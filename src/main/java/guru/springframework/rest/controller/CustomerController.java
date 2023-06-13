package guru.springframework.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.rest.api.v1.model.CustomerDto;
import guru.springframework.rest.api.v1.model.CustomerListDto;
import guru.springframework.rest.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDto> getAllCustomers() {
        return new ResponseEntity<CustomerListDto>(new CustomerListDto(customerService.getAllCustomers()),
                HttpStatus.OK);
    }

    @GetMapping("firstName/{firstName}")
    public ResponseEntity<CustomerDto> getCustomerByFirstName(@PathVariable String firstName) {
        return new ResponseEntity<CustomerDto>(customerService.getCustomerByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("lastName/{lastName}")
    public ResponseEntity<CustomerDto> getCustomerByLastName(@PathVariable String lastName) {
        return new ResponseEntity<CustomerDto>(customerService.getCustomerByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> getCutomerById(@PathVariable Long id) {
        return new ResponseEntity<CustomerDto>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<CustomerDto>(customerService.createNewCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<CustomerDto>(customerService.saveCustomerByDto(id, customerDto), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<CustomerDto>(customerService.patchCustomer(id, customerDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
