package store.politech.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.politech.user.entity.Role;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
