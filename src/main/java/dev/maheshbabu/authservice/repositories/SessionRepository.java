package dev.maheshbabu.authservice.repositories;

import dev.maheshbabu.authservice.models.Session;
import dev.maheshbabu.authservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SessionRepository extends JpaRepository<Session,Long> {

    Optional<Session> findByUserAndToken(User user, String token);

    List<Session> findByUser(User user);

    Integer findCountByUser(User user);

}
