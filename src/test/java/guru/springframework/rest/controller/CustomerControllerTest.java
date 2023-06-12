package guru.springframework.rest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.rest.api.v1.model.CustomerDto;
import guru.springframework.rest.service.CustomerService;

public class CustomerControllerTest extends AbstractRestControllerTest {

    private static final long ID = 1L;

    private static final String LASTNAME = "Mohamed";

    private static final String FIRSTNAME = "Ayman";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testGetAllCustomers() throws Exception {
        // given
        List<CustomerDto> customers = Arrays.asList(new CustomerDto(), new CustomerDto(), new CustomerDto());

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers", Matchers.hasSize(3)));

    }

    @Test
    void testGetCustomerByFirstName() throws Exception {
        // given
        CustomerDto customer = new CustomerDto();
        customer.setId(ID);
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        when(customerService.getCustomerByFirstName(anyString())).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/firstName/Ayman")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.equalTo(FIRSTNAME)));
    }

    @Test
    void testGetCustomerByLastName() throws Exception {
        // given
        CustomerDto customer = new CustomerDto();
        customer.setId(ID);
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        when(customerService.getCustomerByLastName(anyString())).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/lastName/Mohamed")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.equalTo(LASTNAME)));
    }

    @Test
    void testGetCutomerById() throws Exception {
        // given
        CustomerDto customer = new CustomerDto();
        customer.setId(ID);
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)));
    }

    @Test
    void testCreateNewCustomer() throws Exception {
        // given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(FIRSTNAME);
        customerDto.setLastName(LASTNAME);

        CustomerDto returnCustomer = new CustomerDto();
        returnCustomer.setId(ID);
        returnCustomer.setFirstName(FIRSTNAME);
        returnCustomer.setLastName(LASTNAME);
        returnCustomer.setCustomerUrl("/api/v1/customers/1");

        when(customerService.createNewCustomer(any(CustomerDto.class))).thenReturn(returnCustomer);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.equalTo(FIRSTNAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerUrl", Matchers.equalTo("/api/v1/customers/1")));

    }

    @Test
    void testUpdateCustomer() throws Exception {
        // given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(FIRSTNAME);
        customerDto.setLastName(LASTNAME);

        CustomerDto returnDto = new CustomerDto();
        returnDto.setFirstName(FIRSTNAME);
        returnDto.setLastName(LASTNAME);
        returnDto.setId(ID);
        returnDto.setCustomerUrl("/api/v1/customers/1");

        when(customerService.saveCustomerByDto(anyLong(), any(CustomerDto.class))).thenReturn(returnDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.equalTo(FIRSTNAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.equalTo(LASTNAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerUrl", Matchers.equalTo("/api/v1/customers/1")));
    }
}
