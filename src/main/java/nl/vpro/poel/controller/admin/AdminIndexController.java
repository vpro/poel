package nl.vpro.poel.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    @RequestMapping(method = RequestMethod.GET)
    String showAdminIndex() {
        return "admin/index";
    }
}
