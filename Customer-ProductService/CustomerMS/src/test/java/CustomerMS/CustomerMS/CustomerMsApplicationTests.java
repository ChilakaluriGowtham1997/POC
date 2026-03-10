package CustomerMS.CustomerMS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import CustomerMS.Entity.Customer;
import CustomerMS.Repository.CustomerRepository;
import CustomerMS.Service.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerMsApplicationTests {
	
	@Mock
	private CustomerRepository repository;
	
	@InjectMocks
	private CustomerService service;
	
	@Autowired
	private RestTemplate restTemplate;
	@Test
	void shouldSaveCustomer() {
		Customer customer = new Customer();
		customer.setName("Gowtham");
		customer.setEmail("gowtham@email.com");
		when(repository.save(customer)).thenReturn(customer);

		Customer saved = service.save(customer);

		assertNotNull(saved);
		assertEquals("Gowtham", saved.getName());
		assertEquals("gowtham@email.com", saved.getEmail());
		verify(repository, times(1)).save(customer);

	}
	
	@Test
	void shouldReturnAllCustomers() {
		List<Customer> list = List.of(new Customer(), new Customer());
		when(repository.findAll()).thenReturn(list);
		List<Customer> result = service.getAll();
		assertEquals(2, result.size());
		verify(repository, times(1)).findAll();
	}
	
	@Test
	void shouldDeleteCustomer() {
		long id = 1l;
		when(repository.existsById(id)).thenReturn(true);
		service.delete(id);
		verify(repository).deleteById(id);
	}
	
	@Test
	void shouldCallProductService() {
		when(restTemplate.getForObject("http://localhost:8082/products/getAllProducts", List.class)).thenReturn(List.of());
		List<Object> result=service.getAllProducts();
		assertNotNull(result);
		verify(restTemplate).getForObject("http://localhost:8082/products/getAllProducts", List.class);
	}
	
	

}
