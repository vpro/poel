package nl.vpro.poel.controller;

import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.Message;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.dto.MatchForm;
import nl.vpro.poel.dto.MessageForm;
import nl.vpro.poel.service.UserGroupService;
import nl.vpro.poel.service.MatchService;
import nl.vpro.poel.service.MessageService;
import nl.vpro.poel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin")
class AdminController {

    private final UserGroupService userGroupService;

    private final MatchService matchService;

    private final MessageService messageService;

    private final UserService userService;

    @Autowired
    public AdminController(UserGroupService userGroupService, MatchService matchService, MessageService messageService, UserService userService) {
        this.userGroupService = userGroupService;
        this.matchService = matchService;
        this.messageService = messageService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showIndex() {
        return "admin/index";
    }

    @RequestMapping(value = "/matches", method = RequestMethod.GET)
    String showMatches(Model model) {
        List<Match> matches = matchService.findAll();
        model.addAttribute("matches", matches);
        return "admin/matches";
    }

    @RequestMapping(value = "/matches", method = RequestMethod.POST)
    String saveMatches(@ModelAttribute("matches") MatchForm matchForm, BindingResult bindingResult) {
        matchService.save(matchForm);
        return "redirect:/admin/matches";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    String showMessages(Model model) {
        List<Message> messages = messageService.findAll();
        model.addAttribute("messages", messages);
        return "admin/messages";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    String saveMessages(@ModelAttribute("messages") MessageForm messageForm, BindingResult bindingResult) {
        messageService.setMessages(messageForm);
        return "redirect:/admin/messages";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    String showUsers(Model model) {

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);

        List<UserGroup> userGroups = userGroupService.findAll();
        model.addAttribute("userGroups", userGroups);

        return "admin/users";
    }

    @RequestMapping(value = "/usergroups", method = RequestMethod.GET)
    String showGroups(Model model) {
        List<UserGroup> userGroups = userGroupService.findAll();
        model.addAttribute("userGroups", userGroups);
        return "admin/usergroups";
    }
}
