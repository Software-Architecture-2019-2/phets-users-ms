package co.edu.unal.phets.user.controller;

import co.edu.unal.phets.user.dto.TokenDTO;
import co.edu.unal.phets.user.dto.TokenValidDTO;
import co.edu.unal.phets.user.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author julian
 */
@RestController
@RequestMapping("session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "validate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TokenValidDTO> validateToken(@RequestBody TokenDTO tokenDto) {
        Boolean valid = sessionService.validateToken(tokenDto.getToken());
        TokenValidDTO dto = new TokenValidDTO(valid);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
