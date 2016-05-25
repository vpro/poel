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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/user")
class UserController {

    private final UserService userService;

    @Autowired
    public UserController( UserService userService ) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String user(Principal principal, Model model) {
        return "user";
    }

    @RequestMapping(method = RequestMethod.POST)
    String updateUser(Principal principal, @ModelAttribute("updateUser") UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        User user = getUser(principal);
        userService.updateUser(user, userForm);
        redirectAttributes.addFlashAttribute("flash", "Profielgegevens opgeslagen");
        return "redirect:/user";
    }

    private User getUser(Principal principal) {
        CurrentUser currentUser = UserUtil.getCurrentUser(principal)
                .orElseThrow(() -> new RuntimeException("No user?!"));
        return currentUser.getUser();
    }
}
