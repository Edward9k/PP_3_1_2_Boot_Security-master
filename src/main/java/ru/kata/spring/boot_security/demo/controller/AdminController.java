package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getAdminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("admin", user);
        return "admin";
    }

    @GetMapping("/all-users")
    public String getAllUsersPage(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("listOfUsers", list);
        return "all-users";
    }

    @GetMapping("/all-users/{id}/update")
    public String updateUserByIdPage(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("user", userService.getUserById(id));
        return "update-user";
    }

    @PatchMapping("/all-users/{id}")
    public String applyUserUpdate(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/all-users";
    }

    @DeleteMapping("all-users/delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin/all-users";
    }
}
