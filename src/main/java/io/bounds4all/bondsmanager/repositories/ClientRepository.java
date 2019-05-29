package io.bounds4all.bondsmanager.repositories;

import io.bounds4all.bondsmanager.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByUserName(String userName);

    Optional<Client> findByToken(String token);
}
