package nl.vpro.poel.controller;

import nl.vpro.poel.UserUtil;
import nl.vpro.poel.domain.CurrentUser;
import nl.vpro.poel.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * This class sets configuration and model attributes for all controllers.
 */
@ControllerAdvice
public class PoelControllerAdvice {

    private final MessageService messageService;

    private final String title;

    private final int maxMultipliersPerUser;
    private final int pointsForCorrectMatchResult;
    private final int pointsForCorrectMatchWinner;
    private final int scoreMultiplierFactor;

    @Autowired
    public PoelControllerAdvice(
            MessageService messageService,
            @Value("${poel.title}") String title,
            @Value("${poel.maxMultipliersPerUser}") int maxMultipliersPerUser,
            @Value("${poel.pointsForCorrectMatchResult}") int pointsForCorrectMatchResult,
            @Value("${poel.pointsForCorrectMatchWinner}") int pointsForCorrectMatchWinner,
            @Value("${poel.scoreMultiplierFactor}") int scoreMultiplierFactor
    ) {
        this.messageService = messageService;
        this.title = title;
        this.maxMultipliersPerUser = maxMultipliersPerUser;
        this.pointsForCorrectMatchResult = pointsForCorrectMatchResult;
        this.pointsForCorrectMatchWinner = pointsForCorrectMatchWinner;
        this.scoreMultiplierFactor = scoreMultiplierFactor;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(1024); // By default 256 is the max and we might get a POST with more users than that
    }

    @ModelAttribute("user")
    public CurrentUser user(Principal principal) {
        return UserUtil.getCurrentUser(principal).orElse(null);
    }

    @ModelAttribute("message")
    public String message(HttpServletRequest request) {
        String key = request.getRequestURI();
        return messageService.getText(key).orElse("");
    }

    @ModelAttribute("title")
    public String title() {
        return title;
    }

    @ModelAttribute("maxMultipliersPerUser")
    public int maxMultipliersPerUser() {
        return maxMultipliersPerUser;
    }

    @ModelAttribute("pointsForCorrectMatchResult")
    public int pointsForCorrectMatchResult() {
        return pointsForCorrectMatchResult;
    }

    @ModelAttribute("scoreMultiplierFactor")
    public int scoreMultiplierFactor() {
        return scoreMultiplierFactor;
    }

    @ModelAttribute("pointsForCorrectMatchWinner")
    public int pointsForCorrectMatchWinner() {
        return pointsForCorrectMatchWinner;
    }
}
