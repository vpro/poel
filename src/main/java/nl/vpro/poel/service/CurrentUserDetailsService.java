package nl.vpro.poel.service;

import nl.vpro.poel.domain.CurrentUser;
import nl.vpro.poel.domain.User;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.userdetails.AbstractCasAssertionUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService extends AbstractCasAssertionUserDetailsService {

    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected UserDetails loadUserDetails(Assertion assertion) {
        String username = assertion.getPrincipal().getName();
        User user = userService.getOrCreate(username);
        return new CurrentUser(user);
    }
}