package co.edu.unal.phets.user.service.impl;

import co.edu.unal.phets.user.service.LdapService;
import com.novell.ldap.*;
import java.io.UnsupportedEncodingException;
import org.springframework.stereotype.Service;

@Service
public class LdapServiceImpl implements LdapService {

    private LDAPConnection lc = new LDAPConnection();

    @Override
    public Boolean login(String user, String password) {
        if (connect()) {
            return validateUser(user, password);
        }
        return false;
    }

    private Boolean connect() {

        String ldapHost = "host.docker.internal";
        String dn = "cn=admin,dc=phets,dc=com";
        String password = "admin";

        int ldapPort = LDAPConnection.DEFAULT_PORT;
        int ldapVersion = LDAPConnection.LDAP_V3;

        try {
            lc.connect(ldapHost, ldapPort);
            System.out.println("Connecting to LDAP Server...");
            lc.bind(ldapVersion, dn, password.getBytes("UTF8"));
            System.out.println("Authenticated in LDAP Server...");
            return true;
        } catch (LDAPException | UnsupportedEncodingException ex) {
            System.out.println("ERROR when connecting to LDAP Server...");
            return false;
        }
    }

    private Boolean validateUser(String username, String password) {
        String dn = "cn=" + username + ",ou=phets,dc=phets,dc=com";
        try {
            lc.bind(LDAPConnection.LDAP_V3, dn, password.getBytes());
            return true;
        } catch (LDAPException ex) {
            return false;
        }
    }
}
