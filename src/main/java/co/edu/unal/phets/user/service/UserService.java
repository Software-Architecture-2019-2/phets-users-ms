package co.edu.unal.phets.user.service;

import co.edu.unal.phets.user.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author julian
 */
public interface UserService {
    
    public List<User> findAll();
    
    public Optional<User> findByUsername(String username);
    
    public User create(User user);
    
    public User update(String username, User user);
    
    public Optional<User> deleteByUsername(String username);
    
}
