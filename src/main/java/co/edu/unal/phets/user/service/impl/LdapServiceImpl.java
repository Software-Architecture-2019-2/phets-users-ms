package co.edu.unal.phets.user.service.impl;

import co.edu.unal.phets.user.service.LdapService;
import com.novell.ldap.*;
import java.io.UnsupportedEncodingException;
import org.springframework.stereotype.Service;

@Service
public class LdapServiceImpl implements LdapService {

    private final String CONTAINER_NAME = "dc=phets,dc=com";
    private final LDAPConnection LC = new LDAPConnection();

    @Override
    public Boolean login(String user, String password) {
        if (connect()) {
            return validateUser(user, password);
        }
        return false;
    }

    @Override
    public Boolean register(String givenName, String surname, String username, String password) {
        LDAPAttributeSet attributeSet = new LDAPAttributeSet();

        attributeSet.add(new LDAPAttribute(
                "objectclass", new String("inetOrgPerson")));
        attributeSet.add(new LDAPAttribute("cn", username));
        attributeSet.add(new LDAPAttribute("givenname", givenName));
        attributeSet.add(new LDAPAttribute("sn", surname));
        attributeSet.add(new LDAPAttribute("userpassword", password));

        String dn = "cn=" + username + ",ou=phets," + CONTAINER_NAME;
        LDAPEntry entry = new LDAPEntry(dn, attributeSet);

        if (connect()) {
            try {
                LC.add(entry);
                return true;
            } catch (LDAPException ex) {
                return false;
            }
        }

        return false;
    }

    private Boolean connect() {
        if (LC.isConnected()) {
            return true;
        }

        String ldapHost = System.getenv("LDAP_IP");
        String dn = "cn=admin," + CONTAINER_NAME;
        String password = "admin";

        int ldapPort = LDAPConnection.DEFAULT_PORT;
        int ldapVersion = LDAPConnection.LDAP_V3;

        try {
            LC.connect(ldapHost, ldapPort);
            LC.bind(ldapVersion, dn, password.getBytes("UTF8"));
            return true;
        } catch (LDAPException | UnsupportedEncodingException ex) {
            return false;
        }
    }

    private Boolean validateUser(String username, String password) {
        String dn = "cn=" + username + ",ou=phets," + CONTAINER_NAME;
        try {
            LC.bind(LDAPConnection.LDAP_V3, dn, password.getBytes("UTF8"));
            return true;
        } catch (LDAPException | UnsupportedEncodingException ex) {
            return false;
        }
    }
}
