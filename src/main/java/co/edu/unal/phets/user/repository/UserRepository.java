package co.edu.unal.phets.user.repository;

import co.edu.unal.phets.user.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author julian
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(@Param("username") String username);

}
