package nl.vpro.poel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
class FormController {

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    String form() {
        return "form";
    }
}
