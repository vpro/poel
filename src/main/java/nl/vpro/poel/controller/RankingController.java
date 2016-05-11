package nl.vpro.poel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
class RankingController {

    /**
     * TODO: We'll probably want to cache the ranking to avoid calculating
     * every score for every prediction for every user for every request...
     */

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    String ranking() {
        return "ranking";
    }
}
