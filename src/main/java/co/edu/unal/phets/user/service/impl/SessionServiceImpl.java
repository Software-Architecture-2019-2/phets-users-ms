package co.edu.unal.phets.user.service.impl;

import co.edu.unal.phets.user.model.Session;
import co.edu.unal.phets.user.model.User;
import co.edu.unal.phets.user.repository.SessionRepository;
import co.edu.unal.phets.user.service.SessionService;
import co.edu.unal.phets.user.util.JwtUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author julian
 */
@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private SessionRepository sessionRepository;
    
    @Override
    @Transactional
    public String getToken(User user) {
        String token = jwtTokenUtil.generateToken(user);
        Session session = new Session(token, user);
        sessionRepository.save(session);
        return token;
    }

    @Override
    @Transactional
    public Boolean validateToken(String token) {
        Boolean valid = false;
        Optional<Session> sessionOpt = sessionRepository.findByToken(token);
        if (sessionOpt.isPresent()) {
            Session session = sessionOpt.get();
            if (session.getValid()) {
                valid = jwtTokenUtil.validateToken(token, session.getUser().getUsername());
                if (!valid) {
                    blacklist(session);
                }
            }
        }
        return valid;
    }

    @Override
    public void destroyToken(String token) {
        Optional<Session> sessionOpt = sessionRepository.findByToken(token);
        if (sessionOpt.isPresent()) {
            Session session = sessionOpt.get();
            blacklist(session);
        }
    }

    @Transactional
    public void blacklist(Session session) {
        session.setValid(false);
        sessionRepository.save(session);
    }
}
