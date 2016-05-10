package nl.vpro.poel.controller;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.util.Collection;

@Controller
public class MatchController {

    private final MatchService matchService;

    @Autowired
    MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @RequestMapping("/matches")
    ModelAndView currentMatches() {
        Collection<Match> currentMatches = matchService.getValidMatches(Instant.now());
        return new ModelAndView("matches", "matches", currentMatches);
    }
}
