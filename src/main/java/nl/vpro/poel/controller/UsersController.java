package nl.vpro.poel.controller;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.UsersForm;
import nl.vpro.poel.service.UserGroupService;
import nl.vpro.poel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UsersController {

    private UserService userService;

    private UserGroupService userGroupService;

    @Autowired
    public UsersController(UserService userService, UserGroupService userGroupService) {
        this.userService = userService;
        this.userGroupService = userGroupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showUsers(Model model) {

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);

        List<UserGroup> userGroups = userGroupService.findAll();
        model.addAttribute("userGroups", userGroups);

        return "admin/users";
    }

    @RequestMapping(method = RequestMethod.POST)
    String saveUsers(@ModelAttribute("users") UsersForm usersForm, BindingResult bindingResult) {
        userService.updateUserGroupForUsers(usersForm);
        return "redirect:/admin/users";
    }
}
