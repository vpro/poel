package nl.vpro.poel.security;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.security.cas.userdetails.AbstractCasAssertionUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

class PoelUserDetailsService extends AbstractCasAssertionUserDetailsService {

    private static final String NO_PASSWORD = "NO_PASSWORD";

    @Override
    protected UserDetails loadUserDetails(Assertion assertion) {
        AttributePrincipal principal = assertion.getPrincipal();
        String username = principal.getName();

        // TODO: Check if user has an account for this application in this application's database

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // Everybody is a user!
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // TODO: Load extra roles from database?
        if (username.equals("N.Breunese@vpro.nl")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(username, NO_PASSWORD, authorities); // TODO: Create own extension of User with extra fields?
    }
}