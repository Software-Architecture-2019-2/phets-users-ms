package co.edu.unal.phets.user.repository;

import co.edu.unal.phets.user.model.Session;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author julian
 */
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    public Optional<Session> findByToken(String token);

}
