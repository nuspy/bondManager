package io.bounds4all.bondsmanager.repositories;

import io.bounds4all.bondsmanager.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
