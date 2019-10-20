package co.edu.unal.phets.user.service;

import co.edu.unal.phets.user.model.User;


/**
 *
 * @author julian
 */
public interface SessionService {

    public String getToken(User user);
    
    public Boolean validateToken(String token);
    
    public void destroyToken(String token);
    
}
