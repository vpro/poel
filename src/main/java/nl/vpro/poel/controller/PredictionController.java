package nl.vpro.poel.controller;

import nl.vpro.poel.UserUtil;
import nl.vpro.poel.domain.*;
import nl.vpro.poel.dto.PredictionForm;
import nl.vpro.poel.dto.ScoredPrediction;
import nl.vpro.poel.exception.MultiplierException;
import nl.vpro.poel.service.MatchService;
import nl.vpro.poel.service.PredictionService;
import nl.vpro.poel.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/predictions")
class PredictionController {

    private final MatchService matchService;

    private final PredictionService predictionService;

    private final ScoreService scoreService;

    @Autowired
    public PredictionController(MatchService matchService, PredictionService predictionService, ScoreService scoreService) {
        this.matchService = matchService;
        this.predictionService = predictionService;
        this.scoreService = scoreService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showForm(Principal principal, Model model) {

        CurrentUser currentUser = UserUtil.getCurrentUser(principal)
                .orElseThrow(() -> new RuntimeException("No user?!"));

        User user = currentUser.getUser();

        Instant now = Instant.now();

        List<ScoredPrediction> finished = toScoredPredictions(matchService.findAllCompleted(), user);
        List<ScoredPrediction> unfinished = toScoredPredictions(matchService.findAllUnfinished(now), user);
        List<ScoredPrediction> future = toScoredPredictions(matchService.findMatchesToPredict(now), user);

        model.addAttribute("finished", finished);
        model.addAttribute("unfinished", unfinished);
        model.addAttribute("future", future);
        return "predictions";
    }

    @RequestMapping(method = RequestMethod.POST)
    String savePredictions(Principal principal, @ModelAttribute("predictions") PredictionForm predictions, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Instant submittedAt = Instant.now();
        User user = UserUtil.getCurrentUser(principal).orElseThrow(() -> new RuntimeException("No user?!")).getUser();
        try {
            int updates = predictionService.save(user, predictions, submittedAt);
            redirectAttributes.addFlashAttribute("flash", updates + " voorspelling" + (updates != 1 ? "en" : "") + " opgeslagen");
        } catch (MultiplierException e) {
            redirectAttributes.addFlashAttribute("flash", e.getMessage());
        }

        return "redirect:/predictions";
    }

    // TODO: Move this logic out to a service?
    private List<ScoredPrediction> toScoredPredictions(List<Match> matches, User user) {
        return matches.stream()
                .map(match -> predictionService.getPredictionForMatch(user, match).orElseGet(() -> new Prediction(user, match)))
                .map(prediction -> new ScoredPrediction(prediction, scoreService.getScore(prediction)))
                .collect(Collectors.toList());
    }
}
