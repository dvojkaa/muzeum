package cz.cvut.fel.muzeumSys.repository;

import cz.cvut.fel.muzeumSys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary extends JpaRepository<User, Long> {
}
