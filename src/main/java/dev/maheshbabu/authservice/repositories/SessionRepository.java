package dev.maheshbabu.authservice.repositories;

import dev.maheshbabu.authservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {

}
