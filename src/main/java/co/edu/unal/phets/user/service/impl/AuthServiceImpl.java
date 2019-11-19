package co.edu.unal.phets.user.service.impl;

import co.edu.unal.phets.user.model.User;
import co.edu.unal.phets.user.repository.UserRepository;
import co.edu.unal.phets.user.service.AuthService;
import co.edu.unal.phets.user.service.LdapService;
import co.edu.unal.phets.user.service.SessionService;
import co.edu.unal.phets.user.service.UserService;
import co.edu.unal.phets.user.util.BCryptUtil;
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
    private BCryptUtil bCryptUtil;

    @Autowired
    private LdapService ldapService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Override
    public User register(User user) {
        String encryptedPass = bCryptUtil.encryptPassword(user.getPassword());
        user.setPassword(encryptedPass);
        return userService.create(user);
    }

    @Override
    public Optional<String> login(String username, String password) {
        if (ldapService.login(username, password)) {
            Optional<User> userOpt = userRepository.findByUsername(username);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                if (bCryptUtil.validCredentials(user, password)) {
                    return Optional.of(sessionService.getToken(user));
                }
            }
        }
        return Optional.empty();
    }

}
