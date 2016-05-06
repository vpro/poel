package nl.vpro.poel.controller;

import nl.vpro.poel.domain.CurrentUser;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class PoelControllerAdvice {

    @ModelAttribute("user")
    public CurrentUser getCurrentUser(Principal principal) {
        if (principal instanceof CasAuthenticationToken) {
            UserDetails userDetails = ((CasAuthenticationToken)principal).getUserDetails();
            if (userDetails instanceof CurrentUser) {
                return (CurrentUser) userDetails;
            }
        }
        return null;
    }
}
