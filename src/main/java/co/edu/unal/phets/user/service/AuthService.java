package co.edu.unal.phets.user.service;

import co.edu.unal.phets.user.model.User;
import java.util.Optional;

/**
 *
 * @author julian
 */
public interface AuthService {
    
    public Optional<String> login(String username, String password);
    
    public User register(User user);
    
}
