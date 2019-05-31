package io.bounds4all.bondsmanager.repositories;

import io.bounds4all.bondsmanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String admin);
}
