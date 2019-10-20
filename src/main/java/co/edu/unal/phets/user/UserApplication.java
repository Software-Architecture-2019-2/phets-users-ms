package co.edu.unal.phets.user;

import co.edu.unal.phets.user.model.Country;
import co.edu.unal.phets.user.service.CountryService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author julian
 */
@SpringBootApplication
@EnableJpaRepositories("co.edu.unal.phets.user.repository")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CountryService countryService) {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(1L, "Colombia"));
        countries.add(new Country(2L, "Ecuador"));
        countries.add(new Country(3L, "México"));
        countryService.saveCountries(countries);
        return null;
    }
}
