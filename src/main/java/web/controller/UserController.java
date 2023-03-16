package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/add")
    public String addForm(@ModelAttribute User user) {
        return "add";
    }

    @PostMapping(value = "/add")
    public String addUsers(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/edit/{id}")
    public String editForm(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping(value = "/edit/{id}")
    public String editUsers(@ModelAttribute User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUsers(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
