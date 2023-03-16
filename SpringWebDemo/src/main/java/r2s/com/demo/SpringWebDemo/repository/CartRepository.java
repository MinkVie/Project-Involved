package r2s.com.demo.SpringWebDemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import r2s.com.demo.SpringWebDemo.entity.Cart;
import r2s.com.demo.SpringWebDemo.entity.User;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    Optional<Page<Cart>> findAll(Pageable pageable);
}
