package com.github.wolfanyy.springusermanagement.controller;

import com.github.wolfanyy.springusermanagement.dto.UserRequest;
import com.github.wolfanyy.springusermanagement.mapper.UserMapper;
import com.github.wolfanyy.springusermanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute(
                "users",
                userService.findAll()
        );
        return "users/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {

        model.addAttribute(
                "user",
                new UserRequest()
        );

        return "users/create";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("user")
            UserRequest userRequest,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return "users/create";
        }

        userService.create(
                userMapper.toEntity(userRequest)
        );

        return "redirect:/users";
    }
}
