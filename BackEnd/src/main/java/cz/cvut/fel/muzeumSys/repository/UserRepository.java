package cz.cvut.fel.muzeumSys.repository;

import cz.cvut.fel.muzeumSys.model.User;
import cz.cvut.fel.muzeumSys.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);
}
