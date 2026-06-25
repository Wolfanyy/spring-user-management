package com.github.wolfanyy.springusermanagement.controller;

import com.github.wolfanyy.springusermanagement.service.UserService;
import com.github.wolfanyy.springusermanagement.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute
                ("users",
                        userService.findAll()
                );
        return "users/list";
    }
}
