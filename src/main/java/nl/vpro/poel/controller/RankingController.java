package nl.vpro.poel.controller;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.RankingEntry;
import nl.vpro.poel.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/ranking")
class RankingController {

    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showRanking(Model model) {
        List<RankingEntry<User, Integer>> ranking = rankingService.getUserRanking();
        model.addAttribute("userRanking", ranking);

        List<RankingEntry<UserGroup, Double>> userGroupRanking = rankingService.getUserGroupRanking();
        model.addAttribute("userGroupRanking", userGroupRanking);

        return "ranking";
    }
}
