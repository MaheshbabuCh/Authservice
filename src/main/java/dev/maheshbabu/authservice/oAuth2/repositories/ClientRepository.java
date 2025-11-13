package dev.maheshbabu.authservice.oAuth2.repositories;

import java.util.Optional;


import dev.maheshbabu.authservice.oAuth2.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}