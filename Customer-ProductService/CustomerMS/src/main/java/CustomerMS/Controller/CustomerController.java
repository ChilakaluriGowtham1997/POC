package CustomerMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CustomerMS.Entity.Customer;
import CustomerMS.Service.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> create(@RequestBody Customer customer){
		return ResponseEntity.ok(service.save(customer));
	}
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable long id, @RequestBody Customer customer) {
		return ResponseEntity.ok(service.update(id, customer));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		service.delete(id);
		return ResponseEntity.ok("product is deleted successfully");
	}
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Object>> getAllProducts(){
		return ResponseEntity.ok(service.getAllProducts());
	}
	

}
