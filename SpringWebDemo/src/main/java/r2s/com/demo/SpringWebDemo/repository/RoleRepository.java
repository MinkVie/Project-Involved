package r2s.com.demo.SpringWebDemo.repository;

import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import r2s.com.demo.SpringWebDemo.entity.RoleUser;
import r2s.com.demo.SpringWebDemo.entity.User;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleUser, Integer> {


    Optional<Page<User>> findAll(Pageable pageable);

}