package co.edu.unal.phets.user.service;

import co.edu.unal.phets.user.model.Country;
import java.util.List;

/**
 *
 * @author julian
 */
public interface CountryService {
    
    public List<Country> findAll();
    
    public void saveCountries(List<Country> countries);
    
}
