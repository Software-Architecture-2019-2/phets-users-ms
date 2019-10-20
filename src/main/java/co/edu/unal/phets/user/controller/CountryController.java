package co.edu.unal.phets.user.controller;

import co.edu.unal.phets.user.model.Country;
import co.edu.unal.phets.user.service.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author julian
 */
@RestController
@RequestMapping("country")
public class CountryController {

    @Autowired
    private CountryService countryService;
    
    @RequestMapping(method = RequestMethod.GET)
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Country>> findAll() {
        List<Country> countries = countryService.findAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

}
