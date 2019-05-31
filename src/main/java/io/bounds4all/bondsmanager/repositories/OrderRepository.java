package io.bounds4all.bondsmanager.repositories;

import io.bounds4all.bondsmanager.model.Order;
import io.bounds4all.bondsmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser( User user);
}
