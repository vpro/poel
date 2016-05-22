package nl.vpro.poel.controller;

import nl.vpro.poel.UserUtil;
import nl.vpro.poel.domain.CurrentUser;
import nl.vpro.poel.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * This class adds model attributes that are common to all controllers.
 */
@ControllerAdvice
public class PoelControllerAdvice {

    private final MessageService messageService;

    @Autowired
    public PoelControllerAdvice(MessageService messageService) {
        this.messageService = messageService;
    }

    @ModelAttribute("user")
    public CurrentUser getCurrentUser(Principal principal) {
        return UserUtil.getCurrentUser(principal).orElse(null);
    }

    @ModelAttribute("message")
    public String getPageMessage(HttpServletRequest request) {
        String key = request.getRequestURI();
        return messageService.getText(key).orElse("");
    }
}
