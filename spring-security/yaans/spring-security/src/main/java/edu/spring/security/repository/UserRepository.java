package edu.spring.security.repository;

import edu.spring.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {
//    Optional<User> save(User user);
    int countById(String id);
}
