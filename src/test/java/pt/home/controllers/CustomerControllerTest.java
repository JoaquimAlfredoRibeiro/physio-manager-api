package pt.home.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.home.api.v1.model.CustomerDTO;
import pt.home.controllers.v1.CustomerController;
import pt.home.services.CustomerService;
import pt.home.services.ResourceNotFoundException;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pt.home.controllers.AbstractRestControllerTest.asJsonString;

public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testGetAllCustomers() throws Exception {

        //given
        CustomerDTO customer1 = CustomerDTO.builder().fullName("John Doe").customerUrl(CustomerController.BASE_URL + "/1").build();
        CustomerDTO customer2 = CustomerDTO.builder().fullName("Jane Buck").customerUrl(CustomerController.BASE_URL + "/2").build();

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get(CustomerController.BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception {

        //given
        CustomerDTO customer1 = CustomerDTO.builder().fullName("John Doe").customerUrl(CustomerController.BASE_URL + "/1").build();

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        //then
        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", equalTo("John Doe")));
    }

    @Test
    public void testCreateNewCustomer() throws Exception {
        //given
        CustomerDTO customer = CustomerDTO.builder().fullName("John Doe").build();
        CustomerDTO returnDTO = CustomerDTO.builder().fullName(customer.getFullName()).customerUrl(CustomerController.BASE_URL + "/1").build();

        when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);

        //then
        mockMvc.perform(post(CustomerController.BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName", equalTo("John Doe")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        //given
        CustomerDTO customer = CustomerDTO.builder().fullName("John Doe").build();
        CustomerDTO returnDTO = CustomerDTO.builder().fullName(customer.getFullName()).customerUrl(CustomerController.BASE_URL + "/1").build();

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(put(CustomerController.BASE_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", equalTo("John Doe")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {

        mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/111")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
