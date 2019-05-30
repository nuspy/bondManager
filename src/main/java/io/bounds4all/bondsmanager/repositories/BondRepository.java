package io.bounds4all.bondsmanager.repositories;

import io.bounds4all.bondsmanager.model.Bond;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BondRepository extends JpaRepository<Bond, Long> {
}
