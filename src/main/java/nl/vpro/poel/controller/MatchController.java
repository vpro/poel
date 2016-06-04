package nl.vpro.poel.controller;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.Round;
import nl.vpro.poel.dto.MatchForm;
import nl.vpro.poel.service.RoundService;
import nl.vpro.poel.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin/matches")
public class MatchController {

    private final MatchService matchService;

    private final RoundService roundService;

    @Autowired
    public MatchController(MatchService matchService, RoundService roundService) {
        this.matchService = matchService;
        this.roundService = roundService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showMatches(Model model) {
        List<Match> matches = matchService.findAll();
        List<Round> rounds = roundService.findAll();

        model.addAttribute("matches", matches);
        model.addAttribute("rounds", rounds);

        return "admin/matches";
    }

    @RequestMapping(method = RequestMethod.POST)
    String saveMatches(@ModelAttribute("matches") MatchForm matchForm, BindingResult bindingResult) {
        matchService.save(matchForm);
        return "redirect:/admin/matches";
    }
}
