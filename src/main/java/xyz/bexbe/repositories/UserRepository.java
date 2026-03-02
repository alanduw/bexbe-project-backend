package xyz.bexbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bexbe.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
