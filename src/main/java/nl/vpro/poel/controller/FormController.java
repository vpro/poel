package nl.vpro.poel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class FormController {

    @RequestMapping("/form")
    String form() {
        return "form";
    }
}
