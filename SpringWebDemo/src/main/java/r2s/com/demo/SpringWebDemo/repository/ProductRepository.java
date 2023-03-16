package r2s.com.demo.SpringWebDemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import r2s.com.demo.SpringWebDemo.entity.Product;
import r2s.com.demo.SpringWebDemo.entity.User;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Page<Product>> findAll(Pageable pageable);
}
