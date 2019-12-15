package co.edu.unal.phets.user.service;

public interface LdapService {

    public Boolean login(String user, String password);

    public Boolean register(String firstName, String lastName, String username, String password);
    
}