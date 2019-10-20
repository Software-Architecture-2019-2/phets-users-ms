package co.edu.unal.phets.user.service.impl;

import co.edu.unal.phets.user.model.User;
import co.edu.unal.phets.user.repository.UserRepository;
import co.edu.unal.phets.user.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author julian
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public User create(User user) {
        user.setCreation(new Date());
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    @Transactional
    public User update(String username, User user) {
        Optional<User> savedOpt = userRepository.findByUsername(username);
        if (savedOpt.isPresent()) {
            User saved = savedOpt.get();
            BeanUtils.copyProperties(user, saved, "creation", "confirmed");
            User updated = userRepository.save(saved);
            return updated;
        }
        return null;
    }

    @Override
    @Transactional
    public Optional<User> deleteByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            userRepository.delete(userOpt.get());
        }
        return userOpt;
    }

}
