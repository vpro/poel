package nl.vpro.poel.controller.admin;

import nl.vpro.poel.domain.BonusChoice;
import nl.vpro.poel.domain.BonusCategory;
import nl.vpro.poel.dto.BonusChoiceForm;
import nl.vpro.poel.service.BonusChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("admin/bonuschoices")
public class BonusChoiceController {

    private final BonusChoiceService bonusChoiceService;

    @Autowired
    public BonusChoiceController(BonusChoiceService bonusChoiceService){
        this.bonusChoiceService = bonusChoiceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showChoices(Model model) {
        List<BonusChoice> bonusChoices = bonusChoiceService.findAll();
        model.addAttribute("choices", bonusChoices);
        model.addAttribute("categories", BonusCategory.values());
        return "admin/bonuschoices";
    }

    @RequestMapping(method = RequestMethod.POST)
    String saveChoices(@ModelAttribute("choices") BonusChoiceForm bonusChoiceForm, BindingResult bindingResult){
        bonusChoiceService.setChoices(bonusChoiceForm);
        return "redirect:/admin/bonuschoices";
    }
}
