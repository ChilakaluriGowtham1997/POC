package ProductMS.Controller;

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

import ProductMS.Entity.Product;
import ProductMS.Service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
public class productController {
	@Autowired
	private ProductService service;

	@PostMapping("/addProduct")
	public ResponseEntity<Product> create(@RequestBody Product product) {
		return ResponseEntity.ok(service.save(product));

	}

	@GetMapping("getAllProducts")
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable long id, @RequestBody Product product) {
		return ResponseEntity.ok(service.update(id, product));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		service.delete(id);
		return ResponseEntity.ok("product is deleted successfully");
	}

	@GetMapping("/test")
	String test() {
		return "Hello World";
	}

}
