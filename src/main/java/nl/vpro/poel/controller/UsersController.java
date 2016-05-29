package nl.vpro.poel.controller;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.UsersForm;
import nl.vpro.poel.service.UserGroupService;
import nl.vpro.poel.service.UserService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
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

    @RequestMapping(value = "/csv", method = RequestMethod.GET)
    void getUsersCSV(HttpServletResponse response) {

        response.setContentType("text/csv");

        String now = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        response.setHeader("Content-Disposition", "attachment; filename=poel-users-" + now + ".csv");

        try {
            CSVPrinter csvPrinter = CSVFormat.DEFAULT
                    .withHeader("id", "username", "role", "realName", "gameName")
                    .print(response.getWriter());

            for (User user : userService.getAllUsers()) {
                csvPrinter.printRecord(
                        user.getId(),
                        user.getUsername(),
                        user.getRole(),
                        user.getRealName(),
                        user.getGameName()
                );
            }
        } catch (IOException e) {
            log.error("Error writing CSV export", e);
        }
    }
}
