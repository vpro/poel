package nl.vpro.poel.controller;

import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    @RequestMapping("/user")
    String user() {
        return "Hallo, " + getUsername().orElse("onbekende vreemdeling");
    }

    @RequestMapping("/admin")
    String admin() {
        return "Alleen voor admins!";
    }

    private Optional<String> getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof CasAuthenticationToken) {
            CasAuthenticationToken casAuthenticationToken = (CasAuthenticationToken) authentication;
            return Optional.of(casAuthenticationToken.getUserDetails().getUsername());
        }
        return Optional.empty();
    }
}
