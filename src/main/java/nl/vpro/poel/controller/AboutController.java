package nl.vpro.poel.controller;

import nl.vpro.poel.domain.Bulletin;
import nl.vpro.poel.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping(value = "/about")
public class AboutController {

    private final BulletinService bulletinService;

    @Autowired
    public AboutController(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showAbout(Model model) {
        List<Bulletin> bulletins = bulletinService.findAll();
        model.addAttribute("bulletins", bulletins);
        return "about";
    }
}