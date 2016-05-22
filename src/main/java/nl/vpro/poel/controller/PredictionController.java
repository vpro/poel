package nl.vpro.poel.controller;

import nl.vpro.poel.UserUtil;
import nl.vpro.poel.domain.*;
import nl.vpro.poel.dto.PredictionForm;
import nl.vpro.poel.service.MatchService;
import nl.vpro.poel.service.MessageService;
import nl.vpro.poel.service.PredictionService;
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

    private final MessageService messageService;

    private final PredictionService predictionService;

    @Autowired
    public PredictionController(MatchService matchService, MessageService messageService, PredictionService predictionService) {
        this.matchService = matchService;
        this.messageService = messageService;
        this.predictionService = predictionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showForm(Principal principal, Model model) {

        CurrentUser currentUser = UserUtil.getCurrentUser(principal)
                .orElseThrow(() -> new RuntimeException("No user?!"));

        User user = currentUser.getUser();

        Instant now = Instant.now();

        String message = messageService.getText("/predictions").orElse("");
        List<Prediction> finished = toPredictions(matchService.findAllCompleted(), user);
        List<Prediction> unfinished = toPredictions(matchService.findAllUnfinished(now), user);
        List<Prediction> future = toPredictions(matchService.findMatchesToPredict(now), user);

        model.addAttribute("message", message);
        model.addAttribute("finished", finished);
        model.addAttribute("unfinished", unfinished);
        model.addAttribute("future", future);
        return "predictions";
    }

    // TODO: Move this logic out to a service?
    private List<Prediction> toPredictions(List<Match> matches, User user) {
        return matches.stream()
                .map(match -> predictionService.getPredictionForMatch(user, match).orElseGet(() -> new Prediction(user, match, null)))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    String handleFormSubmit(Principal principal, @ModelAttribute("predictions") PredictionForm predictions, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Instant submittedAt = Instant.now();
        User user = UserUtil.getCurrentUser(principal).orElseThrow(() -> new RuntimeException("No user?!")).getUser();
        int updates = predictionService.save(user, predictions, submittedAt);
        redirectAttributes.addFlashAttribute("flash", updates + " voorspelling" + (updates != 1 ? "en" : "") + " opgeslagen");
        return "redirect:/predictions";
    }
}
