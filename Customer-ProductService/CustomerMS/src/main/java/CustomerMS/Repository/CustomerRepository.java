package CustomerMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CustomerMS.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
