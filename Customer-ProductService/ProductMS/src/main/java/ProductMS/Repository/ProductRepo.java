package ProductMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ProductMS.Entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>  {

}
