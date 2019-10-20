package co.edu.unal.phets.user.repository;

import co.edu.unal.phets.user.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author julian
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
