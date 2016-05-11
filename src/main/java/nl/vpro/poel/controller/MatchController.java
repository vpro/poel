package nl.vpro.poel.controller;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Instant;
import java.util.List;

@Controller
public class MatchController {

    private final MatchService matchService;

    @Autowired
    MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @RequestMapping(value = "/matches", method = RequestMethod.GET)
    String currentMatches(Model model) {
        List<Match> currentMatches = matchService.getValidMatches(Instant.now());
        model.addAttribute("matches", currentMatches);
        return "matches";
    }
}
