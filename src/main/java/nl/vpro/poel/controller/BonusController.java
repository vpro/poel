package nl.vpro.poel.controller;

import nl.vpro.poel.domain.Bonus;
import nl.vpro.poel.domain.BonusCategory;
import nl.vpro.poel.domain.BonusChoice;
import nl.vpro.poel.domain.MatchDay;
import nl.vpro.poel.dto.BonusForm;
import nl.vpro.poel.service.BonusChoiceService;
import nl.vpro.poel.service.BonusService;
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
@RequestMapping("admin/bonuses")
public class BonusController {

    private final BonusService bonusService;
    private final BonusChoiceService bonusChoiceService;
    private final MatchDayService matchDayService;

    @Autowired
    public BonusController(BonusService bonusService, BonusChoiceService bonusChoiceService, MatchDayService matchDayService) {
        this.bonusService = bonusService;
        this.bonusChoiceService = bonusChoiceService;
        this.matchDayService = matchDayService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showBonuses(Model model) {
        List<Bonus> bonuses = bonusService.findAll();
        List<BonusChoice> countryChoices = bonusChoiceService.findByCategory(BonusCategory.COUNTRY);
        List<BonusChoice> playerChoices = bonusChoiceService.findByCategory(BonusCategory.PLAYER);
        List<MatchDay> matchDays = matchDayService.findAll();

        model.addAttribute("bonuses", bonuses);
        model.addAttribute("categories", BonusCategory.values());
        model.addAttribute("countries", countryChoices);
        model.addAttribute("players", playerChoices);
        model.addAttribute("matchDays", matchDays);

        return "admin/bonuses";
    }

    @RequestMapping(method = RequestMethod.POST)
    String saveBonuses(@ModelAttribute("bonuses")BonusForm bonusForm, BindingResult bindingResult){
        bonusService.save(bonusForm);
        return "redirect:/admin/bonuses";
    }
}
