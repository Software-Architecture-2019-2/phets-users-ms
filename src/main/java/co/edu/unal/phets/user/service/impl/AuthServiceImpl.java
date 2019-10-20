package co.edu.unal.phets.user.service.impl;

import co.edu.unal.phets.user.model.User;
import co.edu.unal.phets.user.repository.UserRepository;
import co.edu.unal.phets.user.service.AuthService;
import co.edu.unal.phets.user.service.SessionService;
import co.edu.unal.phets.user.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public User register(User user) {
        String encryptedPass = encryptPassword(user.getPassword());
        user.setPassword(encryptedPass);
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
        return password + "-";
    }

    private Boolean validCredentials(User user, String password) {
        String encryptedPass = encryptPassword(password);
        return user.getPassword().equals(encryptedPass);
    }
}
