package CustomerMS.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import CustomerMS.Entity.Customer;
import CustomerMS.ExceptionHandling.ResourceNotFoundException;
import CustomerMS.Repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	@Autowired
	private RestTemplate restTemplate;
	
	public Customer save(Customer customer) {
		return repository.save(customer);
		
	}
	
	public List<Customer> getAll(){
		return repository.findAll();
	}
	
	public Customer update(long id, Customer updatedProduct) {
		Customer existing = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with id" + id));
		existing.setName(updatedProduct.getName());
		existing.setEmail(updatedProduct.getEmail());

		return repository.save(existing);

	}
	
	public void delete(long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Customer not found with the id" + id);
		}
		repository.deleteById(id);
	}
	
	public List<Object> getAllProducts(){
		return restTemplate.getForObject("http://localhost:8082/products/getAllProducts", List.class);
	}

}
