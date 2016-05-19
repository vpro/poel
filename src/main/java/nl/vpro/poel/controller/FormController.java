package nl.vpro.poel.controller;

import nl.vpro.poel.UserUtil;
import nl.vpro.poel.domain.*;
import nl.vpro.poel.dto.MatchEntry;
import nl.vpro.poel.dto.PredictionForm;
import nl.vpro.poel.service.MatchService;
import nl.vpro.poel.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Controller
class FormController {

    private final MatchService matchService;

    private final PredictionService predictionService;

    @Autowired
    public FormController(MatchService matchService, PredictionService predictionService) {
        this.matchService = matchService;
        this.predictionService = predictionService;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    String showForm(Principal principal, Model model) {

        CurrentUser currentUser = UserUtil.getCurrentUser(principal)
                .orElseThrow(() -> new RuntimeException("No user?!"));

        User user = currentUser.getUser();

        Instant now = Instant.now();

        List<MatchEntry> finished = addUserPredictions(matchService.findAllCompleted(), user);
        List<MatchEntry> unfinished = addUserPredictions(matchService.findAllUnfinished(now), user);
        List<MatchEntry> future = addUserPredictions(matchService.findMatchesToPredict(now), user);

        model.addAttribute("finished", finished);
        model.addAttribute("unfinished", unfinished);
        model.addAttribute("future", future);
        return "form";
    }

    // TODO: Move this logic out to a service?
    private List<MatchEntry> addUserPredictions(List<Match> matches, User user) {
        return matches.stream()
                .map(match -> toMatchEntry(match, user))
                .collect(Collectors.toList());
    }

    private MatchEntry toMatchEntry(Match match, User user) {
        MatchResult predictedResult = null;
        int score = 0;
        Prediction prediction = predictionService.getPredictionForMatch(user, match).orElse(null);
        if (prediction != null) {
            predictedResult = prediction.getMatchResult();
            score = prediction.getScore();
        }
        return new MatchEntry(match, predictedResult, score);
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    String handleFormSubmit(@ModelAttribute("predictions") PredictionForm predictions, BindingResult bindingResult) {
        // TODO: Data binding doesn't seem to work here yet
        System.out.println("Predictions: " + predictions);
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return "redirect:/form";
    }
}
