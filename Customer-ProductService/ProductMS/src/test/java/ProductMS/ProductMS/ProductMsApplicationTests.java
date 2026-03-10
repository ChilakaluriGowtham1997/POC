package ProductMS.ProductMS;

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

import ProductMS.Entity.Product;
import ProductMS.Repository.ProductRepo;
import ProductMS.Service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductMsApplicationTests {
	@Mock
	private ProductRepo repository;
	
	@InjectMocks
	private ProductService service;
	
    @Test
	void shouldSaveProduct() {
		Product product = new Product();
		product.setProductName("Laptop");
		product.setProductPrice(2000.0);
		when(repository.save(product)).thenReturn(product);

		Product saved = service.save(product);

		assertNotNull(saved);
		assertEquals("Laptop", saved.getProductName());
		assertEquals(2000.0, saved.getProductPrice());
		verify(repository, times(1)).save(product);

	}
    
    @Test
    void shouldReturnAllProducts() {
    	List<Product> list = List.of(new Product(), new Product());
    	when(repository.findAll()).thenReturn(list);
    	List<Product> result= service.getAll();
    	assertEquals(2, result.size());
    	verify(repository, times(1)).findAll();
    }
    
    @Test
    void shouldDeleteProduct() {
    	long id=1l;
    	when(repository.existsById(id)).thenReturn(true);
    	service.delete(id);
    	verify(repository).deleteById(id);
    }
	

}
