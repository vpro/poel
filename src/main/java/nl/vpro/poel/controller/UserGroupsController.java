package nl.vpro.poel.controller;

import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.UserGroupForm;
import nl.vpro.poel.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin/usergroups")
class UserGroupsController {

    private final UserGroupService userGroupService;

    @Autowired
    public UserGroupsController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showGroups(Model model) {
        List<UserGroup> userGroups = userGroupService.findAll();
        model.addAttribute("userGroups", userGroups);
        return "admin/usergroups";
    }

    @RequestMapping(method = RequestMethod.POST)
    String saveUserGroups(@ModelAttribute("userGroups") UserGroupForm userGroupForm, BindingResult bindingResult) {
        userGroupService.setUserGroups(userGroupForm);
        return "redirect:/admin/usergroups";
    }
}
