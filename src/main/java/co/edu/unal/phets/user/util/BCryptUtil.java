package co.edu.unal.phets.user.util;

import co.edu.unal.phets.user.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptUtil {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    public String encryptPassword(String password) {
        return encoder.encode(password);
    }

    public Boolean validCredentials(User user, String password) {
        return encoder.matches(password, user.getPassword());
    }
}
