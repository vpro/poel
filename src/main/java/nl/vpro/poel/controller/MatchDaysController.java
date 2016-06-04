package nl.vpro.poel.controller;

import nl.vpro.poel.domain.MatchDay;
import nl.vpro.poel.dto.MatchDayForm;
import nl.vpro.poel.service.MatchDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin/matchdays")
public class MatchDaysController {

    private final MatchDayService matchDayService;

    @Autowired
    public MatchDaysController(MatchDayService matchDayService) {
        this.matchDayService = matchDayService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showMatchDays(Model model) {
        List<MatchDay> matchDays = matchDayService.findAll();
        model.addAttribute("matchDays", matchDays);
        return "admin/matchdays";
    }

    @RequestMapping(method = RequestMethod.POST)
    String saveMatchDays(@ModelAttribute("matchDays") MatchDayForm matchDayForm, BindingResult bindingResult) {
        matchDayService.setMatchDays(matchDayForm);
        return "redirect:/admin/matchdays";
    }
}
