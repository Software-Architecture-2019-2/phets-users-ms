package co.edu.unal.phets.user.service.impl;

import co.edu.unal.phets.user.model.Country;
import co.edu.unal.phets.user.repository.CountryRepository;
import co.edu.unal.phets.user.service.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author julian
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;
    
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public void saveCountries(List<Country> countries) {
        countryRepository.saveAll(countries);
    }

}
