package CustomerMS.CustomerMS;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import CustomerMS.Controller.CustomerController;
import CustomerMS.Entity.Customer;
import CustomerMS.Service.CustomerService;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private CustomerService service;

	@Test
	void shouldReturnAllProducts() throws Exception {
		List<Customer> list = List.of(new Customer(), new Customer());

		when(service.getAll()).thenReturn(list);
		mockMvc.perform(get("/customers/getAllCustomers")).andExpect(status().isOk());
	}

}
