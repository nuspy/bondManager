package io.bounds4all.bondsmanager.repositories;

import io.bounds4all.bondsmanager.model.BondHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondHistoryRepository extends JpaRepository<BondHistory, Long> {
}
