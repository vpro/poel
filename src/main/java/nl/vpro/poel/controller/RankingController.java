package nl.vpro.poel.controller;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
class RankingController {

    /**
     * TODO: We'll probably want to cache the ranking to avoid calculating
     * every score for every prediction for every user for every request...
     */

    private final UserService userService;

    @Autowired
    public RankingController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    String ranking(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "ranking";
    }
}
