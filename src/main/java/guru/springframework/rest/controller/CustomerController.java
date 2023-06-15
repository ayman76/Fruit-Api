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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "This is my Customer Controller")
@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This will get list of customers.", notes = "These are some notes about the API")
    @GetMapping
    public ResponseEntity<CustomerListDto> getAllCustomers() {
        return new ResponseEntity<CustomerListDto>(new CustomerListDto(customerService.getAllCustomers()),
                HttpStatus.OK);
    }

    @ApiOperation(value = "This will get customer object with given first name", notes = "These are some notes about the API")
    @GetMapping("firstName/{firstName}")
    public ResponseEntity<CustomerDto> getCustomerByFirstName(@PathVariable String firstName) {
        return new ResponseEntity<CustomerDto>(customerService.getCustomerByFirstName(firstName), HttpStatus.OK);
    }

    @ApiOperation(value = "This will get customer object with given last name", notes = "These are some notes about the API")
    @GetMapping("lastName/{lastName}")
    public ResponseEntity<CustomerDto> getCustomerByLastName(@PathVariable String lastName) {
        return new ResponseEntity<CustomerDto>(customerService.getCustomerByLastName(lastName), HttpStatus.OK);
    }

    @ApiOperation(value = "This will get customer object with given id", notes = "These are some notes about the API")
    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> getCutomerById(@PathVariable Long id) {
        return new ResponseEntity<CustomerDto>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "This will create new customer", notes = "These are some notes about the API")
    @PostMapping()
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<CustomerDto>(customerService.createNewCustomer(customerDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "This will update customer object with given id", notes = "These are some notes about the API")
    @PutMapping("{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<CustomerDto>(customerService.saveCustomerByDto(id, customerDto), HttpStatus.OK);
    }

    @ApiOperation(value = "This will update customer object with given id", notes = "These are some notes about the API")
    @PatchMapping("{id}")
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<CustomerDto>(customerService.patchCustomer(id, customerDto), HttpStatus.OK);
    }

    @ApiOperation(value = "This will delete customer object with given id", notes = "These are some notes about the API")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
