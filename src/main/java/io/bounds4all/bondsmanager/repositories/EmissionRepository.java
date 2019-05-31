package io.bounds4all.bondsmanager.repositories;

import io.bounds4all.bondsmanager.model.Emission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmissionRepository extends JpaRepository<Emission, Long> {
}
