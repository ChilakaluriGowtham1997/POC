package ProductMS.ProductMS;

import static org.mockito.Mockito.when;

import java.io.ObjectInputFilter.Status;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ProductMS.Controller.productController;
import ProductMS.Entity.Product;
import ProductMS.Service.ProductService;

@WebMvcTest(productController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private ProductService service;
	
	@Test
	void shouldReturnAllProducts() throws Exception{
		List<Product> list= List.of(new Product(), new Product());
		
		when(service.getAll()).thenReturn(list);
		mockMvc.perform(get("/products/getAllProducts")).andExpect(status().isOk());
	}

}
