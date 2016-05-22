package nl.vpro.poel.controller;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.dto.MatchForm;
import nl.vpro.poel.service.MatchService;
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

    private final MatchService matchService;

    private final UserService userService;

    @Autowired
    public AdminController(MatchService matchService, UserService userService) {
        this.matchService = matchService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String adminIndex() {
        return "admin/index";
    }

    @RequestMapping(value = "/matches", method = RequestMethod.GET)
    String showMatches(Model model) {
        List<Match> matches = matchService.findAll();
        model.addAttribute("matches", matches);
        return "admin/matches";
    }

    @RequestMapping(value = "/matches", method = RequestMethod.POST)
    String handleFormSubmit(@ModelAttribute("matches") MatchForm matchForm, BindingResult bindingResult) {
        matchService.setMatches(matchForm);
        return "redirect:/admin/matches";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    String admin(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "admin/users";
    }
}
