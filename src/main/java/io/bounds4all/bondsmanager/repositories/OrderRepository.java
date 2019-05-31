package io.bounds4all.bondsmanager.repositories;

import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.model.Order;
import io.bounds4all.bondsmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser( User user);
}
