package co.edu.unal.phets.user.controller;

import co.edu.unal.phets.user.dto.LoginInDTO;
import co.edu.unal.phets.user.dto.TokenDTO;
import co.edu.unal.phets.user.model.User;
import co.edu.unal.phets.user.service.AuthService;
import java.util.Optional;
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
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> register(@RequestBody User user) {
        User saved = authService.register(user);
        if (saved == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TokenDTO> login(@RequestBody LoginInDTO inDto) {
        Optional<String> tokenOpt = authService.login(inDto.getUsername(), inDto.getPassword());
        if (tokenOpt.isPresent()) {
            String token = tokenOpt.get();
            TokenDTO outDto = new TokenDTO(token);
            return new ResponseEntity<>(outDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
