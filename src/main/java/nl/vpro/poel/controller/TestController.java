package nl.vpro.poel.controller;

import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/user")
    String user() {
        return "user";
    }

    @RequestMapping("/admin")
    String admin() {
        return "admin";
    }

    @ModelAttribute("username")
    public String username() {
        // TODO: Is dit gedoe allemaal nodig om in een template te weten wie er ingelogd is? Kan dat niet makkelijker?
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof CasAuthenticationToken) {
            CasAuthenticationToken casAuthenticationToken = (CasAuthenticationToken) authentication;
            return casAuthenticationToken.getUserDetails().getUsername();
        }
        return "";
    }
}
