package nl.vpro.poel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    String user() {
        return "user";
    }
}
