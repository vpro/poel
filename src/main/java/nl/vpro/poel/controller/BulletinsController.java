package nl.vpro.poel.controller;

import nl.vpro.poel.domain.Bulletin;
import nl.vpro.poel.dto.BulletinForm;
import nl.vpro.poel.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin/bulletins")
class BulletinsController {

    private final BulletinService bulletinService;

    @Autowired
    public BulletinsController(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showBulletins(Model model) {
        List<Bulletin> bulletins = bulletinService.findAll();
        model.addAttribute("bulletins", bulletins);
        return "admin/bulletins";
    }

    @RequestMapping(method = RequestMethod.POST)
    String saveBulletins(@ModelAttribute("bulletins") BulletinForm bulletinForm, BindingResult bindingResult) {
        bulletinService.setBulletins(bulletinForm);
        return "redirect:/admin/bulletins";
    }
}
