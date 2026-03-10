package ProductMS.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProductMS.Entity.Product;
import ProductMS.Repository.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
	@Autowired
	private ProductRepo repository;
	
	public Product save(Product product) {
		return repository.save(product);
	}
	
	public List<Product> getAll(){
		return repository.findAll();
		
	}

	public Product update(long id, Product updatedProduct) {
		Product existing = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id" + id));
		existing.setProductName(updatedProduct.getProductName());
		existing.setProductPrice(updatedProduct.getProductPrice());

		return repository.save(existing);

	}
	
	public void delete(long id) {
		if(!repository.existsById(id)) {
			throw new RuntimeException("product not found with the id" + id);
		}
		repository.deleteById(id);
	}

}
