package nl.vpro.poel.controller;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.service.RankingService;
import nl.vpro.poel.domain.CurrentUser;
import nl.vpro.poel.dto.RankingEntry;
import nl.vpro.poel.UserUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Optional;

@Controller
class IndexController {

    private final RankingService rankingService;

    @Autowired
    public IndexController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String showIndex(Principal principal, Model model) {

        CurrentUser currentUser = UserUtil.getCurrentUser(principal).orElse(null);

        if (currentUser != null) {
            Optional<RankingEntry<User>> ranking = rankingService.getRankingEntry( currentUser.getUser());
            model.addAttribute("ranking", ranking.orElse(null));
            model.addAttribute("user", currentUser.getUser() );
        }

        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    String showAdminIndex() {
        return "admin/index";
    }
}
