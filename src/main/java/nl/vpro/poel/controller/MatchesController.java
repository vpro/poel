package nl.vpro.poel.controller;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.MatchDay;
import nl.vpro.poel.dto.MatchForm;
import nl.vpro.poel.service.MatchDayService;
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
public class MatchesController {

    private final MatchService matchService;

    private final MatchDayService matchDayService;

    @Autowired
    public MatchesController(MatchService matchService, MatchDayService matchDayService) {
        this.matchService = matchService;
        this.matchDayService = matchDayService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showMatches(Model model) {
        List<Match> matches = matchService.findAll();
        List<MatchDay> matchDays = matchDayService.findAll();

        model.addAttribute("matches", matches);
        model.addAttribute("matchDays", matchDays);

        return "admin/matches";
    }

    @RequestMapping(method = RequestMethod.POST)
    String saveMatches(@ModelAttribute("matches") MatchForm matchForm, BindingResult bindingResult) {
        matchService.save(matchForm);
        return "redirect:/admin/matches";
    }
}
