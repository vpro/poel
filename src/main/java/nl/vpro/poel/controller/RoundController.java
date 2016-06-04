package nl.vpro.poel.controller;

import nl.vpro.poel.domain.Round;
import nl.vpro.poel.dto.RoundsForm;
import nl.vpro.poel.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin/rounds")
public class RoundController {

    private final RoundService roundService;

    @Autowired
    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showRounds(Model model) {
        List<Round> rounds = roundService.findAll();
        model.addAttribute("rounds", rounds);
        return "admin/rounds";
    }

    @RequestMapping(method = RequestMethod.POST)
    String setRounds(@ModelAttribute("rounds") RoundsForm roundsForm, BindingResult bindingResult) {
        roundService.setRounds(roundsForm);
        return "redirect:/admin/rounds";
    }
}
