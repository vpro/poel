package nl.vpro.poel.controller;

import nl.vpro.poel.domain.CurrentUser;
import nl.vpro.poel.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/user")
    String user() {
        return "user";
    }

    @RequestMapping("/admin")
    String admin() {
        return "admin";
    }

    @RequestMapping("/form")
    String form() {
        return "form";
    }

    @RequestMapping("/ranking")
    String ranking() {
        return "ranking";
    }

    @ModelAttribute("user")
    public User user(Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        return currentUser.getUser();
    }
}
