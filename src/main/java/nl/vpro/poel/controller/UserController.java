package nl.vpro.poel.controller;

import nl.vpro.poel.domain.CurrentUser;
import nl.vpro.poel.domain.Message;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.UserUtil;

import nl.vpro.poel.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
class UserController {

    private final MessageService messageService;

    @Autowired
    public UserController(MessageService messageService) {
        this.messageService = messageService;
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    String user(Principal principal, Model model) {
        CurrentUser currentUser = UserUtil.getCurrentUser(principal)
                .orElseThrow(() -> new RuntimeException("No user?!"));


        Message userMessage = messageService.findByKey("/user").orElse(null);

        if ( userMessage != null ) {
            model.addAttribute("message", userMessage);
        }

        User user = currentUser.getUser();
        return "user";
    }
}
