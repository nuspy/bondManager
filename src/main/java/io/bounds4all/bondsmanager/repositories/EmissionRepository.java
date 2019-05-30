package io.bounds4all.bondsmanager.repositories;

import io.bounds4all.bondsmanager.model.Emission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmissionRepository extends JpaRepository<Emission, Long> {
}
