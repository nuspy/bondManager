package io.bounds4all.bondsmanager.repositories;

import io.bounds4all.bondsmanager.model.Bond;
import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface BondRepository extends JpaRepository<Bond, Long> {
    List<Bond> findByEmission(Emission emission);
    List<Bond> findByEmissionAndOrderNotNull(Emission emission);
    Page<Bond> findByEmissionAndOrderNotNull(Emission emission, Pageable pageable);
    @Lock(LockModeType.PESSIMISTIC_READ)
    Page<Bond> findByEmissionAndOrderNull(Emission emission, Pageable pageable);
    List<Bond> findByEmissionAndOrder(Emission emission, Order order);
    List<Object> findByEmissionAndOrderNull(Emission emission);

}
