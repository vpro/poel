package nl.vpro.poel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class RankingController {

    /**
     * TODO: We'll probably want to cache the ranking to avoid calculating
     * every score for every question for every user for every request...
     */

    @RequestMapping("/ranking")
    String ranking() {
        return "ranking";
    }
}
