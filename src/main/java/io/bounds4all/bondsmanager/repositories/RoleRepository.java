package io.bounds4all.bondsmanager.repositories;

import io.bounds4all.bondsmanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String admin);
}
