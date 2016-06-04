package nl.vpro.poel.controller.admin;

import nl.vpro.poel.domain.Message;
import nl.vpro.poel.dto.MessageForm;
import nl.vpro.poel.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin/messages")
class MessagesController {

    private final MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showMessages(Model model) {
        List<Message> messages = messageService.findAll();
        model.addAttribute("messages", messages);
        return "admin/messages";
    }

    @RequestMapping(method = RequestMethod.POST)
    String saveMessages(@ModelAttribute("messages") MessageForm messageForm, BindingResult bindingResult) {
        messageService.setMessages(messageForm);
        return "redirect:/admin/messages";
    }
}
