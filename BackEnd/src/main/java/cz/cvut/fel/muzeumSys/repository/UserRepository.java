package cz.cvut.fel.muzeumSys.repository;

import cz.cvut.fel.muzeumSys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>   {
    Optional<User> findByEmail(String email);
}
