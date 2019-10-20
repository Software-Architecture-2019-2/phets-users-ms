package co.edu.unal.phets.user.service.impl;

import co.edu.unal.phets.user.model.User;
import co.edu.unal.phets.user.repository.UserRepository;
import co.edu.unal.phets.user.service.AuthService;
import co.edu.unal.phets.user.service.SessionService;
import co.edu.unal.phets.user.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author julian
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User register(User user) {
        String encryptedPass = encryptPassword(user.getPassword());
        user.setPassword(encryptedPass);
        System.out.println(encryptedPass.length());
        return userService.create(user);
    }

    @Override
    public Optional<String> login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (validCredentials(user, password)) {
                return Optional.of(sessionService.getToken(user));
            }
        }
        return Optional.empty();
    }

    private String encryptPassword(String password) {
        return encoder.encode(password);
    }

    private Boolean validCredentials(User user, String password) {
        return encoder.matches(password, user.getPassword());
    }
}
