package nl.vpro.poel.controller;

import nl.vpro.poel.domain.CurrentUser;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.UserUtil;
import nl.vpro.poel.dto.UserForm;

import nl.vpro.poel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
class UserController {

    private final UserService userService;

    @Autowired
    public UserController( UserService userService ) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    String user(Principal principal, Model model) {
        CurrentUser currentUser = UserUtil.getCurrentUser(principal)
                .orElseThrow(() -> new RuntimeException("No user?!"));
        User user = currentUser.getUser();

        model.addAttribute("updateUser", user);

        return "user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    String updateUser(Principal principal, @ModelAttribute("updateUser") UserForm userForm, BindingResult bindingResult) {

        User updateUser = userForm.getUpdateUser();

        CurrentUser currentUser = UserUtil.getCurrentUser(principal)
                .orElseThrow(() -> new RuntimeException("No user?!"));
        User user = currentUser.getUser();

        if ( updateUser.getUsername().equals( user.getUsername() ) ) {
            userService.updateUser(updateUser);
        }

        return "redirect:/user";
    }
}
