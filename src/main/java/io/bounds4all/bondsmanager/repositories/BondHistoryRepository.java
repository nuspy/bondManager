package io.bounds4all.bondsmanager.repositories;

import io.bounds4all.bondsmanager.model.BondHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BondHistoryRepository extends JpaRepository<BondHistory, Long> {
}
